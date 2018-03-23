package com.zr.service;

import com.zr.common.Authentication;
import com.zr.dao.entity.User;
import com.zr.dao.entity.UserExample;
import com.zr.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    public User add(User userInfo) {
        userMapper.insert(userInfo);
        return userInfo;
    }

    public List list(Integer limit) {
        UserExample userExample = new UserExample();

        return userMapper.selectByExample(userExample);
    }
}
