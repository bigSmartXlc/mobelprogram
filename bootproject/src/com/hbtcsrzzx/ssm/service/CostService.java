package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.EnrolCost;

public interface CostService {

	public List<EnrolCost> findAllCost(int currentPage, int pageSize) throws Exception;

	public int getCount();

	public int addEnrolCost(EnrolCost testCost) throws Exception;

	public EnrolCost findEnrolCostById(int id) throws Exception;

	public int updateEnrolCostById(EnrolCost testCost) throws Exception;

	public Integer getEnrolCostByLid(Integer lid)throws Exception;

	public Integer getEnrolCostByLName(String lname);

}
