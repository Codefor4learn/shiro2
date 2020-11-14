package com.hausen.shiro2.service;

public interface UserService {

    void checkLogin(String userName, String userPwd) throws Exception;
}
