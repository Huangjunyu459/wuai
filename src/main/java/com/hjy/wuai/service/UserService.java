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
     * 找回密码
     * @param email 邮箱
     * @param password 新密码
     * @return 返回的结果
     */
    public boolean findPS(String email, String password);


    /**
     * 查询所有用户
     *
     * @return 返回的结果
     */
    List<User> findAllUser();

    /**
     * 根据用户名精准查询
     *
     * @param username 用户名
     * @return 返回的结果
     */
    User findUserByUsername(String username);


    /**
     * 根据用户名 模糊查询
     *
     * @param username 用户名
     * @return 返回的结果
     */
    List<User> findUsersByUsername(String username);


    /**
     * 根据用户名 模糊查询（已删除的用户）
     *
     * @param username 用户名
     * @return 返回的结果
     */
    List<User> findUsersByUsernameIsDelete(String username);

    /**
     * 根据名字模糊查找所有的用户
     *
     * @param username 用户名
     * @return 返回的结果
     */
    List<User> findUsersByUsernameAll(String username);

    /**
     * 会员充值功能
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    boolean recharge(String id);

    /**
     * 会员逾期功能（把用户的会员等级设置为普通用户）
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    boolean overdue(String id);


    /**
     * 用户签到功能
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    boolean signIn(String id);

    /**
     * 下载资源时，扣除用户相应的积分
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    boolean deductScore(String id);

    /**
     * 下载资源功能
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    boolean download(String id);

    /**
     * 重置所有用户签到状态（每天凌晨12点后触发）
     *
     * @return 返回的结果
     */
    boolean resetSignIn();

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 返回的结果
     */
    User findUserByEmail(String email);


    /**
     * 根据 组合条件 查询
     *
     * @param entity 组合条件实体列
     * @return 返回的结果
     */
    List<User> findByMap(NameAndEmail entity);


    /**
     * 查询已删除的用户
     *
     * @return 返回的结果
     */
    List<User> findIsDelete();


    /**
     * 分页查询
     *
     * @param index    索引号
     * @param size     页数大小
     * @param username
     * @return 返回的结果
     */
    IPage<User> pagingQuery(String username, Integer index, Integer size);

    /**
     * 分页查询（已删除的用户）
     *
     * @param index    索引号
     * @param size     页数大小
     * @param username
     * @return 返回的结果
     */
    List<User> pagingQueryIsDelete(String username, Integer index, Integer size);

}
