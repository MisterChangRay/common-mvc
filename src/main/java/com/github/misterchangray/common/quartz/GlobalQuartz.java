package com.github.misterchangray.common.quartz;

import com.github.misterchangray.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * 全局定时任务
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/5/3.
 *
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
 */
@Component("GlobalQuartz")
public class GlobalQuartz {
    Logger logger = LoggerFactory.getLogger(GlobalQuartz.class);

    /**
     * 简单轮询任务
     * fixedDelay = 60 * 1000；单位:毫秒
     */
    @Scheduled(fixedDelay = 60 * 1000)
    public void globalQuartz() {
        // 每分钟执行一次
        logger.info("--------------------------- 定时任务开始[{}] ---------------------------", DateUtils.now(null));
        System.out.println("定时任务");
        logger.info("--------------------------- 定时任务结束[{}] ---------------------------", DateUtils.now(null));

    }
}
