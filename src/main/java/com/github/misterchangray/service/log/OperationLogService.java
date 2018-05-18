package com.github.misterchangray.service.log;



/**
 * 操作日志
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/4/20.
 */
public interface OperationLogService {
    /**
     * 增加业务日志记录
     * @param signature 方法限定符
     * @param businessName 业务名称
     * @param userId 用户id
     * @param userName 用户名称
     * @param data 更新数据
     * @return
     */
    int insertLog(String signature, String businessName, Integer userId, String userName, String data);
}
