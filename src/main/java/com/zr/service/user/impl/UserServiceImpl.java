package com.zr.service.user.impl;

import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.dao.mapper.UserMapper;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;


    public List<User> list(UserQuery userQuery) {
        return null;
    }

    public User add(User user) {
        return null;
    }

    public User delete(User user) {
        return null;
    }

    public User update(User user) {
        return null;
    }

}
