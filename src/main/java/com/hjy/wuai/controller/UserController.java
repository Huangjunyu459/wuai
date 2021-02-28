package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.NameAndEmail;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;
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

    /**
     * 注入 UserServiceImpl
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * 用户注册功能
     *
     * @param user
     */
    @PostMapping("register")
    public String register(User user) {
        return userService.save(user) == true ? "login" : "fail";
    }

    /**
     * 用户登录功能
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("login")
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
     *
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
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("findAllUser")
    public String findAllUser() {
        List<User> userList = userService.findAllUser();
        return userList.size() != 0 ? "success" : "fail";
    }


    /**
     * 根据用户名模糊查询
     *
     * @param username
     * @return
     */
    @GetMapping("findUserByUsername")
    public String findUserByUsername(String username) {
        List<User> userList = userService.findUserByUsername(username);
        return userList.size() != 0 ? "success" : "fail";
    }


    /**
     * 更新用户信息功能
     *
     * @param user
     * @return
     */
    @RequestMapping("updateUser")
    public String updateUser(User user) {
        if (userService.updateById(user)) {
            return "success";
        } else {
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

    /**
     * 会员充值
     *
     * @param id
     * @return
     */
    @GetMapping("recharge")
    public String recharge(Long id) {
        return userService.recharge(id) == true ? "success" : "fail";
    }


    /**
     * 根据传入的 Object ，判断为 Integer 还是 String ，再调用对应的方法
     *
     * @param idOrUsername
     * @return
     */
    @GetMapping("findUserByIdOrUsername")
    public String findUserByIdOrUsername(Object idOrUsername) {
        List<User> userList = userService.findUserByIdOrUsername(idOrUsername);
        return "admin";
    }

    @GetMapping("findUserByEmail")
    public String findUserByEmail(String email) {
        List<User> userList = userService.findUserByEmail(email);
        return "admin";
    }

    @GetMapping("findByMap")
    public String findByMap(NameAndEmail entity) {
        List<User> usserlist = userService.findByMap(entity);
        return "admin";
    }

    @GetMapping("findIsDelete")
    public String findIsDelete() {
        List<User> isDelete = userService.findIsDelete();
        return "admin";
    }

    @GetMapping("pagingQuery")
    public String pagingQuery(Integer index) {
        Serializable userIPage = userService.pagingQuery(index);
        System.out.println(userIPage);
        return "admin";
    }


}

