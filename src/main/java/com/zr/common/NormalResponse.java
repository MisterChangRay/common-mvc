package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用于通用函数返回，包括
 */
public class NormalResponse {
    /**
     * 错误信息
     */
    private String errorMsg;
    /**
     * 错误代码,参见 ErrorCodeEnum
     */
    private Integer errorCode;
    /**
     * 返回的数据
     */
    private Object data;
    /**
     * 快捷访问接口
     */
    private List href;
    /**
     * 页码信息
     */
    private PageInfo pageInfo;


    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public NormalResponse setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public NormalResponse setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode.getCode();
        this.errorMsg = errorCode.getMsg();
        return this;
    }

    public Object getData() {
        return data;
    }

    public NormalResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public NormalResponse setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        return  this;
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


