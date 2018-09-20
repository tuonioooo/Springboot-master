package com.master.classloader;

import org.springframework.util.ClassUtils;

import java.io.File;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-24
 * Time: 15:36
 * info:
 */
public class TestClassLoader {


    private static final String[] WEB_ENVIRONMENT_CLASSES = { "javax.servlet.Servlet",
            "org.springframework.web.context.ConfigurableWebApplicationContext","com.master.classloader.TestClassLoader" };

    private boolean deduceWebEnvironment() {
        ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        System.out.println("classLoader.getClass() = " + classLoader.getClass());
        System.out.println("classLoader.getClass().getName() = " + classLoader.getClass().getName());
        for (String className : WEB_ENVIRONMENT_CLASSES) {
            if (!ClassUtils.isPresent(className, classLoader)) {
                System.out.println("className = " + className);
                System.out.println("isPresent：" + ClassUtils.isPresent(className, null));
                return false;
            }
        }
        return true;
    }

    public static String getClasspath() {
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }
        return path;
    }

    public static void main(String[] args) {

        TestClassLoader testClassLoader = new TestClassLoader();
        //testClassLoader.deduceWebEnvironment();

        System.out.println(getClasspath());

    }
}
