package com.hjy.wuai.controller;

import com.hjy.wuai.pojo.Result1;
import com.hjy.wuai.pojo.Roles;
import com.hjy.wuai.pojo.User;
import com.hjy.wuai.service.impl.RolesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author： hjy
 * @date： 2021/3/1 0001,上午 9:37
 * @email: 541605007@qq.com
 */
@RestController
@RequestMapping("/roles")
@CrossOrigin
public class RolesController {

    @Autowired
    private RolesServiceImpl rolesService;

    /**
     * 新建角色
     *
     * @param roles 视频实体类
     * @return 返回上传的结果 msg
     */
    @PostMapping("addRole")
    public Result1 addRole(@RequestBody Roles roles) {
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
    public Result1 getById(String id) {
        Roles role = rolesService.getById(id);
        if (role != null) {
            return Result1.success().data("role", role);
        } else {
            return Result1.fail().setMessage("角色不存在");
        }
    }

    /**
     * 根据 角色名模糊查询
     *
     * @param roleName 角色名
     * @return 返回的结果 msg
     */
    @GetMapping("findRoleByRoleName")
    public Result1 findRoleByRoleName(String roleName) {
        List<Roles> rolesList = rolesService.findRolesByRoleName(roleName);
        if (rolesList.size() != 0) {
            return Result1.success().data("rolesList", rolesList);
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
    @PostMapping("updateRoleById")
    public Result1 updateRoleById(@RequestBody Roles role) {
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
    @DeleteMapping("removeRoleById")
    public Result1 removeRoleById(String id) {
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
     * 根据角色模糊查询已删除角色
     *
     * @param roleName 角色名
     * @return 返回的结果 msg
     */
    @GetMapping("findRoleByRoleNameIsDelete")
    public Result1 findRoleByRoleNameIsDelete(String roleName) {
        List<Roles> rolesList = rolesService.findRoleByRoleNameIsDelete(roleName);
        if (rolesList.size() == 0) {
            return Result1.fail().setMessage("角色不存在");
        } else {
            return Result1.success().data("rolesList", rolesList);
        }
    }

    /**
     * 角色的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQuery")
    public Result1 pagingQuery(String roleName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        Serializable rolesIPage = rolesService.pagingQuery(roleName,index, size);
        if (rolesIPage != null) {
            return Result1.success().data("rolesIPage", rolesIPage);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }

    /**
     * 角色的分页查询
     *
     * @return 返回的结果 msg
     */
    @GetMapping("pagingQueryIsDelete")
    public Result1 pagingQueryIsDelete(String roleName,Integer index, Integer size) {
        if (index == 1) {
            index -= 1;
        }
        List<Roles> rolesList = rolesService.pagingQueryIsDelete(roleName,index, size);
        if (rolesList.size() != 0) {
            return Result1.success().data("rolesList", rolesList);
        } else {
            return Result1.fail().setMessage("没有数据");
        }
    }
}
