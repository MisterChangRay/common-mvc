package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.entity.UserRoleMap;
import com.github.misterchangray.dao.entity.UserRoleMapQuery;
import com.github.misterchangray.dao.mapper.UserMapper;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.dao.mapper.UserRoleMapMapper;
import com.github.misterchangray.service.user.RoleService;
import com.github.misterchangray.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        if(null == userId) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == roles) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

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
            return normalResponse;
        }
        return normalResponse.setErrorCode(ErrorEnum.INVALID);
    }


    public NormalResponse checkUserInfo(String username, String email, String phone, String idcard) {
        return null;
    }

    public NormalResponse exist(List<Integer> ids) {
        return null;
    }

    public NormalResponse getById(Integer id) {
        return null;
    }

    public NormalResponse getByIds(List<Integer> ids) {
        return null;
    }

    public NormalResponse list(User user, PageInfo pageInfo) {
        return null;
    }

    public NormalResponse add(User user) {
        return null;
    }

    public NormalResponse batchInsert(List<User> users) {
        return null;
    }

    public NormalResponse update(User user) {
        return null;
    }

    public NormalResponse delete(User user) {
        return null;
    }
}
