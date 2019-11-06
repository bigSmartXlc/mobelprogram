package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.ExpertExample;
import com.hbtcsrzzx.ssm.po.ExpertWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpertMapper {
    int countByExample(ExpertExample example);

    int deleteByExample(ExpertExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExpertWithBLOBs record);

    int insertSelective(ExpertWithBLOBs record);

    List<ExpertWithBLOBs> selectByExampleWithBLOBs(ExpertExample example);

    List<Expert> selectByExample(ExpertExample example);

    ExpertWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExpertWithBLOBs record, @Param("example") ExpertExample example);

    int updateByExampleWithBLOBs(@Param("record") ExpertWithBLOBs record, @Param("example") ExpertExample example);

    int updateByExample(@Param("record") Expert record, @Param("example") ExpertExample example);

    int updateByPrimaryKeySelective(ExpertWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ExpertWithBLOBs record);

    int updateByPrimaryKey(Expert record);
}