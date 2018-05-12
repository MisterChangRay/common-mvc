package com.github.misterchangray.service.log;



/**
 * 登录日志
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/6/6.
 */
public interface LoginLogService {
    /**
     * 增加登录日志
     * @param userId    登录用户ID
     * @param loginIp   登录IP地址
     * @param deviceInfo  登录设备标识符
     * @param signInTime    登录时间
     * @param signOutTime   登出时间
     * @return
     */
    int addLog(String userId, String loginIp, String deviceInfo, Long signInTime, Long signOutTime);
}
