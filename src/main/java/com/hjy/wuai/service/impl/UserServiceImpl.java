package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.pojo.NameAndEmail;
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
import java.util.*;

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

    /**
     * 注入 userMapper
     */
    @Autowired
    private UserMapper userMapper;


    /**
     * 用户登录功能
     *
     * @param username 用户名
     * @param password 密码
     * @param email    邮箱
     */
    @Override
    public void checkLogin(String username, String password, String email) {
        Subject subject = SecurityUtils.getSubject();
        MyToken token = new MyToken(username, password, email);
        subject.login(token);
    }


    /**
     * 密码加盐算法
     *
     * @return
     */
    public static List<String> passwordAndSalt(String password) {
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
     * @param entity 用户实体
     * @return 返回的结果
     */
    @Override
    public boolean save(User entity) {
        System.out.println("进入了service层");
        //  获取 hash 后的密码和盐的集合
        List<String> list = passwordAndSalt(entity.getPassword());
        entity.setPasswordSalt(list.get(0));
        //  把加密后的密码重新设置给用户
        entity.setPassword(list.get(1));
        return userMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取用户
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public User getById(Serializable id) {
        return userMapper.selectById(id);
    }


    /**
     * 用户更新信息（有待完善，具体要更新哪些信息）
     *
     * @param entity 用户实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(User entity) {

        List<String> list = passwordAndSalt(entity.getPassword());

        //  获取要更新的用户
        User user = getById(entity.getId());
        user.setUsername(entity.getUsername());

        user.setPassword(list.get(1));
        user.setPasswordSalt(list.get(0));
        return userMapper.updateById(user) == 1;
    }


    /**
     * 根据 id 删除用户
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return userMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有用户
     *
     * @return 返回的结果
     */
    @Override
    public List<User> findAllUser() {
        return userMapper.selectList(null);
    }


    /**
     * 根据用户名 精准查询
     *
     * @param username 用户名
     * @return 返回的结果
     */
    @Override
    public User findUserByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        return userMapper.selectOne(wrapper);
    }


    /**
     * 根据用户名 模糊查询
     *
     * @param username 用户名
     * @return 返回的结果
     */
    @Override
    public List<User> findUsersByUsername(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username", username);
        return userMapper.selectList(wrapper);
    }


    /**
     * 会员充值功能
     * 难点：比如充值一个月会员，把 state 设为 1，如何在一个月之后自动把 state 设为 0？
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Override
    public boolean recharge(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return false;
        } else {
            user.setState(2);
            userMapper.updateById(user);
            return true;
        }
    }


    /**
     * 会员逾期功能
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Override
    public boolean overdue(Long id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return false;
        } else {
            user.setState(1);
            userMapper.updateById(user);
            return true;
        }
    }


    /**
     * 用户签到功能，每次签到增加 5 积分（如何每次过12点就刷新签到功能）
     *
     * @param id 用户id
     * @return 返回的结果
     */
    @Override
    public boolean signIn(Long id) {
        User user = userMapper.selectById(id);
        user.setScore(user.getScore() + 5);
        user.setQiandao(1);
        return userMapper.updateById(user) == 1;
    }

    /**
     * 扣除积分功能
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean deductScore(Long id) {
        User user = userMapper.selectById(id);
        user.setScore(user.getScore() - 5);
        return userMapper.updateById(user) == 1;
    }

    /**
     * 资源下载方法
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean download(Long id) {
        User user = userMapper.selectById(id);
        if (user.getScore() >= 5) {
            return deductScore(id);
        } else {
            return false;
        }
    }

    /**
     * 重置所有用户签到状态（每天凌晨12点后触发）
     *
     * @return
     */
    @Override
    public boolean resetSignIn() {
        User user = new User();
        user.setQiandao(0);
        try {
            userMapper.update(user, null);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 返回的结果
     */
    @Override
    public User findUserByEmail(String email) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        return userMapper.selectOne(wrapper);
    }


    /**
     * 根据 组合条件 查询
     *
     * @param entity 组合条件的实体类
     * @return 返回的结果
     */
    @Override
    public List<User> findByMap(NameAndEmail entity) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", entity.getUsername());
        map.put("email", entity.getEmail());
        if (entity.getEmail() == null && entity.getUsername() == null) {
            return findAllUser();
        } else if (entity.getUsername() == null && entity.getEmail() != null) {
            return Arrays.asList(findUserByEmail(entity.getEmail()));
        } else if (entity.getUsername() != null && entity.getEmail() == null) {
            return findUsersByUsername(entity.getUsername());
        } else {
            return userMapper.selectByMap(map);
        }
    }


    /**
     * 查询已删除的用户
     *
     * @return 返回的结果
     */
    @Override
    public List<User> findIsDelete() {
        return userMapper.findIsDelete();
    }


    /**
     * 分页查询
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<User> pagingQuery(Integer index) {
        IPage<User> page = new Page<>(index, 5);
        IPage<User> userIPage = userMapper.selectPage(page, null);
        return userIPage;
    }

}
