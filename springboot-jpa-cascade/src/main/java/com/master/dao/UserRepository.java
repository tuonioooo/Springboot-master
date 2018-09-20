package com.master.dao;

import com.master.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author tony
 */
public interface UserRepository extends JpaRepository<User, Long> {


}
