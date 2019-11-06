package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/enrolExaminees")
public class EnrolExamineeAction {
	private final static String MODULE = "EnrolExaminee";
	@Autowired
	private EnrolExamineeService examineeService;

	@Autowired
	private SyslogService syslogService;

	@RequestMapping("/findAllEnrolExaminee")
	@ResponseBody
	public Map<String, Object> findAllEnrolExaminee(Integer page, Integer limit, String examineeName, String searchCity,
			String searchAuditStatus, String searchDate) {
		List<EnrolExaminee> cityList = null;
		int res = -1;
		String msg = "";
		try {
			cityList = examineeService.findAllEnrolExaminee(page, limit, examineeName, searchCity, searchAuditStatus,
					searchDate);
			if (cityList != null) {
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
		result.put("datasize", examineeService.getCount());
		result.put("rows", cityList);
		return result;
	}

	@RequestMapping("/updateEnrolExaminee")
	@ResponseBody
	public Map<String, Object> updateEnrolExaminee(HttpServletRequest request, EnrolExaminee enrolExaminee) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && enrolExaminee != null) {
			try {

				int tmp = examineeService.updateEnrolExaminee(enrolExaminee);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateEnrolExaminee",
							enrolExaminee.getId(), admin.getName(), Constants.ADMIN);
					res = 200;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			msg = "此分类不存在";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@ResponseBody
	@RequestMapping("/deleteEnrolExaminee")
	public Map<String, Object> deleteEnrolExaminee(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					EnrolExaminee enrolScene = examineeService.findEnrolExamineeById(id);
					enrolScene.setState(Constants.DEL_STATE);
					int tmp = examineeService.updateEnrolExaminee(enrolScene);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteEnrolExaminee", id,
								admin.getName(), Constants.ADMIN);
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

	@RequestMapping("/examine")
	@ResponseBody
	public Result updateEauditStatus(HttpServletRequest request,
			@RequestParam(defaultValue = "0", required = true, value = "auditStatus") Integer auditStatus,
			@RequestParam(defaultValue = "0", required = true, value = "id") Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (auditStatus != 0 && id != 0) {
					EnrolExaminee enrolExaminee = examineeService.findEnrolExamineeById(id);
					enrolExaminee.setAuditStatus(auditStatus);
					examineeService.updateEnrolExaminee(enrolExaminee);

					return new Result(200, "提交成功");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return new Result(-1, "提交失败");
	}
}
