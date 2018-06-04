package com.github.misterchangray.controller.user;

import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.service.user.LoginService;
import com.github.misterchangray.service.user.UserService;
import com.github.misterchangray.service.user.bo.UserSessionBo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 用户认证控制器
 *
 * 提供用户登陆认证以下用能
 * -用户登入
 * -用户登出
 * -用户心跳更新
 *
 *
 * 如果项目中使用的是单点登录;则可不理会此处实现(建议保留实现);也可以删除用户相关功能(不推荐)
 * 因为项目登入Token校验是在 AuthInterceptor 拦截器中校验;所以只需要改变拦截器实现即可。
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/23/2018.
 */
@Api(tags ="用户认证", description = "AuthController")
@Controller
@RequestMapping("/v1/session")
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserService userService;
    @Autowired
    HttpSession httpSession;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    UserSessionBo userSessionBo;


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
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    @ResponseBody
    public ResultSet signIn(@RequestParam(required = false) String username,
                                @RequestParam(required = false) String email,
                                @RequestParam(required = false) String phone,
                                @RequestParam String password) throws Exception {

        ResultSet res = ResultSet.build();
        if((null == username && null == email && null == phone) || null == password) {
            res.setCode(ResultEnum.INVALID_REQUEST);
            return res;
        }
        if(null != username && null != password) {
            res = loginService.signInByUserName(username, password);
        }
        return res;
    }


    /**
     * 用户登出
     * @param token
     * @return
     */
    @ApiOperation(value = "用户登出", notes = "提供用户用户登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name="Authentication", value = "用户session", required = false, paramType = "header", dataType = "string"),
    })
    @Authentication
    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    @ResponseBody
    public ResultSet signOut(@RequestHeader(value = "Authentication") String token) {
        return  loginService.signOut(token);
    }



    /**
     * 更新心跳
     * @return
     */
    @ApiOperation(value = "心跳检测", notes = "更新心跳,每隔1分钟访问一次;如3分钟后未访问断定位离线")
    @Authentication
    @RequestMapping(value = "/heartbeat", method = RequestMethod.GET)
    @ResponseBody
    public ResultSet heartbeat(@RequestHeader("Authentication") String authentication) {
        userSessionBo.heartbeat(authentication);
        return ResultSet.build();
    }



}
