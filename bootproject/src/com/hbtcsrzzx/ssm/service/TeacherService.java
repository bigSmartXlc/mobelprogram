package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.Teacher;
import com.hbtcsrzzx.ssm.po.TeacherWithBLOBs;

public interface TeacherService {
	public int addTeacher(TeacherWithBLOBs teacher) throws Exception;
	public TeacherWithBLOBs findTeacherById(int id) throws Exception;
	public int updateTeacherById(TeacherWithBLOBs Teacher) throws Exception;
	public int deleteTeacher(int id) throws Exception;
	public List<TeacherWithBLOBs> findAllTeacher(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public List<Teacher> getAllTeacher();
	/*按照姓名拼音排序*/
	public List<Teacher> findAllTeacherBSN(int currentPage,int pageSize)throws Exception;
}
