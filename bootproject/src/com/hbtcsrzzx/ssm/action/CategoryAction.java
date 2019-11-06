package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Category;
import com.hbtcsrzzx.ssm.service.CategoryService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/categorys")
public class CategoryAction {
	private final static String MODULE = "Category";
	@Autowired
	CategoryService  categoryService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addCategory")
	@ResponseBody
	public Map<String, Object> addCategory(HttpServletRequest request, String name) {
		Category category = new Category();	
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		int res = -1;
		try {
			if(admin!=null && name != null) {
				category.setName(name);
				category.setState(Constants.NORMAL_STATE);
				int tmp = categoryService.addCategory(category);
				if(tmp > 0){
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"addCategory",-1,admin.getName(),Constants.ADMIN);
					res=200;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/updateCategory")
	@ResponseBody
	public Map<String, Object> updateCategory(HttpServletRequest request,Integer id, String name) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		Category category = null;
		if(admin!=null && id!=null && id>0) {
			try {
				category = categoryService.findCategoryById(id);
				if(name != null) {
					category.setName(name);
				}
				int tmp = categoryService.updateCategoryById(category);
				if(tmp > 0){
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateCategory",category.getId(),admin.getName(),Constants.ADMIN);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}else {
			msg = "此分类不存在";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	
	@RequestMapping("/deleteCategory")
	@ResponseBody
	public Map<String, Object> deleteCategory(HttpServletRequest request,Integer id) {
		int res = -1;
		String msg="";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if(id!=null&&id>0) {
					Category category =categoryService.findCategoryById(id);
					category.setState(Constants.DEL_STATE);
					int tmp = categoryService.updateCategoryById(category);
					if(tmp>0) {
						res=200;
						syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteCategory",id,admin.getName(),Constants.ADMIN);
					}
				}else {
					msg="此分类不存在";
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}else {
			msg="没有权限";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg",msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/getAllCategory")
	@ResponseBody
	public Map<String, Object> getAllCategory(HttpServletResponse response) {
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		List<Category> categoryList = null;
		int res= -1;
		String msg="";
		try {
			categoryList = categoryService.getAllCategory();
			if(categoryList != null) {
				res = 200;
			}else {
				msg="操作有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("datasize", categoryService.getCount(Constants.NORMAL_STATE));
		result.put("rows", categoryList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllCategory")
	@ResponseBody
	public Map<String, Object> findAllCategory(Model model, Integer page, Integer limit) {
		List<Category> categoryList = null;
		int res= -1;
		String msg="";
		try {
			categoryList = categoryService.findAllCategory(page, limit);
			if(categoryList != null) {
				res = 200;
			}else {
				msg="操作有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("datasize", categoryService.getCount(Constants.NORMAL_STATE));
		result.put("rows", categoryList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
}
