package com.github.misterchangray.service.user;

import com.github.misterchangray.dao.entity.Permission;
import com.github.misterchangray.dao.entity.Permission;
import com.github.misterchangray.dao.entity.PermissionQuery;
import com.github.misterchangray.dao.mapper.PermissionMapper;
import com.github.misterchangray.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 用户权限服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface PermissionService extends BaseService<Permission> {
}