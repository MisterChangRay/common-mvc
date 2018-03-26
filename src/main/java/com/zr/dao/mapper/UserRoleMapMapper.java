package com.zr.dao.mapper;

import com.zr.dao.entity.UserRoleMap;
import com.zr.dao.entity.UserRoleMapQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapMapper {
    long countByQuery(UserRoleMapQuery query);

    int deleteByQuery(UserRoleMapQuery query);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleMap record);

    int insertSelective(UserRoleMap record);

    List<UserRoleMap> selectByQuery(UserRoleMapQuery query);

    UserRoleMap selectByPrimaryKey(Integer id);

    int updateByQuerySelective(@Param("record") UserRoleMap record, @Param("example") UserRoleMapQuery query);

    int updateByQuery(@Param("record") UserRoleMap record, @Param("example") UserRoleMapQuery query);

    int updateByPrimaryKeySelective(UserRoleMap record);

    int updateByPrimaryKey(UserRoleMap record);
}