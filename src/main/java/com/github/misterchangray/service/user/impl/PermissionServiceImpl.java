package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.annotation.OperationLog;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.dao.entity.Permission;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.dao.entity.PermissionQuery;

import com.github.misterchangray.dao.mapper.PermissionMapper;
import com.github.misterchangray.service.user.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 用户权限实现类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/29/2018.
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
    public ResultSet exist(List<Integer> ids) {
        if(null == ids) return ResultSet.build(ResultEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        if(ids.size() == permissionMapper.countByQuery(permissionQuery)) {
            return ResultSet.build().setData(true);
        } else {
            return ResultSet.build().setData(false);
        }
    }

    /**
     * 根据ID获取权限对象
     * @param id 待获取的id
     * @return Permission
     */
    public ResultSet getById(Integer id) {
        if(null == id) return ResultSet.build(ResultEnum.INVALID_REQUEST);
        Permission permission = permissionMapper.selectByPrimaryKey(id);
        if(permission.getDeleted().equals(DBEnum.TRUE.getCode())) return ResultSet.build().setCode(ResultEnum.GONE);
        return ResultSet.build().setData(permission);
    }

    /**
     * 根据ID集合获取权限对象
     * @param ids 待获取的ID集合
     * @return List[Permission]
     */
    public ResultSet getByIds(List<Integer> ids) {
        if(null == ids) ResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.createCriteria().andIdIn(ids).andDeletedEqualTo(DBEnum.FALSE.getCode());
        return ResultSet.build().setData(permissionMapper.selectByQuery(permissionQuery));
    }

    /**
     *  分页获取权限对象
     * @param permission 筛选信息
     * @param pageInfo 分页信息
     * @return List[Permission]
     */
    public ResultSet list(Permission permission, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        PermissionQuery permissionQuery = new PermissionQuery();
        permissionQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        PermissionQuery.Criteria criteria = permissionQuery.createCriteria();
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        if(null != permissionQuery) {
            if(null != permission.getName())criteria.andNameLike(permission.getName());
        }

        pageInfo.setCount(permissionMapper.countByQuery(permissionQuery));
        return ResultSet.build().setData(permissionMapper.selectByQuery(permissionQuery)).setPageInfo(pageInfo);
    }

    /**
     * 新增权限对象
     * @param permission 待新增的对象
     * @return Permission
     */
    @OperationLog(businessName = "增加权限")
    public ResultSet save(Permission permission) {
        permission.setId(null);
        permission.setDeleted(DBEnum.FALSE.getCode());
        permissionMapper.insert(permission);
        return ResultSet.build().setData(permission);
    }

    /**
     * 批量插入权限对象
     * @param permissions 待新增的对象集合
     * @return list[Permission]
     */
    @OperationLog(businessName = "批量增加权限")
    public ResultSet saveAll(List<Permission> permissions) {
        if(null == permissions) return ResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        return ResultSet.build().setData(permissionMapper.batchInsert(permissions));
    }

    /**
     * 更新权限对象
     * @param permission 待更新的权限对象
     * @return Permission
     */
    @OperationLog(businessName = "更新权限")
    public ResultSet edit(Permission permission) {
        if(null == permission || null == permission.getId()) return ResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        Permission dbPermission = permissionMapper.selectByPrimaryKey(permission.getId());
        if(DBEnum.FALSE.getCode().equals(dbPermission.getDeleted())) {
            permissionMapper.updateByPrimaryKeySelective(permission);
            permission = permissionMapper.selectByPrimaryKey(permission.getId());
        }

        return ResultSet.build().setData(permission);
    }

    /**
     * 删除权限对象
     * @param permission 待删除的权限对象
     * @return null
     */
    @OperationLog(businessName = "删除权限")
    public ResultSet delete(Permission permission) {
        if(null == permission || null == permission.getId()) return ResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        permission.setDeleted(DBEnum.TRUE.getCode());
        permissionMapper.updateByPrimaryKeySelective(permission);
        return ResultSet.build().setData(null);
    }
}
