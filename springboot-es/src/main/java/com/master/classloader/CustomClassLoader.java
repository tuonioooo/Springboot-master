package com.master.classloader;

import java.io.*;

public class CustomClassLoader extends ClassLoader {

    private static final String package_name = "com/master/pojo";

    private static final String FILE_TYEP = ".class";

    @Override
    public Class findClass(String name) {
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    public static String getClasspath() {
        String path = (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();
        if (path.indexOf(":") != 1) {
            path = File.separator + path;
        }
        return path;
    }

    private byte[] loadClassData(String name) {
        FileInputStream fis;
        byte[] data = null;
        try {
            File file = new File(getClasspath(), name + FILE_TYEP);
            System.out.println(file.getAbsolutePath());
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }


    public static void main(String[] args) {

        System.out.println(getClasspath()+package_name);
    }
}
