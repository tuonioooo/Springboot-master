package com.master;

import com.master.domain.User;
import com.master.domain.UserDaoImpl;
import com.master.domain.UserRepository;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SpringbootMongodbApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDaoImpl userDaoImpl;

	@Autowired
	private MongoClient mongoClient;

	@Before
	public void setUp() {
		//userRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {
		log.info("MinConnectionsPerHost = {}, MaxConnectionsPerHost = {}",
				mongoClient.getMongoClientOptions().getMinConnectionsPerHost(),
				mongoClient.getMongoClientOptions().getConnectionsPerHost());


		mongoClient.listDatabaseNames();
	}

	@Test
	public void testUser() throws Exception {

		// 创建三个User，并验证User总数
		userRepository.save(new User(1L, "didi", 30));
		userRepository.save(new User(2L, "mama", 40));
		userRepository.save(new User(3L, "kaka", 50));
		Assert.assertEquals(3, userRepository.findAll().size());

		// 删除一个User，再验证User总数
		Optional<User> user = userRepository.findById(1L);
		User u = user.get();
		userRepository.delete(u);
		Assert.assertEquals(2, userRepository.findAll().size());

		// 删除一个User，再验证User总数
		u = userRepository.findByUsername("mama");
		userRepository.delete(u);
		Assert.assertEquals(1, userRepository.findAll().size());

	}

	@Test
	public void testSaveUser() throws Exception {
		userDaoImpl.saveUser(new User(4L, "kobe", 30));
		User user = userDaoImpl.findUserByUserName("kobe");
		Assert.assertEquals(30, user.getAge());
	}

	@Test
	public void testFindUserByUserName() throws Exception {
		User user = userDaoImpl.findUserByUserName("kobe");
		Assert.assertEquals("kobe", user.getUsername());
	}

	@Test
	public void testUpdateUser() throws Exception {
		User user = userDaoImpl.findUserByUserName("kobe");
		user.setUsername("kobe");
		user.setAge(40);
		userDaoImpl.updateUser(user);
		Assert.assertEquals(40, user.getAge());
	}

	@Test
	public void testDeleteUserById() throws Exception {
		userDaoImpl.deleteUserById(4l);
		Assert.assertEquals(3, userRepository.findAll().size());
	}





}
