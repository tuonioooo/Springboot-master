package com.master.web;

import org.springframework.web.bind.annotation.*;

/**
 * @author tuonioooo
 * @version 1.0.0
 * @date 16/5/19 下午1:27.
 * @blog https://blog.csdn.net/tuoni123
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello(@RequestParam String name) {
        return "Hello " + name;
    }

}
