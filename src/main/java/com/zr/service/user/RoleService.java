package com.zr.service.user;

import com.zr.common.NormalResponse;
import com.zr.dao.entity.Role;
import com.zr.dao.entity.RoleQuery;
import com.zr.service.BaseService;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用户角色服务
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 更新角色绑定的权限
     * @param roleId 角色ID
     * @param permissions 更新权限id集合
     * @return
     */
    NormalResponse updatePermission(Integer roleId, List<Integer> permissions) ;

}