package com.hbtcsrzzx.ssm.service;

import java.util.List;

import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.User;
import com.hbtcsrzzx.ssm.po.UserWithBLOBs;


public interface UserService {
	public int addUser(UserWithBLOBs news) throws Exception;
	public UserWithBLOBs findUserById(int id) throws Exception;
	public int updateUserById(UserWithBLOBs news) throws Exception;
	public int deleteUser(int id) throws Exception;
	public List<UserWithBLOBs> findAllUser(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public List<User> getAllUser();
	/*按照姓名拼音排序*/
	public List<User> findAllUserBSN(int currentPage,int pageSize)throws Exception;
}
