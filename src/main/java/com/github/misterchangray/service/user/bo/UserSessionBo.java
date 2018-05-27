package com.github.misterchangray.service.user.bo;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.service.common.RedisCacheService;
import com.github.misterchangray.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 *
 * session管理
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
@Component
public class UserSessionBo {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisCacheService redisCacheService;

    //心跳过期时间;统为3分钟
    private Integer timeout = 3 * 60 * 1000;



    /**
     * 获取session
     * @param session
     * @return
     */
    public String getSession(String session) {
        return String.valueOf(redisCacheService.get(session));
    }


    /**
     * 根据用户ID创建session
     * 一个用户只能创建一个session;多次创建会覆盖
     * @param userId
     */
    public String createSession(String userId) {
        if(null == userId) return null;

        NormalResponse response = userService.getById(Integer.parseInt(userId));
        if(false == response.isSuccess()) return null;
        User user = (User) response.getData();

        //构造session
        String token = UUID.randomUUID().toString().replace("-", "");

        redisCacheService.set(token, user, timeout);

        return token;
    }


    /**
     * 销毁session
     * @param session
     */
    public void destroySession(String session) {
        redisCacheService.del(session);
    }


    /**
     * 判断session是否存在
     * @param session
     */
    public boolean exist(String session) {
        return null == redisCacheService.get(session) ? false : true;
    }


    /**
     * 更新session心跳時間
     * @param session
     */
    public void heartbeat(String session) {
        redisCacheService.expire(session, timeout);
    }

}
