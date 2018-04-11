package com.zr.service.user;

import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;
import com.zr.dao.mapper.PermissionMapper;
import com.zr.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用户权限服务
 */
public interface PermissionService extends BaseService<Permission> {
}