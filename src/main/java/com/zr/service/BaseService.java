package com.zr.service;


import com.zr.common.NormalResponse;

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
    NormalResponse getByIds(List<Integer> ids);


    /**
     * 列表
     * @param entity
     * @return
     */
    NormalResponse list(T entity);

    /**
     * 新增
     * @param entity
     * @return
     */
    NormalResponse add(T entity);

    /**
     * 删除
     * @param entity
     * @return
     */
    NormalResponse delete(T entity);

    /**
     * 编辑
     * @param entity
     * @return
     */
    NormalResponse update(T entity);

    /**
     * 根据Id获取对象
     * @param id
     * @return
     */
    NormalResponse getById(Integer id);
}
