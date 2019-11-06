package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.LevelMapper;
import com.hbtcsrzzx.ssm.po.Level;
import com.hbtcsrzzx.ssm.po.LevelExample;
import com.hbtcsrzzx.ssm.po.LevelExample.Criteria;
import com.hbtcsrzzx.ssm.service.LevelService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class LevelServiceimpl implements LevelService {

	@Autowired
	private LevelMapper levelMapper;

	@Override
	public List<Level> findAllLevel(int currentPage, int pageSize) throws Exception {
		LevelExample example = new LevelExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int startRow = (currentPage - 1) * pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(startRow);
		return levelMapper.selectByExample(example);

	}

	@Override
	public int getCount() {
		LevelExample example = new LevelExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		return levelMapper.countByExample(example);
	}

	@Override
	public int addLevel(Level level) throws Exception {
		int res = 1;
		if (level != null) {
			res = levelMapper.insert(level);
		}
		return res;
	}

	@Override
	public Level findLevelById(int id) throws Exception {

		Level level = levelMapper.selectByPrimaryKey(id);
		if (level != null && level.getState() != Constants.DEL_STATE)
			return level;
		return null;
	}

	@Override
	public int updateLevelById(Level level) throws Exception {
		int res = -1;
		try {
			res = levelMapper.updateByPrimaryKeySelective(level);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Level> getAllLevel() throws Exception {
		LevelExample example = new LevelExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);

		return levelMapper.selectByExample(example);
	}

	@Override
	public List<Level> findLevelByCidAndSid(Integer cid, Integer sid) {
		List<Level> levels = 	levelMapper.findLevelByCidAndSid(cid,sid);
		if(levels==null || levels.size()<= 0){
			throw new RuntimeException("根据类别和科目得到的结果为空");
		}
		return levels;
	}

}
