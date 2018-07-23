package com.master.service;

import com.master.bean.User;
import lombok.extern.java.Log;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CacheConfig(cacheNames = "user") //类级别的缓存名称
@Service
@Log
public class UserCacheRedisService {

    private List<User> data = new ArrayList<>();

    /**
     * 初始化
     */
    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setUsername("张三");
        user1.setAge(20);

        User user2 = new User();
        user2.setUsername("李四");
        user2.setAge(20);

        User user3 = new User();
        user3.setUsername("科比");
        user3.setAge(40);

        data.add(user1);
        data.add(user2);
        data.add(user3);
    }


    /**
     * 如果key不存在，查询db，并将结果更新到缓存中。
     * 如果key存在，直接查询缓存中的数据。
     * @Cacheable ：value为空时，将会调用@CacheConfig(cacheNames = "user")，这里的chacheNames做完缓存的名称，如果找不到，就报错
     * @return
     */
    @Cacheable
    public List<User> selectAllUser(){
        log.info("selectAllUser execute");
        return data;
    }

    /**
     * @Cacheable：
     *          value：缓存的名称
     *          key：  缓存的key，按照SpEL表达式
     * @param id
     * @return
     */
    @Cacheable(value = "getUser", key = "#id")
    public User getUser(int id){
        User user = new User();
        user.setId(id);
        user.setAge(20);
        user.setUsername("玛雅文明");
        log.info("getUser execute");
        return user;
    }

    /**
     * @Cacheable：
     *             key 如果为空，缺省按照方法的所有参数进行组合
     * @param name
     * @return
     */
    @Cacheable(value = "getUserOfName")
    public User getUserOfName(String name){
        User user = new User();
        user.setId(1001);
        user.setAge(20);
        user.setUsername(name);
        log.info("getUserOfName execute");
        return user;
    }

    /**
     * @CachePut：
     *          value：缓存的名称
     *          key：  缓存的key，按照SpEL表达式
     * @param user
     * @return
     */
    @CachePut(value="saveOfUpdate", key = "\"user_\" + #user.id")
    public User saveOfUpdate(User user){
        log.info("saveOfUpdate execute");
        return user;
    }

    /**
     * 清空指定key缓存
     * @param user
     */
    @CacheEvict(value="saveOfUpdate", key="\"user_\" + #user.getId()")
    public void clearUser(User user) {
        log.info("clearUser execute");
    }

    /**
     * allEntries：是否清空所有缓存内容，缺省为 false，如果指定为 true，则方法调用后将立即清空所有缓存
     */
    @CacheEvict(value="saveOfUpdate", allEntries=true)
    public void flushCacle() {
        log.info("flushCacle execute");
    }

}