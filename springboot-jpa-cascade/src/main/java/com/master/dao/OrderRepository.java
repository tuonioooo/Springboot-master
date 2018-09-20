package com.master.dao;


import com.master.bean.Order;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author tony
 */
public interface OrderRepository extends JpaRepository<Order, Long> {


}
