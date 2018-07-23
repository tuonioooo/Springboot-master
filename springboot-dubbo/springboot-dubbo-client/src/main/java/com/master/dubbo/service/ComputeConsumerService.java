package com.master.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * Created by tuonioooo on 2016/7/14.
 */
@Component
public class ComputeConsumerService {


    @Reference(version = "1.0.0")
    ComputeService computeService;

    public void consumer(){
        System.out.println("consumer={" + computeService.add(1,3)+"}");

    }
}
