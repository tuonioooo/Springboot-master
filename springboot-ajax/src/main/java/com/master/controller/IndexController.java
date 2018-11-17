package com.master.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

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

    @PostMapping("/process")
    @ResponseBody
    public String process(){
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


    @GetMapping("/toLongPoling")
    public String toLongPoling(){
        return "long_poling";
    }

    @PostMapping(value = "/needPrice", produces = "text/event-stream;charset=UTF-8")
    @ResponseBody
    public String needPrice(){
        Random r = new Random();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return makeResp(r);
    }

    private String makeResp(Random r){
        StringBuilder stringBuilder = new StringBuilder("");
        stringBuilder.append("retry:10\n")
                .append("data:")
                .append(r.nextInt(100)+50+",")
                .append(r.nextInt(40)+25)
                .append("\n\n");
        return stringBuilder.toString();

    }


}
