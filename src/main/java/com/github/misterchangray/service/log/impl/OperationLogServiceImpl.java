package com.github.misterchangray.service.log.impl;

import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.dao.entity.OperationLog;
import com.github.misterchangray.dao.mapper.OperationLogMapper;
import com.github.misterchangray.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 这里可以实现种日志写入方式,默认实现mysql;推荐es或者mongodb数据库
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/4/20.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {
    @Autowired
    OperationLogMapper operationLogMapper;

    public int addLog(String signature, String businessName, Integer userId, String userName, String data) {
        OperationLog operationLog = new OperationLog();
        operationLog.setBusinessName(businessName);
        operationLog.setUserId(userId);
        operationLog.setUserName(userName);
        operationLog.setSignature(signature);
        operationLog.setData(data);
        return this.addLog(operationLog);
    }

    public int addLog(OperationLog operationLog) {
        operationLog.setCreateDate(new java.util.Date());
        return operationLogMapper.insert(operationLog);
    }
}
