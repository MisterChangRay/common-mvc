package com.zr.common;

/**
 * Created by Miste on 3/20/2018.
 *
 * 请注意：
 * id 0-999 是成功标识
 * id 1000-2000 是失败或异常标识
 *
 */
public enum ResultEnum {
    SUCCESS(1, "成功"), //成功
    FAIL(1001, "失败"), //失败

    CREATE_SUCCESS(201, "创建成功"), //新增正常
    UPDATE_SUCCESS(202, "更新成功"), //修改正常
    DELETE_SUCCESS(203, "删除成功"), //删除正常
    QUERY_SUCCESS(204, "查询成功"),//查询正常

    INVALID_REQUEST(1003, "参数错误或格式错误"), //无效请求,请求有格式或数据错误
    NEED_AUTH(1004, "需要权限认证"), //需要认证,没有权限
    DISABLED(1005, "资源已被禁用"),//资源已被禁用
    INVALID(1006, "资源无效"),//资源无效
    NOT_FOUND(1009, "资源不存在"),//资源不存在
    EXIST(1007, "资源已存在"),//资源已存在
    GONE(1008, "资源已被删除"), //资源已经被删除

    SERVER_ERROR(1009, "服务器错误"); //服务器错误




    private int code;
    private String msg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    private ResultEnum(Integer code, String msg) {
        this.code = code;
    }
}
