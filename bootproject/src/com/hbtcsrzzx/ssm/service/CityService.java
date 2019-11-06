package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.City;

public interface CityService {
	/**
	 * 分页查询城市列表
	 * @param currentPage  当前页
	 * @param pageSize     每页显示数
	 * @return
	 * @throws Exception
	 */
	 public List<City> findAllCity(int currentPage,int pageSize)throws Exception;
	  
	  
	  /**
	   * 查询出状态正常的总记录数
	   * @param state
	   * @return
	   */
	 public int getCount(int state);
	 
	 /**
	  * 新增城市信息
	  * @param city 城市名称
	  * @return
	  * @throws Exception
	  */
	 public int addCity(City city) throws Exception;
	 
	 /**
	  *根据id查询城市信息
	  * @param id
	  * @return
	  * @throws Exception
	  */
	 public City findCityById(int id) throws Exception;
	 
	 /**
	  * 修改单个城市信息
	  * @param city
	  * @return
	  * @throws Exception
	  */
	 public int updateCityById(City city)throws Exception;
	
}
