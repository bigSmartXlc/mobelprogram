package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.QuestionAnswers;

public interface QuestionAnswersService {

	List<QuestionAnswers> findAllQuestionAnswers(Integer currentPage, Integer pageSize);

	int getCount();

	int addEnrolQuestionAnswers(QuestionAnswers questionAnswers);

	int updateQuestionAnswers(QuestionAnswers questionAnswers);

	QuestionAnswers findQuestionAnswersById(Integer id);

}
