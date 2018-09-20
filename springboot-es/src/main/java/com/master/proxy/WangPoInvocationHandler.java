package com.master.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-22
 * Time: 11:09
 * info: 定义一个王婆动态代理类，他可以代理所有的实现KindWomen这种类型的人（JDK动态代理类）
 */
public class WangPoInvocationHandler implements InvocationHandler {

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
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("WangPoInvocationHandler：invoke");
        //执行方法
        return method.invoke(target, args);


    }
}
