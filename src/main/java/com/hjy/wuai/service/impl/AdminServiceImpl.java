package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.pojo.Admin;
import com.hjy.wuai.mapper.AdminMapper;
import com.hjy.wuai.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * md5 加密
     *
     * @param password
     * @return
     */
    public String md5(String password) {
        Md5Hash md5Hash = new Md5Hash(password);
        return md5Hash.toHex();
    }


    /**
     * 管理员登录
     *
     * @param adminName
     * @param password
     * @return
     */
    @Override
    public boolean login(String adminName, String password) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name", adminName);
        Admin admin = adminMapper.selectOne(wrapper);
        return (Objects.equals(md5(password), admin.getPassword()));
    }


}
