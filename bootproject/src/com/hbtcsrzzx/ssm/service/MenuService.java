package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Menu;

public interface MenuService {
	public int addMenu(Menu menu) throws Exception;
	public Menu findMenuById(int id) throws Exception;
	public int updateMenuById(Menu menu) throws Exception;
	public int deleteMenu(int id) throws Exception;
	public List<Menu> findAllMenu(int currentPage,int pageSize)throws Exception;
	public List<Menu> getAllMenu()throws Exception;
	public int getCount(int state);
	public List<Menu> findMenuByPname(String pname)throws Exception;
	public List<Menu>findMenuByLocation(String location) throws Exception;
}
