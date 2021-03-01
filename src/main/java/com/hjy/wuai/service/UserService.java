package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.NameAndEmail;
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
    List<User> findAllUser();

    /**
     * 根据用户名精准查询
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);


    /**
     * 根据用户名 模糊查询
     *
     * @param username 用户名
     * @return
     */
    List<User> findUsersByUsername(String username);

    /**
     * 会员充值功能
     *
     * @param id 用户 id
     * @return
     */
    boolean recharge(Long id);

    /**
     * 会员逾期功能（把用户的会员等级设置为普通用户）
     *
     * @param id
     * @return
     */
    boolean overdue(Long id);


    /**
     * 用户签到功能
     *
     * @param id 用户 id
     * @return
     */
    boolean signIn(Long id);

    /**
     * 重置所有用户签到状态（每天凌晨12点后触发）
     *
     * @return 结果
     */
    boolean resetSignIn();

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    User findUserByEmail(String email);


    /**
     * 根据 组合条件 查询
     *
     * @param entity
     * @return
     */
    List<User> findByMap(NameAndEmail entity);


    /**
     * 查询已删除的用户
     *
     * @return
     */
    List<User> findIsDelete();


    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    IPage<User> pagingQuery(Integer index);
}
