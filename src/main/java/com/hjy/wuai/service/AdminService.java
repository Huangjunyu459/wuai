package com.hjy.wuai.service;

import com.hjy.wuai.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
public interface AdminService extends IService<Admin> {

    /**
     * 管理员登录
     *
     * @param adminName 管理员名
     * @param password  密码
     * @return 返回的结果
     */
    boolean login(String adminName, String password);

}
