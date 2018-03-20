package com.zr.controller;

import com.zr.common.NormalResponse;
import com.zr.common.ResponseErrorCode;
import com.zr.dao.entity.User;
import com.zr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * Created by Miste on 3/20/2018.
 */
@Controller
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;


    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse list(@RequestParam Integer limit) {
        NormalResponse res = new NormalResponse();
        res.setErrorcode(ResponseErrorCode.OK);
        res.setData(userService.list(10));;
        return res;
    }


    @RequestMapping(value = "/add",method = RequestMethod.POST)
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

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public NormalResponse delete(@RequestBody User user) {
        NormalResponse res = new NormalResponse();
        res.setErrorcode(ResponseErrorCode.OK);
        ArrayList list = new ArrayList();
        list.add(user);
        res.setData(list);
        return res;
    }

}
