package com.master;

import com.master.rabbit.RabbitConfig;
import com.master.rabbit.Sender;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootConfigRabbitmqApplicationTests {

	@Autowired
	private Sender sender;

	private final static String QUEUE_NAME = "rabbit-queue";

	@Test
	public void testRabbitMQ() throws Exception {
		sender.send();
	}


	@Test
	public void testUseApplicationContext(){

		ApplicationContext context =
				new AnnotationConfigApplicationContext(RabbitConfig.class);
		AmqpTemplate template = context.getBean(AmqpTemplate.class);
		template.convertAndSend("rabbit-queue", "foo");
		String foo = (String) template.receiveAndConvert("rabbit-queue");
	}

	@Test
	public void testcon() throws Exception{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.111.103");
		factory.setUsername("springboot");
		factory.setPassword("123456");
		factory.setPort(5672);
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		String message = "Hello World!";
		channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		System.out.println(" [x] Sent '" + message + "'");
		channel.close();
		connection.close();
	}
}
