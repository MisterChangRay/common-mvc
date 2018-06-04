package com.github.misterchangray.service.user.impl;

import com.github.misterchangray.common.annotation.OperationLog;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.common.exception.ServiceException;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.entity.UserQuery;
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
 * 用户服务实现类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/26/2018.
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
    @OperationLog(businessName = "更新用户角色")
    public NormalResponse updateRole(Integer userId, List<Integer> roles){
        NormalResponse normalResponse = NormalResponse.build();
        if(null == userId) return normalResponse.setCode(ResultEnum.INVALID_REQUEST);
        if(null == roles) return normalResponse.setCode(ResultEnum.INVALID_REQUEST);

        NormalResponse<Boolean> result = roleService.exist(roles);
        //判断ID是否都存在
        if(result.getData()) {
            //老数据标记为无效
            UserRoleMap userRoleMap= new UserRoleMap();
            userRoleMap.setDeleted(DBEnum.TRUE.getCode());

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
        return normalResponse.setCode(ResultEnum.INVALID);
    }

    /**
     * 检查用户信息是否存在
     * @param username 用户名
     * @param email 邮箱
     * @param phone 电话
     * @param idcard    身份证
     * @return true/false
     */
    public NormalResponse checkUserInfo(String username, String email, String phone, String idcard) {
        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();

        if(null != email) userQuery.or(criteria.andEmailEqualTo(email));
        if(null != phone) userQuery.or(criteria.andPhoneEqualTo(phone));
        if(null != idcard) userQuery.or(criteria.andIdcardEqualTo(idcard));
        if(null != username) userQuery.or(criteria.andUsernameEqualTo(username));

        return NormalResponse.build().setData(userMapper.selectByQuery(userQuery));
    }


    /**
     * 检查id集合是否全部存在
     * @param ids 待检查ID集合
     * @return true/false
     */
    public NormalResponse exist(List<Integer> ids) {
        if(null == ids) NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        if(ids.size() == userMapper.countByQuery(userQuery)) {
           return NormalResponse.build().setData(true);
        } else {
           return NormalResponse.build().setData(false);
        }
    }


    /**
     * 根据ID获取用户
     * @param id 待获取ID
     * @return  User
     */
    public NormalResponse getById(Integer id) {
        if(null == id) return NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);
        User user = userMapper.selectByPrimaryKey(id);

        if(user.getDeleted().equals(DBEnum.TRUE.getCode()))  return NormalResponse.build().setCode(ResultEnum.GONE);
        return NormalResponse.build().setData(user);
    }

    /**
     * 根据ID获取用户
     * @param ids 待获取的ID集合
     * @return  List[User]
     */
    public NormalResponse getByIds(List<Integer> ids) {
        if(null == ids) NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        userQuery.createCriteria().andIdIn(ids).andDeletedEqualTo(DBEnum.FALSE.getCode());

        return NormalResponse.build().setData(userMapper.selectByQuery(userQuery));
    }

    /**
     * 分页查询用户信息
     * @param user 筛选条件
     * @param pageInfo 分页信息
     * @return List[User]
     */
    public NormalResponse list(User user, PageInfo pageInfo) {
        if(null == pageInfo) pageInfo = new PageInfo();

        UserQuery userQuery = new UserQuery();
        userQuery.page(pageInfo.getPage(), pageInfo.getLimit());

        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andDeletedEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnabledEqualTo(DBEnum.TRUE.getCode());
        if(null != user) {
            if(null != user.getName())criteria.andNameLike(user.getName());
            if(null != user.getUsername())criteria.andUsernameLike(user.getUsername());
            if(null != user.getPhone())criteria.andPhoneLike(user.getPhone());
            if(null != user.getSex())criteria.andSexEqualTo(user.getSex());
        }

        pageInfo.setCount(userMapper.countByQuery(userQuery));
        return NormalResponse.build().setData(userMapper.selectByQuery(userQuery)).setPageInfo(pageInfo);
    }

    /**
     * 增加用户
     * @param user 待增加用户
     * @return User
     */
    @OperationLog(businessName = "增加用户")
    public NormalResponse insert(User user) {
        if(null == user) return NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        user.setId(null);
        user.setEnabled(DBEnum.TRUE.getCode());
        user.setDeleted(DBEnum.FALSE.getCode());
        userMapper.insert(user);
        return NormalResponse.build().setData(user);
    }

    /**
     * 批量插入用户信息
     * @param users 待增加用户
     * @return
     */
    @OperationLog(businessName = "批量增加用户")
    public NormalResponse batchInsert(List<User> users) {
        if(null == users) return NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        return NormalResponse.build().setData(userMapper.batchInsert(users));
    }

    /**
     * 更新用户信息
     * @param user 待更新用户
     * @return User
     */
    @OperationLog(businessName = "更新用户")
    public NormalResponse update(User user) {
        if(null == user || null == user.getId()) return NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        User dbUser = userMapper.selectByPrimaryKey(user.getId());
        if(dbUser.getDeleted().equals(DBEnum.FALSE.getCode())) {
            userMapper.updateByPrimaryKeySelective(user);
            user = userMapper.selectByPrimaryKey(user.getId());
        }

        return NormalResponse.build().setData(user);
    }


    /**
     *  删除用户
     * @param user 待删除用户
     * @return null
     */
    @OperationLog(businessName = "删除用户角色")
    public NormalResponse delete(User user) {
        if(null == user || null == user.getId()) return NormalResponse.build().setCode(ResultEnum.INVALID_REQUEST);

        user.setDeleted(DBEnum.TRUE.getCode());
        userMapper.updateByPrimaryKeySelective(user);
        return NormalResponse.build().setData(null);
    }
}
