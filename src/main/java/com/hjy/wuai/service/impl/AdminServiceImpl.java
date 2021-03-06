package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.pojo.Admin;
import com.hjy.wuai.mapper.AdminMapper;
import com.hjy.wuai.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    /**
     * 注入 adminMapper
     */
    @Autowired
    private AdminMapper adminMapper;

    /**
     * md5 加密
     *
     * @param password 密码
     * @return 返回经过加密后的密码
     */
    public String md5(String password) {
        Md5Hash md5Hash = new Md5Hash(password);
        return md5Hash.toHex();
    }


    /**
     * 管理员登录
     *
     * @param adminName 管理员名称
     * @param password  密码
     * @return 返回登录结果
     */
    @Override
    public boolean login(String adminName, String password) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name", adminName);
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            return false;
        } else {
            return (Objects.equals(md5(password), admin.getPassword()));
        }

    }

}
