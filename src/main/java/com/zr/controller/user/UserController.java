package com.zr.controller.user;

import com.zr.common.Authentication;
import com.zr.common.NormalResponse;
import com.zr.common.ResponseErrorCode;
import com.zr.dao.entity.User;
import com.zr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Miste on 3/23/2018.
 * 用户管理控制器
 *
 * 提供用户管理以下用能
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
     * 用户列表
     * @param limit
     * @return
     */
    @Authentication
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse list(@RequestParam Integer limit) {
        NormalResponse res = new NormalResponse();
        res.setErrorcode(ResponseErrorCode.OK);
        res.setData(userService.list(10));
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

        ArrayList list = new ArrayList();
        list.add(user);

        res.setData(list);
        res.setErrorcode(ResponseErrorCode.OK);
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
        res.setErrorcode(ResponseErrorCode.OK);
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
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
        res.setErrorcode(ResponseErrorCode.OK);
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
        return res;
    }
}
