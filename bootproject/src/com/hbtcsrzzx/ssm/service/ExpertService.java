package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.ExpertWithBLOBs;

public interface ExpertService {
	public int addExpert(ExpertWithBLOBs expert) throws Exception;
	public ExpertWithBLOBs findExpertById(int id) throws Exception;
	public int updateExpertById(ExpertWithBLOBs expert) throws Exception;
	public int deleteExpert(int id) throws Exception;
	public List<ExpertWithBLOBs> findAllExpert(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public List<Expert> getAllExpert()throws Exception;
	/*按照姓名拼音排序*/
	public List<Expert> findAllExpertBSN(int currentPage,int pageSize)throws Exception;
}
