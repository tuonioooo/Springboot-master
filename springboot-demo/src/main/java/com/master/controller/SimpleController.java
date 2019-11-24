package com.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class SimpleController {

	@RequestMapping(value = "/index")
    public String toIndex(HttpServletRequest request){
        request.setAttribute("htext","Spring Boot：Hello World");
		return "/index";
    }



}