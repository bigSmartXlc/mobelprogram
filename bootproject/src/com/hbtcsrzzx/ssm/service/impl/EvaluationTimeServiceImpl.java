package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.EvaluationTimeMapper;
import com.hbtcsrzzx.ssm.po.EvaluationTime;
import com.hbtcsrzzx.ssm.po.EvaluationTimeExample;
import com.hbtcsrzzx.ssm.po.EvaluationTimeExample.Criteria;
import com.hbtcsrzzx.ssm.service.EvaluationTimeService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class EvaluationTimeServiceImpl implements EvaluationTimeService {

	@Autowired
	private EvaluationTimeMapper evaluationTimeMapper;

	@Override
	public List<EvaluationTime> findAllEvaluationTime(int currentPage, int pageSize) throws Exception {

		EvaluationTimeExample example = new EvaluationTimeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int startRow = (currentPage - 1) * pageSize;
		example.setStartRow(startRow);
		example.setPageSize(pageSize);
		List<EvaluationTime> evaluationTimeList = evaluationTimeMapper.selectByExample(example);

		return evaluationTimeList;
	}

	@Override
	public int getCount(int state) {

		EvaluationTimeExample example = new EvaluationTimeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);

		return evaluationTimeMapper.countByExample(example);
	}

}
