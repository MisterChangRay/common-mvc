package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ResultEnum;
import com.zr.common.NormalResponse;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.UserMapper;
import com.zr.dao.mapper.UserRoleMapMapper;
import com.zr.service.user.RoleService;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miste on 3/26/2018.
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapMapper userRoleMapMapper;
    @Autowired
    RoleService roleService;


    /**
     * 更新用户下的角色信息
     * @param userId 被更新的用户ID
     * @param roles 更新的角色信息
     * @return
     */
    public NormalResponse updateRole(Integer userId, List<Integer> roles) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == userId) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == roles) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        //判断ID是否都存在
        if(((Boolean)roleService.exist(roles).getData())) {
            //老数据标记为无效
            UserRoleMap userRoleMap= new UserRoleMap();
            userRoleMap.setIsdel(DBEnum.TRUE.getCode());

            UserRoleMapQuery userRoleMapQuery = new UserRoleMapQuery();
            UserRoleMapQuery.Criteria criteria = userRoleMapQuery.createCriteria();
            criteria.andUserIdEqualTo(userId);
            userRoleMapMapper.updateByQuerySelective(userRoleMap, userRoleMapQuery);

            //插入新的映射数据
            List<UserRoleMap> userRoleMaps = new ArrayList<UserRoleMap>();
            UserRoleMap tmp;
            for(Integer i=roles.size(); i<0; i--) {
                tmp = new UserRoleMap();
                tmp.setUserId(userId);
                tmp.setRoleId(roles.get(i));
                userRoleMaps.add(tmp);
            }
            userRoleMapMapper.batchInsert(userRoleMaps);
            return normalResponse.setResult(ResultEnum.UPDATE_SUCCESS);
        }
        return normalResponse.setResult(ResultEnum.INVALID);
    }


    /**
     * 根据用户id获取用户信息
     * @param id
     * @return User
     */
    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        User user = userMapper.selectByPrimaryKey(id);
        if(null == user) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        if(user.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setResult(ResultEnum.GONE);
        if(user.getEnable().equals(DBEnum.FALSE.getCode())) return normalResponse.setResult(ResultEnum.INVALID);

        return normalResponse.setData(user).setResult(ResultEnum.QUERY_SUCCESS);
    }


    /**
     * 测试id集合是否都存在
     * @param ids
     * @return true/false
     */
    public NormalResponse exist(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.TRUE.getCode());

        Long count = userMapper.countByQuery(userQuery);
        if(count != ids.size()) {
            return normalResponse.setResult(false, ResultEnum.SUCCESS);
        } else {
            return normalResponse.setResult(true, ResultEnum.SUCCESS);
        }
    }

    /**
     * 根据ID获取用户列表
     * @param ids
     * @return List<User>
     */
    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.TRUE.getCode());

        List<User> users = userMapper.selectByQuery(userQuery);
        return normalResponse.setData(users).setResult(ResultEnum.QUERY_SUCCESS);
    }

    /**
     * 根据用户信息查询用户列表
     * @param entity
     * @return List<User>
     */
    public NormalResponse list(User entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        UserQuery userQuery =  new UserQuery();
        userQuery.page(entity.getPage(), entity.getLimit());
        List<User> users = userMapper.selectByQuery(userQuery);
        return normalResponse.setData(users).setResult(ResultEnum.QUERY_SUCCESS);
    }


    /**
     * 添加用户，以下字段必填
     * -name
     * -username
     * -password
     * @param entity
     * @return User
     */
    public NormalResponse add(User entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getName()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getUsername()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getPassword()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        entity.setIsdel(DBEnum.FALSE.getCode());
        entity.setEnable(DBEnum.TRUE.getCode());
        userMapper.insert(entity);
        return normalResponse.setData(entity).setResult(ResultEnum.CREATE_SUCCESS);
    }

    /**
     * 根据ID删除用户
     * @param entity
     * @return User
     */
    public NormalResponse delete(User entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getId()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);

        entity.setIsdel(DBEnum.TRUE.getCode());
        userMapper.updateByPrimaryKey(entity);
        return normalResponse.setData(entity).setResult(ResultEnum.DELETE_SUCCESS);
    }

    /**
     * 根据用户ID修改用户信息
     * @param entity
     * @return User
     */
    public NormalResponse update(User entity) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == entity) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);
        if(null == entity.getId()) return normalResponse.setResult(ResultEnum.INVALID_REQUEST);


        userMapper.updateByPrimaryKeySelective(entity);
        return normalResponse.setData(entity).setResult(ResultEnum.UPDATE_SUCCESS);
    }

}
