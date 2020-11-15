package com.hausen.shiro2.controller;

import com.hausen.shiro2.beans.User;
import com.hausen.shiro2.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/login")
    public String login(String userName,String userPwd,boolean rememberMe){

        try {
            userService.checkLogin(userName,userPwd,rememberMe);
            System.out.println("登录成功");
            return "index";
        } catch (Exception e) {
            System.out.println("登录失败");
            return "login";
        }
    }

    @RequestMapping("regist")
    public String rigist(String userName,String userPwd) throws Exception {

        System.out.println("-------注册");
        // 把用户名当盐 md5加密一次
        String salt = userName;
        Md5Hash md5Hash = new Md5Hash(userPwd,salt);
        userPwd = md5Hash.toHex();


        //SimpleHash simpleHash = new SimpleHash("md5",salt,1); 与上面结果一致 md5加密加盐1次

        User user = new User();
        user.setUserName(userName);
        user.setPassword(userPwd);
        user.setPasswordSalt(salt);
        userService.addUser(user);
        System.out.println("-------注册成功");
        return "login";
    }
}
