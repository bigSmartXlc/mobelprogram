package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.News;
import com.hbtcsrzzx.ssm.po.NewsWithBLOBs;

public interface NewsService {
	public int addNews(NewsWithBLOBs news) throws Exception;
	public NewsWithBLOBs findNewsById(int id) throws Exception;
	public int updateNewsById(NewsWithBLOBs news) throws Exception;
	public int deleteNews(int id) throws Exception;
	public List<NewsWithBLOBs> findAllNews(int currentPage,int pageSize)throws Exception;
	public int getCount(int state);
	public List<NewsWithBLOBs> getNewsByNum(int num);
	public List<NewsWithBLOBs> findAllNewsByTitle(Integer page, Integer limit);
}
