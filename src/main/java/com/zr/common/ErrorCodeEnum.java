package com.zr.common;

/**
 * Created by Miste on 3/20/2018.
 */
public enum ErrorCodeEnum {
    OK(200), //一切正常
    CREATE_OK(201), //新增正常
    UPDATE_OK(202), //修改正常
    DELETE_OK(203), //删除正常
    QUERY_OK(204),//查询正常

    INVALID_REQUEST(400), //无效请求,请求有格式或数据错误
    NEED_AUTH(401), //需要认证,没有权限
    INVALID_USER(402),//无效用户
    DISABLED(403),//资源已被禁用

    GONE(410), //资源已经被删除

    SERVER_ERROR(500), //服务器错误
    FAIL(501); //请求失败

    private int code;

    public int getCode() {
        return code;
    }
    private ErrorCodeEnum(int code) {
        this.code = code;
    }
}
