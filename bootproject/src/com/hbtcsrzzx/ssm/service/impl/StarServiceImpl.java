package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.StarMapper;
import com.hbtcsrzzx.ssm.po.Star;
import com.hbtcsrzzx.ssm.po.StarExample;
import com.hbtcsrzzx.ssm.service.StarService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class StarServiceImpl implements StarService {
	
	@Autowired
	StarMapper StarMapper;
	
	@Override
	public int addStar(Star Star) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Star != null)
			res = StarMapper.insert(Star);
		return res;
	}

	@Override
	public Star findStarById(int id) throws Exception {
		// TODO Auto-generated method stub
		Star Star = StarMapper.selectByPrimaryKey(id);
		if(Star!=null && Star.getState()!=Constants.DEL_STATE)
			return Star;
		return null;
	}
	
	@Override
	public int updateStarById(Star Star) throws Exception{
		int  res = -1;
		if(Star!=null)
			res = StarMapper.updateByPrimaryKeyWithBLOBs(Star);
		return res;
	}
	
	@Override
	public int deleteStar(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = StarMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<Star>findAllStar(int currentPage,int pageSize){
		StarExample example = new StarExample();
		StarExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Star> StarList = StarMapper.selectByExampleWithBLOBs(example);
		return StarList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		StarExample example = new StarExample();
		StarExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = StarMapper.countByExample(example);
		return cnt;
	}
}
