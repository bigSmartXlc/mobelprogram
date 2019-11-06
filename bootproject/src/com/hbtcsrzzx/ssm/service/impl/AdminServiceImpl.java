package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.AdminMapper;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.AdminExample;
import com.hbtcsrzzx.ssm.service.AdminService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class AdminServiceImpl implements AdminService{

	@Autowired
	AdminMapper adminMapper;
	@Override
	public int addAdmin(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		int res = -1;
		if(admin != null)
			res = adminMapper.insert(admin);
		return 0;
	}

	@Override
	public Admin findAdminById(int id) throws Exception {
		// TODO Auto-generated method stub
		Admin admin = null;
		admin = adminMapper.selectByPrimaryKey(id);
		if(admin!=null && admin.getState() != Constants.DEL_STATE)
			return admin;
		return null;
	}

	@Override
	public int updateAdminById(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		int res = -1;
	    if(admin != null)
	    	res = adminMapper.updateByPrimaryKey(admin);
		return res;
	}

	@Override
	public int deleteAdmin(int id) throws Exception {
		// TODO Auto-generated method stub
	//	int res  = -1;
	//	if(id > 0)
	//		res = adminMapper.deleteByPrimaryKey(id);
		return -1;
	}

	@Override
	public List<Admin> findAllAdmin(int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Admin> adminList = adminMapper.selectByExample(example);
		return adminList;
	}

	@Override
	public Admin findAdminByName(String name) throws Exception {
		// TODO Auto-generated method stub
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Admin> list= adminMapper.selectByExample(example);
		if(list != null && list.size()>0) {
			Admin admin = (Admin)list.get(0);
			return admin;
		}			
		return null;
	}
	
	@Override
	public int getCount(int state) {
		int cnt = 0;
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = adminMapper.countByExample(example);
		return cnt;
	}

}
