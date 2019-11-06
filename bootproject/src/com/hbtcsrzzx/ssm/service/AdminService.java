package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Admin;

public interface AdminService {
	public int addAdmin(Admin admin) throws Exception;
	public Admin findAdminById(int id) throws Exception;
	public Admin findAdminByName(String name) throws Exception;
	public int updateAdminById(Admin admin) throws Exception;
	public int deleteAdmin(int id) throws Exception;
	public List<Admin> findAllAdmin(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
}
