package com.shiro.config;

import com.shiro.filter.JwtFilter;
import com.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author 大静
 * @version 1.0
 * @date 2021年04月28日 15:48
 */
@Configuration
@SuppressWarnings("all")
public class ShiroConfig {
  /**
   * shiro-aop注解
   * @param securityManager
   * @return
   */
  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      SecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor =
        new AuthorizationAttributeSourceAdvisor();
    authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
    return authorizationAttributeSourceAdvisor;
  }

  @Bean
  public DefaultWebSecurityManager defaultWebSecurityManager() {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    List<Realm> collects = new LinkedList<>();
    collects.add(shiroRealm());
    defaultWebSecurityManager.setRealms(collects);
    defaultWebSecurityManager.setRealm(shiroRealm());
    return defaultWebSecurityManager;
  }

  @Bean
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);
    Map<String, Filter> filterMap = new LinkedHashMap<>();
    filterMap.put("jwt", new JwtFilter());
    shiroFilterFactoryBean.setFilters(filterMap);
    Map<String, String> chainMap = new LinkedHashMap<>();
    chainMap.put("/static/**", "anon");
    chainMap.put("/unAuth/**","anon");
    chainMap.put("/login/**","anon");
    chainMap.put("/**", "jwt");
    chainMap.put("/logout", "logout");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(chainMap);
    shiroFilterFactoryBean.setLoginUrl("/auth/login");
    shiroFilterFactoryBean.setSuccessUrl("/index");
    shiroFilterFactoryBean.setUnauthorizedUrl("/auth/unAuth");
    return shiroFilterFactoryBean;
  }

  @Bean
  public ShiroRealm shiroRealm() {
    ShiroRealm shiroRealm = new ShiroRealm();
    shiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
    shiroRealm.setCachingEnabled(true);
    shiroRealm.setAuthenticationCachingEnabled(true);
    shiroRealm.setAuthorizationCachingEnabled(true);
    return shiroRealm;
  }

  @Bean(name = "hashedCredentialsMatcher")
  public CredentialsMatcher hashedCredentialsMatcher() {
    HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
    matcher.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
    matcher.setHashIterations(5);
    matcher.setStoredCredentialsHexEncoded(true);
    return matcher;
  }

  @Bean(name = "cookieRememberMeManager")
  public CookieRememberMeManager cookieRememberMeManager() {
    CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
    cookieRememberMeManager.setCookie(simpleCookie());
    return cookieRememberMeManager;
  }

  @Bean(name = "simpleCookie")
  public SimpleCookie simpleCookie() {
    SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
    simpleCookie.setMaxAge(604800);
    return simpleCookie;
  }

  /**
   * shiro-bean生命周期管理
   * @return
   */
  @Bean(name = "lifecycleBeanPostProcessor")
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  /**
   * 开启注解
   * @return
   */
  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    advisorAutoProxyCreator.setProxyTargetClass(true);
    return advisorAutoProxyCreator;
  }
}
