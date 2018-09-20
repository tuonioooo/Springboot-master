package com.master.classloader;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        CustomClassLoader loader = new CustomClassLoader();
        try {
            Class c1 = loader.loadClass("com.master.classloader.Test");
            Object object = c1.newInstance();
            System.out.println(object);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
