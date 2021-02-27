package com.hjy.wuai.service.impl;

import com.hjy.wuai.config.MyToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * @author： hjy
 * @date： 2021/2/19 0019,上午 11:58
 * @email: 541605007@qq.com
 */
@Service
@Deprecated
public class TestServiceImpl {

    public void checkLogin(String username, String password, String loginType,String email) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        /**
         * 弃用，不能满足需求
         * UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
         */
        MyToken token = new MyToken(username, password,email);
        
        //token.setRememberMe(rememberMe);
        subject.login(token);

    }
}
