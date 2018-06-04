package com.github.misterchangray.common.aop;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.DBEnum;
import com.github.misterchangray.common.utils.HttpRequestParserUtils;
import com.github.misterchangray.common.utils.JSONUtils;
import com.github.misterchangray.common.utils.MapBuilder;
import com.github.misterchangray.dao.entity.LoginLog;
import com.github.misterchangray.dao.entity.User;
import com.github.misterchangray.service.common.RedisCacheService;
import com.github.misterchangray.service.log.LoginLogService;
import com.github.misterchangray.service.user.bo.UserSessionBo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


/**
 * 登录日志
 * <p>
 * 从业务上来讲：衹要是系統用戶;无论是否登录成功都应该记录日志;方便日后分析
 * 增加登录日志在 LoginService.signIn 方法中调用
 * 登出时间在用户 session 销毁时更新;即 UserSession
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 2018/5/2.
 */
@Component
@Aspect
public class UserLoginLogAop {
    @Autowired
    LoginLogService loginLogService;
    @Autowired
    HttpServletRequest httpServletRequest;
    @Autowired
    UserSessionBo userSessionBo;
    @Autowired
    RedisCacheService redisCacheService;

    //創建用戶session時;創建日志
    @Pointcut(value = "execution(com.github.misterchangray.common.NormalResponse com.github.misterchangray.service.user.LoginService.signInBy*(..))")
    private void createSession() {}

    //銷毀用戶session時;更新用戶登出時間
    @Pointcut(value = "execution(void com.github.misterchangray.service.user.bo.UserSessionBo.destroySession(..))")
    private void destroySession() {}


    @Around(value = "createSession()")
    public Object createSessionAround(ProceedingJoinPoint point) throws Throwable {
        Object res;
        res = point.proceed();


        NormalResponse normalResponse = (NormalResponse) res;
        if(null == normalResponse) return res;

        if(0 != normalResponse.getCode()) return res; //打开此行则只记录登录成功的用户

        User user = null;
        String session = null;
        MapBuilder mapBuilder = null;
        if(null != normalResponse.getData()) {
            mapBuilder = (MapBuilder) normalResponse.getData();
            if(null != mapBuilder) {
                user = (User) mapBuilder.get("user");
                session = (String) mapBuilder.get("Authentication");
            }
        }

        //登录日志数据
        LoginLog loginLog = new LoginLog();
        if(null != user) {
            loginLog.setUserId(user.getId());
        }
        loginLog.setSignInIp(HttpRequestParserUtils.getUserIpAddr(httpServletRequest));
        loginLog.setDeviceInfo(HttpRequestParserUtils.getUserAgent(httpServletRequest));
        loginLog.setSignInTime(new Date());
        loginLog.setSuccess(0 == normalResponse.getCode() ? DBEnum.TRUE.getCode() : DBEnum.FALSE.getCode());
        if(0 != normalResponse.getCode()) {
            loginLog.setDetailsOfFail(normalResponse.getMsg());
        }
        loginLog.setSignInParam(JSONUtils.obj2json(point.getArgs()));
        loginLog.setSession(session);
        loginLogService.insertLog(loginLog);

        return res;
    }


    @Around(value = "destroySession()")
    public Object destroySessionAround(ProceedingJoinPoint point) {
        Object res;

        String session = (String) point.getArgs()[0];

        if(null != session) {
            loginLogService.updateSignOutTime(session);
        }

        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
        return res;
    }
}
