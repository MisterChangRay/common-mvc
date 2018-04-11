package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorEnum;
import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.RoleMapper;
import com.zr.dao.mapper.RolePermissionMapMapper;
import com.zr.service.user.PermissionService;
import com.zr.service.user.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户角色服务 实现类
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapMapper rolePermissionMapMapper;
    @Autowired
    PermissionService permissionService;


    public NormalResponse updatePermission(Integer roleId, List<Integer> permissions) {
        return null;
    }

    public NormalResponse exist(List<Integer> ids) {
        return null;
    }

    public NormalResponse getByIds(List<Integer> ids) {
        return null;
    }

    public NormalResponse list(Role role, PageInfo pageInfo) {
        return null;
    }

    public NormalResponse add(Role role) {
        return null;
    }

    public NormalResponse batchInsert(List<Role> roles) {
        return null;
    }

    public NormalResponse update(Role role) {
        return null;
    }

    public NormalResponse delete(Role role) {
        return null;
    }
}
