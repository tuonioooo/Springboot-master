package com.master.service;

import com.master.bean.one.OrderT;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<OrderT,Integer> {
}