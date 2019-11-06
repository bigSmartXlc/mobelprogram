package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.service.AdminService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/admins")
public class AdminAction {
	@Autowired
	AdminService adminService;
	@Autowired 
	SyslogService syslogService;
	private static final String MODULE = "Admin";
	
	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, String username, String password) {
		Admin admin;
		String msg = "";
		int res = -1;
		try {
			if ((username != null) && !username.contains("=")) {
				admin = adminService.findAdminByName(username);
				if (admin != null && admin.getPsw().equals(password)) {
					admin.setIsonline(Constants.ONLINE);
					adminService.updateAdminById(admin);
					HttpSession session = request.getSession();
					session.setAttribute("admin", admin);
					if(Constants.map.containsKey(session.getId())) {
						Constants.map.put(session.getId(),admin);
					}
					res = 200;
				}else {
					msg="用户名或密码不正确";
				}
			}else {
				msg ="输入用户名有误";
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

	@RequestMapping(value = "/outLogin")
	@ResponseBody
	public int  outLogin(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return 200;
	}
	
	@RequestMapping("/addAdmin")
	@ResponseBody
	public Map<String, Object> addAdmin(HttpServletRequest request,String name,String phone,String auth,
			String psw,String email,String image) {
		int res = -1;
		String msg = "";
	    Admin admin = (Admin)request.getSession().getAttribute("admin");
	    if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
	    	if (name != null && psw != null) {
				Admin am = new Admin();
				am.setName(name);
				am.setPsw(psw);
				am.setAuth(Constants.NORMAL_ADMIN);
				am.setEmail(email);
				am.setImage(image);
				am.setIsonline(Constants.OUTLINE);
				am.setPhone(phone);
				am.setRegtime(DateUtils.getCurrent());
				am.setState(Constants.NORMAL_STATE);
				try {
					int tmp = adminService.addAdmin(am);
					if(tmp > 0) {
						syslogService.addSyslog(request.getRemoteAddr(),MODULE,"addAdmin",-1,
								admin.getName(),Constants.ADMIN);
						res = 200;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				msg = "用户名和密码不能为空值";
			}
	    }else {
	    	msg = "没有权限";
	    }
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		return result;
	}

	@RequestMapping("/updateAdmin")
	@ResponseBody
	public Map<String, Object> updateAdmin(HttpServletRequest request,Integer id, String name,String phone,String image,
			String email,String psw) {
		int res = -1;
		String msg= "";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
			if (id != null) {	
				Admin am = null;
				try {
					am = adminService.findAdminById(id);
					if (name != null)
						am.setName(name);
					if (psw != null)
						am.setPsw(psw);
					if (phone!=null)
						am.setPhone(phone);
					if(email != null)
						am.setEmail(email);
					if(image != null)
						am.setImage(image);
					int tmp = adminService.updateAdminById(am);
					if(tmp > 0) {
						syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateAdmin",am.getId(),
								admin.getName(),Constants.ADMIN);
						res =200;
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				msg="没有此管理员";
			}
		}else {
			msg = "没有权限";
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/deleteAdmin")
	@ResponseBody
	public Map<String, Object> deleteAdmin(HttpServletRequest request,Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if(id != null && id > 0) {		
						Admin am=adminService.findAdminById(id);
						am.setState(Constants.DEL_STATE);
						int tmp = adminService.updateAdminById(am);
						if(tmp > 0) {
							syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteAdmin",am.getId(),
									admin.getName(),Constants.ADMIN);
							res = 200;
						}
					}else {
						msg = "没有此管理员";
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
		return result;
	}

	@RequestMapping("/findAllAdmin")
	@ResponseBody
	public Map<String, Object> findAllAdmin(HttpServletRequest request, Integer page, Integer limit) {
		List<Admin> adminList = null;
		int res =-1;
		String msg="";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!= null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				adminList = adminService.findAllAdmin(page, limit);
				if(adminList != null) {
					res=200;
				}else {
					msg="请求有误";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			msg = "没有权限";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("datasize",adminService.getCount(Constants.NORMAL_STATE));
		result.put("rows", adminList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
