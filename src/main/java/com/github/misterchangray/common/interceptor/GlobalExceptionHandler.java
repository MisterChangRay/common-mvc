package com.github.misterchangray.common.interceptor;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.exception.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 提供全局统一异常处理
 * @author Created by rui.zhang on 2018/6/4.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description
 */
@ControllerAdvice()
public class GlobalExceptionHandler {
    Logger logger = Logger.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public NormalResponse serviceExceptionHandler(Exception ex) {
        //对捕获的异常进行处理并打印日志等，之后返回json数据，方式与Controller相同
        ex.printStackTrace();
        ServiceException serviceException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if(ex instanceof ServiceException) {
            serviceException = (ServiceException) ex;
            logger.info(ex.getMessage(), ex);
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            serviceException = new ServiceException(ResultEnum.SERVER_ERROR);
            logger.error(ex.getMessage(), ex);

        }

        NormalResponse normalResponse = NormalResponse.build(serviceException.getResultEnum());
        if(null != serviceException.getMsg()) normalResponse.setMsg(serviceException.getMsg());

        return normalResponse;
    }


}