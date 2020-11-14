package com.hausen.shiro2.service;

import com.hausen.shiro2.beans.User;
public interface UserService {

    void checkLogin(String userName, String userPwd) throws Exception;

    void addUser(User user) throws Exception;
}
