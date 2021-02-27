package com.hjy.wuai.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/2/20 0020,下午 12:13
 * @email: 541605007@qq.com
 * <p>
 * 自定义认证器
 */
@Slf4j
@Deprecated
public class MyModularRealmAuthenticator extends ModularRealmAuthenticator {

    /**
     * 重写 doAuthenticate 认证方法
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("------------------------------MyModularRealmAuthenticator");

        this.assertRealmsConfigured();
        Collection<Realm> realms = this.getRealms();

        MyToken token = (MyToken) authenticationToken;
        //String loginType = token.getLoginType();

        List<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
//            if (realm.getName().startsWith(loginType)) {
//                typeRealms.add(realm);
//            }
        }

        if (typeRealms.size() == 1) {
            return this.doSingleRealmAuthentication(typeRealms.iterator().next(), authenticationToken);
        } else {
            return this.doMultiRealmAuthentication(typeRealms, authenticationToken);
        }

    }
}
