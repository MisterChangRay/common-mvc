package com.zr.dao.mapper;

import com.zr.dao.entity.User;
import com.zr.dao.entity.UserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByQuery(UserQuery query);

    int deleteByQuery(UserQuery query);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByQuery(UserQuery query);

    User selectByPrimaryKey(Integer id);

    int updateByQuerySelective(@Param("record") User record, @Param("example") UserQuery query);

    int updateByQuery(@Param("record") User record, @Param("example") UserQuery query);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}