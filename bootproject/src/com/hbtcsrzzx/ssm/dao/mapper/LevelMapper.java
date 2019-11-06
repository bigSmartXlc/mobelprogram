package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Level;
import com.hbtcsrzzx.ssm.po.LevelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LevelMapper {
    int countByExample(LevelExample example);

    int deleteByExample(LevelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Level record);

    int insertSelective(Level record);

    List<Level> selectByExample(LevelExample example);

    Level selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByExample(@Param("record") Level record, @Param("example") LevelExample example);

    int updateByPrimaryKeySelective(Level record);

    int updateByPrimaryKey(Level record);

    @Select("select * from level where state <> -1 and cid = #{cid} and sid = #{sid} ")
	List<Level> findLevelByCidAndSid(@Param("cid")Integer cid, @Param("sid")Integer sid);

    @Select("select id from level where state <> -1 and level = #{lname}")
	Integer findLevelById(String lname);
}