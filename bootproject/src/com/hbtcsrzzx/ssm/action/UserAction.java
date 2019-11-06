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
import com.hbtcsrzzx.ssm.po.User;
import com.hbtcsrzzx.ssm.po.UserWithBLOBs;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.ssm.service.UserService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/users")
public class UserAction {
	private final static String MODULE = "UserAction";
	@Autowired
	UserService userService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addUser")
	@ResponseBody
	public Map<String, Object> addUser(HttpServletRequest request, String name, String phone, String introduction,
			String image, String school, String birthplace, String addr, String category, String center, String subject,
			String level, String email, String psw, String context) {
		UserWithBLOBs user = new UserWithBLOBs();
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		user.setAddr(addr);
		user.setBirthplace(birthplace);
		user.setCategory(category);
		user.setIntroduction(introduction);
		user.setLevel(level);
		user.setEmail(email);
		user.setName(name);
		user.setPsw(psw);
		user.setCenter(center);
		user.setSchool(school);
		user.setSubject(subject);
		user.setImage(image);
		user.setContext(context);
		user.setPhone(phone);
		user.setState(Constants.NORMAL_STATE);
		user.setIsonline(Constants.OUTLINE);
		user.setRegtime(DateUtils.getCurrent());
		int res = -1;
		String msg = "";
		try {
			int tmp = userService.addUser(user);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addUser", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加用户失败";
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

	@RequestMapping("/updateUser")
	@ResponseBody
	public Map<String, Object> UpdateUser(HttpServletRequest request, Integer id, String name, String phone,
			String introduction, String image, String school, String birthplace, String addr, String category,
			String center, String subject, String level, String email, String psw, String context) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (id != null) {
			UserWithBLOBs user;
			try {
				user = userService.findUserById(id);

				if (addr != null)
					user.setAddr(addr);
				if (birthplace != null)
					user.setBirthplace(birthplace);
				if (category != null)
					user.setCategory(category);
				if (introduction != null)
					user.setIntroduction(introduction);
				if (level != null)
					user.setLevel(level);
				if (email != null)
					user.setEmail(email);
				if (name != null)
					user.setName(name);
				if (psw != null)
					user.setPsw(psw);
				if (center != null)
					user.setCenter(center);
				if (school != null)
					user.setSchool(school);
				if (subject != null)
					user.setSubject(subject);
				if (image != null)
					user.setImage(image);
				if (context != null)
					user.setContext(context);
				if (phone != null)
					user.setPhone(phone);
				
				int tmp = userService.updateUserById(user);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateUser", user.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
				} else {
					msg = "更新用户有误";
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

	@RequestMapping("/deleteUser")
	@ResponseBody
	public  Map<String, Object> deleteUser(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				UserWithBLOBs user =  userService.findUserById(id);
				user.setState(Constants.DEL_STATE);
				int tmp = userService.updateUserById(user);
				if(tmp >0) {
					 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteExpert",user.getId(),admin.getName(),Constants.ADMIN);
					res = 200;
				}else {
					msg="用户删除失败";
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
	@RequestMapping("/findUserById")
	@ResponseBody
	public Map<String, Object> findUserById(Integer id) {
		UserWithBLOBs user = null;
		int res=-1;
		String msg="";
		try {
			user = userService.findUserById(id);
			if(user !=null) {
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
		result.put("rows", user);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/getAllUser")
	@ResponseBody
	public Map<String, Object> getAllUser(HttpServletRequest request,int num) {
		List<User> userList = null;
		List<User> uList = new ArrayList<>();
		int res=-1;
		String msg="";
		try {
			userList = userService.getAllUser();
			if(userList !=null) {
				res= 200;
				int len = userList.size();
				for(int i=0;i<num;i++) {
					if(len==0){
						break;
					}
					int tmp = new Random().nextInt(len);
					uList.add(userList.get(tmp));
					userList.remove(tmp);
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
		result.put("rows", uList);
		result.put("urows",userList);
		return result;
	}
	
	@RequestMapping("/findAllUserBSN")
	@ResponseBody
	public Map<String, Object> findAllUserBSN(Model model, Integer page, Integer limit) {
		List<User> UserList = null;
		int res=-1;
		String msg="";
		try {
			UserList = userService.findAllUserBSN(page, limit);
			if(UserList !=null) {
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
		result.put("datasize", userService.getCount(Constants.NORMAL_STATE));
		result.put("rows", UserList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
    }


	@RequestMapping("/findAllUser")
	@ResponseBody
	public Map<String, Object> FindAllUser(Model model, Integer page, Integer limit) {
		List<UserWithBLOBs> UserList = null;
		int res=-1;
		String msg="";
		try {
			UserList = userService.findAllUser(page, limit);
			if(UserList !=null) {
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
		result.put("datasize", userService.getCount(Constants.NORMAL_STATE));
		result.put("rows", UserList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
