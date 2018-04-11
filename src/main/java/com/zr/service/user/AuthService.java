package com.zr.service.user;

import com.zr.common.NormalResponse;
import com.zr.service.BaseService;

/**
 * Created by Miste on 3/29/2018.
 * AuthService只需要查询
 */
public interface AuthService {
    NormalResponse login();
}
