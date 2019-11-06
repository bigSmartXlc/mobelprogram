package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.LevelMapper;
import com.hbtcsrzzx.ssm.dao.mapper.EnrolCostMapper;
import com.hbtcsrzzx.ssm.po.Level;
import com.hbtcsrzzx.ssm.po.EnrolCost;
import com.hbtcsrzzx.ssm.po.EnrolCostExample;
import com.hbtcsrzzx.ssm.po.EnrolCostExample.Criteria;
import com.hbtcsrzzx.ssm.service.CostService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class CostServiceImpl implements CostService {

	@Autowired
	private EnrolCostMapper costMapper;

	@Autowired
	private LevelMapper levelMapper;

	@Override
	public List<EnrolCost> findAllCost(int currentPage, int pageSize) throws Exception {
		EnrolCostExample example = new EnrolCostExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int start = (currentPage - 1) * pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);

		List<EnrolCost> costList = costMapper.selectByExample(example);
		// 将关联的级别信息，合并到EnrolCost中
		for (EnrolCost enrolCost : costList) {
			Level level = levelMapper.selectByPrimaryKey(enrolCost.getLid());
			if (level.getState() != Constants.DEL_STATE) {
				enrolCost.setLevel(level);
			}

		}
		return costList;
	}

	@Override
	public int getCount() {

		EnrolCostExample example = new EnrolCostExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);

		return costMapper.countByExample(example);
	}

	@Override
	public int addEnrolCost(EnrolCost enrolCost) throws Exception {

		//查询名称是否重复
		Integer lid = costMapper.getLidIsNull(enrolCost.getLid());
		if(lid!=null){
			throw new RuntimeException("级别名称重复");
		}
		int res = -1;
		if (enrolCost != null)
			res = costMapper.insert(enrolCost);
		return res;
	}

	@Override
	public EnrolCost findEnrolCostById(int id) throws Exception {

		EnrolCost enrolCost = costMapper.selectByPrimaryKey(id);
		if (enrolCost != null && enrolCost.getState() != Constants.DEL_STATE)
			return enrolCost;
		return null;
	}

	@Override
	public int updateEnrolCostById(EnrolCost enrolCost) throws Exception {

		int res = -1;
		try {
			res = costMapper.updateByPrimaryKeySelective(enrolCost);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Integer getEnrolCostByLid(Integer lid) throws Exception {
		Integer cost = costMapper.findEnrolCostByLid(lid);
		if (cost == null || cost < 0) {
			throw new RuntimeException("费用不正确");
		}
		return cost = cost / 100;
	}

	@Override
	public Integer getEnrolCostByLName(String lname) {

		Integer cost = costMapper.getEnrolCostByLName(lname);
		if (cost == null || cost < 0) {
			throw new RuntimeException("费用不正确");
		}
		return cost = cost / 100;

	}

}
