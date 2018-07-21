package com.github.misterchangray.common;

import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.utils.JSONUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通用结果集
 * 用于ajax返回
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 3/20/2018.
 */
@ApiModel(description = "标准返回封装-AjaxResultSet")
public class AjaxResultSet<T> {
    /**
     * 返回消息
     */
    @ApiModelProperty(value = "消息信息", dataType = "String")
    private String msg;
    /**
     * 结果代码,参见 ResultEnum
     */
    @ApiModelProperty(value = "结果代码;0为成功;非0失败", dataType = "Integer")
    private Integer code;
    /**
     * 返回的数据,这里一般是函数的返回值
     */
    @ApiModelProperty(value = "结果返回 JSON 格式", dataType = "JSON")
    private T data;
    /**
     * 页码信息
     */
    @ApiModelProperty(value = "页码信息", dataType = "PageInfo")
    private PageInfo pageInfo;



    public PageInfo getPageInfo() {
        return pageInfo;
    }


    public AjaxResultSet setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public AjaxResultSet setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public AjaxResultSet setCode(Integer code) {
        this.code = code;
        return this;
    }

    public AjaxResultSet setCode(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public AjaxResultSet setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }


    public static AjaxResultSet build() {
        AjaxResultSet ajaxResultSet = new AjaxResultSet();
        ajaxResultSet.setCode(ResultEnum.SUCCESS.getCode());
        ajaxResultSet.setMsg(ResultEnum.SUCCESS.getMsg());
        return ajaxResultSet;
    }

    public static AjaxResultSet build(ResultEnum resultEnum) {
        AjaxResultSet ajaxResultSet = new AjaxResultSet();
        if(null != resultEnum) {
            ajaxResultSet.setCode(resultEnum.getCode());
            ajaxResultSet.setMsg(resultEnum.getMsg());
        } else {
            ajaxResultSet.setCode(ResultEnum.SERVER_ERROR.getCode());
            ajaxResultSet.setMsg(ResultEnum.SERVER_ERROR.getMsg());
        }
        return ajaxResultSet;
    }

    @Override
    public String toString() {
        return JSONUtils.obj2json(this);
    }

    private AjaxResultSet() {}
}


