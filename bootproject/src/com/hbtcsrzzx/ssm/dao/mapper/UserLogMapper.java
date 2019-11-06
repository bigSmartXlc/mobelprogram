package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.EnrolScene;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.po.UserLogExample;

import java.util.List;

import org.apache.ibatis.annotations.*;

public interface UserLogMapper {
    int countByExample(UserLogExample example);

    int deleteByExample(UserLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserLog record);

    int insertSelective(UserLog record);

    List<UserLog> selectByExample(UserLogExample example);

    UserLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByExample(@Param("record") UserLog record, @Param("example") UserLogExample example);

    int updateByPrimaryKeySelective(UserLog record);

    int updateByPrimaryKey(UserLog record);

    @Select("select id , phone  from user_log where state <> -1 and id = #{id}")
    @ResultMap("generalResults")
    UserLog findUserLogById(Integer id);

    @Select("select * from user_log where state <> -1 limit #{startPage} , #{pageSize} ")
    @Results(id = "generalResults", value = {
            @Result(column = "id", property = "id", id = true),
            @Result(column = "id_card", property = "idCard"),
            @Result(column = "parent_name", property = "parentName"),
            @Result(column = "family_address", property = "familyAddress"),
            @Result(column = "family_phone", property = "familyPhone"),
            @Result(column = "creation_time", property = "creationTime"),
            @Result(column = "update_time", property = "updateTime"),
            @Result(column = "distribution_level", property = "distributionLevel"),
            @Result(column = "recommended_time", property = "recommendedTime"),
            @Result(column = "city_id", property = "city", one = @One(select = (
                    "com.hbtcsrzzx.ssm.dao.mapper.CityMapper.findCityByid"
            )))
    })
    List<UserLog> findAllUserLog(@Param("startPage") int startPage, @Param("pageSize") int pageSize);
}