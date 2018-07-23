package com.master;


import com.master.dubbo.service.ComputeConsumerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
//@ImportResource({"classpath:dubbo.xml"})
public class SpringbootDubboClientApplication {

	public static void main(String[] args) {

		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootDubboClientApplication.class, args);
		ComputeConsumerService computeConsumerService = applicationContext.getBean(ComputeConsumerService.class);
		computeConsumerService.consumer();

		}
}
