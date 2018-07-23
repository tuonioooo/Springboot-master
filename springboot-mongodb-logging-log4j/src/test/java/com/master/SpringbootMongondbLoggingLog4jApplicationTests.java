package com.master;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongondbLoggingLog4jApplicationTests {

	//两种方式都会输出到mongodb中
	static Logger logger1 = LogManager.getLogger(SpringbootMongondbLoggingLog4jApplicationTests.class);
	static Logger logger2 = LogManager.getLogger("mongologger");

	//两种方式都会输出到mongodb中
	static org.slf4j.Logger slf4j1 = LoggerFactory.getLogger(SpringbootMongondbLoggingLog4jApplicationTests.class);
	static org.slf4j.Logger slf4j2 = LoggerFactory.getLogger("mongologger");

	@Test
	public void contextLoads() {
		logger1.info("logger1输入mongodb测试");
		logger2.info("logger2输入mongodb测试");
	}

	@Test
	public void testSlf4j() {
		slf4j1.info("slf4j1输入mongodb测试");
		slf4j2.info("slf4j2输入mongodb测试");

	}

}
