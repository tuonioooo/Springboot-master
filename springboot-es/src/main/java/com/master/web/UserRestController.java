package com.master.web;

import com.master.pojo.User;
import com.master.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 
* Title: UserRestController
* Description: 
* 用户数据操作接口
* Version:1.0.0  
* @author pancm
* @date 2018年1月9日
 */
@RestController
@RequestMapping(value = "/api")
public class UserRestController {
	@Autowired
    private UserService userService;
	
    @PostMapping("/user")
    public boolean createUser(@RequestBody User user) {
        return userService.insert(user);
    }

    
    @GetMapping("/user/searchContent")
    public List<User> search(@RequestParam(value = "searchContent") String searchContent) {
		return userService.search(searchContent);
    }
    
    @GetMapping("/user")
    public List<User> searchUser(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return userService.searchUser(pageNumber, pageSize, searchContent);
    }
    
    
    @GetMapping("/user2")
    public List<User> searchUserByWeight(@RequestParam(value = "searchContent") String searchContent) {
     return userService.searchUserByWeight(searchContent);
    }
    
    
}
