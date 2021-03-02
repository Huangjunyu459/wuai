package com.hjy.wuai.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hjy.wuai.pojo.Game;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.mapper.RolesMapper;
import com.hjy.wuai.service.RolesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    /**
     * 注入 rolesMapper
     */
    @Autowired
    private RolesMapper rolesMapper;


    /**
     * 新建角色
     *
     * @param entity 角色实体
     * @return
     */
    @Override
    public boolean save(Roles entity) {
        return rolesMapper.insert(entity) == 1;
    }


    /**
     * 根据 id 获取角色
     *
     * @param id 角色 id
     * @return 返回的结果
     */
    @Override
    public Roles getById(Serializable id) {
        return rolesMapper.selectById(id);
    }


    /**
     * 角色更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 角色实体
     * @return 返回的结果
     */
    @Override
    public boolean updateById(Roles entity) {

        //  获取要更新的角色
        Roles roles = getById(entity.getId());
        roles.setRoleId(entity.getRoleId());
        roles.setRoleName(entity.getRoleName());
        return rolesMapper.updateById(roles) == 1;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return 返回的结果
     */
    @Override
    public boolean removeById(Serializable id) {
        return rolesMapper.deleteById(id) == 1;
    }


    /**
     * 查询所有游戏
     *
     * @return 返回的结果
     */
    @Override
    public List<Roles> findAllRoles() {
        return rolesMapper.selectList(null);
    }


    /**
     * 查询已删除的角色
     *
     * @return 返回的结果
     */
    @Override
    public List<Roles> findIsDelete() {
        return rolesMapper.findIsDelete();
    }


    /**
     * 分页查询
     *
     * @param index 索引页
     * @return 返回的结果
     */
    @Override
    public IPage<Roles> pagingQuery(Integer index) {
        IPage<Roles> page = new Page<>(index, 5);
        IPage<Roles> rolesIPage = rolesMapper.selectPage(page, null);
        return rolesIPage;
    }

}
