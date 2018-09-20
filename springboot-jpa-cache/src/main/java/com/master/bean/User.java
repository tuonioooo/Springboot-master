package com.master.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;


/**
 * @author tony
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer age;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//级联操作所有
    @JoinColumn(name = "user_id")
    private Set<Order> orders = new LinkedHashSet<>();

}
