package com.hjy.wuai.reamls;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hjy.wuai.config.MyToken;
import com.hjy.wuai.mapper.AdminMapper;
import com.hjy.wuai.pojo.Admin;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hjy
 * @date 2021/2/20 0020,上午 11:22
 * @email 541605007@qq.com
 *
 * 自定义的 AdminRealm ，弃用
 */
@Deprecated
@Slf4j
public class AdminRealm extends AuthorizingRealm {

    /**
     * 注入 adminMapper
     */
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 重写 getName 方法，获取当前 Realm 的自定义名字
     *
     * @return
     */
    @Override
    public String getName() {
        return "AdminRealm";
    }

    /**
     * 获取授权数据（将当前用户的角色及权限信息查询出来）
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;

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
        log.info("-----------------adminRealm");
        //  参数 authenticationToken 就是传递的 subject.login(token) 中的 token
        MyToken token = (MyToken) authenticationToken;
        //  从 token 中获取需要登录的用户名
        String adminName = token.getUsername();

        //  根据用户名，从数据库查询当前用户的安全数据
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("admin_name", adminName);
        Admin admin = adminMapper.selectOne(wrapper);

        /**
         * 参数
         * username：当前用户的用户名
         * token.getPassword()：从数据库中查询出来的安全密码
         * ByteSource.Util.bytes(user.getPasswordSalt())：加盐的值，若不加，则可以不写
         * getName()：哪个 realm 返回的数据
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(adminName,
                admin.getPassword(),
                getName());
        return info;
    }
}
