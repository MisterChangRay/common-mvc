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
 *
 * AOP统一处理用户登陆日志
 * 这里拦截AuthService中的 signIn 和 signOut 方法
 *
 * 登陆日志通过以下几个方法记录：
 * - AOP统一记录用户 登录 和登出时间
 * - 轮询任务中;当3个周期还没有检测到心跳接口被访问;则自动 signOut;
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/5/2.
 */
@Component
@Aspect
public class UserLoginLogAop {
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
