package com.master.service.one;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImplOne {

    @Autowired
    @Qualifier("primaryMongoTemplate")
    private MongoTemplate primaryMongoTemplate;

    /**
     * 创建对象
     * @param userOne
     */
    public void saveUser(UserOne userOne) {
        primaryMongoTemplate.save(userOne);

    }

    /**
     * 根据用户名查询对象
     * @param username
     * @return
     */
    public UserOne findUserByUserName(String username) {
        Query query=new Query(Criteria.where("username").is(username));
        UserOne userOne =  primaryMongoTemplate.findOne(query , UserOne.class);
        return userOne;
    }

    /**
     * 更新对象
     * @param userOne
     */
    public void updateUser(UserOne userOne) {
        Query query=new Query(Criteria.where("id").is(userOne.getId()));
        Update update= new Update().set("username", userOne.getUsername()).set("age", userOne.getAge());
        //更新查询返回结果集的第一条
        primaryMongoTemplate.updateFirst(query,update,UserOne.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserOne.class);
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        primaryMongoTemplate.remove(query,UserOne.class);
    }

    public int findAll(){
        return primaryMongoTemplate.findAll(UserOne.class).size();
    }
}