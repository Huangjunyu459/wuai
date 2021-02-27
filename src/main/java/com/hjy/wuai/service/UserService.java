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
     * 查询所有用户
     *
     * @return
     */
    public List<User> findAllUser();


    /**
     * 根据用户名 模糊查询
     *
     * @param username 用户名
     * @return
     */
    List<User> findUserByUsername(String username);

    /**
     * 会员充值功能
     *
     * @param id 用户 id
     * @return
     */
    boolean recharge(Long id);


    /**
     * 用户签到功能
     *
     * @param id 用户 id
     * @return
     */
    public boolean signIn(Long id);

    /**
     * 根据传入的 Object ，判断为 Integer 还是 String ，再调用对应的方法
     *
     * @param idOrUsername 传入的参数
     * @return
     */
    public List<User> findUserByIdOrUsername(Object idOrUsername);

}
