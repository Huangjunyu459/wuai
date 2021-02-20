package com.hjy.wuai.reamls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.pojo.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author： hjy
 * @date： 2021/2/19 0019,下午 15:58
 * @email: 541605007@qq.com
 * <p>
 * 自定义 MyRealm 类，继承 AuthorizingRealm（实现了 Realm 接口）
 */
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    /**
     * 重写 getName 方法，获取当前 Realm 的自定义名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 获取授权数据（将当前用户的角色及权限信息查询出来）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //  获取当前用户的用户名
        String username = (String) principalCollection.iterator().next();
        //  根据用户名查询当前用户的角色列表
        //  逻辑代码 roleNames
        //  根据用户名查询当前用户的权限列表
        //  逻辑代码  ps

/**
 * SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
 * info.setRoles(roleNames);
 * info.setStringPermissions(ps);
 * return info;
 * */
        return null;
    }

    /**
     * 获取认证的安全数据（从数据库查询的用户的正确数据）
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //  参数 authenticationToken 就是传递的 subject.login(token) 中的 token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //  从 token 中获取需要登录的用户名
        String username = token.getUsername();

        //  根据用户名，从数据库查询当前用户的安全数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        User user = userMapper.selectOne(wrapper);

        /**
         * 参数
         * username：当前用户的用户名
         * token.getPassword()：从数据库中查询出来的安全密码
         * ByteSource.Util.bytes(user.getPasswordSalt())：加盐的值，若不加，则可以不写
         * getName()：哪个 realm 返回的数据
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username,
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                getName());

        return info;
    }
}
