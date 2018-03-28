package com.zr.common;

/**
 * Created by Miste on 3/23/2018.
 *
 * 页码信息类
 * 用于返回页码信息
 */
public class PageInfo {
    /**
     * 当前第几页
     */
    private Integer page;
    /**
     * 总共用多少条记录
     */
    private Integer count;
    /**
     * 每页多少数据
     */
    private Integer limit;


    public PageInfo(Integer page, Integer count, Integer limit) {
        this.page = page;
        this.count = count;
        this.limit = limit;
    }

    public PageInfo() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
