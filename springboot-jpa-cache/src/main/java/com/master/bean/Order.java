package com.master.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-9-17
 * Time: 9:48
 * info:
 */
@Table(name="t_order")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;

    private String orderName;

    @Column(name = "user_id")
    private Integer userId;

}
