package com.hbtcsrzzx.ssm.dao.mapper;

import com.hbtcsrzzx.ssm.po.QuestionAnswers;
import com.hbtcsrzzx.ssm.po.QuestionAnswersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface QuestionAnswersMapper {
    int countByExample(QuestionAnswersExample example);

    int deleteByExample(QuestionAnswersExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(QuestionAnswers record);

    int insertSelective(QuestionAnswers record);

    List<QuestionAnswers> selectByExampleWithBLOBs(QuestionAnswersExample example);

    List<QuestionAnswers> selectByExample(QuestionAnswersExample example);

    QuestionAnswers selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") QuestionAnswers record, @Param("example") QuestionAnswersExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionAnswers record, @Param("example") QuestionAnswersExample example);

    int updateByExample(@Param("record") QuestionAnswers record, @Param("example") QuestionAnswersExample example);

    int updateByPrimaryKeySelective(QuestionAnswers record);

    int updateByPrimaryKeyWithBLOBs(QuestionAnswers record);

    int updateByPrimaryKey(QuestionAnswers record);
    
    @Select("select id ,question from question_answers where state <> -1 limit #{currentPage} , #{pageSize}")
    List<QuestionAnswers> findAllQuestionAnswers(@Param("currentPage")Integer currentPage,@Param("pageSize")Integer pageSize);
}