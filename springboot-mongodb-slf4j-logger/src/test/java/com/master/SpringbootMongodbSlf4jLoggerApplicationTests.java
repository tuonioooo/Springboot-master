package com.master;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbSlf4jLoggerApplicationTests {

	//两种方式都会输出到mongodb中
	static Logger slf4j1 = LoggerFactory.getLogger(SpringbootMongodbSlf4jLoggerApplicationTests.class);
	static Logger slf4j2 = LoggerFactory.getLogger("mongologger");

	@Test
	public void contextLoads() {

		slf4j1.info("slf4j1输入mongodb测试");
		slf4j2.info("slf4j2输入mongodb测试");
	}

}
