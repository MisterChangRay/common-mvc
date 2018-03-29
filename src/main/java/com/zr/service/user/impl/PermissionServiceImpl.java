package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ResultEnum;
import com.zr.common.NormalResponse;
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


    public NormalResponse exist(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        Long count = permissionMapper.countByQuery(permissionQuery);
        if(count != ids.size()) {
            return normalResponse.setResult(ResultEnum.FALSE);
        } else {
            return normalResponse.setResult(ResultEnum.TRUE);
        }
    }

    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions).setResult(ResultEnum.QUERY_OK);
    }

    public NormalResponse list(Permission permission) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        PermissionQuery permissionQuery =  new PermissionQuery();
        permissionQuery.page(permission.getPage(), permission.getLimit());

        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions).setResult(ResultEnum.QUERY_OK);

    }

    public NormalResponse add(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        entity.setIsdel(DBEnum.FALSE.getCode());
        permissionMapper.insert(entity);

        return normalResponse.setResult(entity, ResultEnum.CREATE_OK);

    }

    public NormalResponse delete(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getId()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        entity.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKey(entity);
        return normalResponse.setData(entity).setResult(ResultEnum.DELETE_OK);
    }

    public NormalResponse update(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getId()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        entity.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKey(entity);
        return normalResponse.setData(entity).setResult(ResultEnum.UPDATE_OK);
    }

    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(null == permission)  return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(permission.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setResult(ResultEnum.GONE);

        return normalResponse.setData(permission).setResult(ResultEnum.QUERY_OK);
    }
}
