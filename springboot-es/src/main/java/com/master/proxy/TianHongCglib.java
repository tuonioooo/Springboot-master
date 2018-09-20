package com.master.proxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-22
 * Time: 11:46
 * info: 定义一个田弘这样一个cglib的人（专门给别人松美女歌姬）
 */
public class TianHongCglib implements MethodInterceptor {

    private Object target;// 目标对象/委托对象/被代理对象

    /**
     *  @Author daizhao
     *  @Date 2018-8-22 11:13
     *  @Params [target]
     *  @Return java.lang.Object
     *  @Info   绑定委托对象并返回一个代理类
     */
    public Object bind(Object target) {
        this.target = target;
        //取得代理对象
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(this.target.getClass());
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("intercept：" + o.getClass().getName());
        return proxy.invokeSuper(o, args);
    }
}
