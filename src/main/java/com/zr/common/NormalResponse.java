package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
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

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode.getCode();
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public List getHref() {
        return href;
    }

    public void setHref(List href) {
        this.href = href;
    }
}


