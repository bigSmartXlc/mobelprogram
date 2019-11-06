package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Notice;
import com.hbtcsrzzx.ssm.po.NoticeWithBLOBs;

public interface NoticeService {
	public int addNotice(NoticeWithBLOBs notice) throws Exception;
	public NoticeWithBLOBs findNoticeById(int id) throws Exception;
	public int updateNoticeById(NoticeWithBLOBs notice) throws Exception;
	public int deleteNotice(int id) throws Exception;
	public List<NoticeWithBLOBs> findAllNotice(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
}
