package com.master;

import com.master.Service.UserService;
import com.master.bean.User;
import com.master.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaCacheApplicationTests {

	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	/**
	 * springboot +spring-data-jpa 缓存查询测试1
	 */
	@Test
	public void contextLoads() {
		User user = userService.getUser(1l);
		System.out.println("user.getName() = " + user.getName());
		User user2 = userService.getUser(1l);
		System.out.println("user2.getName() = " + user2.getName());
	}

	/**
	 * springboot +spring-data-jpa 缓存查询测试2
	 */
	@Test
	public void configCache(){
		User user = userRepository.findByName("Kobe");
		System.out.println("user.getName() = " + user.getName());
		User user2 = userRepository.findByName("Kobe");
		System.out.println("user2.getName() = " + user2.getName());
	}

}
