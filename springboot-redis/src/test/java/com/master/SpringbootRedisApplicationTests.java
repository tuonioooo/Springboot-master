package com.master;

import com.alibaba.fastjson.JSONObject;
import com.master.bean.User;
import com.master.service.UserCacheRedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * spring boot 1.5.x ~ 2.0.x 版本的测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Autowired
	private UserCacheRedisService userCacheRedisService;

	@Test
	public void testRedis() throws Exception {

		// 保存字符串
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));

		// 保存对象
		User user = new User(1,"超人", 20);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User(2,"蝙蝠侠", 30);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		user = new User(3,"蜘蛛侠", 40);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		// 保存汉字
		stringRedisTemplate.opsForValue().set("bbb", "保存汉字");
		Assert.assertEquals("保存汉字", stringRedisTemplate.opsForValue().get("bbb"));

		// user对象序列化json
		user = new User(4,"钢铁侠", 50);
		stringRedisTemplate.opsForValue().set(user.getUsername(), JSONObject.toJSONString(user));


		Assert.assertEquals(20, redisTemplate.opsForValue().get("超人").getAge().longValue());
		Assert.assertEquals(30, redisTemplate.opsForValue().get("蝙蝠侠").getAge().longValue());
		Assert.assertEquals(40, redisTemplate.opsForValue().get("蜘蛛侠").getAge().longValue());

	}

	@Test
	public void testUserCacheRediseSelectAllUser() throws Exception {
		System.out.println("===========第一次调用=======");
		List<User> list = userCacheRedisService.selectAllUser();
		System.out.println("===========第二次调用=======");
		List<User> list2 = userCacheRedisService.selectAllUser();
		for (User u : list2){
			System.out.println("u = " + u);
		}
	}

	@Test
	public void testGetUser() throws Exception {
		System.out.println("===========第一次调用=======");
		User user1 = userCacheRedisService.getUser(10);

		System.out.println("user1.getUsername() = " + user1.getUsername());

		System.out.println("===========第二次调用=======");
		User user2 = userCacheRedisService.getUser(10);

		System.out.println("user2.getUsername() = " + user2.getUsername());

	}

	@Test
	public void testGetUserOfName() throws Exception {
		System.out.println("===========第一次调用=======");
		User user1 = userCacheRedisService.getUserOfName("天下第一");

		System.out.println("user1.getUsername() = " + user1.getUsername());

		System.out.println("===========第二次调用=======");
		User user2 = userCacheRedisService.getUserOfName("天下第一");

		System.out.println("user2.getUsername() = " + user2.getUsername());

	}

	@Test
	public void testSaveOfUpdate() throws Exception {
		User user = new User();
		user.setUsername("James");
		user.setAge(20);
		user.setId(10);

		System.out.println("===========第一次调用=======");
		userCacheRedisService.saveOfUpdate(user);

		System.out.println("===========第二次调用=======");
		userCacheRedisService.saveOfUpdate(user);
	}

	@Test
	public void testClearUser() throws Exception {
		User user = new User();
		user.setUsername("James");
		user.setAge(20);
		user.setId(10);

		userCacheRedisService.clearUser(user);
	}

	@Test
	public void testFlushCacle() throws Exception {

		userCacheRedisService.flushCacle();

	}


}
