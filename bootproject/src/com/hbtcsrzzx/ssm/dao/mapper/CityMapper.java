package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.City;
import com.hbtcsrzzx.ssm.po.CityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface CityMapper {
    int countByExample(CityExample example);

    int deleteByExample(CityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(City record);

    int insertSelective(City record);

    List<City> selectByExample(CityExample example);

    City selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") City record, @Param("example") CityExample example);

    int updateByExample(@Param("record") City record, @Param("example") CityExample example);

    int updateByPrimaryKeySelective(City record);

    int updateByPrimaryKey(City record);
    
    
    @Select("select * from City where id = #{id} and state <> -1")
    @Results({
    	
    	@Result(id=true,column="id",property="id"),
    	@Result(column="city_name", property="cityName"),
    	@Result(column="state", property="state"),
    	@Result(column="parentid", property="parentid")
    })
    City findCityByid(Integer id);
}