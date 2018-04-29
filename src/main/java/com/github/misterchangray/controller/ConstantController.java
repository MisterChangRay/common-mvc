package com.github.misterchangray.controller;

import com.github.misterchangray.common.NormalResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 页面常量控制类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  3/23/2018.
 */
@Api(tags ="页面常量获取", description = "ConstantController")
@Controller
@RequestMapping("/v1/constant")
public class ConstantController {

    @ApiOperation(value = "获取所有常量枚举", notes = "返回所有页面常量枚举，前端做缓存")
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse constant() {
        NormalResponse res = new NormalResponse();

        return res;
    }
}
