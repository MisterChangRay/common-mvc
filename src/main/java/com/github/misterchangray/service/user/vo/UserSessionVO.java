package com.github.misterchangray.service.user.vo;

/**
 *
 * 用户 session 缓存对象
 * CO/cache object
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
public class UserSessionVO {
    /**
     * 用户登录得token
     */
    private String session;
    /**
     * 用户名
     */
    private String username;
    /**
     * 登录用户ID
     */
    private String userId;
    /**
     * 最近一次心跳时间
     */
    private Long heartBeatDate;

    /**
     * 登录时间
     */
    private Long loginDate;

    /**
     * 登录日志ID
     */
    private Integer loginLogId;


    public Integer getLoginLogId() {
        return loginLogId;
    }

    public void setLoginLogId(Integer loginLogId) {
        this.loginLogId = loginLogId;
    }

    public Long getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Long loginDate) {
        this.loginDate = loginDate;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getHeartBeatDate() {
        return heartBeatDate;
    }

    public void setHeartBeatDate(Long heartBeatDate) {
        this.heartBeatDate = heartBeatDate;
    }

    public UserSessionVO(String session, String username, String userId, Long heartBeatDate) {
        this.session = session;
        this.username = username;
        this.userId = userId;
        this.heartBeatDate = heartBeatDate;
        this.loginDate = heartBeatDate;
    }


}
