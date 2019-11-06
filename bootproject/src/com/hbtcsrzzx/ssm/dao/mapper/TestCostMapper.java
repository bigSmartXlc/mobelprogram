package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.TestCost;
import com.hbtcsrzzx.ssm.po.TestCostExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TestCostMapper {
    int countByExample(TestCostExample example);

    int deleteByExample(TestCostExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TestCost record);

    int insertSelective(TestCost record);

    List<TestCost> selectByExample(TestCostExample example);

    TestCost selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TestCost record, @Param("example") TestCostExample example);

    int updateByExample(@Param("record") TestCost record, @Param("example") TestCostExample example);

    int updateByPrimaryKeySelective(TestCost record);

    int updateByPrimaryKey(TestCost record);
}