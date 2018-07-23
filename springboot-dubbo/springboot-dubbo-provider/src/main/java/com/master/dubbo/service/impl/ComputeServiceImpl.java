package com.master.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.master.dubbo.service.ComputeService;

/**
 * Created by tuonioooo on 2016/7/14.
 */
@Service(version = "1.0.0")
public class ComputeServiceImpl implements ComputeService {

    @Override
    public Integer add(int a, int b) {
        return a + b;
    }

}
