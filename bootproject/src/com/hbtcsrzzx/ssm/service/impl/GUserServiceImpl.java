package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.GUserMapper;
import com.hbtcsrzzx.ssm.po.GUser;
import com.hbtcsrzzx.ssm.po.GUserExample;
import com.hbtcsrzzx.ssm.po.GUserExample.Criteria;
import com.hbtcsrzzx.ssm.service.GUserService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class GUserServiceImpl implements GUserService {

	@Autowired
	GUserMapper gUserMapper;

	@Override
	public List<GUser> findAllGUser(int currentPage, int pageSize) {

		GUserExample example = new GUserExample();
		Criteria criteria = example.createCriteria();
		// 查询状态不为-1的结果集
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int start = (currentPage - 1) * pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		List<GUser> gUserList = gUserMapper.selectByExample(example);

		return gUserList;
	}

	@Override
	public int getCount(int state) {
		GUserExample example = new GUserExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		return gUserMapper.countByExample(example);
	}

	@Override
	public int addGUser(GUser gUser) throws Exception {
		int res = -1;
		if (gUser != null) {
			gUser.setState(Constants.NORMAL_STATE);
			gUser.setRegtime(new Date());
			res = gUserMapper.insert(gUser);
		}
		return res;
	}

	@Override
	public GUser findGUserById(int id) throws Exception {
		GUser gUser = gUserMapper.selectByPrimaryKey(id);
		if (gUser != null && gUser.getState() != Constants.DEL_STATE)
			return gUser;
		return null;
	}

	@Override
	public int updateGUserById(GUser gUser) throws Exception {
		int res = -1;
	
		try {
			res = gUserMapper.updateByPrimaryKeySelective(gUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

}
