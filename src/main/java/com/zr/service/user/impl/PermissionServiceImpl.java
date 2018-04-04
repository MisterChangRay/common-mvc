package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorEnum;
import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;
import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;

import com.zr.dao.mapper.PermissionMapper;
import com.zr.service.user.PermissionService;
import com.zr.service.user.vo.PermissionVO;
import org.springframework.beans.BeanUtils;
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

    /**
     * 查询ID是否全存在
     * @param ids
     * @return true id全都存在/false id全部不能存在或部分id不存在
     */
    public NormalResponse exist(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        Long count = permissionMapper.countByQuery(permissionQuery);
        if(count != ids.size()) {
            return normalResponse.setData(false);
        } else {
            return normalResponse.setData(true);
        }
    }

    /**
     * 根据id获取权限
     * @param ids
     * @return List<PermissionVO>
     */
    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions);
    }

    /**
     * 查询权限列表
     * @param permission
     * @return  List<PermissionVO>
     */
    public NormalResponse list(PermissionVO permission, PageInfo pageInfo) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        PermissionQuery permissionQuery =  new PermissionQuery();
        permissionQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        List<Permission> permissions = permissionMapper.selectByQuery(permissionQuery);
        return normalResponse.setData(permissions);

    }

    /**
     * 增加权限
     * @param vo
     * @return
     */
    public NormalResponse add(PermissionVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();

        Permission permission = new Permission();

        permission.setIsdel(DBEnum.FALSE.getCode());
        permissionMapper.insert(permission);

        return normalResponse.setData(permission);

    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    public NormalResponse delete(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Permission permission = new Permission();
        permission.setIsdel(DBEnum.TRUE.getCode());
        permission.setId(id);

        permissionMapper.updateByPrimaryKeySelective(permission);
        return normalResponse.setData(permission);
    }

    /**
     * 更新权限
     * @param vo
     * @return
     */
    public NormalResponse update(PermissionVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getId()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Permission permission = new Permission();
        BeanUtils.copyProperties(vo, permission);
        permission.setIsdel(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKey(permission);
        return normalResponse.setData(permission);
    }

    /**
     * 根据id获取权限
     * @param id
     * @return
     */
    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(null == permission)  return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(permission.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setErrorCode(ErrorEnum.GONE);

        return normalResponse.setData(permission);
    }
}
