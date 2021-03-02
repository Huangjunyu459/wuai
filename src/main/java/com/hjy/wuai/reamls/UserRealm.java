package com.hjy.wuai.reamls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.mapper.RolesMapper;
import com.hjy.wuai.mapper.UserMapper;
import com.hjy.wuai.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.Set;

/**
 * @author hjy
 * @date 2021/2/20 0020,上午 11:22
 * @email 541605007@qq.com
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    /**
     * 注入 userMapper
     */
    @Autowired
    private UserMapper userMapper;

    /**
     * 注入 rolesMapper
     */
    @Autowired
    private RolesMapper rolesMapper;


    /**
     * 重写 getName 方法，获取当前 Realm 的自定义名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "UserRealm";
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
        System.out.println(username);

        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", username));

        //  根据用户名查询当前用户的角色列表
        Set<String> roles = rolesMapper.queryRoleNamesByUserId(user.getId());
        Iterator<String> iterator = roles.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        //  逻辑代码  ps
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(roles);
        return info;


        //  根据用户名查询当前用户的角色列表
        //rolesMapper.queryRoleNamesByUserId()
        //  逻辑代码 roleNames
        //  根据用户名查询当前用户的权限列表
        //  逻辑代码  ps

/**
 * SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
 * info.setRoles(roleNames);
 * info.setStringPermissions(ps);
 * return info;
 * */
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
        log.info("--------------------userRealm");
        //  参数 authenticationToken 就是传递的 subject.login(token) 中的 token
        MyToken token = (MyToken) authenticationToken;

        //  获取用户的邮箱，匹配数据库
        String email = token.getEmail();

        //  根据用户名，从数据库查询当前用户的安全数据
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", email);
        User user = userMapper.selectOne(wrapper);

        /**
         * 参数
         * username：当前用户的用户名
         * token.getPassword()：从数据库中查询出来的安全密码
         * ByteSource.Util.bytes(user.getPasswordSalt())：加盐的值，若不加，则可以不写
         * getName()：哪个 realm 返回的数据
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(),
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                getName());

        return info;
    }
}
