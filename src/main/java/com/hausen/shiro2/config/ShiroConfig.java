package com.hausen.shiro2.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
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

    //Realm
    @Bean
    public MyRealm getMyRealm(){

        return  new MyRealm();
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
        filterMap.put("register.html","anon");
        filterMap.put("/user/login","anon");
        filterMap.put("/user/register.html","anon");
        filterMap.put("/static/**","anon");
        filterMap.put("/**","authc");

        filter.setFilterChainDefinitionMap(filterMap);

        filter.setLoginUrl("/");
        // 设置未授权提示url
        filter.setUnauthorizedUrl("/login.html");
        return filter;
    }
}
