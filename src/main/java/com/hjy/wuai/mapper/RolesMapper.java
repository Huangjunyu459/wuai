package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
@Repository
public interface RolesMapper extends BaseMapper<Roles> {

    /**
     * 根据用户 id 查询对应的权限
     * @param id
     * @return
     */
    @Select("SELECT role_name " +
            "FROM USER INNER JOIN urs " +
            "ON user.state = urs.u_state INNER JOIN roles " +
            "ON urs.rid = roles.role_id " +
            "WHERE user.id=#{id}")
     Set<String> queryRoleNamesByUserId(Long id);

}
