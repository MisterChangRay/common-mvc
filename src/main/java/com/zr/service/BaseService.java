package com.zr.service;


import java.util.List;

/**
 * Created by Miste on 3/27/2018.
 */
public interface BaseService<T, K> {
    /**
     * 列表
     * @param entityQuery
     * @return
     */
    List<T> list(K entityQuery);

    /**
     * 新增
     * @param entity
     * @return
     */
    T add(T entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    T delete(T entity);

    /**
     * 编辑
     * @param entity
     * @return
     */
    T update(T entity);

    /**
     * 根据Id获取权限
     * @param id
     * @return
     */
    T getById(Integer id);
}
