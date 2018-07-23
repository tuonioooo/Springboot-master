package com.master.controller;

import com.master.rabbit.Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JournalController {

    @Autowired
    private Sender sender;

    @RequestMapping("/")
    public String index(Model model){
        sender.send();
        return "index";
    }
}