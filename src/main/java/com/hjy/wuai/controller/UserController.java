package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.ActiveCode;
import com.hjy.wuai.pojo.NameAndEmail;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.ActiveCodeServiceImpl;
import com.hjy.wuai.service.impl.UserServiceImpl;
import com.hjy.wuai.service.impl.ValidateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
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
@RequestMapping("/user")
@EnableCaching
@EnableAsync(proxyTargetClass = true)
@Slf4j
@CrossOrigin
public class UserController {

    /**
     * 注入 UserServiceImpl
     */
    @Autowired
    private UserServiceImpl userService;

    /**
     * 注入 activeCodeService
     */
    @Autowired
    private ActiveCodeServiceImpl activeCodeService;


    @Autowired
    private ValidateServiceImpl validateService;


    /**
     * 找回密码
     *
     * @param email 找回密码的邮箱
     * @return 返回的结果 msg
     */
    @GetMapping("findPS")
    public Result1 findPS(String email,String newPassword) {
        boolean statue = userService.findPS(email,newPassword);
        if (statue) {
            return Result1.success().setMessage("找回密码成功");
        } else {
            return Result1.fail().setMessage("找回密码失败");
        }
    }

    /**
     * 邮箱验证码
     *
     * @param email 找回密码的邮箱
     * @return 返回的结果 msg
     */
    @GetMapping("acceptVC")
    public Result1 acceptVC(String email) {
        boolean statue = validateService.sendEmailVCode(email);
        if (statue) {
            return Result1.success().setMessage("验证码发送成功");
        } else {
            return Result1.fail().setMessage("验证码发送失败");
        }
    }

    /**
     * 邮箱注册码
     *
     * @param email 找回密码的邮箱
     * @return 返回的结果 msg
     */
    @GetMapping("acceptRC")
    public Result1 acceptRC(String email) {
        boolean statue = validateService.sendEmailRCode(email);
        if (statue) {
            return Result1.success().setMessage("注册码获取成功");
        } else {
            return Result1.fail().setMessage("注册码获取失败");
        }
    }

    /**
     * 检测注册码
     *
     * @param email 注册的邮箱
     * @param RCode 注册码
     * @return 返回的结果 msg
     */
    @GetMapping("checkRCode")
    public Result1 checkRCode(String email, String RCode) {
        boolean statue = validateService.checkRCode(email, RCode);
        if (statue) {
            return Result1.success().setMessage("注册码正确");
        } else {
            return Result1.fail().setMessage("注册码错误");
        }
    }

    /**
     * 检测注册码
     *
     * @param email 找回密码的邮箱
     * @param VCode 验证码
     * @return 返回的结果 msg
     */
    @GetMapping("checkVCode")
    public Result1 checkVCode(String email, String VCode) {
        boolean statue = validateService.checkVCode(email, VCode);
        if (statue) {
            return Result1.success().setMessage("验证码正确");
        } else {
            return Result1.fail().setMessage("验证码错误");
        }
    }


    /**
     * 用户注册功能
     *
     * @param user 用户实体类
     * @return 返回的结果 msg
     */
    @PostMapping("register")
    public Result1 register(@RequestBody User user) {
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
            return Result1.fail().setMessage("登录失败");
        }
    }


    /**
     * 根据用户 id 删除 用户
     *
     * @param id 用户 id
     * @return 返回的结果 msg
     */
    @DeleteMapping("/removeUserById")
    public Result1 removeUserById(String id) {
        boolean statue = userService.removeById(id);
        if (statue == true) {
            return Result1.success().setMessage("用户删除成功");
        } else {
            return Result1.fail().setMessage("用户删除失败");
        }
    }


    /**
     * 根据 id 查询用户
     *
     * @param id 用户的 id
     * @return 返回的结果 msg
     */
    @RequestMapping("findUserById")
    public Result1 findUserById(String id) {
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
    @PostMapping("updateUser")
    public Result1 updateUser(@RequestBody User user) {
        System.out.println("-------------");
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
     * @param activeCode 激活码对象
     * @return 返回的结果 msg
     */
    @PostMapping("recharge")
    public Result1 recharge(@RequestBody ActiveCode activeCode) {
        System.out.println(activeCode);
        if (activeCodeService.recharge(activeCode)) {
            return Result1.success().setMessage("充值成功");
        } else {
            return Result1.fail().setMessage("充值失败");
        }
    }


    /**
     * 用户签到功能
     *
     * @param id 用户id
     * @return 返回的结果 msg
     */
    @GetMapping("signIn")
    public Result1 signIn(String id) {
        if (userService.signIn(id)) {
            return Result1.success().setMessage("签到成功");
        } else {
            return Result1.fail().setMessage("签到失败");
        }
    }


    /**
     * 资源下载方法
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @GetMapping("download")
    public Result1 download(String id) {
        if (userService.download(id)) {
            return Result1.success().setMessage("跳转下载页面成功");
        } else {
            return Result1.fail().setMessage("跳转下载页面失败");
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
        if (user == null) {
            return Result1.fail().setMessage("用户不存在");
        } else {
            return Result1.success().data("user", user);
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
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(String username, Integer index, Integer size) {
        Serializable userIPage = userService.pagingQuery(username, index, size);
        if (userIPage != null) {
            return Result1.success().data("userIPage", userIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

}

