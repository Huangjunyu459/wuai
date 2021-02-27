package com.hjy.wuai.service;

import com.hjy.wuai.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
public interface AdminService extends IService<Admin> {

    public boolean login(String adminName,String password);

}
