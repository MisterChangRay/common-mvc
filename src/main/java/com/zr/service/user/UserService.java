package com.zr.service.user;

import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import com.zr.service.BaseService;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用户服务
 */
public interface UserService extends BaseService<User> {
    boolean updateRole(Integer userId, List<Integer> roles);

}