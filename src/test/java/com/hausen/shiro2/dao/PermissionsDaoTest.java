package com.hausen.shiro2.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PermissionsDaoTest {

    @Resource
    private PermissionsDao permissionsDao;

    @Test
    void queryPermissionsByUsername() {
        Set<String> permissions = permissionsDao.queryPermissionsByUsername("ww");
        Iterator<String> it = permissions.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
        }
    }
}