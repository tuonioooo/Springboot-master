package com.master;

import com.master.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootEhcacheApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testQuery(){
		System.out.println("第一次调用：" + userService);
		userService.query(1L);
		System.out.println("第二次调用：" + userService);
		userService.query(1L);
	}

	@Test
	public void testPut(){
		userService.put(4L, "李四");
	}

	@Test
	public void testPut2(){
		userService.put2(5L, "James");
	}

	@Test
	public void testRemove(){
		userService.remove(5L);
	}

}
