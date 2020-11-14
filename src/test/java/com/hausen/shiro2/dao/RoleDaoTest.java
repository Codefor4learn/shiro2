package com.hausen.shiro2.dao;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Iterator;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
class RoleDaoTest {

    @Resource
    private RoleDao roleDao;

    @Test
    void queryRolesByUsername() {

        Set<String> rolenames = roleDao.queryRolesByUsername("zs");
        Iterator<String> it = rolenames.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
}