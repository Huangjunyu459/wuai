package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Roles;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hjy.wuai.pojo.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
public interface RolesService extends IService<Roles> {

    /**
     * 查询所有作品
     *
     * @return 返回的结果
     */
    List<Roles> findAllRoles();


    /**
     * 查询已删除的作品
     *
     * @return 返回的结果
     */
    List<Roles> findIsDelete();

    /**
     * 根据 角色名模糊查询
     *
     * @param roleName 角色名
     * @return 返回的结果
     */
    List<Roles> findRolesByRoleName(String roleName);

    /**
     * 根据名字模糊查找所有的角色
     *
     * @param id 角色 id
     * @return 返回的结果
     */
    List<Roles> findRolesByRoleNameAll(Integer id);

    /**
     * 根据用户名 模糊查询（已删除的用户）
     *
     * @param roleName 角色名
     * @return 返回的结果
     */
    List<Roles> findRoleByRoleNameIsDelete(String roleName);

    /**
     * 分页查询
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    IPage<Roles> pagingQuery(String roleName,Integer index, Integer size);

    /**
     * 分页查询（已删除的角色）
     *
     * @param index 索引号
     * @param size  页数大小
     * @return 返回的结果
     */
    List<Roles> pagingQueryIsDelete(String roleName,Integer index, Integer size);

}
