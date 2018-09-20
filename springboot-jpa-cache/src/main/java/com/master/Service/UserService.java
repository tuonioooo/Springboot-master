package com.master.Service;

import com.master.bean.User;
import com.master.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-9-20
 * Time: 11:49
 * info:
 */
@CacheConfig(cacheNames = "users")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Cacheable
    public User getUser(Long id){
        return userRepository.findById(id).get();
    }
}
