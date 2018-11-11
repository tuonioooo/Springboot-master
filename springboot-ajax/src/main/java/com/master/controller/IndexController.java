package com.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class IndexController {

    @GetMapping("/index")
    public String index(HttpServletRequest request){
        request.setAttribute("title","ajax短轮询");
        return "index";
    }

    @GetMapping("/process")
    @ResponseBody
    public String process(@RequestParam(name = "name") String name){
        System.out.println("name = " + name);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());
    }

    @GetMapping("/toAjax1")
    public String toAjax1(){
        return "ajax1";
    }

    @GetMapping("/toAjax2")
    public String toAjax2(){
        return "ajax2";
    }

    @GetMapping("/toAjaxUpload1")
    public String toAjaxUpload1(){
        return "ajax_upload_1";
    }

    @GetMapping("/toAjaxUploadJson1")
    public String toAjaxUploadJson1(){
        return "ajax_upload_json_1.html";
    }

    @GetMapping("/toAjaxUpload2")
    public String toAjaxUpload2(){
        return "ajax_upload_2";
    }

    @GetMapping("/toAjaxUpload3")
    public String toAjaxUpload3(){
        return "ajax_upload_3";
    }


    @GetMapping("/toAjaxUploadJson2")
    public String toAjaxUploadJson2(){
        return "ajax_upload_json_2";
    }






}
