package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.City;
import com.hbtcsrzzx.ssm.po.EnrolScene;
import com.hbtcsrzzx.ssm.po.EnrolSceneExample;
import com.hbtcsrzzx.ssm.po.EvaluationDate;

import java.util.List;

import org.apache.ibatis.annotations.*;
public interface EnrolSceneMapper {
    int countByExample(EnrolSceneExample example);

    int deleteByExample(EnrolSceneExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EnrolScene record);

    int insertSelective(EnrolScene record);

    List<EnrolScene> selectByExample(EnrolSceneExample example);

    EnrolScene selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EnrolScene record, @Param("example") EnrolSceneExample example);

    int updateByExample(@Param("record") EnrolScene record, @Param("example") EnrolSceneExample example);

    int updateByPrimaryKeySelective(EnrolScene record);

    int updateByPrimaryKey(EnrolScene record);
    
    
  
    @Select("select * from enrol_scene where state <> -1 limit #{pageNum} , #{pageSize}")
    @Results(value={
    	@Result(id=true,column="id",property="id"),
    	@Result(column="scene", property="scene"),
    	@Result(column="state",property="state"),
    	@Result(column="city_id", property="cityId"),
    	@Result(column="date_id",property="dateId"),
    	@Result(column="create_time", property="createTime"),
    	@Result(column="update_time",property="updateTime"),
    	@Result(column="detailed_address",property="detailedAddress"),
    	//关联城市信息
    	@Result(column="city_id",property="city",javaType=City.class,
    	one=@One(select="com.hbtcsrzzx.ssm.dao.mapper.CityMapper.findCityByid")),
    	//关联时间信息
    	@Result(column="date_id",property="evaluationDate",javaType=EvaluationDate.class,
    	one=@One(select="com.hbtcsrzzx.ssm.dao.mapper.EvaluationDateMapper.findEvaluationDateById"))
    	
    })
    List<EnrolScene> findAllEnrolScene(@Param("pageNum") Integer pageNum , @Param("pageSize") Integer pageSize);
    
    
    @Select("select * from enrol_scene where state <> -1 and id=#{id}")
    @Results(value={
        	@Result(id=true,column="id",property="id"),
        	@Result(column="scene", property="scene"),
        	@Result(column="state",property="state"),
        	@Result(column="city_id", property="cityId"),
        	@Result(column="date_id",property="dateId"),
        	@Result(column="create_time", property="createTime"),
        	@Result(column="update_time",property="updateTime"),
        	@Result(column="detailed_address",property="detailedAddress"),
        	//关联城市信息
        	@Result(column="city_id",property="city",javaType=City.class,
        	one=@One(select="com.hbtcsrzzx.ssm.dao.mapper.CityMapper.findCityByid")),
        	//关联时间信息
        	@Result(column="date_id",property="evaluationDate",javaType=EvaluationDate.class,
        	one=@One(select="com.hbtcsrzzx.ssm.dao.mapper.EvaluationDateMapper.findEvaluationDateById"))
        	
        })
    EnrolScene findEnrolSceneById(Integer id);

    @Select("select * from enrol_scene where state <> -1 and date_id=#{dateId}")
    @Results(value={
        	@Result(id=true,column="id",property="id"),
        	@Result(column="scene", property="scene"),
        	@Result(column="state",property="state"),
        	@Result(column="city_id", property="cityId"),
        	@Result(column="date_id",property="dateId"),
        	@Result(column="create_time", property="createTime"),
        	@Result(column="update_time",property="updateTime"),
        	@Result(column="detailed_address",property="detailedAddress"),
        	//关联城市信息
        	@Result(column="city_id",property="city",javaType=City.class,
        	one=@One(select="com.hbtcsrzzx.ssm.dao.mapper.CityMapper.findCityByid"))
        })
	EnrolScene findEnrolSceneByDateId(Integer dateId);

    @Select("SELECT scene,id FROM enrol_scene WHERE date_id in(SELECT id FROM evaluation_date  WHERE test_date =#{testDate}) ")
	List<EnrolScene> findSceneBytestDate(@Param("testDate")String testDate);


    @Select("select  * from city where state <> -1 and id in" +
			"(SELECT city_id from enrol_scene  where state <> -1 and date_id in" +
			"(select id from  evaluation_date where state <> -1 and test_date  = #{testDate}))")
	@Results({
			@Result(column = "city_name" ,property = "cityName")
	})
    List<City> findEnrolSceneByCity(String testDate);

    @Select("select id , scene from enrol_scene where state <> -1 and city_id = #{cityId}" +
			" and date_id in((select id from  evaluation_date where state <> -1 and test_date  = #{testDate}))")
	List<EnrolScene> findEnrolSceneByCityAndTestDate(@Param("testDate") String testDate, @Param("cityId")Integer cityId);
}