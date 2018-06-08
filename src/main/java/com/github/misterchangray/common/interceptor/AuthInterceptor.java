package com.github.misterchangray.common.interceptor;


import com.github.misterchangray.common.annotation.Authentication;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.exception.ServiceException;
import com.github.misterchangray.service.user.UserService;
import com.github.misterchangray.service.user.bo.UserSessionBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 权限校验拦截器：
 * 1.首先判断访问的方法是否包含 @Authentication 注解;
 * 2.再判断当前登录用户集合中是否存在该session；并校验session正确性
 *
 * tips:请在 tokenValidate 方法中实现你自己的登录验证;
 *
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/26/2018.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    UserService userService;
    @Autowired
    UserSessionBo userSessionBo;

    /**
     * 在这个方法里实现你的权限验证逻辑
     * 成功返回true;失败返回false
     * @return
     */
    private boolean tokenValidate(String token) {
        return userSessionBo.exist(token);
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果是OPTIONS请求则跳过
        if( null != request.getMethod() && "options".equals(request.getMethod().toLowerCase())) {
            return true;
        }

        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // 通过注解判断接口是否需要权限认证
        Authentication classAnnotation = method.getDeclaringClass().getAnnotation(Authentication.class);
        Authentication methodAnnotation = method.getAnnotation(Authentication.class);
        // 有 @Authentication 注解，需要认证
        if (null != classAnnotation || methodAnnotation != null) {
            // 执行权限认证
            String token = request.getHeader("Authentication");  // 从 http 请求头中取出 Authentication

            if (null == token) throw new ServiceException(ResultEnum.NEED_AUTH, "无token，请先登录");

            if(tokenValidate(token)) throw new ServiceException(ResultEnum.NEED_AUTH, "token异常，请重新登录");
            return true;
        }
        return true;

    }
}
