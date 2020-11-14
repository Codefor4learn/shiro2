package com.hausen.shiro2.dao;

import com.hausen.shiro2.Shiro2Application;
import com.hausen.shiro2.beans.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoTest {

    @Resource
    private UserDao userDao;

    @Test
    void queryUserByUsername() {
        User zs = userDao.queryUserByUsername("zs");
        System.out.println(zs);
    }
}