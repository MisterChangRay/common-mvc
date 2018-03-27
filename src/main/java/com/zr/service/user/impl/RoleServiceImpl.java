package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.PermissionMapper;
import com.zr.dao.mapper.RolePermissionMapMapper;
import com.zr.service.user.PermissionService;
import com.zr.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户角色服务 实现类
 */
@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleService roleService;
    @Autowired
    RolePermissionMapMapper rolePermissionMapMapper;
    @Autowired
    PermissionService permissionService;

    public boolean exist(List<Integer> ids) {
        return false;
    }

    public List<Role> list(RoleQuery entityQuery) {
        return null;
    }

    public Role add(Role entity) {
        return null;
    }

    public Role delete(Role entity) {
        return null;
    }

    public Role update(Role entity) {
        return null;
    }

    public Role getById(Integer id) {
        return null;
    }


    /**
     * 更新角色的权限信息
     * @param roleId 被更新角色ID
     * @param permissions 权限列表
     * @return
     */
    public boolean updatePermission(Integer roleId, List<Integer> permissions) {
        if(null == roleId || null == permissions) return  false;

        if(permissionService.exist(permissions)) {
            //删除老数据
            RolePermissionMap rolePermissionMap = new RolePermissionMap();
            rolePermissionMap.setIsdel((byte) DBEnum.TRUE.getCode());

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
            return  true;
        }
        return  false;
    }



}
