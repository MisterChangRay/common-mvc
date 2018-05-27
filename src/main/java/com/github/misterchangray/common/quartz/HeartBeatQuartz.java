package com.github.misterchangray.common.quartz;

import com.github.misterchangray.service.common.SessionCacheService;
import com.github.misterchangray.service.user.LoginService;
import com.github.misterchangray.service.user.vo.UserSessionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;

/**
 * 心跳检测;每分钟检测一次;超过三分钟未更新心跳时间则判定为死亡
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/5/3.
 */
@Component("HeartBeatQuartz")
public class HeartBeatQuartz {
    @Autowired
    LoginService loginService;
    @Autowired
    SessionCacheService sessionCacheService;


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
     *
     * 根据心跳时间;清除无效的session
     * 计算公式：
     * 时间差 = 当前时间 - 最近心跳时间
     * 当时间差大于 3分钟 时;该 session 判定为死亡
     */
    @Scheduled(fixedDelayString = "60000")
    public void HeartBeatQuartz() {
        //每分钟执行一次;清除死亡的session
        Map<String, UserSessionVO> onLineUsers = (Map<String, UserSessionVO>) sessionCacheService.get("onLineUsers");
        UserSessionVO userSessionVO = null;
        int threeMinutes = 3 * 60 * 1000;
        long currentTimeMillis = System.currentTimeMillis();


        Iterator<Map.Entry<String, UserSessionVO>> it = onLineUsers.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, UserSessionVO> entry = it.next();
            userSessionVO = entry.getValue();
            //三分钟没有心跳则注销该session;
            if(threeMinutes < currentTimeMillis - userSessionVO.getHeartBeatDate()) {
                loginService.signOut(userSessionVO.getSession());
            }
        }


    }
}
