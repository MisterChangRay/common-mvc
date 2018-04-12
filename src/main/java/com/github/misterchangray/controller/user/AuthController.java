package com.github.misterchangray.controller.user;

import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.entity.UserQuery;
import com.github.misterchangray.dao.mapper.UserMapper;
import com.github.misterchangray.common.annotation.Authentication;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Miste on 3/23/2018.
 * 用户认证控制器
 *
 * 提供用户登陆认证以下用能
 * -用户登陆认证
 * -检查用户信息是否存在
 *
 */
@Api(tags ="用户认证", description = "AuthController")
@Controller
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    UserMapper userMapper;



    /**
     * 用户登陆
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "用户登陆", notes = "提供用户登陆接口,登陆成功后返回 Authentication ,在以后的请求中应该把此字段增加到请求头中")
    @ApiImplicitParams({
        @ApiImplicitParam(name="username", value = "用户名", required = true, paramType = "query", dataType = "string"),
        @ApiImplicitParam(name="password", value = "密码", required = true, paramType = "query", dataType = "string"),
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public NormalResponse login(@RequestParam String username, @RequestParam String password, HttpServletRequest httpRequest, HttpSession httpSession) {
        NormalResponse res = new NormalResponse();

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);

        List<User> userList = userMapper.selectByQuery(userQuery);
        if(0 < userList.size()) {
            UUID token = UUID.randomUUID();
            httpSession.setAttribute("Authentication", UUID.randomUUID());

            Map result = new HashMap();
            result.put("Authentication", token.toString());

            res.setData(result);
        } else {
            res.setErrorCode(ErrorEnum.INVALID).setErrorMsg("无效用户名或密码");
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
        NormalResponse res = new NormalResponse();


        /**
         * 生成的sql为 select * from user where username = '%username' or phone = '%phone' or idcard = '%idcard' or email = '%email'
         */
        UserQuery userQuery = new UserQuery();
        if(null != username) userQuery.or().andUsernameEqualTo(username);
        if(null != phone) userQuery.or().andPhoneEqualTo(phone);
        if(null != idcard) userQuery.or().andIdcardEqualTo(idcard);
        if(null != email) userQuery.or().andEmailEqualTo(email);

        List<User> userList = userMapper.selectByQuery(userQuery);
        if(0 < userList.size()) {
            res.setData(true);
        } else {
            res.setData(false);
        }
        return res;

    }


}
