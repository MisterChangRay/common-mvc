package com.zr.controller.user;

import com.zr.common.Authentication;
import com.zr.common.NormalResponse;
import com.zr.common.ErrorCodeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Miste on 3/23/2018.
 * 用户认证控制器
 *
 * 提供用户登陆认证以下用能
 * -用户登陆认证
 * -检查用户信息是否存在
 *
 */
@Controller
@RequestMapping("/v1/auth")
public class AuthController {

    /**
     * 用户登陆
     * @param limit
     * @return
     */
    @Authentication
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse login(@RequestParam Integer limit) {
        NormalResponse res = new NormalResponse();
        res.setErrorcode(ErrorCodeEnum.OK);
        return res;
    }

    /**
     * 检查用户信息是否存在
     * @param username 用户名
     * @param phone 手机号
     * @param idcard 身份证
     * @param email 邮箱
     * @return
     */
    @Authentication
    @RequestMapping(value = "/checkUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse checkUser(@RequestParam(required = false) String username,
                                    @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) String idcard,
                                    @RequestParam(required = false) String email) {
        NormalResponse res = new NormalResponse();
        res.setErrorcode(ErrorCodeEnum.OK);
        return res;
    }


}
