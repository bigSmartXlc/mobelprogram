package com.hbtcsrzzx.ssm.service;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;

import java.util.Date;
import java.util.List;

public interface EnrolExamineeService {

	List<EnrolExaminee> findAllEnrolExaminee(Integer pageNum, Integer pageSize, String examineeName , String searchCity, String searchAuditStatus, String searchDate);

	int getCount();

	int updateEnrolExaminee(EnrolExaminee enrolExaminee);
	
	EnrolExaminee findEnrolExamineeById(int id) throws Exception;

	int addEnrolExaminee(EnrolExaminee enrolExaminee) throws Exception;

	List<EnrolExaminee> findEnrolExamineeByPhone(String phone);

	EnrolExaminee findEnrolExamineeByExaminationCard(String examinationCard);

	/**
	 * 根据订单id,查询考生
	 * @param orderId 订单id
	 * @return  考生
	 */
	EnrolExaminee findEnrolExamineeByOrderId(Long orderId);

	/**
	 * 查询超时未支付的考生
	 * @param date 超时时间
	 * @param payStatus  支付状态
	 * @return 考生结果集
	 */
	List<EnrolExaminee> findEnrolExamineeByTimeout(Integer payStatus,Date date);
}
