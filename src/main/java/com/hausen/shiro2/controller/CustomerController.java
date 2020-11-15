package com.hausen.shiro2.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("cust")
public class CustomerController {

    @RequestMapping("/listCustomer")
    //@RequiresPermissions("sys:k:find")
    public String listCustomer(){

        Subject subject = SecurityUtils.getSubject();
        if(subject.isPermitted("sys:k:find")){
            System.out.println("查询到客户信息");
            return "customer/customer_list";
        }else{
            System.out.println("授权失败");
            return "lesspermission";
        }
    }
}
