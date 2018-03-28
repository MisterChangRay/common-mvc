package com.zr.service;


import java.util.List;

/**
 * Created by Miste on 3/27/2018.
 */
public interface BaseService<T> {

    /**
     * 查询ID是否存在
     * @param ids
     * @return
     */
    boolean exist(List<Integer> ids);


    /**
     * 查询ID集合
     * @param ids
     * @return
     */
    List<T> getByIds(List<Integer> ids);


    /**
     * 列表
     * @param entity
     * @return
     */
    List<T> list(T entity);

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
