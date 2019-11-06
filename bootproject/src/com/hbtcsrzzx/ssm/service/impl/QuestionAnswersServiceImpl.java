package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtcsrzzx.ssm.dao.mapper.QuestionAnswersMapper;
import com.hbtcsrzzx.ssm.po.QuestionAnswers;
import com.hbtcsrzzx.ssm.po.QuestionAnswersExample;
import com.hbtcsrzzx.ssm.po.QuestionAnswersExample.Criteria;
import com.hbtcsrzzx.ssm.service.QuestionAnswersService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Service
public class QuestionAnswersServiceImpl implements QuestionAnswersService {
	@Autowired
	private QuestionAnswersMapper questionAnswersMapper;

	@Override
	public List<QuestionAnswers> findAllQuestionAnswers(Integer currentPage, Integer pageSize) {

		if (currentPage == null || currentPage <= 0) {
			currentPage = 1;
		}

		if (pageSize == null) {
			pageSize = 0;
		}

		currentPage = (currentPage - 1) * pageSize;
		List<QuestionAnswers> answers = questionAnswersMapper.findAllQuestionAnswers(currentPage, pageSize);
		if (answers == null || answers.size() <= 0) {
			throw new RuntimeException("没有对应的问题");
		}
		return answers;
	}

	@Override
	public int getCount() {

		QuestionAnswersExample example = new QuestionAnswersExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int count = questionAnswersMapper.countByExample(example);
		if (count <= 0) {
			throw new RuntimeException("问题记录数不存在");
		}

		return count;
	}

	@Override
	public int addEnrolQuestionAnswers(QuestionAnswers questionAnswers) {
		questionAnswers.setState(Constants.NORMAL_STATE);
		questionAnswers.setCreatetime(new Date());
		questionAnswers.setUpdatetime(new Date());
		int result = questionAnswersMapper.insert(questionAnswers);
		if (result != 1) {
			throw new RuntimeException("新增出错");
		}

		return result;
	}

	@Override
	public int updateQuestionAnswers(QuestionAnswers questionAnswers) {
		
		questionAnswers.setUpdatetime(new Date());
		int result =	questionAnswersMapper.updateByPrimaryKeySelective(questionAnswers);
		if (result != 1) {
			throw new RuntimeException("修改出错");
		}
		return result;
	}

	@Override
	public QuestionAnswers findQuestionAnswersById(Integer id) {
		
		QuestionAnswers questionAnswers = questionAnswersMapper.selectByPrimaryKey(id);
		if(questionAnswers == null || Constants.DEL_STATE == questionAnswers.getState()){
			throw new RuntimeException("查询结果有误");
		}
		return questionAnswers;
	}

}
