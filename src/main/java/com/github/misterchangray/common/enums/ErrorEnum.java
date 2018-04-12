package com.github.misterchangray.common.enums;

/**
 * Created by Miste on 3/20/2018.
 *
 * 错误消息，请注意此类只应该包含错误消息：
 *
 * ID格式：
 * 1 01 001
 * 1 代表错误等级
 * 01 代表第01个模块
 * 001 代表第10模块内的第一个错误消息序号
 *
 */
public enum ErrorEnum {
    INVALID_REQUEST(101001, "参数错误或格式错误"), //无效请求,请求有格式或数据错误
    NEED_AUTH(101002, "需要权限认证"), //需要认证,没有权限
    DISABLED(101003, "资源已被禁用"),//资源已被禁用
    INVALID(101004, "资源无效"),//资源无效
    NOT_FOUND(101005, "资源不存在"),//资源不存在
    EXIST(101006, "资源已存在"),//资源已存在
    GONE(1001007, "资源已被删除"), //资源已经被删除
    SERVER_ERROR(101008, "服务器错误"); //服务器错误




    private int code;
    private String msg;

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    private ErrorEnum(Integer code, String msg) {
        this.code = code;
    }
}
