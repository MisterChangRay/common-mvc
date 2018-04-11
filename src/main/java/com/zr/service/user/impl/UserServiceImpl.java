package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorEnum;
import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.UserMapper;
import com.zr.dao.mapper.UserRoleMapMapper;
import com.zr.service.user.RoleService;
import com.zr.service.user.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Miste on 3/26/2018.
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapMapper userRoleMapMapper;
    @Autowired
    RoleService roleService;


    public NormalResponse updateRole(Integer userId, List<Integer> roles) {
        return null;
    }

    public NormalResponse checkUserInfo(String username, String email, String phone, String idcard) {
        return null;
    }

    public NormalResponse exist(List<Integer> ids) {
        return null;
    }

    public NormalResponse getByIds(List<Integer> ids) {
        return null;
    }

    public NormalResponse list(User user, PageInfo pageInfo) {
        return null;
    }

    public NormalResponse add(User user) {
        return null;
    }

    public NormalResponse batchInsert(List<User> users) {
        return null;
    }

    public NormalResponse update(User user) {
        return null;
    }

    public NormalResponse delete(User user) {
        return null;
    }
}
