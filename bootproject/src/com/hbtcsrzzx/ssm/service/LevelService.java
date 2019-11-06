package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Level;
public interface LevelService {

	public List<Level> findAllLevel(int currentPage, int pageSize) throws Exception;

	public int getCount();

	public int addLevel(Level level) throws Exception;

	public Level findLevelById(int id) throws Exception;

	public int updateLevelById(Level level) throws Exception;

	public List<Level> getAllLevel() throws Exception;

	public List<Level> findLevelByCidAndSid(Integer cid, Integer sid);
}
