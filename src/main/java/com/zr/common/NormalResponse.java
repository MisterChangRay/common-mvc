package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 */
public class NormalResponse {
    /**
     * 错误信息
     */
    private String errormsg;
    /**
     * 错误代码,参见 ErrorCodeEnum
     */
    private ErrorCodeEnum errorcode;
    /**
     * 返回的数据
     */
    private List data;
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

    public ErrorCodeEnum getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(ErrorCodeEnum errorcode) {
        this.errorcode = errorcode;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public void setErrormsg(String errormsg) {
        this.errormsg = errormsg;
    }


    public List getHref() {
        return href;
    }

    public void setHref(List href) {
        this.href = href;
    }
}


