package com.master;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * 描述:消息消费者
 *
 * @author tuonioooo
 * @create 2017-10-16 18:33
 **/
@Component
public class MsgConsumer {

    @KafkaListener(topics = {"shuaige","hello-kafka"})
    public void processMessage(String content) {

        System.out.println("消息被消费"+content);
    }

}