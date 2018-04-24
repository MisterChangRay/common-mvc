package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.utils.EntityUtils;
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


    /**
     * 检查权限是否存在
     * @param ids 待检测的ID集合
     * @return false有部分不存在/true全都存在
     */
    public NormalResponse exist(List<Integer> ids) {
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        if(ids.size() == permissionMapper.countByQuery(permissionQuery)) {
            return NormalResponse.newInstance().setData(true);
        } else {
            return NormalResponse.newInstance().setData(false);
        }
    }

    /**
     * 根据ID获取权限对象
     * @param id 待获取的id
     * @return Permission
     */
    public NormalResponse getById(Integer id) {
        if(null == id) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(permission.getDeleted().equals(DBEnum.TRUE.getCode())) return NormalResponse.newInstance().setErrorCode(ErrorEnum.GONE);
        return NormalResponse.newInstance().setData(permission);
    }

    /**
     * 根据ID集合获取权限对象
     * @param ids 待获取的ID集合
     * @return List[Permission]
     */
    public NormalResponse getByIds(List<Integer> ids) {
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.createCriteria().andIdIn(ids).andDeletedEqualTo(DBEnum.FALSE.getCode());
        return NormalResponse.newInstance().setData(permissionMapper.selectByQuery(permissionQuery));
    }

    /**
     *  分页获取权限对象
     * @param permission 筛选信息
     * @param pageInfo 分页信息
     * @return List[Permission]
     */
    public NormalResponse list(Permission permission, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        if(null != permissionQuery) {
            if(null != permission.getName())criteria.andNameLike(permission.getName());
        }

        return NormalResponse.newInstance().setData(permissionMapper.selectByQuery(permissionQuery));
    }

    /**
     * 新增权限对象
     * @param permission 待新增的对象
     * @return Permission
     */
    public NormalResponse insert(Permission permission) {
        permission.setId(null);
        permission.setDeleted(DBEnum.FALSE.getCode());
        permissionMapper.insert(permission);
        return NormalResponse.newInstance().setData(permission);
    }

    /**
     * 批量插入权限对象
     * @param permissions 待新增的对象集合
     * @return list[Permission]
     */
    public NormalResponse batchInsert(List<Permission> permissions) {
        if(null == permissions) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        return NormalResponse.newInstance().setData(permissionMapper.batchInsert(permissions));
    }

    /**
     * 更新权限对象
     * @param permission 待更新的权限对象
     * @return Permission
     */
    public NormalResponse update(Permission permission) {
        if(null == permission || null == permission.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        Permission dbPermission = permissionMapper.selectByPrimaryKey(permission.getId());
        if(DBEnum.FALSE.getCode().equals(dbPermission.getDeleted())) {
            permissionMapper.updateByPrimaryKeySelective(permission);
            permission = permissionMapper.selectByPrimaryKey(permission.getId());
        }

        return NormalResponse.newInstance().setData(permission);
    }

    /**
     * 删除权限对象
     * @param permission 待删除的权限对象
     * @return null
     */
    public NormalResponse delete(Permission permission) {
        if(null == permission || null == permission.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        permission.setDeleted(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKeySelective(permission);
        return NormalResponse.newInstance().setData(null);
    }
}
