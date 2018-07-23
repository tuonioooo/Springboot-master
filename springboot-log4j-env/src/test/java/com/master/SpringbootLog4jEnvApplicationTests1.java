package com.master;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringbootLog4jEnvApplication.class)
public class SpringbootLog4jEnvApplicationTests1 {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Test
	public void contextLoads() {
		logger.info("输出info");
		logger.debug("输出debug");
		logger.error("输出error");
	}

}
