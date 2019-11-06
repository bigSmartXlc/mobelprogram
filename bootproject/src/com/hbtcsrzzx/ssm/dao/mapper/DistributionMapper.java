package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Distribution;
import com.hbtcsrzzx.ssm.po.DistributionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DistributionMapper {
    int countByExample(DistributionExample example);

    int deleteByExample(DistributionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Distribution record);

    int insertSelective(Distribution record);

    List<Distribution> selectByExample(DistributionExample example);

    Distribution selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Distribution record, @Param("example") DistributionExample example);

    int updateByExample(@Param("record") Distribution record, @Param("example") DistributionExample example);

    int updateByPrimaryKeySelective(Distribution record);

    int updateByPrimaryKey(Distribution record);

    @Select("select * from distribution where state <> -1")
    @Results(id = "generalResults",value = {
            @Result(column = "id",property = "id",id = true),
            @Result(column = "distribution_level",property = "distributionLevel"),
            @Result(column = "recommender_level",property = "recommenderLevel"),
            @Result(column = "return_proportion",property = "returnProportion"),
            @Result(column = "create_time",property = "createTime"),
            @Result(column = "update_time",property = "updateTime"),
    })
    List<Distribution> findAllDistribution();

    @Select ("select * from distribution where state <> -1 and id = #{id} ")
    Distribution findDistributionById(Integer id);
}