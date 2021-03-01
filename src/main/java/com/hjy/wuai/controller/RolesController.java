package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.service.impl.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/3/1 0001,上午 9:37
 * @email: 541605007@qq.com
 */
@RestController
@RequestMapping("roles")
public class RolesController {

    @Autowired
    private RolesServiceImpl rolesService;

    /**
     * 新建角色
     *
     * @param roles 视频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("/save")
    public Result1 save(Roles roles) {
        if (rolesService.save(roles)) {
            return Result1.success().setMessage("新建角色成功");
        } else {
            return Result1.fail().setMessage("新建角色失败");
        }
    }


    /**
     * 根据 id 获取角色
     *
     * @param id 视频 id
     * @return 返回的结果 msg
     */
    @GetMapping("getById")
    public Result1 getById(Long id) {
        Roles role = rolesService.getById(id);
        if (role != null) {
            return Result1.success().data("role", role);
        } else {
            return Result1.fail().setMessage("角色不存在");
        }
    }

    /**
     * 更新角色信息
     *
     * @param role 角色实体类
     * @return 返回的结果 msg
     */
    @PostMapping("updateById")
    public Result1 updateById(Roles role) {
        if (rolesService.updateById(role)) {
            return Result1.success().setMessage("更新成功");
        } else {
            return Result1.fail().setMessage("更新失败");
        }
    }

    /**
     * 根据 id 删除角色
     *
     * @param id 角色的id
     * @return 返回的结果 msg
     */
    @GetMapping("removeById")
    public Result1 removeById(Long id) {
        if (rolesService.removeById(id)) {
            return Result1.success().setMessage("删除成功");
        } else {
            return Result1.fail().setMessage("删除失败");
        }
    }

    /**
     * 查找所有角色
     *
     * @return 返回的结果 msg
     */
    @GetMapping("/findAllRoles")
    public Result1 findAllRoles() {
        List<Roles> rolesList = rolesService.findAllRoles();
        if (rolesList.size() != 0) {
            return Result1.success().data("rolesList", rolesList);
        } else {
            return Result1.fail().setMessage("角色不存在");
        }
    }

    /**
     * 查询已删除的角色
     *
     * @return 返回的结果 msg
     */
    @GetMapping("findIsDelete")
    public Result1 findIsDelete() {
        List<Roles> rolesList = rolesService.findIsDelete();
        if (rolesList.size() != 0) {
            return Result1.success().data("rolesList", rolesList);
        } else {
            return Result1.fail().setMessage("角色不存在");
        }
    }

    /**
     * 角色分页查询
     *
     * @param index 起始页
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(Integer index) {
        Serializable rolesIPage = rolesService.pagingQuery(index);
        if (rolesIPage != null) {
            return Result1.success().data("rolesIPage", rolesIPage);
        } else {
            return Result1.fail().setMessage("角色不存在");
        }
    }
}
