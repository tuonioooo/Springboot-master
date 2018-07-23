package com.master;

import com.master.service.OrderDao;
import com.master.service2.AccountDao2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaDatasourceApplicationTests {


	@Autowired
	private OrderDao orderDao;

	@Autowired
	private AccountDao2 accountDao2;

	@Test
	public void contextLoads() {
	}

	@Test
	public void orderDao() {
		System.out.println("***[" + orderDao.getOne(1).getName() + "]***");
	}

	@Test
	public void accountDao2() {
		System.out.println("***[" + accountDao2.getOne(1).getName() + "]***");
	}

}
