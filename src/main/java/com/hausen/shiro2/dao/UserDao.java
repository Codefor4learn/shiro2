package com.hausen.shiro2.dao;

import com.hausen.shiro2.beans.User;

public interface UserDao {

    public User queryUserByUsername(String username);
}
