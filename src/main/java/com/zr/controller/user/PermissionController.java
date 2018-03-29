package com.zr.controller.user;

import com.zr.common.ResultEnum;
import com.zr.common.NormalResponse;
import com.zr.common.annotation.Authentication;
import com.zr.dao.entity.Permission;
import com.zr.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Miste on 3/23/2018.
 * 权限管理控制器
 *
 * 提供用户权限以下用能
 * -权限列表
 * -权限新增
 * -权限编辑
 * -权限删除
 */
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
    @Authentication
    @RequestMapping(value="/{permissionId}", method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer permissionId) {
        NormalResponse res = permissionService.getById(permissionId);
        return res;
    }


    /**
     * 权限列表
     * @param page
     * @param limit
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse list(@RequestParam() Integer page, @RequestParam() Integer limit) {
        NormalResponse res = new NormalResponse();

        res.setResult(ResultEnum.QUERY_OK);
        return res;
    }

    /**
     * 新增权限
     * @param permission
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse add(@RequestBody Permission permission) {
        NormalResponse res = new NormalResponse();
        permissionService.add(permission);

        res.setData(permission);
        res.setResult(ResultEnum.CREATE_OK);
        return res;
    }


    /**
     * 删除权限
     * @param permission
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public NormalResponse delete(@RequestBody Permission permission) {
        NormalResponse res = new NormalResponse();
        res.setResult(ResultEnum.DELETE_OK);

        permissionService.delete(permission);
        return res;
    }


    /**
     * 编辑权限
     * @param permission
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public NormalResponse edit(@RequestBody Permission permission) {
        NormalResponse res = new NormalResponse();
        res.setResult(ResultEnum.UPDATE_OK);
        permissionService.update(permission);

        return res;
    }

}
