package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Star;
import com.hbtcsrzzx.ssm.po.StarExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StarMapper {
    int countByExample(StarExample example);

    int deleteByExample(StarExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Star record);

    int insertSelective(Star record);

    List<Star> selectByExampleWithBLOBs(StarExample example);

    List<Star> selectByExample(StarExample example);

    Star selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Star record, @Param("example") StarExample example);

    int updateByExampleWithBLOBs(@Param("record") Star record, @Param("example") StarExample example);

    int updateByExample(@Param("record") Star record, @Param("example") StarExample example);

    int updateByPrimaryKeySelective(Star record);

    int updateByPrimaryKeyWithBLOBs(Star record);

    int updateByPrimaryKey(Star record);
}