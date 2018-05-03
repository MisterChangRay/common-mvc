package com.github.misterchangray.common.aop;

import com.github.misterchangray.common.annotation.OperationLog;
import com.github.misterchangray.common.utils.JSONUtils;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.service.log.OperationLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;


/**
 * 自定义注解
 * AOP统一处理操作日志
 * 这里统一拦截处理@OperationLog注解的方法
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/4/23.
 */
@Component
@Aspect
public class OperationLogAop {
   @Autowired
   OperationLogService operationLogService;

    @Pointcut(value = "@annotation(com.github.misterchangray.common.annotation.OperationLog)")
    private void OperationLog() {}


    @Around(value = "OperationLog()")
    public Object around(ProceedingJoinPoint point) {
       Object res;
       try {
            res = point.proceed();
       } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
       }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");

        Method targetMethod = ((MethodSignature)(point.getSignature())).getMethod();
        OperationLog annotation = targetMethod.getAnnotation(OperationLog.class);
        String signature, businessName, userName, data;
        Integer userId = user.getId();
        signature = targetMethod.toString();
        businessName = annotation.businessName();
        userName = user.getUsername();
        data = JSONUtils.obj2json(point.getArgs());

        this.operationLogService.addLog(signature, businessName, userId, userName, data);
        return  res;
    }
}
