package com.master.controller;

import com.master.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController  
@RequestMapping(value = "/admin/v1")  
public class TestController {  
    
	@Autowired
	private UserService userService;
	//  http://localhost:8080/admin/v1/first?id=1
	@RequestMapping(value = "/first", method = RequestMethod.GET)
	@ResponseBody
    public String firstResp (Long id){
		return userService.query(id);
    }
	
	@RequestMapping(value = "/put", method = RequestMethod.GET)
	@ResponseBody
    public String put(Long id, String value){
		return userService.put(id, value);
    }
	
	@RequestMapping(value = "/put2", method = RequestMethod.GET)
	@ResponseBody
    public String put2(Long id, String value){
		return userService.put2(id, value);
    }
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@ResponseBody
    public void delete(Long id){
		userService.remove(id);
    }
} 