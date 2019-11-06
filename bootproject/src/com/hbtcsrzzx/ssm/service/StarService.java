package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Star;

public interface StarService {
	public int addStar(Star star) throws Exception;
	public Star findStarById(int id) throws Exception;
	public int updateStarById(Star star) throws Exception;
	public int deleteStar(int id) throws Exception;
	public List<Star> findAllStar(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
}
