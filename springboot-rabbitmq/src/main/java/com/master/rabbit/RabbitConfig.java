package com.master.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tuonioooo
 * @create 2016/9/25.
 * @blog https://blog.csdn.net/tuoni123
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue myQueue() {
        return new Queue("rabbit-queue");
    }

}
