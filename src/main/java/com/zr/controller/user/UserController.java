package com.zr.controller.user;

import com.zr.common.annotation.Authentication;
import com.zr.common.NormalResponse;
import com.zr.common.ErrorCodeEnum;
import com.zr.common.annotation.PrintRunTime;
import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
 *
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 根据ID获取用户
     * @param userId
     * @return
     */
    @Authentication
    @RequestMapping(value="/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse getById(@PathVariable Integer userId) {
        NormalResponse res = new NormalResponse();
        User user = userService.getById(userId);
        res.setErrorCode(ErrorCodeEnum.OK);
        res.setData(user);
        return res;
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
        NormalResponse res = new NormalResponse();
        List users = userService.list(new UserQuery().page(page, limit));

        res.setErrorCode(ErrorCodeEnum.OK);
        res.setData(users);
        return res;
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
        NormalResponse res = new NormalResponse();
        userService.add(user);

        res.setData(user);
        res.setErrorCode(ErrorCodeEnum.OK);
        return res;
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
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK);

        userService.delete(user);
        return res;
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
        NormalResponse res = new NormalResponse();
        res.setErrorCode(ErrorCodeEnum.OK);
       userService.update(user);

        res.setData(user);
        return res;
    }
}
