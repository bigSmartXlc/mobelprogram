package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.GUser;

public interface GUserService {
	
	public List<GUser> findAllGUser(int currentPage , int pageSize) throws Exception;
	public int getCount(int state);
	public int addGUser(GUser gUser) throws Exception;
	public GUser findGUserById(int id) throws Exception;
	public int updateGUserById(GUser subject) throws Exception;
}
