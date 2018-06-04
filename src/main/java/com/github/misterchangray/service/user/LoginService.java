package com.github.misterchangray.service.user;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.exception.ServiceException;

/**
 * LoginService
 * 提供用户登录服务
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/29/2018.
 */
public interface LoginService {
    /**
     * 通过账号+密码登录
     * @param username
     * @param password
     * @return
     */
    NormalResponse signInByUserName(String username, String password) throws ServiceException;

    /**
     * 通过邮箱+密码登录
     * @param email
     * @param password
     * @return
     */
    NormalResponse signInByEmail(String email, String password);

    /**
     * 通过短信+验证码登录
     * @param phone
     * @param verificationCode
     * @return
     */
    NormalResponse signInByPhone(String phone, String verificationCode);



    /**
     * 登出用户
     * @param userId
     * @return
     */
    NormalResponse signOut(String userId);

}
