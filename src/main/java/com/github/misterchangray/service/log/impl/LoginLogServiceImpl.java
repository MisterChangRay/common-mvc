package com.github.misterchangray.service.log.impl;

import com.github.misterchangray.dao.entity.LoginLog;
import com.github.misterchangray.dao.entity.OperationLog;
import com.github.misterchangray.dao.mapper.LoginLogMapper;
import com.github.misterchangray.dao.mapper.OperationLogMapper;
import com.github.misterchangray.service.log.LoginLogService;
import com.github.misterchangray.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

/**
 * 登录日志服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/5/6.
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Autowired
    LoginLogMapper loginLogMapper;

    /**
     * 增加登录日志
     * @param userId    登录用户ID
     * @param loginIp   登录IP地址
     * @param deviceInfo  登录设备信息
     * @param signInTime    登录时间
     * @param signOutTime   登出时间
     * @return
     */
    public int addLog(String userId, String loginIp, String deviceInfo, Long signInTime, Long signOutTime) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(Integer.parseInt(userId));
        loginLog.setLoginIp(loginIp);
        loginLog.setDeviceInfo(deviceInfo);
        loginLog.setSignInTime(new Date(signInTime));
        loginLog.setSignOutTime(new Date(signOutTime));

        return this.addLog(loginLog);
    }

    public int addLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }
}
