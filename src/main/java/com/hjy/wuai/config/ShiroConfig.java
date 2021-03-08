package com.hjy.wuai.config;

import com.hjy.wuai.reamls.AdminRealm;
import com.hjy.wuai.reamls.MyRealm;
import com.hjy.wuai.reamls.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hjy
 * @date 2021/2/19 0019,上午 11:37
 * @email 541605007@qq.com
 * <p>
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {

    /**
     * 自定义认证器，暂时不用
     *
     * @return 返回一个 myModularRealmAuthenticator 对象
     */
    public MyModularRealmAuthenticator getMyModularRealmAuthenticator() {
        MyModularRealmAuthenticator myModularRealmAuthenticator = new MyModularRealmAuthenticator();
        return myModularRealmAuthenticator;
    }

    /**
     * 获取 AdminRealm，弃用
     *
     * @param matcher 加密器
     * @return 返回一个 adminRealm 对象
     */
    public AdminRealm getAdminRealm(HashedCredentialsMatcher matcher) {
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(matcher);
        return adminRealm;
    }

    /**
     * 获取 UserRealm
     *
     * @param matcher 加密器
     * @return 返回一个 userRealm 对象
     */
    @Bean
    public UserRealm getUserRealm(HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }

    /**
     * RememberMe 管理器
     *
     * @return 返回一个 cookieRememberMeManager 对象
     */
    public CookieRememberMeManager getCookieRememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //  创建 cookies
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        //  设置 cookies 存活时间，单位为 秒
        cookie.setMaxAge(30 * 24 * 60 * 60);
        //  把 cookies 交给 rememberMeManager
        cookieRememberMeManager.setCookie(cookie);
        return cookieRememberMeManager;
    }

    /**
     * 自定义 sessionManager 会话管理器
     *
     * @return 返回一个 defaultWebSessionManager 对象
     */
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //  配置 sessionManager，单位是毫秒
        defaultWebSessionManager.setGlobalSessionTimeout(5 * 60 * 1000);
        return defaultWebSessionManager;
    }

    /**
     * 开启 shiro 权限注解
     *
     * @return 开启 shiro 权限对象的注解
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    /**
     * shiro 的权限注解
     *
     * @return 返回一个 authorizationAttributeSourceAdvisor 对象
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 加密器
     *
     * @return 返回一个 matcher 对象
     */
    @Bean
    public HashedCredentialsMatcher getHashedCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        //  用来指定加密规则，加密方式：md5
        matcher.setHashAlgorithmName("md5");
        //  hash 加密循环的次数
        matcher.setHashIterations(1);
        return matcher;
    }


    /**
     * 废弃，不使用文件的 realm
     *
     * @return 返回一个 myModularRealmAuthenticator 对象
     */
    public IniRealm getIniRealm() {
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        return iniRealm;
    }

    /**
     * 废弃，改用自定义 realm
     *
     * @param dataSource 数据源
     * @return 返回一个 jdbcRealm 对象
     */
    public JdbcRealm getJdbcRealm(DataSource dataSource) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        //  JdbcRealm   会自行从数据库查询用户及权限数据（前提是数据库的表结构符合 JdbcRealm 的规范）
        jdbcRealm.setDataSource(dataSource);
        //  JdbcRealm 默认开启认证功能，需要手动开启授权功能
        jdbcRealm.setPermissionsLookupEnabled(true);
        return jdbcRealm;
    }

    /**
     * 弃用
     *
     * @param matcher 加密器
     * @return 返回一个 myRealm 对象
     */
    public MyRealm getMyRealm(HashedCredentialsMatcher matcher) {
        MyRealm myRealm = new MyRealm();
        //  把加密规则给 MyRealm
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }


    /**
     * 安全管理器
     *
     * @param userRealm      用户类的 Realm
     * @param sessionManager 会话管理器
     * @return 返回一个 securityManager 对象
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //  securityManager 要完成校验，就需要 realm
        securityManager.setRealm(userRealm);

        //  设置 sessionManger
        securityManager.setSessionManager(sessionManager);

        return securityManager;
    }


    /**
     * shiro 过滤器，核心
     *
     * @param securityManager 安全管理器
     * @return 返回一个 shiro 过滤器的对象
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //  过滤器就是shiro进行权限校验的核心，进行认证和授权是需要SecurityManager的
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /**
         * 设置 shiro 的拦截规则
         * anon：匿名用户可访问
         * authc：认证用户可访问
         * user：使用RememberMe的用户可访问
         * perms：对应权限可访问
         * role：对应的角色可访问
         *
         */
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("/", "anon");
        filterMap.put("/login.html", "anon");
        filterMap.put("/index.html", "user");
        filterMap.put("/regist.html", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/admin/login.html", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/oss/upload", "anon");
        filterMap.put("/admin/login", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/static/**", "anon");


        filterMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //  设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");

        //  设置未授权访问的页面路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");

        return shiroFilterFactoryBean;
    }
}
