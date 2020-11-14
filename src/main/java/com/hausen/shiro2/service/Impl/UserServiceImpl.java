package com.hausen.shiro2.service.Impl;

import com.hausen.shiro2.beans.User;
import com.hausen.shiro2.dao.UserDao;
import com.hausen.shiro2.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void checkLogin(String userName, String userPwd) throws Exception{

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,userPwd);

        subject.login(token);
    }

    @Override
    public void addUser(User user) throws Exception {

        userDao.addUser(user);
        return;
    }
}
