package com.hjy.wuai.service;

import com.hjy.wuai.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
public interface UserService extends IService<User> {

    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     */
    void checkLogin(String username, String password, String email);


    /**
     * 根据用户名 模糊查询
     *
     * @param username
     * @return
     */
    List<User> list(String username);

    /**
     * 会员充值功能
     *
     * @param id
     * @return
     */
    boolean recharge(Long id);

}
