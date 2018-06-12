package com.github.misterchangray.controller.user;

import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.service.user.PermissionService;
import com.github.misterchangray.service.user.RoleService;
import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.dao.entity.Role;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色管理控制器
 *
 * 提供用户角色以下用能
 * -角色列表
 * -角色新增
 * -角色编辑
 * -角色启用/停用
 * -角色删除
 * -角色权限编辑
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  3/23/2018.
 */
@Api(tags ="角色管理", description = "RoleController")
@Controller
@RequestMapping("/v1/role")
public class RoleController {
    @Autowired
    PermissionService permissionService;
    @Autowired
    RoleService roleService;

    /**
     * 编辑角色权限
     * @param roleId
     * @return
     */
    @ApiOperation(value = "编辑角色权限", notes = "编辑角色下的权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name="roleId", value = "角色ID", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name="permissionIds", value = "权限ID", required = true, paramType = "form", dataType = "int"),
    })
    @Authentication
    @RequestMapping(value="/{roleId}/permission", method = RequestMethod.PATCH)
    @ResponseBody
    public ResultSet editRolePermission(@PathVariable Integer roleId, @RequestParam List permissionIds) {
        ResultSet resultSet = ResultSet.build();
        if(null != roleId && null != permissionIds && 0 < permissionIds.size()) {
            return roleService.updatePermission(roleId, permissionIds);
        }
        return resultSet.setCode(ResultEnum.INVALID_REQUEST);
    }


    /**
     * 获取角色列表
     * @param limit
     * @return
     */
    @ApiOperation(value = "获取角色列表", notes = "获取角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name="limit", value = "每页条数", required = true, paramType = "query", dataType = "int"),
    })
    @Authentication
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResultSet list(@RequestParam Integer page, @RequestParam Integer limit) {
        Role role = new Role();
        return roleService.list(role, PageInfo.newInstance(page, limit));
    }

    /**
     * 新增角色
     * @param role
     * @return
     */
    @ApiOperation(value = "新增角色", notes = "新增角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role", value = "角色实体对象", required = true, paramType = "body",  dataType = "com.github.misterchangray.dao.entity.Role"),
    })
    @Authentication
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResultSet add(@RequestBody Role role) {
       return roleService.save(role);
    }


    /**
     * 删除角色
     * @param id
     * @return
     */
    @ApiOperation(value = "删除角色", notes = "根据ID删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "角色ID", required = true, paramType = "path", dataType = "int"),
    })
    @Authentication
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultSet delete(@PathVariable("id") Integer id) {
        Role role = new Role();
        role.setId(id);
        return roleService.delete(role);
    }


    /**
     * 编辑角色
     * @param role
     * @return
     */
    @ApiOperation(value = "编辑角色", notes = "根据ID编辑角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name="role", value = "角色实体对象", required = true, paramType = "body", dataType = "com.github.misterchangray.dao.entity.Role"),
    })
    @Authentication
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResultSet edit(@RequestBody Role role) {
        return roleService.edit(role);
    }

}
