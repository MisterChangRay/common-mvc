package com.github.misterchangray.controller.user;

import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.common.annotation.Authorization;
import com.github.misterchangray.dao.entity.Permission;
import com.github.misterchangray.service.user.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 权限管理控制器
 *
 * 提供用户权限以下用能
 * -权限列表
 * -权限新增
 * -权限编辑
 * -权限删除
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  3/23/2018.
 */
@Api(tags ="权限控制", description = "PermissionController")
@Controller
@RequestMapping("/v1/permission")
public class PermissionController {
    @Autowired
    PermissionService permissionService;


    /**
     * 根据ID获取权限
     * @param permissionId
     * @return
     */
    @ApiOperation(value = "根据ID获取权限", notes = "根据ID获取权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name="permissionId", value = "权限ID", required = true, paramType = "path", dataType = "int"),
    })
    @Authorization
    @RequestMapping(value="/{permissionId}", method = RequestMethod.GET)
    @ResponseBody
    public ResultSet getById(@PathVariable Integer permissionId) {
        ResultSet res = permissionService.getById(permissionId);
        return res;
    }


    /**
     * 权限列表
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value = "权限列表", notes = "获取权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page", value = "页码", required = true, paramType = "query", dataType = "int"),
            @ApiImplicitParam(name="limit", value = "每页条数", required = true, paramType = "query", dataType = "int"),
    })
    @Authorization
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResultSet list(@RequestParam() Integer page, @RequestParam() Integer limit) {
        Permission permission = new Permission();
        return permissionService.list(permission, PageInfo.newInstance(page, limit));
    }

    /**
     * 新增权限
     * @param permission
     * @return
     */
    @ApiOperation(value = "新增权限", notes = "新增权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name="permission", value = "权限实体JSON对象", required = true, paramType = "body", dataType = "com.github.misterchangray.dao.entity.Permission"),
    })
    @Authorization
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResultSet add(@RequestBody Permission permission) {
       return permissionService.save(permission);
    }


    /**
     * 删除权限
     * @param id
     * @return
     */
    @ApiOperation(value = "删除权限", notes = "根据ID删除权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value = "权限ID", required = true, paramType = "path", dataType = "int"),
    })
    @Authorization
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResultSet delete(@PathVariable Integer id) {
        Permission permission = new Permission();
        permission.setId(id);
        return permissionService.delete(permission);
    }


    /**
     * 编辑权限
     * @param permission
     * @return
     */
    @ApiOperation(value = "编辑权限", notes = "根据ID编辑权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name="permission", value = "权限实体JSON对象", required = true, paramType = "body", dataType = "com.github.misterchangray.dao.entity.Permission"),
    })
    @Authorization
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public ResultSet edit(@RequestBody Permission permission) {
        return permissionService.edit(permission);
    }

}
