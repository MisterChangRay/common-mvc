package com.github.misterchangray.controller;

import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.exception.ServiceException;
import com.github.misterchangray.service.common.RedisCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Created by rui.zhang on 2018/5/25.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description
 */
@Controller
@Authentication
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    RedisCacheService redisCacheService;

    @RequestMapping(value = "test1",method = RequestMethod.GET)
    @ResponseBody
    public ResultSet constant(@RequestParam(required = false) Integer pid) throws Exception {
        ResultSet res = ResultSet.build();

//        EmailBuilder.build().sendSimpleEmail("jioulongzi@qq.com", " 914590431@qq.com", "wocao", "dajia 快看啊 阿道夫为");
//        res.getData().toString();
        throw new ServiceException(ResultEnum.EXIST);
//        return res;
    }

    @RequestMapping(value = "test2",method = RequestMethod.GET)
    @ResponseBody
    public ResultSet constant2(@RequestParam(required = false) Integer pid) throws Exception {
        ResultSet res = ResultSet.build();

//        EmailBuilder.build().sendSimpleEmail("jioulongzi@qq.com", " 914590431@qq.com", "wocao", "dajia 快看啊 阿道夫为");
        res.getData().toString();
//        throw new ServiceException(ResultEnum.EXIST);
        return res;
    }
}
