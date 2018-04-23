package com.github.misterchangray.service;

import com.github.misterchangray.common.enums.DBEnum;

import java.sql.Date;

/**
 * @author Created by rui.zhang on 2018/4/20.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description 业务日志
 */
public interface BusinessLogService {
    /**
     * 增加业务日志记录
     * @param tableName 表名称
     * @param businessName 业务名称
     * @param userId 用户id
     * @param userName 用户名称
     * @param method 操作方式;DBEnum枚举类(QUERY,INSERT,UPDATE,DELETE)
     * @return
     */
    int addLog(String tableName,String businessName, String userId, String userName, DBEnum method);
}
