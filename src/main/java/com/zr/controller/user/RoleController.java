package com.zr.controller.user;

import com.zr.common.annotation.Authentication;
import com.zr.common.ErrorCodeEnum;
import com.zr.common.NormalResponse;
import com.zr.dao.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

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
 */
@Controller
@RequestMapping("/v1/role")
public class RoleController {

    /**
     * 获取角色
     * @param roleId
     * @return
     */
    @Authentication
    @RequestMapping(value="/{roleId}", method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer roleId) {
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK);
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
        res.setErrorCode(ErrorCodeEnum.OK);
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
        res.setErrorCode(ErrorCodeEnum.OK);
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
        res.setErrorCode(ErrorCodeEnum.OK);
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
        res.setErrorCode(ErrorCodeEnum.OK);
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
        return res;
    }

}
