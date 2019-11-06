package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.ExamineePayLog;
import com.hbtcsrzzx.ssm.po.ExamineePayLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExamineePayLogMapper {
    int countByExample(ExamineePayLogExample example);

    int deleteByExample(ExamineePayLogExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(ExamineePayLog record);

    int insertSelective(ExamineePayLog record);

    List<ExamineePayLog> selectByExample(ExamineePayLogExample example);

    ExamineePayLog selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") ExamineePayLog record, @Param("example") ExamineePayLogExample example);

    int updateByExample(@Param("record") ExamineePayLog record, @Param("example") ExamineePayLogExample example);

    int updateByPrimaryKeySelective(ExamineePayLog record);

    int updateByPrimaryKey(ExamineePayLog record);
}