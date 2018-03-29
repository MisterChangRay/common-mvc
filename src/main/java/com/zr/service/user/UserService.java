package com.zr.service.user;

import com.zr.common.NormalResponse;
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
    /**
     * 更新用户所绑定的角色
     * @param userId 用户ID
     * @param roles 角色id集合
     * @return
     */
    NormalResponse updateRole(Integer userId, List<Integer> roles);

}