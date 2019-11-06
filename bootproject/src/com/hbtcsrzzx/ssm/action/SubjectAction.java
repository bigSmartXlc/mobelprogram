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
import com.hbtcsrzzx.ssm.po.Subject;
import com.hbtcsrzzx.ssm.service.CategoryService;
import com.hbtcsrzzx.ssm.service.SubjectService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("jsp/subjects")
public class SubjectAction {
	private final static String MODULE = "SubjectAction";
	@Autowired
	SubjectService subjectService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addSubject")
	@ResponseBody
	public Map<String, Object> addSubject(HttpServletRequest request, Integer id, Integer category, String name) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Subject subject = new Subject();
		subject.setId(id);
		subject.setName(name);
		subject.setCid(category);
		subject.setState(Constants.NORMAL_STATE);
		int res = -1;
		String msg = "";
		try {
			int tmp = subjectService.addSubject(subject);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addSubject", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加科目失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		return result;
	}

	@RequestMapping("/updateSubject")
	@ResponseBody
	public Map<String, Object> UpdateSubject(HttpServletRequest request, Model model, Integer id, Integer category,
			String name) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (id != null) {
			try {
				Subject subject = subjectService.findSubjectById(id);
				if (name != null) {
					subject.setName(name);
				}
				if (category != null) {
					subject.setCid(category);
				}
				int tmp = subjectService.updateSubjectById(subject);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateUser", subject.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
				} else {
					msg = "更新科目有误";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "操作有误";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/getSubjectByCid")
	@ResponseBody
	public Map<String, Object> getSubjectByCid(HttpServletRequest request,Integer cid,HttpServletResponse response){
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		int res =-1;
		String msg="";
		List<Subject> list = null;
		if(cid != null){
			list = subjectService.findSubjectByCid(cid);
			res=200;
		}else {
			msg="操作有误";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", list);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/getSubjectByCName")
	@ResponseBody
	public Map<String, Object> getSubjectByCName(HttpServletRequest request,String cname,HttpServletResponse response){
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		int res =-1;
		String msg="";
		List<Subject> list = null;
		if(cname != null){
			try {
				Category category=categoryService.findCategoryByName(cname);
				if(category!=null) {
					return getSubjectByCid(request, category.getId(),response);
				}else {
					msg="分类错误";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			msg="操作有误";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", list);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/deleteSubject")
	@ResponseBody
	public Map<String, Object> deleteSubject(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				 Subject subject = subjectService.findSubjectById(id);
				 subject.setState(Constants.DEL_STATE);
				 int tmp =subjectService.updateSubjectById(subject);
				if(tmp >0) {
					 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteExpert",subject.getId(),admin.getName(),Constants.ADMIN);
					res = 200;
				}else {
					msg="删除科目失败";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}else {
			msg = "没有权限";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
		// model.addAttribute("delSubject", res);
	}



	@RequestMapping("/findAllSubject")
	@ResponseBody
	public Map<String, Object> FindAllSubject(Model model, Integer page, Integer limit) {
		List<Subject> subjectList = null;
		int res=-1;
		String msg="";
		try {
			subjectList = subjectService.findAllSubject(page, limit);
			if(subjectList !=null) {
				res= 200;
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
		result.put("datasize", subjectService.getCount(Constants.NORMAL_STATE));
		result.put("rows", subjectList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

}
