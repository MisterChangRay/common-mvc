package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.AjaxResultSet;
import com.github.misterchangray.common.annotation.OperationLog;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.dao.entity.*;
import com.github.misterchangray.service.user.PermissionService;
import com.github.misterchangray.service.user.RoleService;
import com.github.misterchangray.dao.mapper.RoleMapper;
import com.github.misterchangray.dao.mapper.RolePermissionMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户角色服务 实现类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/26/2018.
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapMapper rolePermissionMapMapper;
    @Autowired
    PermissionService permissionService;


    /**
     * 检查角色是否都存在
     * @param ids 待检测ID集合
     * @return true/id都存在,false/有部分id不存在
     */
    public AjaxResultSet exist(List<Integer> ids) {
        if(null == ids) AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        RoleQuery roleQuery = new RoleQuery();
        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        criteria.andIdIn(ids);
        if(ids.size() == roleMapper.countByQuery(roleQuery)) {
            return AjaxResultSet.build().setData(true);
        } else {
            return AjaxResultSet.build().setData(false);
        }
    }


    /**
     * 根据id获取角色对象
     * @param id 带获取角色id
     * @return Role
     */
    public AjaxResultSet getById(Integer id) {
        if(null == id) return AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);
        Role role = roleMapper.selectByPrimaryKey(id);
        if(role.getDeleted().equals(DBEnum.TRUE.getCode())) return AjaxResultSet.build().setCode(ResultEnum.GONE);
        return AjaxResultSet.build().setData(role);
    }

    /**
     * 根据id集合获取多个对象
     * @param ids  待获取对象的id集合
     * @return List[Role]
     */
    public AjaxResultSet getByIds(List<Integer> ids) {
        if(null == ids) AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        RoleQuery roleQuery = new RoleQuery();
        roleQuery.createCriteria().andIdIn(ids).andDeletedEqualTo(DBEnum.FALSE.getCode());
        return AjaxResultSet.build().setData(roleMapper.selectByQuery(roleQuery));
    }

    /**
     * 分页获取角色数据
     * @param role  筛选条件
     * @param pageInfo 分页信息
     * @return  List[Role]
     */
    public AjaxResultSet list(Role role, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        RoleQuery roleQuery = new RoleQuery();
        roleQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnabledEqualTo(DBEnum.TRUE.getCode());
        if(null != role) {
            if(null != role.getName())criteria.andNameLike(role.getName());
        }

        pageInfo.setCount(roleMapper.countByQuery(roleQuery));
        return AjaxResultSet.build().setData(roleMapper.selectByQuery(roleQuery)).setPageInfo(pageInfo);
    }

    /**
     * 增加角色信息
     * @param role 增加角色信息
     * @return  Role
     */
    @OperationLog(businessName = "增加角色")
    public AjaxResultSet save(Role role) {
        role.setId(null);
        role.setEnabled(DBEnum.TRUE.getCode());
        role.setDeleted(DBEnum.FALSE.getCode());
        roleMapper.insert(role);
        return AjaxResultSet.build().setData(role);
    }

    /**
     * 批量插入角色数据
     * @param roles List[Role]
     * @return
     */
    @OperationLog(businessName = "批量增加角色")
    public AjaxResultSet saveAll(List<Role> roles) {
        if(null == roles) return AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        return AjaxResultSet.build().setData(roleMapper.batchInsert(roles));
    }

    /**
     * 更新角色数据
     * @param role 角色对象
     * @return Role
     */
    @OperationLog(businessName = "修改角色")
    public AjaxResultSet edit(Role role) {
        if(null == role || null == role.getId()) return AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        Role dbRole = roleMapper.selectByPrimaryKey(role.getId());
        if(DBEnum.FALSE.getCode().equals(dbRole.getDeleted())) {
            roleMapper.updateByPrimaryKeySelective(role);
            role = roleMapper.selectByPrimaryKey(role.getId());
        }

        return AjaxResultSet.build().setData(role);
    }

    /**
     * 删除角色对象
     * @param role 待删除角色对象
     * @return null
     */
    @OperationLog(businessName = "删除角色")
    public AjaxResultSet delete(Role role) {
        if(null == role || null == role.getId()) return AjaxResultSet.build().setCode(ResultEnum.INVALID_REQUEST);

        role.setDeleted(DBEnum.TRUE.getCode());
        roleMapper.updateByPrimaryKeySelective(role);
        return AjaxResultSet.build().setData(null);
    }


    /**
     * 更新角色的权限信息
     * @param roleId 被更新角色ID
     * @param permissions 权限列表
     * @return
     */
    @OperationLog(businessName = "更新角色权限")
    public AjaxResultSet updatePermission(Integer roleId, List<Integer> permissions) {
        AjaxResultSet ajaxResultSet = AjaxResultSet.build();
        if(null == roleId) return ajaxResultSet.setCode(ResultEnum.INVALID_REQUEST);
        if(null == permissions) return ajaxResultSet.setCode(ResultEnum.INVALID_REQUEST);

        //判断ID是否都存在
        if(permissions.size() == ((List<Permission>)permissionService.getByIds(permissions)).size()) {
            //老数据标记为无效
            RolePermissionMap rolePermissionMap = new RolePermissionMap();
            rolePermissionMap.setDeleted(DBEnum.TRUE.getCode());

            RolePermissionMapQuery rolePermissionMapQuery = new RolePermissionMapQuery();
            RolePermissionMapQuery.Criteria criteria = rolePermissionMapQuery.createCriteria();
            criteria.andRoleIdEqualTo(roleId);
            rolePermissionMapMapper.updateByQuerySelective(rolePermissionMap, rolePermissionMapQuery);

            //插入新的映射数据
            List<RolePermissionMap> rolePermissionMaps = new ArrayList<RolePermissionMap>();
            RolePermissionMap tmp;
            for(Integer i=permissions.size(); i<0; i--) {
                tmp = new RolePermissionMap();
                tmp.setRoleId(roleId);
                tmp.setPermissionId(permissions.get(i));
                rolePermissionMaps.add(tmp);
            }
            rolePermissionMapMapper.batchInsert(rolePermissionMaps);
            return ajaxResultSet;
        }
        return ajaxResultSet.setCode(ResultEnum.INVALID);
    }

}
