package com.hausen.shiro2.dao;

import java.util.Set;

public interface RoleDao {

    public Set<String> queryRolesByUsername(String username);
}
