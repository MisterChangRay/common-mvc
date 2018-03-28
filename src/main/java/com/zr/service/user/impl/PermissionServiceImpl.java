package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorCodeEnum;
import com.zr.common.NormalResponse;
import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;

import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
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
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        Long count = permissionMapper.countByQuery(permissionQuery);
        if(count != ids.size()) {
            return  false;
        } else {
            return  true;
        }
    }

    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("ID不能为空");

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions).setErrorCode(ErrorCodeEnum.QUERY_OK.getCode()).setErrorMsg("成功");
    }

    public NormalResponse list(Permission permission) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        PermissionQuery permissionQuery =  new PermissionQuery();
        permissionQuery.page(permission.getPage(), permission.getLimit());

        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions).setErrorCode(ErrorCodeEnum.QUERY_OK.getCode()).setErrorMsg("成功");

    }

    public NormalResponse add(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        entity.setIsdel(DBEnum.FALSE.getCode());
        permissionMapper.insert(entity);

        return normalResponse.setData(entity).setErrorCode(ErrorCodeEnum.CREATE_OK.getCode()).setErrorMsg("成功");

    }

    public NormalResponse delete(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("参数不能为空");
        if(null == entity.getId()) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("ID无效");

        entity.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKey(entity);
        return normalResponse.setData(entity).setErrorCode(ErrorCodeEnum.DELETE_OK.getCode()).setErrorMsg("成功");
    }

    public NormalResponse update(Permission entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("参数不能为空");
        if(null == entity.getId()) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("ID无效");

        entity.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKey(entity);
        return normalResponse.setData(entity).setErrorCode(ErrorCodeEnum.UPDATE_OK.getCode()).setErrorMsg("成功");
    }

    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("参数不能为空");

        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(null == permission)  return normalResponse.setErrorCode(ErrorCodeEnum.INVALID_REQUEST.getCode()).setErrorMsg("权限不存在");
        if(permission.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setErrorCode(ErrorCodeEnum.GONE.getCode()).setErrorMsg("用户已经被删除");

        return normalResponse.setData(permission).setErrorCode(ErrorCodeEnum.QUERY_OK.getCode()).setErrorMsg("成功");
    }
}
