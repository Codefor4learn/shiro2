package com.hausen.shiro2.dao;

import java.util.Set;

public interface PermissionsDao {

    public Set<String> queryPermissionsByUsername(String username);
}
