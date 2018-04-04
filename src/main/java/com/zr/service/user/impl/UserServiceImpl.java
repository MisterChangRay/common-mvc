package com.zr.service.user.impl;

import com.zr.common.DBEnum;
import com.zr.common.ErrorEnum;
import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;
import com.zr.dao.entity.*;
import com.zr.dao.mapper.UserMapper;
import com.zr.dao.mapper.UserRoleMapMapper;
import com.zr.service.user.RoleService;
import com.zr.service.user.UserService;
import com.zr.service.user.vo.UserVO;
import org.springframework.beans.BeanUtils;
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



    public NormalResponse checkUserInfo(String username, String email, String phone, String idcard) {
        NormalResponse res = NormalResponse.newInstance();

        /**
         * 生成的sql为 select * from user where username = '%username' or phone = '%phone' or idcard = '%idcard' or email = '%email'
         */
        UserQuery userQuery = new UserQuery();
        if(null != username) userQuery.or().andUsernameEqualTo(username);
        if(null != phone) userQuery.or().andPhoneEqualTo(phone);
        if(null != idcard) userQuery.or().andIdcardEqualTo(idcard);
        if(null != email) userQuery.or().andEmailEqualTo(email);

        List<User> userList = userMapper.selectByQuery(userQuery);
        if(0 < userList.size()) {
            res.setData(true);
        } else {
            res.setData(false);
        }
        return res;
    }


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



    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    public NormalResponse getById(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        User user = userMapper.selectByPrimaryKey(id);
        if(null == user) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        if(user.getIsdel().equals(DBEnum.TRUE.getCode())) return normalResponse.setErrorCode(ErrorEnum.GONE);
        if(user.getEnable().equals(DBEnum.FALSE.getCode())) return normalResponse.setErrorCode(ErrorEnum.INVALID);

        return normalResponse.setData(user);
    }


    /**
     * 测试id集合是否都存在
     * @param ids
     * @return true id全都存在/false id全部不能存在或部分id不存在
     */
    public NormalResponse exist(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.TRUE.getCode());

        Long count = userMapper.countByQuery(userQuery);
        if(count != ids.size()) {
            return normalResponse.setData(false);
        } else {
            return normalResponse.setData(true);
        }
    }

    /**
     * 根据ID获取用户列表
     * @param ids
     * @return
     */
    public NormalResponse getByIds(List<Integer> ids) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == ids) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        UserQuery userQuery = new UserQuery();
        UserQuery.Criteria criteria = userQuery.createCriteria();
        criteria.andIdIn(ids);
        criteria.andIsdelEqualTo(DBEnum.FALSE.getCode());
        criteria.andEnableEqualTo(DBEnum.TRUE.getCode());

        List<User> users = userMapper.selectByQuery(userQuery);
        return normalResponse.setData(users);
    }

    /**
     * 根据用户信息查询用户列表
     * @param vo
     * @return
     */
    public NormalResponse list(UserVO vo, PageInfo pageInfo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        UserQuery userQuery =  new UserQuery();
        userQuery.page(pageInfo.getPage(), pageInfo.getLimit());
        List<User> users = userMapper.selectByQuery(userQuery);
        return normalResponse.setData(users);
    }


    /**
     * 添加用户，以下字段必填
     * -name
     * -username
     * -password
     * @param vo
     * @return
     */
    public NormalResponse add(UserVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getName()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getUsername()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getPassword()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        User user = new User();
        BeanUtils.copyProperties(vo, user);

        user.setIsdel(DBEnum.FALSE.getCode());
        user.setEnable(DBEnum.TRUE.getCode());
        userMapper.insert(user);
        return normalResponse.setData(vo);
    }

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    public NormalResponse delete(Integer id) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == id) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        User user = new User();
        user.setIsdel(DBEnum.TRUE.getCode());
        user.setId(id);

        userMapper.updateByPrimaryKeySelective(user);
        return normalResponse.setData(user);
    }

    /**
     * 根据用户ID修改用户信息
     * @param vo
     * @return
     */
    public NormalResponse update(UserVO vo) {
        NormalResponse normalResponse = NormalResponse.newInstance();
        if(null == vo) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);
        if(null == vo.getId()) return normalResponse.setErrorCode(ErrorEnum.INVALID_REQUEST);

        User user = new User();
        BeanUtils.copyProperties(vo, user);

        userMapper.updateByPrimaryKeySelective(user);
        return normalResponse.setData(user);
    }

}
