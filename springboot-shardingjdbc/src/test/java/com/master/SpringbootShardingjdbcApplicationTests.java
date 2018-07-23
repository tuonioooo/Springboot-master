package com.master;

import com.master.dao.OrderMapper;
import com.master.entity.TOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootShardingjdbcApplicationTests {

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void testInsert() {

		orderMapper.insert(new TOrder(1,1001));

		orderMapper.insert(new TOrder(2,2002));

		orderMapper.insert(new TOrder(3,3003));

		orderMapper.insert(new TOrder(4,4004));

	}

	@Test
	public void testFind() {
		TOrder order = orderMapper.findById(31);
		System.out.println(order.getOrderId() + "" + order.getUserId());
	}

}
