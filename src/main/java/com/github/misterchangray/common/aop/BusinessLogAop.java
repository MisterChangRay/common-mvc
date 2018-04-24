package com.github.misterchangray.common.aop;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.utils.JSONUtils;
import com.github.misterchangray.dao.entity.BusinessLog;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.dao.mapper.BusinessLogMapper;
import com.github.misterchangray.service.BusinessLogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by rui.zhang on 2018/4/23.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description AOP统一处理业务日志
 * 这里统一拦截处理BaseService中的以下方法：
 * - insert
 * - batchInsert
 * - update
 * - delete
 */
@Component
@Aspect
public class BusinessLogAop {
   @Autowired
    BusinessLogService businessLogService;

    @Pointcut(value = "execution(* com.github.misterchangray.service.BaseService.insert(..))")
    private void insert() {}

    @Pointcut(value = "execution(* com.github.misterchangray.service.BaseService.batchInsert(..))")
    private void batchInsert() {}

    @Pointcut(value = "execution(* com.github.misterchangray.service.BaseService.update(..))")
    private void update() {}

    @Pointcut(value = "execution(* com.github.misterchangray.service.BaseService.delete(..))")
    private void delete() {}

    @Around(value = "insert()")
    public Object insertAround(ProceedingJoinPoint point) {
       Object res;
       try {
            res = point.proceed();
       } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
       }
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");

        Object newValue = ((NormalResponse) res).getData();
        Object param = point.getArgs()[0];
        businessLogService.addLog("", "", user.getId().toString(), user.getUsername(), DBEnum.INSERT, JSONUtils.obj2json(newValue), null);
        return  res;
    }

    @Around(value = "batchInsert()")
    public Object batchInsertAround(ProceedingJoinPoint point) {
        Object res;

        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }


        return  res;
    }

    @Around(value = "update()")
    public Object updateAround(ProceedingJoinPoint point) {
        Object res;

        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        User user = (User) request.getSession().getAttribute("user");

        Object newValue = ((NormalResponse) res).getData();
        Object oldValue = point.getArgs()[0];
        businessLogService.addLog("", "", user.getId().toString(), user.getUsername(), DBEnum.UPDATE, JSONUtils.obj2json(newValue), JSONUtils.obj2json(oldValue));
        System.out.println("11111111111lan拦截到修改操作");
        return  res;
    }

    @Around(value = "delete()")
    public Object deleteAround(ProceedingJoinPoint point) {
        Object res;

        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }


        return  res;
    }
}
