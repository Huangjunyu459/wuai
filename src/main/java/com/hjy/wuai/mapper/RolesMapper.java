package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
@Repository
public interface RolesMapper extends BaseMapper<Roles> {

    /**
     * 根据用户 id 查询对应的角色
     *
     * @param id 用户id
     * @return 返回 该用户的 角色集合
     */
    @Select("SELECT role_name " +
            "FROM USER INNER JOIN urs " +
            "ON user.state = urs.u_state INNER JOIN roles " +
            "ON urs.rid = roles.role_id " +
            "WHERE user.id=#{id}")
    Set<String> queryRoleNamesByUserId(Long id);


    /**
     * 查询已删除的角色
     *
     * @return 返回已被删除的角色集合
     */
    @Select("select * from roles where is_delete = 1")
    List<Roles> findIsDelete();

}
