package com.hjy.wuai.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hjy
 * @date 2021/2/19 0019,上午 11:50
 * @email 541605007@qq.com
 * 页面跳转测试 controller
 */
@Deprecated
@RestController
public class PageController {

    @RequestMapping("/{page}")
    public String page(@PathVariable String page) {
        return page;

    }

    @RequestMapping("/login.html")
    public String login() {
        return "login";
    }

    @RequestMapping("/")
    public String login1() {
        return "login";
    }

    @RequestMapping("/index.html")
    public String index() {
        return "index";
    }

    @RequestMapping("/regist.html")
    public String regist() {
        return "regist";
    }

    @RequestMapping("/admin/login.html")
    public String adminLogin() {
        return "admin/login";
    }
}
