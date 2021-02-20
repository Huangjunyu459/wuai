package com.hjy.wuai.controller;

import com.hjy.wuai.mapper.AdminMapper;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.TestServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author： hjy
 * @date： 2021/2/19 0019,上午 11:57
 * @email: 541605007@qq.com
 */
@Controller
@RequestMapping("test")
@Slf4j
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private UserMapper userMapper;


    @RequestMapping("login")
    public String login(String username, String password,  String loginType,String email) {

        try {
            testService.checkLogin(username, password, loginType,email);
            System.out.println("登录成功");
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping("regist")
    public String regist(User user) {

        System.out.println("注册开始...");


        /**
         * md5 加密处理，不加盐:
         * Md5Hash md5Hash = new Md5Hash(user.getPassword());
         * md5 加密加盐，多次 hash
         * Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt,3);
         *
         * 另一种方式：
         * SimpleHash hash = new SimpleHash("md5",user.getPassword(),salt,3);
         */

        //  随机生成一个 10000-99999 的盐
        String salt = (new Random().nextInt(90000) + 10000) + "";
        user.setPasswordSalt(salt);
        //  加密加盐
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt);
        //  把加密后的密码重新设置给用户
        user.setPassword(md5Hash.toHex());
        userMapper.insert(user);
        log.info(String.valueOf(user.getId()));

        return "login";
    }


    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }
}
