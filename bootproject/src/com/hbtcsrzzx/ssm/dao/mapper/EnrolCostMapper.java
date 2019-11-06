package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.EnrolCost;
import com.hbtcsrzzx.ssm.po.EnrolCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface EnrolCostMapper {
    int countByExample(EnrolCostExample example);

    int deleteByExample(EnrolCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EnrolCost record);

    int insertSelective(EnrolCost record);

    List<EnrolCost> selectByExample(EnrolCostExample example);

    EnrolCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EnrolCost record, @Param("example") EnrolCostExample example);

    int updateByExample(@Param("record") EnrolCost record, @Param("example") EnrolCostExample example);

    int updateByPrimaryKeySelective(EnrolCost record);

    int updateByPrimaryKey(EnrolCost record);

    @Select("select cost from enrol_cost where state <> -1 and  lid =  (select id from level where state <> -1 and id=#{lid})")
	Integer findEnrolCostByLid(Integer lid);

    
    @Select("select cost from enrol_cost where  state <> -1 and  lid = (select id from level where state <> -1 and level=#{lname})")
	Integer getEnrolCostByLName(String lname);

    @Select("select lid from enrol_cost where state <> -1 and lid = #{lid} ")
	Integer getLidIsNull(Integer lid);
}