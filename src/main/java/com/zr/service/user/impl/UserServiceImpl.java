package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.RoleMapper;
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
    public boolean updateRole(Integer userId, List<Integer> roles) {
        if(null == userId || null == roles) return  false;

        //判断ID是否都存在
        if(roleService.exist(roles)) {
            //老数据标记为无效
            UserRoleMap userRoleMap= new UserRoleMap();
            userRoleMap.setIsdel((byte) DBEnum.TRUE.getCode());

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
            return  true;
        }
        return  false;
    }


    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    public User getById(Integer id) {
        if(null != id) {
            return  userMapper.selectByPrimaryKey(id);
        }
        return  null;
    }


    /**
     * 测试id集合是否都存在
     * @param ids
     * @return
     */
    public boolean exist(List<Integer> ids) {
        if(null == ids) return false;

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo((byte) DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo((byte) DBEnum.TRUE.getCode());

        Long count = userMapper.countByQuery(userQuery);
        if(count != ids.size()) {
            return  false;
        } else {
            return  true;
        }
    }

    /**
     * 根据ID获取用户列表
     * @param ids
     * @return
     */
    public List<User> getByIds(List<Integer> ids) {
        if(null == ids) return null;

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo((byte) DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo((byte) DBEnum.TRUE.getCode());

        return userMapper.selectByQuery(userQuery);
    }

    /**
     * 根据用户信息查询用户列表
     * @param user
     * @return
     */
    public List<User> list(User user) {
        if(null == user) return null;

        UserQuery userQuery =  new UserQuery();
        userQuery.page(user.getPage(), user.getLimit());
        return userMapper.selectByQuery(userQuery);
    }


    /**
     * 添加用户，以下字段必填
     * -name
     * -username
     * -password
     * @param user
     * @return
     */
    public User add(User user) {
        if(null != user.getName() && null != user.getUsername() && null != user.getPassword()) {
            user.setIsdel((byte) DBEnum.FALSE.getCode());
            user.setEnable((byte) DBEnum.TRUE.getCode());
            userMapper.insert(user);
        }
        return user;
    }

    /**
     * 根据ID删除用户
     * @param user
     * @return
     */
    public User delete(User user) {
        if(null != user && null != user.getId()) {
            user.setIsdel((byte)DBEnum.TRUE.getCode());
        }
        userMapper.updateByPrimaryKey(user);
        return  user;
    }

    /**
     * 根据用户ID修改用户信息
     * @param user
     * @return
     */
    public User update(User user) {
        if(null != user.getId() &&
                null != user.getName() && null != user.getUsername() && null != user.getPassword()) {
            userMapper.updateByPrimaryKeySelective(user);
        }
        return  user;
    }

}
