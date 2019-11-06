package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Subject;

public interface SubjectService {
	public int addSubject(Subject subject) throws Exception;
	public Subject findSubjectById(int id) throws Exception;
	public int updateSubjectById(Subject subject) throws Exception;
	public int deleteSubject(int id) throws Exception;
	public List<Subject> findAllSubject(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public List<Subject> findSubjectByCid(int cid);
}
