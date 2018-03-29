package com.zr.controller.user;

import com.zr.common.annotation.Authentication;
import com.zr.common.NormalResponse;
import com.zr.common.ResultEnum;
import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.dao.mapper.UserMapper;
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
            res.setResult(ResultEnum.SUCCESS);
        } else {
            res.setResultMsg("无效用户名或密码");
            res.setResult(ResultEnum.INVALID);
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
            res.setResultMsg("资料已经存在");
            res.setResult(ResultEnum.INVALID);
        } else {
            res.setResult(ResultEnum.SUCCESS);
        }
        return res;

    }


}
