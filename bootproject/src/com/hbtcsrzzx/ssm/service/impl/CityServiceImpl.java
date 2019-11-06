package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CityMapper;
import com.hbtcsrzzx.ssm.po.City;
import com.hbtcsrzzx.ssm.po.CityExample;
import com.hbtcsrzzx.ssm.po.CityExample.Criteria;
import com.hbtcsrzzx.ssm.service.CityService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class CityServiceImpl implements CityService{

	@Autowired
	private CityMapper cityMapper;
	
	@Override
	public List<City> findAllCity(int currentPage, int pageSize) throws Exception {
		CityExample example = new CityExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		List<City> cityList = cityMapper.selectByExample(example);
		return cityList;
	}

	@Override
	public int getCount(int state) {
		int cnt = 0;
		CityExample example = new CityExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(state);
		cnt = cityMapper.countByExample(example);
		
		return cnt;
	}

	@Override
	public int addCity(City city) throws Exception {
		
		int res = -1;
		if(city != null)
			res = cityMapper.insert(city);



	 //int i = 1/0;
		return res;
	}

	@Override
	public City findCityById(int id) throws Exception {
		City city = cityMapper.selectByPrimaryKey(id);
		if(city!=null && city.getState() != Constants.DEL_STATE)
			return city;
		return null;
	}

	@Override
	public int updateCityById(City city) throws Exception {
		int  res = -1;
		try {
			res = cityMapper.updateByPrimaryKey(city);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	

}
