package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.SyslogMapper;
import com.hbtcsrzzx.ssm.po.Syslog;
import com.hbtcsrzzx.ssm.po.SyslogExample;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

public class SyslogServiceImpl implements SyslogService {
	
	@Autowired
	SyslogMapper SyslogMapper;
	
	private int insertSyslog(Syslog Syslog) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Syslog != null)
			res = SyslogMapper.insert(Syslog);
		return res;
	}

	@Override
	public Syslog findSyslogById(int id) throws Exception {
		// TODO Auto-generated method stub
		Syslog Syslog = SyslogMapper.selectByPrimaryKey(id);
		if(Syslog!=null && Syslog.getState()!=Constants.DEL_STATE)
			return Syslog;
		return null;
	}
	@Override
	public int updateSyslogById(Syslog Syslog) throws Exception{
		int  res = -1;
		if(Syslog != null)
			res = SyslogMapper.updateByPrimaryKey(Syslog);
		return res;
	}
	
	@Override
	public int deleteSyslog(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = SyslogMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<Syslog>findAllSyslog(int currentPage,int pageSize){
		SyslogExample example = new SyslogExample();
		SyslogExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Syslog> SyslogList = SyslogMapper.selectByExample(example);
		return SyslogList;
	}
	
	@Override
    public int addSyslog(String ip, String module, String subModule, int uid, String uname, int type) throws Exception {
		int res = -1;
		Syslog syslog = new Syslog();
		syslog.setIp(ip);
		syslog.setLogtime(DateUtils.getCurrent());
		syslog.setModule(module);
		syslog.setState(Constants.NORMAL_STATE);
		syslog.setSubmodule(subModule);
		syslog.setType(type);
		syslog.setUid(uid);
		syslog.setUname(uname);				
		res = insertSyslog(syslog);
		return res;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		SyslogExample example = new SyslogExample();
		SyslogExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = SyslogMapper.countByExample(example);
		return cnt;
	}
}
