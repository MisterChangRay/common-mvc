package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorEnum;
import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.RoleMapper;
import com.zr.dao.mapper.RolePermissionMapMapper;
import com.zr.service.user.PermissionService;
import com.zr.service.user.RoleService;
import com.zr.service.user.vo.RoleVO;
import org.springframework.beans.BeanUtils;
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
    RoleMapper roleMapper;
    @Autowired
    RolePermissionMapMapper rolePermissionMapMapper;
    @Autowired
    PermissionService permissionService;


    /**
     * 查询id是否存在
     * @param ids
     * @return true id全都存在/false id全部不能存在或部分id不存在
     */
    public NormalResponse exist(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        RoleQuery roleQuery = new RoleQuery();
        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.FALSE.getCode());
        Long count = roleMapper.countByQuery(roleQuery);
        if(count != ids.size()) {
            return normalResponse.setData(false);
        } else {
            return normalResponse.setData(true);
        }
    }

    /**
     * 根据id获取角色
     * @param ids
     * @return List<RoleVO>
     */
    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = new NormalResponse();
        if(null == ids) return null;

        RoleQuery roleQuery = new RoleQuery();
        RoleQuery.Criteria criteria = roleQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.FALSE.getCode());
        List<Role>  roles = roleMapper.selectByQuery(roleQuery);

        return normalResponse.setData(roles);
    }

    /**
     * 返回角色列表
     * @param vo
     * @return List<RoleVO>
     */
    public NormalResponse list(RoleVO vo, PageInfo pageInfo) {
        return null;
    }

    /**
     *
     * @param vo
     * @return RoleVO
     */
    public NormalResponse add(RoleVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getId()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Role role = new Role();
        BeanUtils.copyProperties(vo, role);
        role.setIsdel(DBEnum.FALSE.getCode());

        return normalResponse.setData(role);
    }

    /**
     * 删除角色
     * @param id
     * @return RoleVO
     */
    public NormalResponse delete(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Role role = new Role();
        role.setId(id);
        role.setIsdel(DBEnum.TRUE.getCode());
        roleMapper.updateByPrimaryKey(role);

        return normalResponse.setData(role);
    }

    /**
     * 更新角色
     * @param vo
     * @return RoleVO
     */
    public NormalResponse update(RoleVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getId()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Role role = new Role();
        BeanUtils.copyProperties(vo, role);

        roleMapper.updateByPrimaryKeySelective(role);
        return normalResponse.setData(role);
    }

    /**
     * 给句ID获取角色
     * @param id
     * @return RoleVO
     */
    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        Role role = roleMapper.selectByPrimaryKey(id);
        if(null == role) return normalResponse.setErrorCode(ErrorEnum.INVALID);
        if(role.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setErrorCode(ErrorEnum.GONE);
        if(role.getEnable().equals(DBEnum.FALSE.getCode())) return normalResponse.setErrorCode(ErrorEnum.INVALID);

        return normalResponse.setData(role);
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
