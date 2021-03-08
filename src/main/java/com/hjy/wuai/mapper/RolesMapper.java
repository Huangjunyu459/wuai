package com.hjy.wuai.mapper;

import com.hjy.wuai.pojo.Roles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hjy.wuai.pojo.User;
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
    Set<String> queryRoleNamesByUserId(String id);

    /**
     * 根据id查找所有的角色
     *
     * @param id 用户名
     * @return 返回的结果
     */
    @Select("SELECT * FROM roles WHERE role_id = #{id} ")
    List<Roles> findRolesByRoleNameAll(Integer id);


    /**
     * 查询已删除的角色
     *
     * @return 返回已被删除的角色集合
     */
    @Select("select * from roles where is_delete = 1")
    List<Roles> findIsDelete();

    /**
     * 根据角色名模糊查询角色
     *
     * @param roleName 角色名
     * @return 返回的结果
     */
    @Select("SELECT * FROM role WHERE  role_name LIKE #{roleName} ")
    List<Roles> findRolesByRoleName(String roleName);

    /**
     * 分页查询已删除的用户
     *
     * @param index
     * @param size
     * @param  roleName
     * @return
     */
    @Select("select * from roles where is_delete = 1 AND role_name like #{roleName} limit #{index} , #{size}")
    List<Roles> pagingQueryIsDelete(String roleName,Integer index, Integer size);

    /**
     * 根据名字模糊查找已删除的用户
     *
     * @param roleName 角色名
     * @return 返回的结果
     */
    @Select("SELECT * FROM roles WHERE is_delete = 1 AND role_name LIKE #{roleName} ")
    List<Roles> findUsersByUsernameIsDelete(String roleName);


}
