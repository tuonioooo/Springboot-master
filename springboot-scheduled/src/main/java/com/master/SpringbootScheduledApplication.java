package com.master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //开启定时任务
public class SpringbootScheduledApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootScheduledApplication.class, args);
	}
}
