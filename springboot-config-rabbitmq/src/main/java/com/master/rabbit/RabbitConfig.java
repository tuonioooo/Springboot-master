package com.master.rabbit;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("192.168.111.103");
        cachingConnectionFactory.setUsername("springboot");
        cachingConnectionFactory.setPassword("123456");
        cachingConnectionFactory.setPort(5672);
        return cachingConnectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}
