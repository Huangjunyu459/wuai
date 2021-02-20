package com.hjy.wuai.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.hjy.wuai.reamls.AdminRealm;
import com.hjy.wuai.reamls.MyRealm;
import com.hjy.wuai.reamls.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author： hjy
 * @date： 2021/2/19 0019,上午 11:37
 * @email: 541605007@qq.com
 * <p>
 * Shiro的配置类
 */
@Configuration
public class ShiroConfig {


    /**
     * 自定义认证器
     *
     * @return
     */
    @Bean
    public MyModularRealmAuthenticator getMyModularRealmAuthenticator() {
        MyModularRealmAuthenticator myModularRealmAuthenticator = new MyModularRealmAuthenticator();
        return myModularRealmAuthenticator;
    }


    @Bean
    public AdminRealm getAdminRealm(HashedCredentialsMatcher matcher) {
        AdminRealm adminRealm = new AdminRealm();
        adminRealm.setCredentialsMatcher(matcher);
        return adminRealm;
    }


    @Bean
    public UserRealm getUserRealm(HashedCredentialsMatcher matcher) {
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(matcher);
        return userRealm;
    }


    /**
     * RememberMe 管理器
     *
     * @return
     */
    @Bean
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
     * @return
     */
    @Bean
    public DefaultWebSessionManager getDefaultWebSessionManager() {
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //  配置 sessionManager，单位是毫秒
        defaultWebSessionManager.setGlobalSessionTimeout(5 * 60 * 1000);

        return defaultWebSessionManager;
    }


    /**
     * 缓存管理器
     *
     * @return
     */
/*    @Bean
    public EhCacheManager getEhCacheManager() {

        CacheManager cacheManager = CacheManager.getCacheManager("");
        if (cacheManager == null) {
            try {
                cacheManager = CacheManager.create(ResourceUtils.getInputStreamForPath("classpath:ehcache.xml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        EhCacheManager ehCacheManager = new EhCacheManager();
        //  设置缓存管理机制
        ehCacheManager.setCacheManager(cacheManager);
        return ehCacheManager;

    }*/


    /**
     * 开启 shiro 权限注解
     *
     * @return
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
     * @return
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
     * @return
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
     * 启用 thymeleaf 对 shiro 的方言支持
     *
     * @return
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


    /**
     * @Bean 废弃，不使用文件的 realm，使用 jdbcRealm
     * public IniRealm getIniRealm() {
     * IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
     * return iniRealm;
     * }
     * @Bean 废弃，改用自定义 realm
     * public JdbcRealm getJdbcRealm(DataSource dataSource) {
     * JdbcRealm jdbcRealm = new JdbcRealm();
     * <p>
     * //  JdbcRealm   会自行从数据库查询用户及权限数据（前提是数据库的表结构符合 JdbcRealm 的规范）
     * jdbcRealm.setDataSource(dataSource);
     * //  JdbcRealm 默认开启认证功能，需要手动开启授权功能
     * jdbcRealm.setPermissionsLookupEnabled(true);
     * <p>
     * return jdbcRealm;
     * <p>
     * }
     */


    @Bean
    public MyRealm getMyRealm(HashedCredentialsMatcher matcher) {
        MyRealm myRealm = new MyRealm();
        //  把加密规则给 MyRealm
        myRealm.setCredentialsMatcher(matcher);
        return myRealm;
    }


    /**
     * 安全管理器
     *
     * @param
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(UserRealm userRealm,AdminRealm adminRealm, DefaultWebSessionManager sessionManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

        //  配置认证器
        securityManager.setAuthenticator(getMyModularRealmAuthenticator());

        //  securityManager 要完成校验，就需要 realm
        //securityManager.setRealm(myRealm);

        //  设置多 realm
        List<Realm> realms = new ArrayList<>();
        realms.add(userRealm);
        realms.add(adminRealm);
        securityManager.setRealms(realms);

        //  设置缓存管理器
        //securityManager.setCacheManager(ehCacheManager);

        //  设置 sessionManger
        securityManager.setSessionManager(sessionManager);

        //  设置 RememberMeManager
        securityManager.setRememberMeManager(getCookieRememberMeManager());

        return securityManager;
    }


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
        filterMap.put("/user/login", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/static/**", "anon");


        filterMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

        //  设置登录页面
        shiroFilterFactoryBean.setLoginUrl("/login.html");


        //  设置未授权访问的页面路径
        shiroFilterFactoryBean.setUnauthorizedUrl("/login.html");

        return shiroFilterFactoryBean;
    }
}
