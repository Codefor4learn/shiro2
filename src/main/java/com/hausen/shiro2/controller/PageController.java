package com.hausen.shiro2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String login1(){
        return  "login";
    }

    @RequestMapping("/login.html")
    public String login2(){
        return "login";
    }

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }

    @RequestMapping("/regist.html")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/c_save.html")
    public String c_save(){
        return "c_save";
    }

    @RequestMapping("/lesspermission.html")
    public String lesspermission(){
        return "lesspermission";
    }

}
