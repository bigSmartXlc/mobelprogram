package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.EvaluationTime;

public interface EvaluationTimeService {

	public List<EvaluationTime> findAllEvaluationTime(int currentPage, int pageSize) throws Exception;

	public int getCount(int state);

}
