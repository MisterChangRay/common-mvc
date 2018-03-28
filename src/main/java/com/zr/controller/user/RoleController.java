package com.zr.controller.user;

import com.zr.common.annotation.Authentication;
import com.zr.common.ErrorCodeEnum;
import com.zr.common.NormalResponse;
import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;
import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.service.user.PermissionService;
import com.zr.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miste on 3/23/2018.
 * 角色管理控制器
 *
 * 提供用户角色以下用能
 * -角色列表
 * -角色新增
 * -角色编辑
 * -角色启用/停用
 * -角色删除
 * -角色权限编辑
 */
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
    @Authentication
    @RequestMapping(value="/{roleId}/permission", method = RequestMethod.PATCH)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer roleId, @RequestParam List permissionIds) {
        NormalResponse res = new NormalResponse();

        if(null != roleId && null != permissionIds && 0 < permissionIds.size()) {
            if(roleService.updatePermission(roleId,permissionIds)) {
                res.setErrorCode(ErrorCodeEnum.OK.getCode());
            } else {
                res.setErrorCode(ErrorCodeEnum.SERVER_ERROR.getCode());
            }
        }
        return res;
    }


    /**
     * 角色列表
     * @param limit
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse list(@RequestParam Integer limit) {
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK.getCode());
        return res;
    }

    /**
     * 新增角色
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse add(@RequestBody User user) {
        NormalResponse res = new NormalResponse();

        ArrayList list = new ArrayList();
        list.add(user);

        res.setData(list);
        res.setErrorCode(ErrorCodeEnum.OK.getCode());
        return res;
    }


    /**
     * 删除角色
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public NormalResponse delete(@RequestBody User user) {
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK.getCode());
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
        return res;
    }


    /**
     * 编辑角色
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public NormalResponse edit(@RequestBody User user) {
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK.getCode());
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
        return res;
    }

}
