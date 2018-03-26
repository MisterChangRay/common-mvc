package com.zr.dao.mapper;

import com.zr.dao.entity.Role;
import com.zr.dao.entity.RoleQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    long countByQuery(RoleQuery query);

    int deleteByQuery(RoleQuery query);

    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByQuery(RoleQuery query);

    Role selectByPrimaryKey(Integer id);

    int updateByQuerySelective(@Param("record") Role record, @Param("example") RoleQuery query);

    int updateByQuery(@Param("record") Role record, @Param("example") RoleQuery query);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}