package com.hausen.shiro2.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //shiro方言
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }

    // 让 shrio注解生效
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);

        return advisor;
    }

    // 加密
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher(){

        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();

        //设置加密方式
        matcher.setHashAlgorithmName("md5");
        //设置加密次数
        matcher.setHashIterations(1);

        return matcher;
    }

    //Realm
    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher matcher){

        MyRealm myRealm = new MyRealm();
        myRealm.setCredentialsMatcher(matcher);

        return myRealm;
    }

    //DefaultWebSecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(myRealm);

        return securityManager;
    }

    //过滤器
    @Bean
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();

        filter.setSecurityManager(securityManager);

        Map<String,String> filterMap = new HashMap<String, String>();
        filterMap.put("/","anon");
        filterMap.put("/login.html","anon");
        //filterMap.put("/index.html","anon");
        filterMap.put("/regist.html","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/regist","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/**","authc");

        filterMap.put("/c_save.html","perms[sys:c:save]");

        filterMap.put("/logout","logout");
        filter.setFilterChainDefinitionMap(filterMap);

        // 设置登录页面
        filter.setLoginUrl("/");
        // 设置未授权访问页面
        filter.setUnauthorizedUrl("/lesspermission.html");
        return filter;
    }
}
