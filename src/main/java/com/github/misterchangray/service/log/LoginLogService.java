package com.github.misterchangray.service.log;


import com.github.misterchangray.dao.entity.LoginLog;

/**
 * 登录日志
 *
 * 从业务上来讲：衹要是系統用戶;无论是否登录成功都应该记录日志;方便日后分析
 * 增加登录日志在 LoginService.signIn 方法中调用
 * 登出时间在用户 session 销毁时更新;即 UserSessionBo.destroySession 方法调用时
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
     * @param success   是否成功
     * @param detailsOfFail   失败原因
     * @return
     */
    int insertLog(String userId, String loginIp, String deviceInfo, Long signInTime, Boolean success, String detailsOfFail);


    /**
     * 增加登录日志
     * @param loginLog
     * @return
     */
    int insertLog(LoginLog loginLog);

    /**
     * 更新登录日志的 登出时间
     * 此函数在每次发起心跳时更新;即刚登录时此值应该为null
     * @param session 待更新记录的session
     * @return
     */
    int updateSignOutTime(String session);
}
