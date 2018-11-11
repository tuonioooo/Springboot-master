package com.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-8
 * Time: 10:14
 * info:
 */

@Controller
@RequestMapping("/")
public class HomeController {

    @GetMapping("/home")
    @ResponseBody
    public String index(){
        return "weclome HOME";
    }

}
