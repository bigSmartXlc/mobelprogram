package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.NoticeMapper;
import com.hbtcsrzzx.ssm.po.NoticeExample;
import com.hbtcsrzzx.ssm.po.NoticeWithBLOBs;
import com.hbtcsrzzx.ssm.service.NoticeService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class NoticeServiceImpl implements NoticeService {
	
	@Autowired
	NoticeMapper noticeMapper;
	
	@Override
	public int addNotice(NoticeWithBLOBs Notice) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Notice != null)
			res = noticeMapper.insert(Notice);
		return res;
	}

	@Override
	public NoticeWithBLOBs findNoticeById(int id) throws Exception {
		// TODO Auto-generated method stub
		NoticeWithBLOBs Notice = noticeMapper.selectByPrimaryKey(id);
		if(Notice.getState() !=Constants.DEL_STATE) {
			return Notice;
		}
		return null;
	}
	@Override
	public int updateNoticeById(NoticeWithBLOBs Notice) throws Exception{
		int  res = -1;
		if(Notice != null)
			res = noticeMapper.updateByPrimaryKeyWithBLOBs(Notice);
		return res;
	}
	
	@Override
	public int deleteNotice(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = noticeMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<NoticeWithBLOBs>findAllNotice(int currentPage,int pageSize){
		NoticeExample example = new NoticeExample();
		NoticeExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		example.setOrderByClause("STR_TO_DATE( regtime,'%Y年%m月%d日 %H:%i:%s') DESC");
		List<NoticeWithBLOBs> NoticeList = noticeMapper.selectByExampleWithBLOBs(example);
		return NoticeList;
	}
	
	
	@Override
	public int getCount(int state) {
		int cnt = 0;
		NoticeExample example = new NoticeExample();
		NoticeExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = noticeMapper.countByExample(example);
		return cnt;
	}
}
