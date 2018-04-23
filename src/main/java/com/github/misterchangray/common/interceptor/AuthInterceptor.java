package com.github.misterchangray.common.interceptor;


import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.utils.JSONUtils;
import com.github.misterchangray.service.user.UserService;
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
            String loginToken = (String) request.getSession().getAttribute("Authentication");

            NormalResponse normalResponse = new NormalResponse();
            if (null == token || null == loginToken) {
                normalResponse.setErrorCode(ErrorEnum.NEED_AUTH);
                normalResponse.setErrorMsg("无token，请先登录");
                response.getWriter().append(JSONUtils.obj2json(normalResponse));
                response.setContentType("application/json");
                return false;
            }
            if(!token.equals(loginToken)) {
                normalResponse.setErrorCode(ErrorEnum.NEED_AUTH);
                normalResponse.setErrorMsg("token异常，请重新登录");
                response.getWriter().append(JSONUtils.obj2json(normalResponse));
                response.setContentType("application/json");
                return false;
            }
            return true;
        }
        return true;

    }
}
