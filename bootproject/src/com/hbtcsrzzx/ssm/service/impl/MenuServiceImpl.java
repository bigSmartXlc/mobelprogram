package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.MenuMapper;
import com.hbtcsrzzx.ssm.po.Menu;
import com.hbtcsrzzx.ssm.po.MenuExample;
import com.hbtcsrzzx.ssm.service.MenuService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class MenuServiceImpl implements MenuService {
	
	@Autowired
	MenuMapper menuMapper;
	
	@Override
	public int addMenu(Menu menu) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(menu != null)
			res = menuMapper.insert(menu);
		return res;
	}

	@Override
	public Menu findMenuById(int id) throws Exception {
		// TODO Auto-generated method stub
		Menu menu = menuMapper.selectByPrimaryKey(id);
		if(menu.getState() != Constants.DEL_STATE)
			return menu;
		return null;
	}
	@Override
	public int updateMenuById(Menu menu) throws Exception{
		int  res = -1;
		if(menu !=null)
			res = menuMapper.updateByPrimaryKey(menu);	
		return res;
	}
	
	@Override
	public int deleteMenu(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = menuMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<Menu>findAllMenu(int currentPage,int pageSize){
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Menu> MenuList = menuMapper.selectByExample(example);
		return MenuList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		cnt = menuMapper.countByExample(example);
		return cnt;
	}
	
	@Override
	public List<Menu> getAllMenu(){
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Menu> MenuList = menuMapper.selectByExample(example);
		return MenuList;
	}
	
	@Override
	public List<Menu> findMenuByPname(String pname){
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		criteria.andPnameEqualTo(pname);
		List<Menu> MenuList = menuMapper.selectByExample(example);
		return MenuList;
	}
	@Override
	public List<Menu>findMenuByLocation(String location){
		MenuExample example = new MenuExample();
		MenuExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		criteria.andIurlEqualTo(location);
		List<Menu> MenuList = menuMapper.selectByExample(example);
		return MenuList;
	}
}
