package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.GUser;
import com.hbtcsrzzx.ssm.service.GUserService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/gusers")
public class GUserAction {

	private final static String MODULE = "GUser";
	@Autowired
	GUserService gUserService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/findAllGUser")
	@ResponseBody
	public Map<String, Object> findAllCategory(Integer page, Integer limit) {
		List<GUser> gUserList = null;
		int res = -1;
		String msg = "";
		try {
			gUserList = gUserService.findAllGUser(page, limit);
			if (gUserList != null) {
				res = 200;
			} else {
				msg = "操作有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("datasize", gUserService.getCount(Constants.NORMAL_STATE));
		result.put("rows", gUserList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/addGUser")
	@ResponseBody
	public Result addGUser(HttpServletRequest request, GUser gUser) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		
		try {
			if (admin != null && gUser != null) {
				int tmp = gUserService.addGUser(gUser);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"addGUser",-1,admin.getName(),Constants.ADMIN);
					return new Result(200, "添加成功");
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		
		}

		return new Result(-1, "添加失败");
	}
	
	
	@RequestMapping("/updateGUser")
	@ResponseBody
	public Map<String, Object> UpdateGUser(HttpServletRequest request,  Integer id, Integer category,
			GUser gUser) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			try {
				int tmp = gUserService.updateGUserById(gUser);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateGUser", gUser.getId(),
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
	
	
	@RequestMapping("/deleteGUser")
	@ResponseBody
	public Map<String, Object> deleteGUser(HttpServletRequest request,Integer id) {
		int res = -1;
		String msg="";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if(id!=null&&id>0) {
					GUser gUser =gUserService.findGUserById(id);
					gUser.setState(Constants.DEL_STATE);
					int tmp = gUserService.updateGUserById(gUser);
					if(tmp>0) {
						res=200;
						syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteGUser",id,admin.getName(),Constants.ADMIN);
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
}
