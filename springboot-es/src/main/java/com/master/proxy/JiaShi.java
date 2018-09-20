package com.master.proxy;

/**
 * @Author tuonioooo
 * @Date 2018-8-22 10:48
 * @Info 贾氏
 * @Blog https://blog.csdn.net/tuoni123
 */
public class JiaShi implements KindWomen {

    public void happyWithMan() {
        System.out.println("贾氏正在Happy中......");

    }

    @Override
    public void dragonEnteredShuangFeng() {
        System.out.println("dragonEnteredShuangFeng：hahaha");
    }

    public void makeEyesWithMan() {
        System.out.println("贾氏抛媚眼");
    }
}