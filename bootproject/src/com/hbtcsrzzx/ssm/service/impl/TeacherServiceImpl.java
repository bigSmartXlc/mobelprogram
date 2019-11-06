package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.TeacherMapper;
import com.hbtcsrzzx.ssm.po.Teacher;
import com.hbtcsrzzx.ssm.po.TeacherExample;
import com.hbtcsrzzx.ssm.po.TeacherExample.Criteria;
import com.hbtcsrzzx.ssm.po.TeacherWithBLOBs;
import com.hbtcsrzzx.ssm.service.TeacherService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class TeacherServiceImpl implements TeacherService {
	
	@Autowired
	TeacherMapper teacherMapper;
	
	@Override
	public int addTeacher(TeacherWithBLOBs Teacher) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Teacher != null)
			res = teacherMapper.insert(Teacher);
		return res;
	}

	@Override
	public TeacherWithBLOBs findTeacherById(int id) throws Exception {
		// TODO Auto-generated method stub
		TeacherWithBLOBs Teacher = teacherMapper.selectByPrimaryKey(id);
		if(Teacher!=null && Teacher.getState()!=Constants.DEL_STATE)
			return Teacher;
		return null;
	}
	@Override
	public int updateTeacherById(TeacherWithBLOBs Teacher) throws Exception{
		int  res = -1;
		if(Teacher!=null)
			res = teacherMapper.updateByPrimaryKeyWithBLOBs(Teacher);
		return res;
	}
	
	@Override
	public int deleteTeacher(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = teacherMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<TeacherWithBLOBs>findAllTeacher(int currentPage,int pageSize){
		TeacherExample example = new TeacherExample();
		TeacherExample.Criteria criteria =example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<TeacherWithBLOBs> TeacherList = teacherMapper.selectByExampleWithBLOBs(example);
		return TeacherList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		TeacherExample example = new TeacherExample();
		TeacherExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = teacherMapper.countByExample(example);
		return cnt;
	}
	/*按照姓名拼音排序*/
	@Override
    public List<Teacher> findAllTeacherBSN(int currentPage, int pageSize){
		TeacherExample example = new TeacherExample();
		example.setOrderByClause("convert (`name` using gbk) ASC");
		TeacherExample.Criteria criteria =example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Teacher> teacherList = teacherMapper.selectByExample(example);
		return teacherList;
	}
		
	@Override
    public List<Teacher> getAllTeacher(){
		TeacherExample example = new TeacherExample();
		Criteria criteria =example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Teacher> teacherList = teacherMapper.selectByExample(example);
		return teacherList;
	}
}
