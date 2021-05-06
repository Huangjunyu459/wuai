package com.hjy.wuai.controller;


import com.hjy.wuai.pojo.Admin;
import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.AdminServiceImpl;
import com.hjy.wuai.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(originPatterns = "*",allowCredentials = "true",allowedHeaders = "*",methods = {})
public class AdminController {

    /**
     * 注入 adminService
     */
    @Autowired
    private AdminServiceImpl adminService;

    /**
     * 注入 UserServiceImpl
     */
    @Autowired
    private UserServiceImpl userService;


    /**
     * 管理员登录验证
     *
     * @param admin 管理员实体类
     * @return 返回的结果
     */
    @PostMapping("login")
    public Result1 login(@RequestBody Admin admin) {
        boolean statue = adminService.login(admin.getAdminName(), admin.getPassword());
        if (statue) {
            return Result1.success().setMessage("登录成功").data("statue", true);
        } else {
            return Result1.fail().setMessage("登录失败").data("statue", false);
        }
    }


    /**
     * 用户注册功能
     *
     * @param user 用户实体类
     * @return 返回的结果 msg
     */
    @PostMapping("addUser")
    public Result1 addUser(@RequestBody User user) {
        boolean statue = userService.save(user);
        if (statue) {
            return Result1.success().setMessage("注册成功");
        } else {
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
    public Result1 findUserById(String id) {
        User user = userService.getById(id);
        if (user != null) {
            return Result1.success().data("user", user);
        } else {
            return Result1.fail().setMessage("用户不存在");
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
        if (statue) {
            return Result1.success().setMessage("用户删除成功");
        } else {
            return Result1.fail().setMessage("用户删除失败");
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
     * 根据用户名模糊查询已删除用户
     *
     * @param username 用户名
     * @return 返回的结果 msg
     */
    @GetMapping("findUsersByUsernameIsDelete")
    public Result1 findUsersByUsernameIsDelete(String username) {
        List<User> userList = userService.findUsersByUsernameIsDelete(username);
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
        if (index == 1) {
            index -= 1;
        }
        Serializable userIPage = userService.pagingQuery(username, index, size);
        if (userIPage != null) {
            return Result1.success().data("userIPage", userIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 用户的分页查询(已删除的用户)
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String username, Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<User> userList = userService.pagingQueryIsDelete(username, index, size);
        if (userList.size() != 0) {
            return Result1.success().data("userList", userList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }


}

