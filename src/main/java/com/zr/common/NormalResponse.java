package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用于通用函数返回，包括
 */
public class NormalResponse {
    /**
     * 是否成功,不成功时参见错误代码
     * false/ true
     */
    private boolean success = true;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 结果代码,参见 ErrorCodeEnum
     * 注意，这里只是有业务错误的时候才会有
     */
    private Integer errorCode;
    /**
     * 返回的数据,这里一般是函数的返回值
     */
    private Object data;
    /**
     * 快捷访问接口,帮助提示接口
     */
    private List href;
    /**
     * 页码信息
     */
    private PageInfo pageInfo;


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 设置resultcode的时候同时设置resultMsg和isSuccess
     * @param resultCode
     */
    public NormalResponse setErrorCode(ErrorEnum resultCode) {
        this.errorCode = resultCode.getCode();
        this.success = false;

        if(null != this.errorMsg) {
            this.errorMsg = resultCode.getMsg();
        }

        return this;
    }



    public PageInfo getPageInfo() {
        return pageInfo;
    }


    public NormalResponse setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }


    public String getErrorMsg() {
        return errorMsg;
    }

    public NormalResponse setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Object getData() {
        return data;
    }

    public NormalResponse setData(Object data) {
        this.data = data;
        return this;
    }



    public List getHref() {
        return href;
    }

    public NormalResponse setHref(List href) {
        this.href = href;
        return this;
    }

    public static  NormalResponse  newInstance() {
        return new NormalResponse();
    }

}


