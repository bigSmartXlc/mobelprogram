package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Center;
import com.hbtcsrzzx.ssm.po.CenterExample;
import com.hbtcsrzzx.ssm.po.CenterWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CenterMapper {
    int countByExample(CenterExample example);

    int deleteByExample(CenterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CenterWithBLOBs record);

    int insertSelective(CenterWithBLOBs record);

    List<CenterWithBLOBs> selectByExampleWithBLOBs(CenterExample example);

    List<Center> selectByExample(CenterExample example);

    CenterWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") CenterWithBLOBs record, @Param("example") CenterExample example);

    int updateByExampleWithBLOBs(@Param("record") CenterWithBLOBs record, @Param("example") CenterExample example);

    int updateByExample(@Param("record") Center record, @Param("example") CenterExample example);

    int updateByPrimaryKeySelective(CenterWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CenterWithBLOBs record);

    int updateByPrimaryKey(Center record);
}