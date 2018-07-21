package com.github.misterchangray.service.user;

import com.github.misterchangray.common.AjaxResultSet;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.service.BaseService;

import java.util.List;

/**
 * 用户服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
public interface UserService extends BaseService<User> {
    /**
     * 更新用户所绑定的角色
     * @param userId 用户ID
     * @param roles 角色id集合
     * @return
     */
    AjaxResultSet updateRole(Integer userId, List<Integer> roles);


    /**
     * 检查用户信息是否已注册
     * @param username
     * @param email
     * @param phone
     * @param idcard
     * @return
     */
    AjaxResultSet checkUserInfo(String username, String email, String phone, String idcard);

}