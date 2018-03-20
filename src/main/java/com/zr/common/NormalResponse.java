package com.zr.common;

import java.util.List;

/**
 * Created by Miste on 3/20/2018.
 */
public class NormalResponse {
    private String errormsg;
    private ResponseErrorCode errorcode;
    private List data;
    private List href;

    public ResponseErrorCode getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(ResponseErrorCode errorcode) {
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
