package com.github.misterchangray.common;

import com.github.misterchangray.common.enums.ResultEnum;
import com.github.misterchangray.common.utils.JSONUtils;
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
     * 返回消息
     */
    @ApiModelProperty(value = "返回消息", dataType = "String")
    private String msg;
    /**
     * 结果代码,参见 ResultEnum
     */
    @ApiModelProperty(value = "结果代码;0为成功", dataType = "Integer")
    private Integer code;
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



    public PageInfo getPageInfo() {
        return pageInfo;
    }


    public NormalResponse setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        return this;
    }

    public NormalResponse setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public NormalResponse setCode(Integer code) {
        this.code = code;
        return this;
    }

    public NormalResponse setCode(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public NormalResponse setData(T data) {
        this.data = data;
        return this;
    }

    public T getData() {
        return data;
    }

    public List getHref() {
        return href;
    }

    public NormalResponse setHref(List href) {
        this.href = href;
        return this;
    }

    public static NormalResponse build() {
        NormalResponse normalResponse = new NormalResponse();
        normalResponse.setCode(ResultEnum.SUCCESS.getCode());
        normalResponse.setMsg(ResultEnum.SUCCESS.getMsg());
        return normalResponse;
    }

    public static NormalResponse build(ResultEnum resultEnum) {
        NormalResponse normalResponse = new NormalResponse();
        if(null != resultEnum) {
            normalResponse.setCode(resultEnum.getCode());
            normalResponse.setMsg(resultEnum.getMsg());
        } else {
            normalResponse.setCode(ResultEnum.SERVER_ERROR.getCode());
            normalResponse.setMsg(ResultEnum.SERVER_ERROR.getMsg());
        }
        return normalResponse;
    }

    @Override
    public String toString() {
        return JSONUtils.obj2json(this);
    }

    private NormalResponse() {}
}


