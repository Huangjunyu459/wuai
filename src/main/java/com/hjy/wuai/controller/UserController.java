package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.UserService;
import com.hjy.wuai.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@Controller
@RequestMapping("user")
@EnableCaching
@EnableAsync(proxyTargetClass = true)
@Slf4j
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册功能
     *
     * @param user
     */
    @RequestMapping("register")
    public String register(User user) {
        log.info("controller注册");

        return userService.save(user) == true ? "login" : "fail";
    }

    /**
     * 用户登录功能
     *
     * @param username
     * @param password
     * @param loginType
     * @return
     */
    @RequestMapping("login")
    public String login(String username, String password, String email) {
        try {
            userService.checkLogin(username, password, email);
            return "index";
        } catch (Exception e) {
            return "fail";
        }
    }

    /**
     * 根据 id 查询用户
     * @param id
     * @return
     */
    @RequestMapping("findUserById")
    public String findUserById(Long id) {
        User user = userService.getById(id);
        System.out.println(user);
        return user != null ? "index" : "fail";
    }


    /**
     * 更新用户信息功能
     * @param user
     * @return
     */
    @RequestMapping("updateUser")
    public String updateUser(User user){
        if (userService.updateById(user)){
            return "success";
        }else {
            return "fail";
        }
    }




    /**
     * 退出登录功能
     *
     * @return
     */
    @RequestMapping("logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/";
    }


}

