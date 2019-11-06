package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.NewsMapper;
import com.hbtcsrzzx.ssm.po.NewsExample;
import com.hbtcsrzzx.ssm.po.NewsWithBLOBs;
import com.hbtcsrzzx.ssm.service.NewsService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class NewsServiceImpl implements NewsService {
	
	@Autowired
	NewsMapper newsMapper;
	
	@Override
	public int addNews(NewsWithBLOBs News) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(News != null)
			res = newsMapper.insert(News);
		return res;
	}

	@Override
	public NewsWithBLOBs findNewsById(int id) throws Exception {
		// TODO Auto-generated method stub
		NewsWithBLOBs news = newsMapper.selectByPrimaryKey(id);
		if(news != null && news.getState() != Constants.DEL_STATE) {
			return news;
		}
		return null;
	}
	@Override
	public int updateNewsById(NewsWithBLOBs news) throws Exception{
		int  res = -1;
		if(news != null)
			res = newsMapper.updateByPrimaryKeyWithBLOBs(news);
		return res;
	}
	
	@Override
	public int deleteNews(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = newsMapper.deleteByPrimaryKey(id);
		return -1;//res;
	}
	
	@Override
	public List<NewsWithBLOBs>findAllNews(int currentPage,int pageSize){
		NewsExample example = new NewsExample();
		NewsExample.Criteria criteria = example.createCriteria();
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		//排序
		example.setOrderByClause("date DESC");
		List<NewsWithBLOBs> newsList = newsMapper.selectByExampleWithBLOBs(example);
		return newsList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		NewsExample example = new NewsExample();
		NewsExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = newsMapper.countByExample(example);
		return cnt;
	}
	
	@Override
	public List<NewsWithBLOBs> getNewsByNum(int num) {
		NewsExample example = new NewsExample();
		NewsExample.Criteria criteria = example.createCriteria();
		int start = 0;
		example.setPageSize(num);
		example.setStartRow(start);
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<NewsWithBLOBs> newsList = newsMapper.selectByExampleWithBLOBs(example);
		return newsList;
	}

	@Override
	public List<NewsWithBLOBs> findAllNewsByTitle(Integer page, Integer limit) {
	
		if(page ==null || page <= 0){
			page = 1;
		}
		
		if(limit ==null || limit <= 0){
			limit = 10;
		}
		Integer start = (page-1)*limit;
		
		List<NewsWithBLOBs> news = 	newsMapper.findAllNewsByTitle(start,limit);
		
		if(news == null || news.size()<=0){
			throw new RuntimeException("新闻不存在");
		}
		
		return news;
	}
}
