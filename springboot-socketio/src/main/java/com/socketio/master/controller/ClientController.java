package com.socketio.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p></p>
 *
 * @author: daizhao 14:09 2019/11/24
 */
@Controller
public class ClientController {

    @RequestMapping("/client")
    public String client(){
        return "/client.html";
    }
}
