package com.github.misterchangray.controller.user;

import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.service.user.AuthService;
import com.github.misterchangray.service.user.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 用户认证控制器
 *
 * 提供用户登陆认证以下用能
 * -用户登陆认证
 * -检查用户信息是否存在
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/23/2018.
 */
@Api(tags ="用户认证", description = "AuthController")
@Controller
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    UserService userService;




    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登陆", notes = "提供用户登陆接口,登陆成功后返回 Authentication ,在以后的请求中应该把此字段增加到请求头中,这里可以使用(手机号,帐号,邮箱)+密码进行登录")
    @ApiImplicitParams({
        @ApiImplicitParam(name="username", value = "用户名", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name="email", value = "邮箱", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name="phone", value = "手机号", required = false, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name="password", value = "密码", required = false, paramType = "query", dataType = "string")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse login(@RequestParam(required = false) String username,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phone,
                                @RequestParam String password) {

        NormalResponse res = new NormalResponse();
        if((null == username && null == email && null == phone) || null == password) {
            res.setErrorCode(ErrorEnum.INVALID_REQUEST);
            return res;
        }
        if(null != username && null != password) {
            return authService.loginByUserName(username, password);
        }
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
    @ApiOperation(value = "用户信息校验", notes = "检查用户信息是否已经注册,true表示已经注册,false为未注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name="username", value = "用户名", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name="phone", value = "手机号", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name="idcard", value = "身份证", required = false, paramType = "query", dataType = "string"),
            @ApiImplicitParam(name="email", value = "邮箱", required = false, paramType = "query", dataType = "string"),
    })
    @Authentication
    @RequestMapping(value = "/checkUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse checkUser(@RequestParam(required = false) String username,
                                    @RequestParam(required = false) String phone,
                                    @RequestParam(required = false) String idcard,
                                    @RequestParam(required = false) String email) {

        return userService.checkUserInfo(username, email, phone, idcard);
    }


}
