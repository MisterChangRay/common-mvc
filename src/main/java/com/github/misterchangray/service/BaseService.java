package com.github.misterchangray.service;


import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.common.NormalResponse;
import com.github.misterchangray.common.PageInfo;
import com.github.misterchangray.common.exception.ServiceException;

import javax.management.Query;
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
    NormalResponse exist(List<Integer> ids);

    /**
     * 根据实体ID获取对象
     * @param id
     * @return
     */
    NormalResponse getById(Integer id);


    /**
     * 根据实体ID获取对象
     * @param ids
     * @return
     */
    NormalResponse getByIds(List<Integer> ids);

    /**
     * 简单列表
     * @param entity
     * @return
     */
    NormalResponse list(Entity entity, PageInfo pageInfo);

    /**
     * 新增
     * @param entity
     * @return
     */
    NormalResponse insert(Entity entity);

    /**
     * 批量新增
     * @param entities
     * @return
     */
    NormalResponse batchInsert(List<Entity> entities);

    /**
     * 编辑
     * @param entity
     * @return
     */
    NormalResponse update(Entity entity);


    /**
     * 删除实体
     * @param entity
     * @return
     */
    NormalResponse delete(Entity entity);

}
