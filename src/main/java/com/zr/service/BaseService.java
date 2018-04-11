package com.zr.service;


import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;

import javax.management.Query;
import java.util.List;

/**
 * Created by Miste on 3/27/2018.
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
    NormalResponse add(Entity entity);

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
