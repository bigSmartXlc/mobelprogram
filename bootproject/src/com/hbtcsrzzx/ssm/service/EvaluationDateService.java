package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.*;

public interface EvaluationDateService {

	List<EvaluationDate> findAllEvaluationDate(int currentPage, int pageSize) throws Exception;

	int getCount();

	int addEvaluationDate(EvaluationDate evaluationDate) throws Exception;

	int updateEvaluationDateById(EvaluationDate evaluationDate) throws Exception;

	EvaluationDate findEvaluationDateById(int id) throws Exception;

	List<EvaluationDate> findEnrolDate(Integer lid, Integer sid, Integer cid);

	List<EvaluationDate> findTestDateGroupBy();

	List<Level> findLevelByCidAndSid(Integer cid, Integer sid);

	List<Level> findEvaluationDateByLid(String cname, String sname);

	List<Category> findCategoryGroupBy();

	List<Subject> findSubjectByCname(String cname);

	List<EvaluationDate> findEvaluationDateByLnameAndCnameAndSname(String lname, String cname, String sname);

	List<EvaluationDate> findEvaluationDateByLidAndCidAndSidAndTestDate(Integer lid, Integer sid, Integer cid,
			String testDate);
}
