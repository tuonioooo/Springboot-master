package com.master.controller;

import com.master.service.TGoodsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-9
 * Time: 10:20
 * info:
 */

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private TGoodsInfoService tGoodsInfoService;

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        return tGoodsInfoService.findGoods(1).toString();
    }
}
