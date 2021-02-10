package com.fadedos.springbootjspshiro.config;

import com.fadedos.springbootjspshiro.shiro.realms.CustomerRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * @Description:用来整合shiro 相关的配置类
 * @author: pengcheng
 * @date: 2020/12/24
 */
@Configuration
public class ShiroConfig {
    //1.创建shiroFilter  负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统受限资源
        //配置系统公共资源
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //默认的认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
        filterChainDefinitionMap.put("/user/login", "anon"); //anno 设置为公共资源
        //放行注册页面
        filterChainDefinitionMap.put("/register.jsp", "anon");
        filterChainDefinitionMap.put("/user/register", "anon");
        //authc 请求这个资源需要认证和授权
        filterChainDefinitionMap.put("/**", "authc"); //authc  请求这个资源需要认证和授权
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }


    //2.创建安全管理器 SecurityManager
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        //给安全管理器设置realm
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm() {
        //修改凭证校验匹配器
        CustomerRealm customerRealm = new CustomerRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法和散列次数
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);
        customerRealm.setCredentialsMatcher(hashedCredentialsMatcher);

        //开启缓存
        customerRealm.setCacheManager(new EhCacheManager());
        customerRealm.setCachingEnabled(true);
        //认证缓存
        customerRealm.setAuthenticationCachingEnabled(true);
        customerRealm.setAuthenticationCacheName("authenticationCache");
        //开启授权缓存
        customerRealm.setAuthorizationCachingEnabled(true);
        customerRealm.setAuthorizationCacheName("authorizationCache");
        return customerRealm;
    }
}
