package com.hjy.wuai.controller;


import com.hjy.wuai.service.impl.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @RequestMapping("login")
    public String login(String adminName, String password) {
        return adminService.login(adminName, password) == true ? "admin" : "fail";
    }

}

