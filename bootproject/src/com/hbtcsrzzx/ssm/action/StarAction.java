package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Star;
import com.hbtcsrzzx.ssm.service.StarService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("jsp/stars")
public class StarAction {
	private final static String MODULE = "StarAction";
	@Autowired
	StarService starService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addStar")
	@ResponseBody
	public Map<String, Object> addStar(HttpServletRequest request, Integer uid, String image, String context) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Star star = new Star();
		star.setImage(image);
		star.setUid(uid);
		star.setState(Constants.NORMAL_STATE);
		star.setContext(context);
		star.setRegtime(DateUtils.getCurrent());
		int res = -1;
		String msg = "";
		try {
			int tmp = starService.addStar(star);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addStar", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加明日之星失败";
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

	@RequestMapping("/updateStar")
	@ResponseBody
	public Map<String, Object> updateStar(HttpServletRequest request, Integer id, Integer uid, String image,
			String context) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		Star star;
		try {
			star = starService.findStarById(id);
			if (image != null)
				star.setImage(image);
			if (uid != null)
				star.setUid(uid);
			star.setState(Constants.NORMAL_STATE);
			if (context != null)
				star.setContext(context);
			int tmp = starService.updateStarById(star);
			if (tmp > 0) {
				if(admin!=null)
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateStar", star.getId(), admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "没有此专家";
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/deleteStar")
	@ResponseBody
	public Map<String, Object> deleteStar(HttpServletRequest request, Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				Star star = starService.findStarById(id);
				star.setState(Constants.DEL_STATE);
				int tmp = starService.updateStarById(star);
				if(tmp>0) {
					 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteStar",star.getId(),admin.getName(),Constants.ADMIN);
					 res= 200;
				 }else {
					 msg="操作有误";
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
		return result;
	}
	@RequestMapping("/findStarById")
	@ResponseBody
	public Map<String, Object> findStarById(Integer id) {
		Star star = null;
		int res=-1;
		String msg="";
		try {
			star = starService.findStarById(id);
			if(star !=null) {
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
		result.put("msg",msg);
		result.put("rows", star);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllStar")
	@ResponseBody
	public Map<String, Object> FindAllStar(Model model, Integer page, Integer limit) {
		List<Star> StarList = null;
		int res=-1;
		String msg="";
		try {
			StarList = starService.findAllStar(page, limit);
			if(StarList !=null) {
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
		result.put("message",msg);
		result.put("datasize", starService.getCount(Constants.NORMAL_STATE));
		result.put("rows", StarList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
