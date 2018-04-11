package com.github.misterchangray.service.user;

import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.service.BaseService;

/**
 * Created by Miste on 3/29/2018.
 * AuthService只需要查询
 */
public interface AuthService {
    NormalResponse login();
}
