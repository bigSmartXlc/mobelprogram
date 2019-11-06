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
import com.hbtcsrzzx.ssm.po.Expert;
import com.hbtcsrzzx.ssm.po.ExpertWithBLOBs;
import com.hbtcsrzzx.ssm.service.ExpertService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/experts")
public class ExpertAction {
	private final static String MODULE = "ExpertAction";
	@Autowired
	ExpertService expertService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addExpert")
	@ResponseBody
	public Map<String, Object> addExpert(HttpServletRequest request, String name, String image, String birthplace,
			String company, String addr, String category, String subject, String eduLevel, String direction,
			String email, String introduction, String psw, String context, String cert) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		ExpertWithBLOBs expert = new ExpertWithBLOBs();
		expert.setAddr(addr);
		expert.setEduLevel(eduLevel);
		expert.setBirthplace(birthplace);
		expert.setCategory(category);
		expert.setCert(cert);
		expert.setCompany(company);
		expert.setContext(context);
		expert.setDirection(direction);
		expert.setEmail(email);
		expert.setImage(image);
		expert.setIntroduction(introduction);
		expert.setIsonline(Constants.OUTLINE);
		expert.setName(name);
		expert.setPsw(psw);
		expert.setRegtime(DateUtils.getCurrent());
		expert.setState(Constants.NORMAL_STATE);
		expert.setSubject(subject);
		int res = -1;
		String msg = "";
		try {
			int tmp = expertService.addExpert(expert);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addCategory", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加专家失败";
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/updateExpert")
	@ResponseBody
	public Map<String, Object> UpdateExpert(HttpServletRequest request, Integer id, String name, String image,
			String birthplace, String company, String addr, String category, String subject, String eduLevel,
			String direction, String email, String introduction, String psw, String context, String cert) {
		ExpertWithBLOBs expert;
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		try {
			expert = expertService.findExpertById(id);
			if (addr != null)
				expert.setAddr(addr);
			if (eduLevel != null)
				expert.setEduLevel(eduLevel);
			if (birthplace != null)
				expert.setBirthplace(birthplace);
			if (category != null)
				expert.setCategory(category);
			if (cert != null)
				expert.setCert(cert);
			if (company != null)
				expert.setCompany(company);
			if (context != null)
				expert.setContext(context);
			if (direction != null)
				expert.setDirection(direction);
			if (email != null)
				expert.setEmail(email);
			if (image != null)
				expert.setImage(image);
			if (introduction != null)
				expert.setIntroduction(introduction);
			expert.setIsonline(Constants.OUTLINE);
			if (name != null)
				expert.setName(name);
			if (psw != null)
				expert.setPsw(psw);
			if (subject != null)
				expert.setSubject(subject);
			expert.setRegtime(DateUtils.getCurrent());
			expert.setState(Constants.NORMAL_STATE);
			int tmp = expertService.updateExpertById(expert);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateExpert",expert.getId(),admin.getName(),Constants.ADMIN);
				res = 200;
			} else {
				msg = "没有此专家";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/deleteExpert")
	@ResponseBody
	public Map<String, Object> deleteExpert(HttpServletRequest request, Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg="";
		if(admin!= null && admin.getAuth() ==Constants.SUPER_ADMIN) {
			try {
				 ExpertWithBLOBs expert = expertService.findExpertById(id);
				 expert.setState(Constants.DEL_STATE);
				 int tmp = expertService.updateExpertById(expert);
				 if(tmp>0) {
					 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteExpert",expert.getId(),admin.getName(),Constants.ADMIN);
					 res= 200;
				 }else {
					 msg="操作有误";
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
		result.put("msg", msg);
		return result;
	}
	@RequestMapping("/findExpertById")
	@ResponseBody
	public Map<String, Object> findExpertById(Integer id) {
		ExpertWithBLOBs expert = null;
		int res=-1;
		String msg="";
		try {
			expert = expertService.findExpertById(id);
			if(expert !=null) {
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
		result.put("rows", expert);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/getAllExpert")
	@ResponseBody
	public Map<String, Object> getAllExpert(HttpServletRequest request,int num) {
		List<Expert> expertList = null;
		List<Expert> eList = new ArrayList<>();
		List<Integer>  iList= new ArrayList<>(); 
		int res=-1;
		String msg="";
		try {
			expertList = expertService.getAllExpert();
			if(expertList !=null) {
				res= 200;
				int len = expertList.size();
				for(int i=0;i<num;i++) {
					int tmp = new Random().nextInt(len);
					eList.add(expertList.get(tmp));
					expertList.remove(tmp);
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
		result.put("rows", eList);
		result.put("erows",expertList);
		return result;
	}
	/**
	 * 	按照姓名进行排序
	 * findAllExpertBySortName
	 * */
	@RequestMapping("/findAllExpertBSN")
	@ResponseBody
	public Map<String, Object> findAllExpertBSN(Integer page, Integer limit) {
		List<Expert> ExpertList = null;
		int res=-1;
		String msg="";
		try {
			ExpertList = expertService.findAllExpertBSN(page, limit);
			if(ExpertList !=null) {
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
		result.put("datasize", expertService.getCount(Constants.NORMAL_STATE));
		result.put("rows", ExpertList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllExpert")
	@ResponseBody
	public Map<String, Object> FindAllExpert(Model model, Integer page, Integer limit) {
		List<ExpertWithBLOBs> ExpertList = null;
		int res=-1;
		String msg="";
		try {
			ExpertList = expertService.findAllExpert(page, limit);
			if(ExpertList !=null) {
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
		result.put("datasize", expertService.getCount(Constants.NORMAL_STATE));
		result.put("rows", ExpertList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
