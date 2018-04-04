package com.zr.service;


import com.zr.common.NormalResponse;
import com.zr.common.PageInfo;

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
    NormalResponse exist(List<Integer> ids);


    /**
     * 查询ID集合
     * @param ids
     * @return
     */
    NormalResponse getByIds(List<Integer> ids);


    /**
     * 列表
     * @param vo
     * @return
     */
    NormalResponse list(T vo, PageInfo pageInfo);

    /**
     * 新增
     * @param vo
     * @return
     */
    NormalResponse add(T vo);

    /**
     * 删除
     * @param id
     * @return
     */
    NormalResponse delete(Integer id);

    /**
     * 编辑
     * @param vo
     * @return
     */
    NormalResponse update(T vo);

    /**
     * 根据Id获取对象
     * @param id
     * @return
     */
    NormalResponse getById(Integer id);
}
