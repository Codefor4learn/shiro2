package com.hausen.shiro2.config;

import com.hausen.shiro2.beans.User;
import com.hausen.shiro2.dao.PermissionsDao;
import com.hausen.shiro2.dao.RoleDao;
import com.hausen.shiro2.dao.UserDao;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {

    @Resource
    private UserDao userDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionsDao permissionsDao;

    String getNames(){
        return "MyRealm";
    }

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // 获取用户名
        String username = (String) principalCollection.iterator().next();

        // 查询角色列表
        Set<String> roleNames = roleDao.queryRolesByUsername(username);

        // 查询权限列表
        Set<String> ps = permissionsDao.queryPermissionsByUsername(username);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roleNames);
        info.setStringPermissions(ps);

        return info;
    }

    // 认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 获取用户名
        String username = (String) authenticationToken.getPrincipal();

        User user =userDao.queryUserByUsername(username);

        if(user == null){

            return null;
        }

        // 不加盐
        //AuthenticationInfo info = new SimpleAuthenticationInfo(username,user.getPassword(),getNames());

        // 加盐
        AuthenticationInfo info = new SimpleAuthenticationInfo(username,user.getPassword(), ByteSource.Util.bytes(user.getPasswordSalt()),getNames());

        return info;
    }
}
