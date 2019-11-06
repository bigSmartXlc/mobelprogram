package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CategoryMapper;
import com.hbtcsrzzx.ssm.dao.mapper.SubjectMapper;
import com.hbtcsrzzx.ssm.po.Subject;
import com.hbtcsrzzx.ssm.po.SubjectExample;
import com.hbtcsrzzx.ssm.service.SubjectService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class SubjectServiceImpl implements SubjectService {

	@Autowired
	SubjectMapper SubjectMapper;
	@Autowired
	CategoryMapper categoryMapper;

	@Override
	public int addSubject(Subject Subject) throws Exception {
		// TODO Auto-generated method stub
		int res = -1;
		if (Subject != null)
			res = SubjectMapper.insert(Subject);
		return res;
	}

	@Override
	public Subject findSubjectById(int id) throws Exception {
		// TODO Auto-generated method stub
		Subject Subject = SubjectMapper.selectByPrimaryKey(id);
		if (Subject != null && Subject.getState() != Constants.DEL_STATE) {
			return Subject;
		}
		return null;
	}

	@Override
	public int updateSubjectById(Subject Subject) throws Exception {
		int res = -1;
		if (Subject != null)
			res = SubjectMapper.updateByPrimaryKey(Subject);
		return res;
	}

	@Override
	public int deleteSubject(int id) throws Exception {
		// TODO Auto-generated method stub
		// int res = SubjectMapper.deleteByPrimaryKey(id);
		return -1;// res;
	}

	@Override
	public List<Subject> findAllSubject(int currentPage, int pageSize) {
		SubjectExample example = new SubjectExample();
		SubjectExample.Criteria criteria = example.createCriteria();
		int start = (currentPage - 1) * pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Subject> SubjectList = SubjectMapper.selectByExample(example);
	
		for (Subject subject : SubjectList) {
			subject.setCategory(categoryMapper.selectByPrimaryKey(subject.getCid()));
		}
		return SubjectList;
	}

	@Override
	public int getCount(int state) {
		int cnt = 0;
		SubjectExample example = new SubjectExample();
		SubjectExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		// criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = SubjectMapper.countByExample(example);
		return cnt;
	}

	@Override
	public List<Subject> findSubjectByCid(int cid) {
		SubjectExample example = new SubjectExample();
		SubjectExample.Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Subject> list = SubjectMapper.selectByExample(example);
		return list;
	}
}
