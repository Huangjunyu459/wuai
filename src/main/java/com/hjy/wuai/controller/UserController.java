package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.NameAndEmail;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;


/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@RestController
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
     * @param user 用户实体类
     * @return 返回的结果 msg
     */
    @PostMapping("register")
    public Result1 register(User user) {
        System.out.println(user);
        boolean statue = userService.save(user);
        if (statue) {
            return Result1.success().setMessage("注册成功");
        } else {
            return Result1.fail().setMessage("注册失败");
        }
    }


    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回的结果 msg
     */
    @PostMapping("login")
    public Result1 login(String username, String password, String email) {
        try {
            userService.checkLogin(username, password, email);
            return Result1.success().setMessage("登录成功");
        } catch (Exception e) {
            return Result1.fail().setMessage("注册失败");
        }
    }


    /**
     * 根据 id 查询用户
     *
     * @param id 用户的 id
     * @return 返回的结果 msg
     */
    @RequestMapping("findUserById")
    public Result1 findUserById(Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return Result1.success().data("user", user);
        } else {
            return Result1.fail().setMessage("用户不存在");
        }
    }


    /**
     * 查询所有用户
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findAllUser")
    public Result1 findAllUser() {
        List<User> userList = userService.findAllUser();
        return Result1.success().data("userList", userList);
    }


    /**
     * 根据用户名精准查询
     *
     * @param username 用户名
     * @return 返回的结果 msg
     */
    @GetMapping("findUserByUsername")
    public Result1 findUserByUsername(String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result1.fail().setMessage("用户不存在");
        } else {
            return Result1.success().data("user", user);
        }
    }


    /**
     * 根据用户名模糊查询
     *
     * @param username 用户名
     * @return 返回的结果 msg
     */
    @GetMapping("findUsersByUsername")
    public Result1 findUsersByUsername(String username) {
        List<User> userList = userService.findUsersByUsername(username);
        if (userList.size() == 0) {
            return Result1.fail().setMessage("用户不存在");
        } else {
            return Result1.success().data("userList", userList);
        }
    }


    /**
     * 更新用户信息功能
     *
     * @param user 用户实体类
     * @return 返回的结果 msg
     */
    @RequestMapping("updateUser")
    public Result1 updateUser(User user) {
        if (userService.updateById(user)) {
            return Result1.success().setMessage("修改成功");
        } else {
            return Result1.fail().setMessage("修改失败");
        }
    }


    /**
     * 退出登录功能
     *
     * @return 重定向到登录页
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
     * @param id 用户的 id
     * @return 返回的结果 msg
     */
    @GetMapping("recharge")
    public Result1 recharge(Long id) {
        if (userService.recharge(id)) {
            return Result1.success().setMessage("充值成功");
        } else {
            return Result1.fail().setMessage("充值失败");
        }
    }


    /**
     * 根据 邮箱 查询用户
     *
     * @param email 邮箱
     * @return 返回的结果 msg
     */
    @GetMapping("findUserByEmail")
    public Result1 findUserByEmail(String email) {
        User user = userService.findUserByEmail(email);
        if (user != null) {
            return Result1.success().data("user", user);
        } else {
            return Result1.fail().setMessage("用户不存在");
        }
    }


    /**
     * 根据组合条件查询用户
     *
     * @param entity 传入的组合条件实体类
     * @return 返回的结果 msg
     */
    @GetMapping("findByMap")
    public Result1 findByMap(NameAndEmail entity) {
        List<User> userList = userService.findByMap(entity);
        if (userList.size() == 0) {
            return Result1.fail().setMessage("用户不存在");
        } else {
            return Result1.success().data("userList", userList);
        }
    }


    /**
     * 查询已删除的用户
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<User> userList = userService.findIsDelete();
        if (userList.size() == 0) {
            return Result1.fail().setMessage("用户不存在");
        } else {
            return Result1.success().data("userList", userList);
        }
    }


    /**
     * 用户的分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable userIPage = userService.pagingQuery(index);
        if (userIPage != null) {
            return Result1.success().data("userIPage", userIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

}

