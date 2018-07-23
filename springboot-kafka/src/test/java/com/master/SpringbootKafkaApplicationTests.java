package com.master;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootKafkaApplicationTests {

	@Autowired
	private MsgProducer msgProducer;

	@Autowired
	private MsgConsumer msgConsumer;

	@Test
	public void test() throws Exception {
		msgProducer.sendMessage("shuaige", "hello shuaige ！！");
		msgProducer.sendMessage("hello-kafka", "hello-kafka ！！");
	}

	@Test
	public void testMsgConsumer() throws Exception {
		msgConsumer.processMessage("hello shuaige ！！");
		msgConsumer.processMessage("hello-kafka ！！");
	}
}
