package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Center;
import com.hbtcsrzzx.ssm.po.CenterWithBLOBs;

public interface CenterService {
	public int addCenter(CenterWithBLOBs center) throws Exception;
	public CenterWithBLOBs findCenterById(int id) throws Exception;
	public int updateCenterById(CenterWithBLOBs center) throws Exception;
	public int deleteCenter(int id) throws Exception;
	public List<CenterWithBLOBs> findAllCenter(int currentPage,int pageSize)throws Exception;
	public List<CenterWithBLOBs> getAllCenter()throws Exception;
	public int getCount(int state);
}
