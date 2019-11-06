package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.UserMapper;
import com.hbtcsrzzx.ssm.po.User;
import com.hbtcsrzzx.ssm.po.UserExample;
import com.hbtcsrzzx.ssm.po.UserWithBLOBs;
import com.hbtcsrzzx.ssm.service.UserService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int addUser(UserWithBLOBs user) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(user != null)
			res = userMapper.insert(user);
		return res;
	}

	@Override
	public UserWithBLOBs findUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		UserWithBLOBs user = userMapper.selectByPrimaryKey(id);
		if(user!=null && user.getState()!=Constants.DEL_STATE)
			return user;
		return  null;
	}
	@Override
	public int updateUserById(UserWithBLOBs User) throws Exception{
		int  res = -1;
		if(User!= null)
			res = userMapper.updateByPrimaryKeyWithBLOBs(User);
		return res;
	}
	
	@Override
	public int deleteUser(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = userMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<UserWithBLOBs>findAllUser(int currentPage,int pageSize){
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<UserWithBLOBs> userList = userMapper.selectByExampleWithBLOBs(example);
		return userList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = userMapper.countByExample(example);
		return cnt;
	}

	@Override
	public List<User> findAllUserBSN(int currentPage, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		example.setOrderByClause("convert (`name` using gbk) ASC");
		UserExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<User> userList = userMapper.selectByExample(example);
		return userList;
	}
	@Override
	public List<User> getAllUser(){
		UserExample example = new UserExample();
		UserExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<User> userList = userMapper.selectByExample(example);
		return userList;
	}
 }
