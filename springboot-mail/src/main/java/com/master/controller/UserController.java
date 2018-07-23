package com.master.controller;

import com.alibaba.fastjson.JSONObject;
import com.master.bean.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {  
  
    @RequestMapping("/{id}")
    @ResponseBody
    public String view(@PathVariable("id") int id, HttpServletRequest req) {
        Account account = new Account();
        account.setId(id);
        account.setName("zhang");

        return JSONObject.toJSONString(account);
    }  
}  