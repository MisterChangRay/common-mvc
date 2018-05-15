package com.github.misterchangray.common;

import com.github.misterchangray.common.enums.ErrorEnum;
import com.github.misterchangray.common.utils.JSONUtils;
import com.github.misterchangray.dao.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 通用返回类
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@ApiModel(description = "标准返回封装-NormalResponse")
public class NormalResponse <T> {
    /**
     * 是否成功,不成功时参见错误代码
     * false/ true
     */
    @ApiModelProperty(value = "业务是否成功，如果失败参见errorMsg", dataType = "boolean")
    private boolean success = true;
    /**
     * 错误消息
     */
    @ApiModelProperty(value = "此值只有在success为false时才有参考价值", dataType = "Integer")
    private String errorMsg;
    /**
     * 结果代码,参见 ErrorCodeEnum
     * 注意，这里只是有业务错误的时候才会有
     */
    @ApiModelProperty(value = "此值只有在success为false时才有参考价值", dataType = "Integer")
    private Integer errorCode;
    /**
     * 返回的数据,这里一般是函数的返回值
     */
    @ApiModelProperty(value = "结果返回 JSON 格式", dataType = "JSON")
    private T data;
    /**
     * 快捷访问接口,帮助提示接口
     */
    @ApiModelProperty(value = "帮助接口", dataType = "Array")
    private List href;
    /**
     * 页码信息
     */
    @ApiModelProperty(value = "页码信息", dataType = "PageInfo")
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

        if(null == this.errorMsg) {
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

    public T getData() {
        return data;
    }

    public NormalResponse setData(T data) {
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

    public static NormalResponse newInstance() {
        return new NormalResponse();
    }

    @Override
    public String toString() {
        return JSONUtils.obj2json(this);
    }

    private NormalResponse() {}
}


