package com.master;

import com.master.service.one.UserDaoImplOne;
import com.master.service.one.UserOne;
import com.master.service.two.UserDaoImplTwo;
import com.master.service.two.UserTwo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbDatasouceApplicationTests {


	@Autowired
	private UserDaoImplOne userDaoImplOne;

	@Autowired
	private UserDaoImplTwo userDaoImplTwo;


	@Test
	public void testPrimaryMongoDataSource(){
		userDaoImplOne.saveUser(new UserOne(1L, "primaryMongoDataSource", 30));
		Assert.assertEquals(1, userDaoImplOne.findAll());

	}

	@Test
	public void testSecondaryMongoDataSource(){
		userDaoImplTwo.saveUser(new UserTwo(1L, "secondaryMongoTemplate", 30));
		Assert.assertEquals(1, userDaoImplTwo.findAll());

	}

}
