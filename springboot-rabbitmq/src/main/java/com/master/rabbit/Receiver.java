package com.master.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author tuonioooo
 * @create 2016/9/25.
 * @blog https://blog.csdn.net/tuoni123
 */
@Component
public class Receiver {

    @RabbitListener(queues = "rabbit-queue")
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver : " + hello);
    }

}
