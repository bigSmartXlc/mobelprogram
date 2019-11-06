package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.Subject;
import com.hbtcsrzzx.ssm.po.SubjectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SubjectMapper {
    int countByExample(SubjectExample example);

    int deleteByExample(SubjectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Subject record);

    int insertSelective(Subject record);

    List<Subject> selectByExample(SubjectExample example);

    Subject selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByExample(@Param("record") Subject record, @Param("example") SubjectExample example);

    int updateByPrimaryKeySelective(Subject record);

    int updateByPrimaryKey(Subject record);

    @Select("select id from subject where state <> -1 and name = #{name}")
	Integer findSubjectBysname(String sname);
}