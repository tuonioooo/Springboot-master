package com.master.service2;

import com.master.bean.two.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountDao2 extends JpaRepository<Account,Integer> {
}