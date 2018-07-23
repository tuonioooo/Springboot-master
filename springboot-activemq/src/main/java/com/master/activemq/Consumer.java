package com.master.activemq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
*  @Author tuonioooo
*  @Date 2018-7-20 11:15
*  @Info
*  @Blog https://blog.csdn.net/tuoni123
*/
@Component
public class Consumer {

    // 使用JmsListener配置消费者监听的队列，其中text是接收到的消息
    @JmsListener(destination = "mytest.queue")
    public void receiveQueue(String text) {
        System.out.println("Consumer收到的报文为:" + text);
    }
}
