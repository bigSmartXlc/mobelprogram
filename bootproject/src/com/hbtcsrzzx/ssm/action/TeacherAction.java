package com.hbtcsrzzx.ssm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Teacher;
import com.hbtcsrzzx.ssm.po.TeacherWithBLOBs;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.ssm.service.TeacherService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("jsp/teachers")
public class TeacherAction {
	private final static String MODULE = "TeacherAction";
	@Autowired
	TeacherService teacherService;
	@Autowired
	SyslogService syslogService;
	
	@RequestMapping("/addTeacher")
	@ResponseBody
	public Map<String, Object> addTeacher(HttpServletRequest request,String name, String phone,String image, String company, String birthplace,
			String category,String eduLevel,String direction,String cert,String subject,String email,String introduction,String psw,String context) {
		TeacherWithBLOBs teacher = new TeacherWithBLOBs();
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		teacher.setBirthplace(birthplace);
		teacher.setCategory(category);
		teacher.setCert(cert);
		teacher.setEduLevel(eduLevel);
		teacher.setEmail(email);
		teacher.setCompany(company);
		teacher.setIntroduction(introduction);
		teacher.setDirection(direction);
		teacher.setName(name);
		teacher.setImage(image);
		teacher.setPsw(psw);
		teacher.setContext(context);
		teacher.setSubject(subject);
		teacher.setIsonline(Constants.OUTLINE);
		teacher.setState(Constants.NORMAL_STATE);
		teacher.setPhone(phone);
		teacher.setRegtime(DateUtils.getCurrent());
		int res = -1;
		String msg="";
		try {
			int tmp = teacherService.addTeacher(teacher);
			if(tmp >0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addTeacher",-1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			}else {
				msg="添加老师失败";
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

	
	@RequestMapping("/updateTeacher")
	@ResponseBody
	public Map<String, Object> UpdateTeacher(HttpServletRequest request,Integer id,String name, String phone,String image, String company, String birthplace,
			String category,String eduLevel,String direction,String cert,String subject,String email,String introduction,String psw,String context) {
		int res = -1;
		String msg="";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(id != null) {
			TeacherWithBLOBs teacher;
			try {
				teacher = teacherService.findTeacherById(id);
				if(teacher != null) {
					if(birthplace!=null)
						teacher.setBirthplace(birthplace);
					if(category!=null)
						teacher.setCategory(category);
					if(cert!=null)
						teacher.setCert(cert);
					if(eduLevel!=null)
						teacher.setEduLevel(eduLevel);
					if(email!=null)
						teacher.setEmail(email);
					if(company!=null)
						teacher.setCompany(company);
					if(introduction!=null)
						teacher.setIntroduction(introduction);
					if(direction!=null)
						teacher.setDirection(direction);
					if(name!=null)
						teacher.setName(name);
					if(image!=null)
						teacher.setImage(image);
					if(psw!=null)
						teacher.setPsw(psw);
					if(context!=null)
						teacher.setContext(context);
					if(subject!=null)
						teacher.setSubject(subject);
					if(phone!=null)
						teacher.setPhone(phone);	
					int tmp = teacherService.updateTeacherById(teacher);
					if (tmp > 0) {
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateTeacher", teacher.getId(),
								admin.getName(), Constants.ADMIN);
						res = 200;
					} else {
						msg = "更新老师有误";
					}
				}else {
					msg="操作有误";
				}	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}		
		}else {
			msg="没有此老师纪录";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		return result;
	}
	
	
	
	@RequestMapping("/deleteTeacher")
	@ResponseBody
	public Map<String, Object>  deleteTeacher(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				TeacherWithBLOBs teacher = teacherService.findTeacherById(id);
				teacher.setState(Constants.DEL_STATE);
				int tmp  = teacherService.updateTeacherById(teacher);
				if(tmp >0) {
					 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteExpert",teacher.getId(),admin.getName(),Constants.ADMIN);
					res = 200;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "没有权限";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findTeacherById")
	@ResponseBody
	public Map<String, Object> findTeacherById(Integer id) {
		TeacherWithBLOBs teacher = null;
		int res=-1;
		String msg="";
		try {
			teacher = teacherService.findTeacherById(id);
			if(teacher !=null) {
				res= 200;
			}else {
				msg="操作有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", teacher);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/getAllTeacher")
	@ResponseBody
	public Map<String, Object> getAllTeacher(HttpServletRequest request,Integer num) {
		List<Teacher> teacherList = null;
		List<Teacher> tList = new ArrayList<>();
		List<Integer>  iList= new ArrayList<>(); 
		int res=-1;
		String msg="";
		try {
			teacherList = teacherService.getAllTeacher();
			if(teacherList !=null) {
				res= 200;
				int len = teacherList.size();
				
				for(int i=0;i<num;i++) {
					if(len==0){
						break;
					}
					int tmp = new Random().nextInt(len);
					tList.add(teacherList.get(tmp));
					teacherList.remove(tmp);
					len--;
					
				}
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
		result.put("rows", tList);
		result.put("trows",teacherList);
		return result;
	}
	
	
	@RequestMapping("/findAllTeacherBSN")
	@ResponseBody
	public Map<String, Object> findAllTeacherBSN(Model model, Integer page, Integer limit) {
		List<Teacher> TeacherList = null;
		int res=-1;
		String msg="";
		try {
			TeacherList = teacherService.findAllTeacherBSN(page, limit);
			if(TeacherList !=null) {
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
		result.put("datasize", teacherService.getCount(Constants.NORMAL_STATE));
		result.put("rows", TeacherList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllTeacher")
	@ResponseBody
	public Map<String, Object> FindAllTeacher(Model model, Integer page, Integer limit) {
		List<TeacherWithBLOBs> TeacherList = null;
		int res=-1;
		String msg="";
		try {
			TeacherList = teacherService.findAllTeacher(page, limit);
			if(TeacherList !=null) {
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
		result.put("datasize", teacherService.getCount(Constants.NORMAL_STATE));
		result.put("rows", TeacherList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
