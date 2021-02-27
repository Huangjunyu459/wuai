package com.hjy.wuai.service.impl;

import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.pojo.Admin;
import com.hjy.wuai.mapper.AdminMapper;
import com.hjy.wuai.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-20
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {


//    public void checkLogin(String username, String password, String loginType) {
//
//        Subject subject = SecurityUtils.getSubject();
//        /**
//         * 弃用，不能满足需求
//         * UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
//         */
//        MyToken token = new MyToken(username, password, loginType,null);
//
//        subject.login(token);
//
//    }

}
