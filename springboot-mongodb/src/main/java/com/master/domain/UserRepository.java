package com.master.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author tuonioooo
 * @version 1.0.0
 * @date 16/4/27 下午10:16.
 * @blog https://blog.csdn.net/tuoni123
 */
public interface UserRepository extends MongoRepository<User, Long> {

    User findByUsername(String username);

}
