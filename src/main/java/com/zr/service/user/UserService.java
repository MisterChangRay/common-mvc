package com.zr.service.user;

import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;


import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用户服务定义类
 */
public interface UserService {
    /**
     * 列表
     * @param userQuery
     * @return
     */
    List<User> list(UserQuery userQuery);

    /**
     * 新增
     * @param user
     * @return
     */
    User add(User user);

    /**
     * 删除
     * @param user
     * @return
     */
    User delete(User user);

    /**
     * 编辑
     * @param user
     * @return
     */
    User update(User user);

}