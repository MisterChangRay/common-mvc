package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ErrorEnum;
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
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        if(ids.size() == permissionMapper.countByQuery(permissionQuery)) {
            return NormalResponse.newInstance().setData(true);
        } else {
            return NormalResponse.newInstance().setData(false);
        }
    }

    public NormalResponse getById(Integer id) {
        if(null == id) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);
        return NormalResponse.newInstance().setData(permissionMapper.selectByPrimaryKey(id));
    }

    public NormalResponse getByIds(List<Integer> ids) {
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.createCriteria().andIdIn(ids);
        return NormalResponse.newInstance().setData(permissionMapper.selectByQuery(permissionQuery));
    }

    public NormalResponse list(Permission permission, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        if(null != permissionQuery) {
            if(null != permission.getName())criteria.andNameLike(permission.getName());
        }

        return NormalResponse.newInstance().setData(permissionMapper.selectByQuery(permissionQuery));
    }

    public NormalResponse add(Permission permission) {
        permissionMapper.insert(permission);
        return NormalResponse.newInstance().setData(permission);
    }

    public NormalResponse batchInsert(List<Permission> permissions) {
        if(null == permissions) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        return NormalResponse.newInstance().setData(permissionMapper.batchInsert(permissions));
    }

    public NormalResponse update(Permission permission) {
        if(null == permission || null == permission.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);
        permissionMapper.updateByPrimaryKeySelective(permission);
        return NormalResponse.newInstance().setData(permission);
    }

    public NormalResponse delete(Permission permission) {
        if(null == permission || null == permission.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        permission.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKeySelective(permission);
        return NormalResponse.newInstance().setData(null);
    }
}
