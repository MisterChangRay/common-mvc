package com.github.misterchangray.controller;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.utils.EmailBuilder;
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
@RequestMapping("/v1/test")
public class TestController {
    @Autowired
    RedisCacheService redisCacheService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public NormalResponse constant(@RequestParam(required = false) Integer pid) {
        NormalResponse res = NormalResponse.newInstance();

        EmailBuilder.build().sendSimpleEmail("jioulongzi@qq.com", " 914590431@qq.com", "wocao", "dajia 快看啊 阿道夫为");
        return res;
    }
}
