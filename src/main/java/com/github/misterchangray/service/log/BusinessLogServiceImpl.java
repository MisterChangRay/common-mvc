package com.github.misterchangray.service.log;

import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.dao.entity.BusinessLog;
import com.github.misterchangray.dao.mapper.BusinessLogMapper;
import com.github.misterchangray.service.BusinessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

/**
 * @author Created by rui.zhang on 2018/4/20.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description 这里可以实现种日志写入方式,默认实现mysql;推荐es或者mongodb数据库
 *
 */
@Service
public class BusinessLogServiceImpl implements BusinessLogService {
    @Autowired
    BusinessLogMapper businessLogMapper;

    public int addLog(String tableName, String businessName, String userId, String userName, DBEnum method, String newValue, String oldValue) {
        BusinessLog businessLog = new BusinessLog();
        businessLog.setTableName(tableName);
        businessLog.setBusinessName(businessName);
        businessLog.setUserId(userId);
        businessLog.setUserName(userName);
        businessLog.setMethod(method.getDesc());
        businessLog.setNewValue(newValue);
        businessLog.setOldValue(oldValue);
        return this.addLog(businessLog);
    }

    public int addLog(BusinessLog businessLog) {
        businessLog.setCreateDate(new java.util.Date());
        return businessLogMapper.insert(businessLog);
    }
}
