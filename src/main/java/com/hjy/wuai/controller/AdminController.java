package com.hjy.wuai.controller;


import com.hjy.wuai.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class AdminController {

    /**
     * 注入 adminService
     */
    @Autowired
    private AdminServiceImpl adminService;


    /**
     * 管理员登录验证
     *
     * @param adminName 管理员名称
     * @param password  密码
     * @return 返回的结果
     */
    @RequestMapping("login")
    public String login(String adminName, String password) {
        return adminService.login(adminName, password) == true ? "admin" : "fail";
    }

}

