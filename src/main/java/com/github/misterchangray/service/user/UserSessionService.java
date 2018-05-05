package com.github.misterchangray.service.user;


import com.github.misterchangray.service.user.vo.UserSessionVO;


/**
 *
 * 用户session管理
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
public interface UserSessionService {
    /**
     * 初始化session環境
     * 注冊：onLineUsers 到 servletContext
     */
    void initUserSessionContext();


    /**
     * 根据用户ID创建session
     * 一个用户只能创建一个session;多次创建会覆盖
     * @param userId
     */
    UserSessionVO createSession(String userId);

    /**
     * 获取session
     * @param session
     * @return
     */
    UserSessionVO getSession(String session);


    /**
     * 销毁session
     * @param session
     */
    void destroySession(String session);

    /**
     * 判断session是否存在
     * @param session
     */
    boolean exist(String session);


    /**
     * 根据心跳时间;清除无效的session
     * 计算公式：
     * 时间差 = 当前时间 - 最近心跳时间
     * 当时间差大于 timeout 时;该 session 判定为死亡
     * timeout 应该大于等于 3
     * @param timeout 超过多少分钟更新心跳时间判定未失效 单位分钟
     */
    void clearInvalid(int timeout);


    /**
     * 更新session心跳時間
     * @param session
     */
    void heartbeat(String session);

}
