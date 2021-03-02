package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjy
 * @date 2021/3/1 0001,下午 22:26
 * @email 541605007@qq.com
 * <p>
 * 此 controller 用来 处理用户 的会员 过期设置操作和 重置签到状态
 */
@RestController
@RequestMapping("/timedtask")
public class TimedTaskController {

    /**
     * 注入 userService
     */
    @Autowired
    private UserServiceImpl userService;


    /**
     * 重置签到状态
     *
     * @return 返回的结果
     */
    @GetMapping("resetSignIn")
    public Result1 resetSignIn() {
        boolean statue = userService.resetSignIn();
        if (statue) {
            return Result1.success().setMessage("重置签到状态成功");
        } else {
            return Result1.fail().setMessage("重置签到状态失败");
        }
    }


    /**
     * 会员逾期重置等级
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @GetMapping("overdue")
    public Result1 overdue(Long id) {
        boolean statue = userService.overdue(id);
        if (statue) {
            return Result1.success().setMessage("重置用户为普通用户成功");
        } else {
            return Result1.fail().setMessage("重置用户为普通用户失败");
        }
    }


    /**
     * 注册时自动调用该方法检查是否存在同名的用户
     * <p>
     * statue 用于返回给前端进行判断，展示对应的 message
     *
     * @param username 用户名
     * @return 返回的结果
     */
    @GetMapping("checkUserExist")
    public Result1 checkUserExist(String username) {
        User user = userService.findUserByUsername(username);
        if (user == null) {
            return Result1.success().setMessage("用户名可注册").data("statue", true);
        } else {
            return Result1.fail().setMessage("用户名已存在").data("statue", false);
        }
    }
}
