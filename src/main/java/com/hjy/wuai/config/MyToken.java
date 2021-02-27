package com.hjy.wuai.config;

import com.alibaba.druid.pool.ha.selector.StickyRandomDataSourceSelector;
import lombok.Data;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author： hjy
 * @date： 2021/2/20 0020,下午 12:06
 * @email: 541605007@qq.com
 * <p>
 * 自定义 Token 继承 UsernamePasswordToken
 */
@Data
public class MyToken extends UsernamePasswordToken {

    /**
     * 自定义的 loginType 属性
     */
    private String loginType;

    private String email;


    /**
     * 构造器方法
     *
     * @param username
     * @param password
     * @param loginType
     */
    public MyToken(String username, String password, String loginType, String email) {
        super(username, password);
        this.loginType = loginType;
        this.email = email;
    }


    public MyToken(String username, String password) {
        super(username,password);
    }
}
