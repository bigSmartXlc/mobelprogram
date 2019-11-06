package com.hbtcsrzzx.ssm.service.impl;

import java.util.Date;
import java.util.List;

import com.hbtcsrzzx.ssm.dao.mapper.EnrolExamineeMapper;
import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import com.hbtcsrzzx.ssm.po.EnrolExamineeExample;
import com.hbtcsrzzx.ssm.po.ExamineePayLog;
import com.hbtcsrzzx.ssm.service.ExamineePayLogService;
import com.hbtcsrzzx.utils.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbtcsrzzx.ssm.po.EnrolExamineeExample.Criteria;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Service
public class EnrolExamineeServiceImpl implements EnrolExamineeService {

	@Autowired
	private EnrolExamineeMapper enrolExamineeMapper;

	@Autowired
	private ExamineePayLogService examineePayLogService;
	@Override
	public List<EnrolExaminee> findAllEnrolExaminee(Integer pageNum, Integer pageSize, String examineeName,
													String searchCity, String searchAuditStatus, String searchDate) {

		if (pageNum == null || pageNum == 0) {
			pageNum = 1;
		}

		if (pageSize == null || pageSize == 0) {
			pageSize = 10;
		}

		if (examineeName == "") {
			examineeName = null;
		}
		if (searchCity == "") {
			searchCity = null;
		}

		if (searchAuditStatus == "") {
			searchAuditStatus = null;
		}

		if (searchDate == "") {
			searchDate = null;
		}
		int startPage = (pageNum - 1)*pageSize;

		return enrolExamineeMapper.findAllEnrolExaminee(startPage, pageSize, examineeName, searchCity,
				searchAuditStatus, searchDate);

	}

	@Override
	public int getCount() {
		EnrolExamineeExample example = new EnrolExamineeExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);

		return enrolExamineeMapper.countByExample(example);
	}

	@Override
	public int updateEnrolExaminee(EnrolExaminee enrolExaminee) {
		enrolExaminee.setUpdateTime(new Date());
		return enrolExamineeMapper.updateByPrimaryKeySelective(enrolExaminee);
	}

	@Override
	public EnrolExaminee findEnrolExamineeById(int id) throws Exception {
		EnrolExaminee examinee = enrolExamineeMapper.findEnrolExamineeById(id);
		if (examinee == null || examinee.getState() == Constants.DEL_STATE) {
			throw new RuntimeException("考生信息有误");
		}
		return examinee;
	}

	@Override
	public int addEnrolExaminee(EnrolExaminee enrolExaminee) throws Exception {

		enrolExaminee.setState(Constants.NORMAL_STATE);
		enrolExaminee.setCreateTime(new Date());
		enrolExaminee.setUpdateTime(new Date());
		enrolExaminee.setPayStatus(0);
		enrolExaminee.setAuditStatus(0);
		enrolExaminee.setExaminationResults(0);
		//插入订单id
		long orderId = new IdWorker().nextId();
		enrolExaminee.setOrderId(orderId);
		int i = enrolExamineeMapper.insert(enrolExaminee);
		if(i != 1){
			throw new RuntimeException("报考信息填写失败");
		}
		//创建支付日志信息
		ExamineePayLog examineePayLog = new ExamineePayLog();
		examineePayLog.setOrderId(orderId);
		examineePayLog.setCreateTime(new Date());
		examineePayLog.setTradeState(Constants.TRADE_STATE_NO);
		examineePayLog.setTotalFee(enrolExaminee.getCost());
		examineePayLogService.insertExamineePayLog(examineePayLog);

		return enrolExaminee.getId();

	}

	@Override
	public List<EnrolExaminee> findEnrolExamineeByPhone(String phone) {

		List<EnrolExaminee> examinees = enrolExamineeMapper.findEnrolExamineeByPhone(phone);

		return examinees;
	}

	@Override
	public EnrolExaminee findEnrolExamineeByExaminationCard(String examinationCard) {

		return enrolExamineeMapper.findEnrolExamineeByExaminationCard(examinationCard);
	}

	@Override
	public EnrolExaminee findEnrolExamineeByOrderId(Long orderId) {


		return enrolExamineeMapper.findEnrolExamineeByOrderId(orderId);
	}

	@Override
	public List<EnrolExaminee> findEnrolExamineeByTimeout(Integer payStatus, Date date) {


		return enrolExamineeMapper.findEnrolExamineeByTimeout(payStatus,date);
	}

}
