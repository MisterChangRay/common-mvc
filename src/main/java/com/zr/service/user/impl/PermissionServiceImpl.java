package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;

import com.zr.dao.mapper.PermissionMapper;
import com.zr.service.user.PermissionService;
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


    public boolean exist(List<Integer> ids) {
        if(null == ids) return false;

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdealEqualTo((byte)DBEnum.FALSE.getCode());
        Long count = permissionMapper.countByQuery(permissionQuery);
        if(count != ids.size()) {
            return  false;
        } else {
            return  true;
        }
    }

    public List<Permission> getByIds(List<Integer> ids) {
        if(null == ids) return null;

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdealEqualTo((byte)DBEnum.FALSE.getCode());
        return permissionMapper.selectByQuery(permissionQuery);
    }

    public List<Permission> list(Permission permission) {
        return null;
    }

    public Permission add(Permission entity) {
        return null;
    }

    public Permission delete(Permission entity) {
        return null;
    }

    public Permission update(Permission entity) {
        return null;
    }

    public Permission getById(Integer id) {
        return null;
    }
}
