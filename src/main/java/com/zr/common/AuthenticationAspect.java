package com.zr.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Miste on 3/22/2018.
 *
 * 拦截权限注解，在执行目标代码前执行权限认证逻辑
 *
 */
@Component
@Aspect
public class AuthenticationAspect {

    @Pointcut(value = "@annotation(com.zr.common.Authentication)")
    private void pointcut() {}

    @Around(value = "pointcut() && @annotation(Authentication)")
    public Object around(ProceedingJoinPoint point) {
        System.out.println("这个方法需要进行权限检查");
        //        这里写你的权限校验方法;如果校验没通过可以直接返回
        try {
            return point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
    }

}
