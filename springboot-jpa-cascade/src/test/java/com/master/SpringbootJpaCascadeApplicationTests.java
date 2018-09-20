package com.master;

import com.master.bean.Order;
import com.master.bean.User;
import com.master.dao.OrderRepository;
import com.master.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootJpaCascadeApplicationTests {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
	}

	/**
	 * @OneToMany单向关联测试
	 */
	@Test
	public void testOneToMany(){
		Order order1 = new Order(1l, "上衣", 1);
		Order order2 = new Order(2l, "裤子", 1);
		Order order3 = new Order(3l, "胸罩", 1);

		Set<Order> orders = new LinkedHashSet<>();
		orders.add(order1);
		orders.add(order2);
		orders.add(order3);

		User user = new User(1l, "Kobe", 20, orders);
		//保存
		userRepository.save(user);//级联保存，因为配置了CascadeType.ALL
		//级联查询
		//findAll方法默认是  FetchType.LAZY策略，需要修改FetchType.EAGER，可以抓取到子表
		List<User> userList = userRepository.findAll();
		userList.forEach(user1 -> {
			System.out.println("user1.getName() = " + user1.getName());
			user1.getOrders().forEach(order -> {
				System.out.println("order.getOrderName() = " + order.getOrderName());
			});
		});

	}

}
