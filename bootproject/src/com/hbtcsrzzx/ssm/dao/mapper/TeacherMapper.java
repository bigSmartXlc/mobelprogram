package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Teacher;
import com.hbtcsrzzx.ssm.po.TeacherExample;
import com.hbtcsrzzx.ssm.po.TeacherWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {
    int countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TeacherWithBLOBs record);

    int insertSelective(TeacherWithBLOBs record);

    List<TeacherWithBLOBs> selectByExampleWithBLOBs(TeacherExample example);

    List<Teacher> selectByExample(TeacherExample example);

    TeacherWithBLOBs selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TeacherWithBLOBs record, @Param("example") TeacherExample example);

    int updateByExampleWithBLOBs(@Param("record") TeacherWithBLOBs record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(TeacherWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TeacherWithBLOBs record);

    int updateByPrimaryKey(Teacher record);
}