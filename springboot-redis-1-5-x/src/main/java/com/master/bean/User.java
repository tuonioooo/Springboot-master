package com.master.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-5-27
 * Time: 12:22
 * info:
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1L;

    private int id;
    private String username;
    private Integer age;


}