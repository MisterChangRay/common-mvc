package com.zr.common;

/**
 * Created by Miste on 3/27/2018.
 * 设置数据库中的一些枚举
 * 请注意数据库中设置的数据类型
 */
public enum DBEnum {
    TRUE(1), //表肯定
    FALSE(0), //表否定

    MAN(1), //男
    WOMAN(0);//女

    private int code;

    public int getCode() {
        return code;
    }
    private DBEnum(int code) {
        this.code = code;
    }
}
