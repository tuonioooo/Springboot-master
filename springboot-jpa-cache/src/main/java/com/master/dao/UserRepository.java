package com.master.dao;

import com.master.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author tony
 */

public interface UserRepository extends JpaRepository<User, Long> {
    @Cacheable(value="user")
    User findByName(String name);
}