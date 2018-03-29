package com.zr.common;

/**
 * Created by Miste on 3/20/2018.
 */
public enum ErrorCodeEnum {
    OK(200, "正常"), //一切正常
    CREATE_OK(201, "正常"), //新增正常
    UPDATE_OK(202, "正常"), //修改正常
    DELETE_OK(203, "正常"), //删除正常
    QUERY_OK(204, "正常"),//查询正常

    INVALID_REQUEST(400, "参数错误或格式错误"), //无效请求,请求有格式或数据错误
    NEED_AUTH(401, "需要权限认证"), //需要认证,没有权限
    INVALID_USER(402, "无效用户"),//无效用户
    DISABLED(403, "资源已被禁用"),//资源已被禁用

    GONE(410, "资源已被删除"), //资源已经被删除

    SERVER_ERROR(500, "服务器错误"), //服务器错误
    FAIL(501, "失败"); //请求失败

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    private ErrorCodeEnum(Integer code, String msg) {
        this.code = code;
    }
}
