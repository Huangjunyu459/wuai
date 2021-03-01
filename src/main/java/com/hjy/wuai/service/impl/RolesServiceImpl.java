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
 *  服务实现类
 * </p>
 *
 * @author hjy
 * @since 2021-02-21
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    @Autowired
    private RolesMapper rolesMapper;


    /**
     * 上传
     *
     * @param entity
     * @return
     */
    @Override
    public boolean save(Roles entity) {
        return rolesMapper.insert(entity) == 1 ? true : false;
    }


    /**
     * 根据 id 获取游戏
     *
     * @param id
     * @return
     */
    @Override
    public Roles getById(Serializable id) {
        return rolesMapper.selectById(id);
    }

    /**
     * 游戏更新（有待完善，具体要更新哪些信息）
     *
     * @param entity 游戏实体
     * @return
     */
    @Override
    public boolean updateById(Roles entity) {

        //  获取要更新的用户
        Roles roles = getById(entity.getId());
        roles.setRoleId(entity.getRoleId());
        roles.setRoleName(entity.getRoleName());
        return rolesMapper.updateById(roles) == 1 ? true : false;
    }

    /**
     * 根据 id 删除
     *
     * @param id 用户 id
     * @return
     */
    @Override
    public boolean removeById(Serializable id) {
        return rolesMapper.deleteById(id) == 1 ? true : false;
    }


    /**
     * 查询所有游戏
     *
     * @return
     */
    @Override
    public List<Roles> findAllRoles() {
        return rolesMapper.selectList(null);
    }

    /**
     * 查询已删除的角色
     *
     * @return
     */
    @Override
    public List<Roles> findIsDelete() {
        return rolesMapper.findIsDelete();
    }

    /**
     * 分页查询
     *
     * @param index
     * @return
     */
    @Override
    public IPage<Roles> pagingQuery(Integer index) {
        IPage<Roles> page = new Page<>(index, 5);
        IPage<Roles> rolesIPage = rolesMapper.selectPage(page, null);
        return rolesIPage;
    }
}
