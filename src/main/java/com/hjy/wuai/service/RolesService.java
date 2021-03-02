package com.hjy.wuai.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Roles;
import com.baomidou.mybatisplus.extension.service.IService;

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
     * 分页查询
     *
     * @param index 索引号
     * @return 返回的结果
     */
    IPage<Roles> pagingQuery(Integer index);

}
