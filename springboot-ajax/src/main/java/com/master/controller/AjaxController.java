package com.master.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-11-10
 * Time: 12:17
 * info:
 */
@Controller
@RequestMapping("/")
public class AjaxController {

    @PostMapping("/ajax1")
    @ResponseBody
    public String ajax1(String name, String passwd){
        return "name={" + name + "}, passwd={"+passwd+"}";
    }


    @PostMapping("/ajax2Post")
    @ResponseBody
    public String ajax2Post(String name, String passwd){
        return "name={" + name + "}, passwd={"+passwd+"}";
    }

    @GetMapping("/ajax2Get")
    @ResponseBody
    public String ajax2Get(String name, String passwd){
        return "name={" + name + "}, passwd={"+passwd+"}";
    }

    @PostMapping("/ajax_upload_1")
    @ResponseBody
    public String ajaxUpload1(String name, String passwd, MultipartFile file){
        return "name={" + name + "}, passwd={"+passwd+"}," + file.getOriginalFilename();
    }

    @PostMapping("/ajax_upload_2")
    @ResponseBody
    public String ajaxUpload2(String name, String passwd, MultipartFile file){
        return "name={" + name + "}, passwd={"+passwd+"}," + file.getOriginalFilename();
    }

    @PostMapping("/ajax_upload_3")
    @ResponseBody
    public String ajaxUpload3(@RequestBody JSONObject data){
        System.out.println("data.get(\"name\") = " + data.get("name"));
        System.out.println("data.get(\"passwd\") = " + data.get("passwd"));
        return data.toJSONString();
    }


    @PostMapping("/ajax_upload_json_1")
    @ResponseBody
    public String ajaxUploadJson1(String data, MultipartFile file){
        return data + "==" + file.getOriginalFilename();
    }

}
