package com.github.misterchangray.service;


import com.github.misterchangray.common.ResultSet;
import com.github.misterchangray.common.PageInfo;

import java.util.List;

/**
 * 基础服务定义
 *
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on  2018/4/20.
 */
public interface BaseService<Entity> {

    /**
     * 查询id是否存在
     * @param ids
     * @return
     */
    ResultSet exist(List<Integer> ids);

    /**
     * 根据实体ID获取对象
     * @param id
     * @return
     */
    ResultSet getById(Integer id);


    /**
     * 根据实体ID获取对象
     * @param ids
     * @return
     */
    ResultSet getByIds(List<Integer> ids);

    /**
     * 简单列表
     * @param entity
     * @return
     */
    ResultSet list(Entity entity, PageInfo pageInfo);

    /**
     * 新增
     * @param entity
     * @return
     */
    ResultSet insert(Entity entity);

    /**
     * 批量新增
     * @param entities
     * @return
     */
    ResultSet batchInsert(List<Entity> entities);

    /**
     * 编辑
     * @param entity
     * @return
     */
    ResultSet update(Entity entity);


    /**
     * 删除实体
     * @param entity
     * @return
     */
    ResultSet delete(Entity entity);

}
