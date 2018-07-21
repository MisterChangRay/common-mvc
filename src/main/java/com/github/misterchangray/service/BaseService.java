package com.github.misterchangray.service;


import com.github.misterchangray.common.AjaxResultSet;
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
     * 查询 Id 是否存在
     * @param ids
     * @return
     */
    AjaxResultSet exist(List<Integer> ids);

    /**
     * 根据 Id 获取对象
     * @param id
     * @return
     */
    AjaxResultSet getById(Integer id);


    /**
     * 根据 Id 获取多个对象
     * @param ids
     * @return
     */
    AjaxResultSet getByIds(List<Integer> ids);

    /**
     * 列表
     * @param entity
     * @return
     */
    AjaxResultSet list(Entity entity, PageInfo pageInfo);

    /**
     * 保存
     * @param entity
     * @return
     */
    AjaxResultSet save(Entity entity);

    /**
     * 批量保存
     * @param entities
     * @return
     */
    AjaxResultSet saveAll(List<Entity> entities);

    /**
     * 编辑
     * @param entity
     * @return
     */
    AjaxResultSet edit(Entity entity);


    /**
     * 删除实体
     * @param entity
     * @return
     */
    AjaxResultSet delete(Entity entity);

}
