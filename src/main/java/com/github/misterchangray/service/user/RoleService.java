package com.github.misterchangray.service.user;

import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.dao.entity.Role;
import com.github.misterchangray.service.BaseService;

import java.util.List;

/**
 * 用户角色服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface RoleService extends BaseService<Role> {
    /**
     * 更新角色绑定的权限
     * @param roleId 角色ID
     * @param permissions 更新权限id集合
     * @return
     */
    ResultSet updatePermission(Integer roleId, List<Integer> permissions) ;

}