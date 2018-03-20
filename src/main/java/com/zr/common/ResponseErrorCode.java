package com.zr.common;

/**
 * Created by Miste on 3/20/2018.
 */
public enum ResponseErrorCode {
    OK(200), SERVER_ERROR(500);

    private int code;

    public int getCode() {
        return code;
    }
    private ResponseErrorCode(int code) {
        this.code = code;
    }
}
