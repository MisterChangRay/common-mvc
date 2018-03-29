package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 *
 * 用于通用函数返回，包括
 */
public class NormalResponse {
    /**
     * 处理结果是否正常,该值只有true和false
     * 请注意，这里只会在正常返回结果的情况下为true
     */
    private Boolean isSuccess;
    /**
     * 结果消息
     */
    private String resultMsg;
    /**
     * 结果代码,参见 ResultEnum
     */
    private Integer resultCode;
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





    /**
     * 设置resultcode的时候同时设置resultMsg和isSuccess
     * @param resultCode
     */
    public NormalResponse setResult(ResultEnum resultCode) {
        this.resultCode = resultCode.getCode();
        if(null != this.resultMsg) {
            this.resultMsg = resultCode.getMsg();
        }
        if(null != this.resultCode && this.resultCode < 999) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
        return this;
    }


    /**
     * 设置resultcode的时候同时设置resultMsg和isSuccess
     * @param data
     * @param resultCode
     */
    public NormalResponse setResult(Object data, ResultEnum resultCode) {
        this.data = data;
        this.resultCode = resultCode.getCode();
        if(null != this.resultMsg) {
            this.resultMsg = resultCode.getMsg();
        }
        if(null != this.resultCode && this.resultCode < 999) {
            this.isSuccess = true;
        } else {
            this.isSuccess = false;
        }
        return this;
    }



    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public Boolean isSuccess() {
        return isSuccess;
    }

    public NormalResponse setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }


    public String getResultMsg() {
        return resultMsg;
    }

    public NormalResponse setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
        return this;
    }

    public Integer getResultCode() {
        return resultCode;
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


