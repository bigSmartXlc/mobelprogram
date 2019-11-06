package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.ExpertMapper;
import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.ExpertExample;
import com.hbtcsrzzx.ssm.po.ExpertWithBLOBs;
import com.hbtcsrzzx.ssm.service.ExpertService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class ExpertServiceImpl implements ExpertService {
	
	@Autowired
	ExpertMapper expertMapper;
	
	@Override
	public int addExpert(ExpertWithBLOBs Expert) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Expert != null)
			res = expertMapper.insert(Expert);
		return res;
	}

	@Override
	public ExpertWithBLOBs findExpertById(int id) throws Exception {
		// TODO Auto-generated method stub
		ExpertWithBLOBs expert = expertMapper.selectByPrimaryKey(id);
		if(expert!=null && expert.getState()!=Constants.DEL_STATE)
			return expert;
		return null;
	}
	@Override
	public int updateExpertById(ExpertWithBLOBs Expert) throws Exception{
		int  res = -1;
		if(Expert !=null)
			res = expertMapper.updateByPrimaryKeyWithBLOBs(Expert);
		return res;
	}
	
	@Override
	public int deleteExpert(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = expertMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<ExpertWithBLOBs>findAllExpert(int currentPage,int pageSize){
		ExpertExample example = new ExpertExample();
		ExpertExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<ExpertWithBLOBs> expertList = expertMapper.selectByExampleWithBLOBs(example);
		return expertList;
	}
	@Override
	public List<Expert> getAllExpert(){
		ExpertExample example = new ExpertExample();
		ExpertExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Expert> expertList = expertMapper.selectByExample(example);
		return expertList;	
	}
	
	@Override
	public int getCount(int state) {
		int cnt = 0;
		ExpertExample example = new ExpertExample();
		ExpertExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = expertMapper.countByExample(example);
		return cnt;
	}
	
	@Override
	public List<Expert> findAllExpertBSN(int currentPage,int pageSize){
		ExpertExample example = new ExpertExample();
		example.setOrderByClause("convert (`name` using gbk) ASC");
		ExpertExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Expert> expertList = expertMapper.selectByExample(example);
		return expertList;
	}
}
