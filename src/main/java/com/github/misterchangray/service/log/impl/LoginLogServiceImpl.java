package com.github.misterchangray.service.log.impl;

import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.dao.entity.LoginLog;
import com.github.misterchangray.dao.entity.LoginLogQuery;
import com.github.misterchangray.dao.mapper.LoginLogMapper;
import com.github.misterchangray.service.log.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * @param deviceInfo  登录设备标识符
     * @param signInTime    登录时间
     * @param success   是否成功
     * @param detailsOfFail   失败原因
     * @return
     */
    public int insertLog(String userId, String loginIp, String deviceInfo, Long signInTime, Boolean success, String detailsOfFail){
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(Integer.parseInt(userId));
        loginLog.setSignInIp(loginIp);
        loginLog.setDeviceInfo(deviceInfo);
        loginLog.setSignInTime(new Date(signInTime));
        loginLog.setSuccess(success ? DBEnum.TRUE.getCode() : DBEnum.DELETE.getCode());
        loginLog.setDeviceInfo(detailsOfFail);

        return this.insertLog(loginLog);
    }

    /**
     * 更新登录日志的登出时间
     * @param id 待更新记录的Id
     * @return
     */
    public int updateSignOutTime(int id) {
        LoginLog loginLog = new LoginLog();
        loginLog.setId(id);
        loginLog.setSignOutTime(new Date(System.currentTimeMillis()));

        LoginLogQuery loginLogQuery = new LoginLogQuery();
        LoginLogQuery.Criteria criteria =  loginLogQuery.createCriteria();
        criteria.andIdEqualTo(id);

        return loginLogMapper.updateByQuerySelective(loginLog, loginLogQuery);
    }



    public int insertLog(LoginLog loginLog) {
        return loginLogMapper.insert(loginLog);
    }
}
