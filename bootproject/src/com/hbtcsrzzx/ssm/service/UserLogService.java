package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.UserLog;

public interface UserLogService {
	
	public List<UserLog> findAllUserLog(int currentPage , int pageSize) throws Exception;
	public int getCount(int state);
	public int addUserLog(UserLog userLog);
	public UserLog findUserLogById(int id) throws Exception;
	public int updateUserLogById(UserLog subject) throws Exception;

	/**
	 * 查询该用户的具体信息
	 * @param userPhone
	 * @return
	 */
	public UserLog findUserLogByPhone(String userPhone);
	/**
	 * 判断用户输入的账号密码是否正确
	 * @param phone
	 * @param password
	 * @return
	 */
	public boolean getIsUserLog(String phone, String password);
	/**
	 * 记录用户的登录信息
	 * @param ipAddress
	 * @param phone
	 */
	public void addUserLogRecord(String ipAddress, String phone);
}
