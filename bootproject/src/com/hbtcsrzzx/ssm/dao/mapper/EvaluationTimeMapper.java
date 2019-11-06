package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.EvaluationTime;
import com.hbtcsrzzx.ssm.po.EvaluationTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EvaluationTimeMapper {
    int countByExample(EvaluationTimeExample example);

    int deleteByExample(EvaluationTimeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationTime record);

    int insertSelective(EvaluationTime record);

    List<EvaluationTime> selectByExample(EvaluationTimeExample example);

    EvaluationTime selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EvaluationTime record, @Param("example") EvaluationTimeExample example);

    int updateByExample(@Param("record") EvaluationTime record, @Param("example") EvaluationTimeExample example);

    int updateByPrimaryKeySelective(EvaluationTime record);

    int updateByPrimaryKey(EvaluationTime record);
}