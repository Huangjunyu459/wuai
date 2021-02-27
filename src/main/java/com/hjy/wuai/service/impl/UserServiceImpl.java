package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-18
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录功能
     *
     * @param username
     * @param password
     */
    public void checkLogin(String username, String password,  String email) {

        Subject subject = SecurityUtils.getSubject();
        /**
         * 弃用，不能满足需求
         * UsernamePasswordToken token = new UsernamePasswordToken(name, pwd);
         */
        MyToken token = new MyToken(username, password, email);

        //token.setRememberMe(rememberMe);
        subject.login(token);

    }

    /**
     * 密码加盐算法
     * @return
     */
    public static List<String> passwordAndSalt(String password){
        List<String> list = new ArrayList<>();
        //  随机生成一个 10000-99999 的盐
        String salt = (new Random().nextInt(90000) + 10000) + "";
        list.add(salt);
        //  加密加盐
        Md5Hash md5Hash = new Md5Hash(password, salt);
        list.add(md5Hash.toHex());
        return list;
    }

    /**
     * 用户注册功能
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(User entity) {

        //  获取 hash 后的密码和盐的集合
        List<String> list = passwordAndSalt(entity.getPassword());
        entity.setPasswordSalt(list.get(0));
        //  把加密后的密码重新设置给用户
        entity.setPassword(list.get(1));

        log.info(String.valueOf(entity.getId()));
        log.info("service进来注册了");

        if (userMapper.insert(entity) == 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据 id 获取用户
     *
     * @param id
     * @return
     */
    @Override
    public User getById(Serializable id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return userMapper.selectOne(wrapper);
    }

    /**
     * 用户更新信息
     *
     * @param entity
     * @return
     */
    @Override
    public boolean updateById(User entity) {

        List<String> list = passwordAndSalt(entity.getPassword());

        //  获取要更新的永华
        User user = getById(entity.getId());
        user.setUsername(entity.getUsername());

        user.setPassword(list.get(1));
        user.setPasswordSalt(list.get(0));
        if (userMapper.updateById(user)==1){
            return true;
        }else {
            return false;
        }
    }
}
