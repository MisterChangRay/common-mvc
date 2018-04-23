package com.github.misterchangray.common.enums;

/**
 * Created by Miste on 3/27/2018.
 * 设置数据库中的一些枚举
 * 请注意数据库中设置的数据类型
 */
public enum DBEnum {
    TRUE(1, "true"), //表肯定
    FALSE(0, "false"), //表否定

    MAN(1, "男"), //男
    WOMAN(0, "女"),//女

    QUERY(100, "query"), //查询
    INSERT(101, "insert"),//新增
    UPDATE(102, "update"),//修改
    DELETE(103, "delete");//删除

    private Integer code;
    private String desc;

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
    private DBEnum(Integer code, String desc) {
        this.code = code;
    }
}
