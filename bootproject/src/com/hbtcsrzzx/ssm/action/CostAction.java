package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.EnrolCost;
import com.hbtcsrzzx.ssm.service.CostService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/costs")
public class CostAction {

	private final static String MODULE = "Costs";
	@Autowired
	CostService costService;

	@Autowired
	SyslogService syslogService;


	@RequestMapping("/findAllCost")
	@ResponseBody
	public Map<String, Object> findAllCost(Integer page, Integer limit) {
		List<EnrolCost> costList = null;
		int res = -1;
		String msg = "";
		try {
			costList = costService.findAllCost(page, limit);
			if (costList != null) {
				res = 200;
			} else {
				msg = "操作有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("datasize", costService.getCount());
		result.put("rows", costList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/addCost")
	@ResponseBody
	public Map<String, Object> addCost(HttpServletRequest request, EnrolCost cost) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		int res = -1;
		try {
			if (admin != null && cost != null) {
				cost.setCost(cost.getCost() * 100);
				cost.setState(Constants.NORMAL_STATE);
				int tmp = costService.addEnrolCost(cost);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addCost", -1, admin.getName(),
							Constants.ADMIN);
					res = 200;
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

	@RequestMapping("/updateCost")
	@ResponseBody
	public Map<String, Object> updateCost(HttpServletRequest request, EnrolCost cost) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && cost != null) {
			try {

				cost.setCost(cost.getCost() * 100);
				int tmp = costService.updateEnrolCostById(cost);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateCost", cost.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
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

	@RequestMapping("/deleteCost")
	@ResponseBody
	public Map<String, Object> deleteCost(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					EnrolCost cost = costService.findEnrolCostById(id);
					cost.setState(Constants.DEL_STATE);
					int tmp = costService.updateEnrolCostById(cost);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteCost", id, admin.getName(),
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

	@RequestMapping("/getCostByLid")
	@ResponseBody
	public Map<String, Object> getCostByLid(HttpServletRequest request, Integer lid,HttpServletResponse response) {
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		int res = -1;
		String msg = "";
		Integer cost = null;
		try {
			if (lid != null) {
				cost = costService.getEnrolCostByLid(lid);
				res = 200;
			} else {
				msg = "操作有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", cost);
		return result;
	}

	@RequestMapping("/getCostByLName")
	@ResponseBody
	public Map<String, Object> getEnrolCostByCName(HttpServletRequest request, String lname,HttpServletResponse response) {
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		int res = -1;
		String msg = "";
		Integer cost = null;
		try {
			if (lname != null) {
				cost = costService.getEnrolCostByLName(lname);
				res = 200;
			} else {
				msg = "分类错误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", cost);
		return result;
	}

	@RequestMapping("/deleteEnrolCost")
	@ResponseBody
	public Map<String, Object> deleteEnrolCost(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				EnrolCost subject = costService.findEnrolCostById(id);
				subject.setState(Constants.DEL_STATE);
				int tmp = costService.updateEnrolCostById(subject);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteExpert", subject.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
				} else {
					msg = "删除科目失败";
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
		// model.addAttribute("delEnrolCost", res);
	}

}
