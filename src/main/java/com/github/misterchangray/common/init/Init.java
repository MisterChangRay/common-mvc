package com.github.misterchangray.common.init;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * spring 容器初始化方法;在spring初始化完成后執行此方法
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
@Component
public class Init implements ApplicationListener<ContextRefreshedEvent> {


    //需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


    }
}