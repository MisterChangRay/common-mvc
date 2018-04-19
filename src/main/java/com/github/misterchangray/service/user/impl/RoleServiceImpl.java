package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.NormalResponse;
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
 * Created by Miste on 3/26/2018.
 * 用户角色服务 实现类
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
    public NormalResponse exist(List<Integer> ids) {
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        RoleQuery roleQuery = new RoleQuery();
        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andIdIn(ids);
        if(ids.size() == roleMapper.countByQuery(roleQuery)) {
            return NormalResponse.newInstance().setData(true);
        } else {
            return NormalResponse.newInstance().setData(false);
        }
    }


    /**
     * 根据id获取角色对象
     * @param id 带获取角色id
     * @return Role
     */
    public NormalResponse getById(Integer id) {
        if(null == id) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);
        return NormalResponse.newInstance().setData(roleMapper.selectByPrimaryKey(id));
    }

    /**
     * 根据id集合获取多个对象
     * @param ids  待获取对象的id集合
     * @return List[Role]
     */
    public NormalResponse getByIds(List<Integer> ids) {
        if(null == ids) NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        RoleQuery roleQuery = new RoleQuery();
        roleQuery.createCriteria().andIdIn(ids);
        return NormalResponse.newInstance().setData(roleMapper.selectByQuery(roleQuery));
    }

    /**
     * 分页获取角色数据
     * @param role  筛选条件
     * @param pageInfo 分页信息
     * @return  List[Role]
     */
    public NormalResponse list(Role role, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        RoleQuery roleQuery = new RoleQuery();
        roleQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.TRUE.getCode());
        if(null != role) {
            if(null != role.getName())criteria.andNameLike(role.getName());
        }

        return NormalResponse.newInstance().setData(roleMapper.selectByQuery(roleQuery));
    }

    /**
     * 增加角色信息
     * @param role 增加角色信息
     * @return  Role
     */
    public NormalResponse add(Role role) {
        role.setEnable(DBEnum.TRUE.getCode());
        role.setIsdel(DBEnum.FALSE.getCode());
        roleMapper.insert(role);
        return NormalResponse.newInstance().setData(role);
    }

    /**
     * 批量插入角色数据
     * @param roles List[Role]
     * @return
     */
    public NormalResponse batchInsert(List<Role> roles) {
        if(null == roles) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        return NormalResponse.newInstance().setData(roleMapper.batchInsert(roles));
    }

    /**
     * 更新角色数据
     * @param role 角色对象
     * @return Role
     */
    public NormalResponse update(Role role) {
        if(null == role || null == role.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);
        roleMapper.updateByPrimaryKeySelective(role);

        roleMapper.selectByPrimaryKey(role.getId());
        return NormalResponse.newInstance().setData(role);
    }

    /**
     * 删除角色对象
     * @param role 待删除角色对象
     * @return null
     */
    public NormalResponse delete(Role role) {
        if(null == role || null == role.getId()) return NormalResponse.newInstance().setErrorCode(ErrorEnum.INVALID_REQUEST);

        role.setIsdel(DBEnum.TRUE.getCode());
        roleMapper.updateByPrimaryKeySelective(role);
        return NormalResponse.newInstance().setData(null);
    }


    /**
     * 更新角色的权限信息
     * @param roleId 被更新角色ID
     * @param permissions 权限列表
     * @return
     */
    public NormalResponse updatePermission(Integer roleId, List<Integer> permissions) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == roleId) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == permissions) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        //判断ID是否都存在
        if(permissions.size() == ((List<Permission>)permissionService.getByIds(permissions)).size()) {
            //老数据标记为无效
            RolePermissionMap rolePermissionMap = new RolePermissionMap();
            rolePermissionMap.setIsdel(DBEnum.TRUE.getCode());

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
            return normalResponse;
        }
        return normalResponse.setErrorCode(ErrorEnum.INVALID);
    }

}
