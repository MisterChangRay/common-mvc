package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.AjaxResultSet;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.exception.ServiceException;
import com.github.misterchangray.common.utils.MapBuilder;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.entity.UserQuery;
import com.github.misterchangray.dao.mapper.UserMapper;
import com.github.misterchangray.service.user.LoginService;
import com.github.misterchangray.service.user.bo.UserSessionBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/29/2018.
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpSession httpSession;
    @Autowired
    UserSessionBo userSessionBo;


    public AjaxResultSet signInByUserName(String username, String password) throws ServiceException {
        AjaxResultSet res = AjaxResultSet.build();

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);

        List<User> userList = userMapper.selectByQuery(userQuery);
        if(0 == userList.size()) throw new ServiceException(ResultEnum.INVALID, "无效用户名或密码");
        User user = userList.get(0);
        if(DBEnum.TRUE.getCode().equals(user.getDeleted())) throw new ServiceException(ResultEnum.GONE, "该用户已被删除");
        if(DBEnum.FALSE.getCode().equals(user.getEnabled())) throw new ServiceException(ResultEnum.DISABLED, "该用户已被禁用");


        String session = userSessionBo.createSession(String.valueOf(user.getId()));
        httpSession.setAttribute("Authentication", session);
        httpSession.setAttribute("user", user);

        /**
         * 请注意:
         * 此返回结构在操作日志中有用到;故如果修改返回结构应该同步修改操作日志文件
         */
        Map data = MapBuilder.build().put("Authorization", session).put("user", user);
        res.setData(data);
        return res;
    }

    public AjaxResultSet signInByEmail(String email, String password) {
        return null;
    }

    public AjaxResultSet signInByPhone(String phone, String verificationCode) {
        return null;
    }



    public AjaxResultSet signOut(String userId) {
       userSessionBo.destroySession(userId);
        return AjaxResultSet.build();
    }
}
