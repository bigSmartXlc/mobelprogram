package com.hbtcsrzzx.ssm.service;

import java.util.List;
import java.util.Map;

import com.hbtcsrzzx.ssm.po.Category;

public interface CategoryService {
	public int addCategory(Category category) throws Exception;
	public Category findCategoryById(int id) throws Exception;
	public int updateCategoryById(Category category) throws Exception;
	public int deleteCategory(int id) throws Exception;
	public List<Category> findAllCategory(int currentPage,int pageSize)throws Exception;
	public List<Category> getAllCategory()throws Exception;
	public int getCount(int state);
	public Category findCategoryByName(String name)throws Exception;
}
