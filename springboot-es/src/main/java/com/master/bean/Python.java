package com.master.bean;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-23
 * Time: 14:17
 * info:
 */
public class Python {

    private String name;

    private String hobby;

    public Python(String name, String hobby) {
        this.name = name;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
