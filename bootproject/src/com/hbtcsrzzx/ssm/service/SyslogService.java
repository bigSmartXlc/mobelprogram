package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Syslog;

public interface SyslogService {
	public Syslog findSyslogById(int id) throws Exception;
	public int updateSyslogById(Syslog Syslog) throws Exception;
	public int deleteSyslog(int id) throws Exception;
	public List<Syslog> findAllSyslog(int currentPage,int pageSize)throws Exception;
	public int addSyslog(String ip,String module,String subModule,int uid,String uname,int type) throws Exception;
	public int getCount(int state);
}
