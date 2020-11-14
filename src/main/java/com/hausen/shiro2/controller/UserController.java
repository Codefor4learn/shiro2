package com.hausen.shiro2.controller;

import com.hausen.shiro2.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String login(String userName,String userPwd){

        try {
            userService.checkLogin(userName,userPwd);
            System.out.println("登录成功");
            return "index";
        } catch (Exception e) {
            System.out.println("登录失败");
            return "login";
        }

    }
}
