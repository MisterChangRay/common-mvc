package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.dao.entity.Permission;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.dao.entity.PermissionQuery;

import com.github.misterchangray.dao.mapper.PermissionMapper;
import com.github.misterchangray.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户权限实现类
 */
@Service
public class PermissionServiceImpl implements PermissionService{
    @Autowired
    PermissionMapper permissionMapper;


    public NormalResponse exist(List<Integer> ids) {
        return null;
    }

    public NormalResponse getById(Integer id) {
        return null;
    }

    public NormalResponse getByIds(List<Integer> ids) {
        return null;
    }

    public NormalResponse list(Permission permission, PageInfo pageInfo) {
        return null;
    }

    public NormalResponse add(Permission permission) {
        return null;
    }

    public NormalResponse batchInsert(List<Permission> permissions) {
        return null;
    }

    public NormalResponse update(Permission permission) {
        return null;
    }

    public NormalResponse delete(Permission permission) {
        return null;
    }
}
