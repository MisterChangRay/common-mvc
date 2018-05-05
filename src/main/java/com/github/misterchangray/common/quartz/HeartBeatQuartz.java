package com.github.misterchangray.common.quartz;

import com.github.misterchangray.service.user.LoginService;
import com.github.misterchangray.service.user.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 心跳检测;每分钟检测一次;超过三分钟未更新心跳时间则判定为死亡
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/5/3.
 */
@Component("HeartBeatQuartz")
public class HeartBeatQuartz {
    @Autowired
    UserSessionService userSessionService;
    @Autowired
    LoginService loginService;


    /**
     * cronExpression的配置说明
     * 字段   允许值   允许的特殊字符
     * 秒    0-59    , - * /
     * 分    0-59    , - * /
     * 小时   0-23    , - * /
     * 日期    1-31    , - * ? / L W C
     * 月份    1-12 或者 JAN-DEC    , - * /
     * 星期    1-7 或者 SUN-SAT    , - * ? / L C #
     * 年（可选）    留空, 1970-2099    , - * /
     * - 区间
     * * 通配符
     * ? 你不想设置那个字段
     *
     * 心跳检测;每分钟一次
     */
    @Scheduled(cron = "0 0/1 * * * ?")
    public void HeartBeatQuartz() {
        //每分钟清除一次死亡的session
        userSessionService.clearInvalid(3);
    }
}
