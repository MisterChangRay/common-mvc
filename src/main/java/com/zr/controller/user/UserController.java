package com.zr.controller.user;

import com.zr.common.annotation.Authentication;
import com.zr.common.NormalResponse;
import com.zr.dao.entity.User;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Miste on 3/23/2018.
 * 用户管理控制器
 *
 * 提供用户管理以下用能
 * -根据ID获取指定用户
 * -用户列表
 * -用户新增
 * -用户删除
 * -用户启用/停用
 * -用户编辑
 * -用户角色编辑
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 编辑用户角色
     * @param userId 用户id
     * @param roleIds 角色Id集合
     * @return
     */
    @Authentication
    @RequestMapping(value="/{userId}/role", method = RequestMethod.PATCH)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer userId, @RequestParam List roleIds) {
        return userService.updateRole(userId, roleIds);
    }

    /**
     * 根据ID获取用户
     * @param userId
     * @return
     */
    @Authentication
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }


    /**
     * 用户列表
     * @param limit
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse list(@RequestParam(required =  false) Integer page, @RequestParam(required = false) Integer limit) {
        User user = new User();
        user.setPage(page);
        user.setLimit(limit);

        return userService.list(user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse add(@RequestBody User user) {
        return userService.add(user);
    }


    /**
     * 删除用户
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public NormalResponse delete(@RequestBody User user) {
        return userService.delete(user);
    }


    /**
     * 编辑用户
     * @param user
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public NormalResponse edit(@RequestBody User user) {
       return userService.update(user);
    }
}
