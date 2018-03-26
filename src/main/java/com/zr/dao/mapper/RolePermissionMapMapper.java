package com.zr.dao.mapper;

import com.zr.dao.entity.RolePermissionMap;
import com.zr.dao.entity.RolePermissionMapQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapMapper {
    long countByQuery(RolePermissionMapQuery query);

    int deleteByQuery(RolePermissionMapQuery query);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionMap record);

    int insertSelective(RolePermissionMap record);

    List<RolePermissionMap> selectByQuery(RolePermissionMapQuery query);

    RolePermissionMap selectByPrimaryKey(Integer id);

    int updateByQuerySelective(@Param("record") RolePermissionMap record, @Param("example") RolePermissionMapQuery query);

    int updateByQuery(@Param("record") RolePermissionMap record, @Param("example") RolePermissionMapQuery query);

    int updateByPrimaryKeySelective(RolePermissionMap record);

    int updateByPrimaryKey(RolePermissionMap record);
}