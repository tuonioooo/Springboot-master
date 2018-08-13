package com.master;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-8-13
 * Time: 15:55
 * info:
 */
@Setter
@Getter
@ToString
public class User {

    private String name;

    private int age;

    private Long id;

    private Date createAt;
}
