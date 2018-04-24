package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.utils.MapBuilder;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.entity.UserQuery;
import com.github.misterchangray.dao.mapper.UserMapper;
import com.github.misterchangray.service.user.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Miste on 3/29/2018.
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    HttpSession httpSession;

    public NormalResponse loginByUserName(String username, String password) {
        NormalResponse res = new NormalResponse();

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andUsernameEqualTo(username).andPasswordEqualTo(password);

        List<User> userList = userMapper.selectByQuery(userQuery);
        if(0 == userList.size()) {
            res.setErrorCode(ErrorEnum.INVALID).setErrorMsg("无效用户名或密码");
            return res;
        }
        User user = userList.get(0);
        if(DBEnum.TRUE.getCode().equals(user.getDeleted())) {
            res.setErrorCode(ErrorEnum.GONE).setErrorMsg("该用户已被删除");
            return  res;
        }
        if(DBEnum.FALSE.getCode().equals(user.getEnabled())) {
            res.setErrorCode(ErrorEnum.DISABLED).setErrorMsg("该用户已被禁用");
            return  res;
        }

        String token = UUID.randomUUID().toString();
        httpSession.setAttribute("Authentication", token);
        httpSession.setAttribute("user", user);

        Map data = MapBuilder.build().put("Authentication", token).put("user", user);
        res.setData(data);
        return res;
    }

    public NormalResponse loginByPhone(String phone, String password) {
        return null;
    }

    public NormalResponse loginByEmail(String email, String password) {
        return null;
    }
}
