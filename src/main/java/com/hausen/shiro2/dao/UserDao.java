package com.hausen.shiro2.dao;

import com.hausen.shiro2.beans.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface UserDao {

    public User queryUserByUsername(String username);

    //@Insert("insert into tb_users(username,password) values (#{userName},#{userPwd})")
    public void addUser(User user);
}
