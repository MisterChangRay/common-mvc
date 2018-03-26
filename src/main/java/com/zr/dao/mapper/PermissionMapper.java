package com.zr.dao.mapper;

import com.zr.dao.entity.Permission;
import com.zr.dao.entity.PermissionQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PermissionMapper {
    long countByQuery(PermissionQuery query);

    int deleteByQuery(PermissionQuery query);

    int deleteByPrimaryKey(Integer id);

    int insert(Permission record);

    int insertSelective(Permission record);

    List<Permission> selectByQuery(PermissionQuery query);

    Permission selectByPrimaryKey(Integer id);

    int updateByQuerySelective(@Param("record") Permission record, @Param("example") PermissionQuery query);

    int updateByQuery(@Param("record") Permission record, @Param("example") PermissionQuery query);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);
}