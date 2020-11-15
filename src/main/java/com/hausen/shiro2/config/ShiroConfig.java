package com.hausen.shiro2.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
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

    // 记住我管理器
    @Bean
    public CookieRememberMeManager getCookieRememberMeManager(){

        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setMaxAge(30*24*60*60);
        rememberMeManager.setCookie(simpleCookie);

        return  rememberMeManager;
    }

    // Session管理器
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager(){

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        System.out.println(sessionManager.getGlobalSessionTimeout()); //默认1800000 30*60*1000
        sessionManager.setGlobalSessionTimeout(15*1000); // 默认毫秒
        return sessionManager;
    }

    // 缓存管理器
    @Bean
    public EhCacheManager getEhCacheManager(){

        EhCacheManager cacheManager = new EhCacheManager();
        //cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
        return cacheManager;
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

    //DefaultWebSecurityManager 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(MyRealm myRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        securityManager.setRealm(myRealm);
        securityManager.setCacheManager(getEhCacheManager());//缓存管理器
        securityManager.setSessionManager(getDefaultWebSessionManager());//Session管理器
        securityManager.setRememberMeManager(getCookieRememberMeManager());//设置Remeberme管理器

        return securityManager;
    }

    //过滤器
    @Bean
    public ShiroFilterFactoryBean getFilter(DefaultWebSecurityManager securityManager){

        ShiroFilterFactoryBean filter = new ShiroFilterFactoryBean();

        filter.setSecurityManager(securityManager);

        // anon 未认证可访问
        // user 记住我可访问(已认真也可以访问)
        // authc 认证访问
        // perms 具有指定权限访问
        // logout 指定退出的url
        Map<String,String> filterMap = new HashMap<String, String>();
        filterMap.put("/","anon");
        filterMap.put("/login.html","anon");
        filterMap.put("/index.html","user");
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
