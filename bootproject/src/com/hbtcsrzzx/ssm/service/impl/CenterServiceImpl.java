package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CenterMapper;
import com.hbtcsrzzx.ssm.po.CenterExample;
import com.hbtcsrzzx.ssm.po.CenterWithBLOBs;
import com.hbtcsrzzx.ssm.service.CenterService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class CenterServiceImpl implements CenterService {
	
	@Autowired
	CenterMapper centerMapper;
	
	@Override
	public int addCenter(CenterWithBLOBs Center) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Center != null)
			res = centerMapper.insert(Center);
		return res;
	}

	@Override
	public CenterWithBLOBs findCenterById(int id) throws Exception {
		// TODO Auto-generated method stub
		CenterWithBLOBs center = centerMapper.selectByPrimaryKey(id);
		if(center!=null && center.getState() != Constants.DEL_STATE) 
			return center;
		return null;
	}
	@Override
	public int updateCenterById(CenterWithBLOBs center) throws Exception{
		int  res = -1;
		if(center != null)
			res = centerMapper.updateByPrimaryKeyWithBLOBs(center);
		return res;
	}
	
	@Override
	public int deleteCenter(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = centerMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	@Override
    public List<CenterWithBLOBs> getAllCenter()throws Exception{
		CenterExample example = new CenterExample();
		CenterExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<CenterWithBLOBs> centerList = centerMapper.selectByExampleWithBLOBs(example);
		return centerList;
	}
	
	@Override
	public List<CenterWithBLOBs>findAllCenter(int currentPage,int pageSize){
		CenterExample example = new CenterExample();
		CenterExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<CenterWithBLOBs> centerList = centerMapper.selectByExampleWithBLOBs(example);
		return centerList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		CenterExample example = new CenterExample();
		CenterExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		cnt = centerMapper.countByExample(example);
		return cnt;
	}
}
