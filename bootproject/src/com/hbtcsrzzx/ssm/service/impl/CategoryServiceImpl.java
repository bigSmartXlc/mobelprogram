package com.hbtcsrzzx.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hbtcsrzzx.ssm.dao.mapper.CategoryMapper;
import com.hbtcsrzzx.ssm.po.Category;
import com.hbtcsrzzx.ssm.po.CategoryExample;
import com.hbtcsrzzx.ssm.service.CategoryService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryMapper categoryMapper;
	
	@Override
	public int addCategory(Category Category) throws Exception{
		// TODO Auto-generated method stub
		int res = -1;
		if(Category != null)
			res = categoryMapper.insert(Category);
		return res;
	}

	@Override
	public Category findCategoryById(int id) throws Exception {
		// TODO Auto-generated method stub
		Category category = categoryMapper.selectByPrimaryKey(id);
		if(category!=null && category.getState() != Constants.DEL_STATE)
			return category;
		return null;
	}
	@Override
	public int updateCategoryById(Category Category) throws Exception{
		int  res = -1;
		res = categoryMapper.updateByPrimaryKey(Category);
		return res;
	}
	
	@Override
	public int deleteCategory(int id) throws Exception {
		// TODO Auto-generated method stub
		//int res = categoryMapper.deleteByPrimaryKey(id);
		return -1;
	}
	@Override
	public List<Category> getAllCategory()throws Exception{
		CategoryExample example = new CategoryExample();
		CategoryExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		List<Category> categoryList = categoryMapper.selectByExample(example);
		return categoryList;
	}
	@Override
	public Category findCategoryByName(String name)throws Exception {
		CategoryExample example = new CategoryExample();
		CategoryExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<Category> categoryList = categoryMapper.selectByExample(example);
		if(categoryList !=null) {
			return categoryList.get(0);
		}
		return null;
	}
	@Override
	public List<Category>findAllCategory(int currentPage,int pageSize){
		CategoryExample example = new CategoryExample();
		CategoryExample.Criteria criteria = example.createCriteria();
		criteria.andStateNotEqualTo(Constants.DEL_STATE);
		int start = (currentPage-1)*pageSize;
		example.setPageSize(pageSize);
		example.setStartRow(start);
		List<Category> categoryList = categoryMapper.selectByExample(example);
		return categoryList;
	}
	@Override
	public int getCount(int state) {
		int cnt = 0;
		CategoryExample example = new CategoryExample();
		CategoryExample.Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		//criteria.andStateNotEqualTo(Constants.DEL_STATE);
		cnt = categoryMapper.countByExample(example);
		return cnt;
	}
}
