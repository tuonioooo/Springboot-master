package com.master.bean.one;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@Proxy(lazy = false)//延迟加载设置false,默认是true
public class OrderT {
    @Id
    @GeneratedValue
    private int id ;
    private String name ;
    private double money;

}