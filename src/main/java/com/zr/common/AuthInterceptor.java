package com.zr.common;


import com.zr.common.annotation.Authentication;
import com.zr.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by Miste on 3/26/2018.
 * 权限校验拦截器
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 通过注解判断接口是否需要权限认证
        Authentication methodAnnotation = method.getAnnotation(Authentication.class);
        // 有 @LoginRequired 注解，需要认证
        if (methodAnnotation != null) {
            // 执行认证
            String token = request.getHeader("Authentication");  // 从 http 请求头中取出 Authentication
            if (token == null) {
                throw new RuntimeException("无token，请重新登录");
            }
            return true;
        }
        return true;

    }
}
