package com.master.service.two;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserDaoImplTwo {

    @Autowired
    @Qualifier("secondaryMongoTemplate")
    private MongoTemplate secondaryMongoTemplate;


    /**
     * 创建对象
     * @param userTwo
     */
    public void saveUser(UserTwo userTwo) {
        secondaryMongoTemplate.save(userTwo);
    }

    /**
     * 根据用户名查询对象
     * @param username
     * @return
     */
    public UserTwo findUserByUserName(String username) {
        Query query=new Query(Criteria.where("username").is(username));
        UserTwo userTwo =  secondaryMongoTemplate.findOne(query , UserTwo.class);
        return userTwo;
    }

    /**
     * 更新对象
     * @param userTwo
     */
    public void updateUser(UserTwo userTwo) {
        Query query=new Query(Criteria.where("id").is(userTwo.getId()));
        Update update= new Update().set("username", userTwo.getUsername()).set("age", userTwo.getAge());
        //更新查询返回结果集的第一条
        secondaryMongoTemplate.updateFirst(query,update,UserTwo.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserTwo.class);
    }

    /**
     * 删除对象
     * @param id
     */
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        secondaryMongoTemplate.remove(query,UserTwo.class);
    }

    public int findAll(){
        return secondaryMongoTemplate.findAll(UserTwo.class).size();
    }
}