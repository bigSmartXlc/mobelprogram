package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.queryVo.Royalty;
import com.hbtcsrzzx.ssm.service.UserAndExamineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/userLogs")
public class UserLogAction {

	private final static String MODULE = "UserLog";
	@Autowired
	UserLogService userLogService;

	@Autowired
	SyslogService syslogService;

	@Autowired
	private UserAndExamineeService userAndExamineeService;
	@RequestMapping("/findAllUserLog")
	@ResponseBody
	public Map<String, Object> findAllCategory(Integer page, Integer limit) {
		int res = -1;
		String msg = "";
		List<UserLog> userLogList = null;
		try {
			userLogList = userLogService.findAllUserLog(page, limit);
			if (userLogList != null) {
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
		result.put("datasize", userLogService.getCount(Constants.NORMAL_STATE));
		result.put("rows", userLogList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/addUserLog")
	@ResponseBody
	public Result addUserLog(HttpServletRequest request, UserLog userLog) {

		Admin admin = (Admin) request.getSession().getAttribute("admin");

		try {
			if (admin != null && userLog != null) {
				int tmp = userLogService.addUserLog(userLog);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addUserLog", -1, admin.getName(),
							Constants.ADMIN);
					return new Result(200, "添加成功");
				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return new Result(-1, "添加失败");
	}

	@RequestMapping("/updateUserLog")
	@ResponseBody
	public Map<String, Object> UpdateUserLog(HttpServletRequest request, Integer id, Integer category,
			UserLog userLog) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null) {
			try {
				int tmp = userLogService.updateUserLogById(userLog);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateUserLog", userLog.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
					msg = "更新科目成功";
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

	@RequestMapping("/deleteUserLog")
	@ResponseBody
	public Map<String, Object> deleteUserLog(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					UserLog userLog = userLogService.findUserLogById(id);
					userLog.setState(Constants.DEL_STATE);
					int tmp = userLogService.updateUserLogById(userLog);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteUserLog", id, admin.getName(),
								Constants.ADMIN);
					}
				} else {
					msg = "此分类不存在";
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

	/**
	 *
	 * @param userId
	 * @return
	 */
	@RequestMapping("/queryUserrebate")
	@ResponseBody
	public Result queryUserrebate(Integer userId,Integer page,Integer limit){
		PageInfo<Royalty> pageInfo =  userAndExamineeService.queryUserrebate(userId,page,limit);
		return Result.ok(pageInfo);
	}
}
