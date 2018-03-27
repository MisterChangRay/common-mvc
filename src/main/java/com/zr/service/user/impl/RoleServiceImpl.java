package com.zr.service.user.impl;

import com.zr.dao.entity.Permission;
import com.zr.dao.entity.Role;
import com.zr.dao.entity.RoleQuery;
import com.zr.dao.mapper.PermissionMapper;
import com.zr.dao.mapper.RolePermissionMapMapper;
import com.zr.service.user.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return false;
    }



}
