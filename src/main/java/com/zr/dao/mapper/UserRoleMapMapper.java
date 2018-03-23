package com.zr.dao.mapper;

import com.zr.dao.entity.UserRoleMap;
import com.zr.dao.entity.UserRoleMapQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserRoleMapMapper {
    long countByExample(UserRoleMapQuery example);

    int deleteByExample(UserRoleMapQuery example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserRoleMap record);

    int insertSelective(UserRoleMap record);

    List<UserRoleMap> selectByExample(UserRoleMapQuery example);

    UserRoleMap selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserRoleMap record, @Param("example") UserRoleMapQuery example);

    int updateByExample(@Param("record") UserRoleMap record, @Param("example") UserRoleMapQuery example);

    int updateByPrimaryKeySelective(UserRoleMap record);

    int updateByPrimaryKey(UserRoleMap record);
}