package com.master.bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-23
 * Time: 14:22
 * info:
 */
public class TestP {

    public static void main(String[] args) {
        // ------------------------ 测试

        ApplicationContext annotationContext = new AnnotationConfigApplicationContext("com.master");
        Python python = annotationContext.getBean("python", Python.class);
        System.out.println("python.getName() = " + python.getName());
    }
}
