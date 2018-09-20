package com.master.dao;

import com.master.pojo.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 
* Title: UserDao
* Description:
* spring-data-es 查询接口
* Version:1.0.0  
* @author pancm
* @date 2018年4月25日
 */
public interface UserDao extends ElasticsearchRepository<User, Long>{

}
