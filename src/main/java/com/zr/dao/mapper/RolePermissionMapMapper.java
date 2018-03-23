package com.zr.dao.mapper;

import com.zr.dao.entity.RolePermissionMap;
import com.zr.dao.entity.RolePermissionMapQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePermissionMapMapper {
    long countByExample(RolePermissionMapQuery example);

    int deleteByExample(RolePermissionMapQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(RolePermissionMap record);

    int insertSelective(RolePermissionMap record);

    List<RolePermissionMap> selectByExample(RolePermissionMapQuery example);

    RolePermissionMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RolePermissionMap record, @Param("example") RolePermissionMapQuery example);

    int updateByExample(@Param("record") RolePermissionMap record, @Param("example") RolePermissionMapQuery example);

    int updateByPrimaryKeySelective(RolePermissionMap record);

    int updateByPrimaryKey(RolePermissionMap record);
}