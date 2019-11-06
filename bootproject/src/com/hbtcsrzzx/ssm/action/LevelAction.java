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
import com.hbtcsrzzx.ssm.po.Level;
import com.hbtcsrzzx.ssm.service.LevelService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/levels")
public class LevelAction {

	private final static String MODULE = "Level";
	@Autowired
	LevelService levelService;

	@Autowired
	SyslogService syslogService;

	@RequestMapping("/findAllLevel")
	@ResponseBody
	public Map<String, Object> findAllLevel(Model model, Integer page, Integer limit) {
		List<Level> levelList = null;
		int res = -1;
		String msg = "";
		try {
			levelList = levelService.findAllLevel(page, limit);
			if (levelList != null) {
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
		result.put("datasize", levelService.getCount());
		result.put("rows", levelList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/addLevel")
	@ResponseBody
	public Map<String, Object> addLevel(HttpServletRequest request, String levelName) {
		Level level = new Level();
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		int res = -1;
		try {
			if (admin != null && levelName != null) {
				level.setLevel(levelName);
				level.setState(Constants.NORMAL_STATE);
				int tmp = levelService.addLevel(level);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addLevel", -1, admin.getName(),
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

	@RequestMapping("/updateLevel")
	@ResponseBody
	public Map<String, Object> updateLevel(HttpServletRequest request, Integer id, String levelName) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		Level level = null;
		if (admin != null && id != null && id > 0) {
			try {
				level = levelService.findLevelById(id);
				if (levelName != null) {
					level.setLevel(levelName);
				}
				int tmp = levelService.updateLevelById(level);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateLevel", level.getId(),
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

	@RequestMapping("/deleteLevel")
	@ResponseBody
	public Map<String, Object> deleteLevel(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					Level level = levelService.findLevelById(id);
					level.setState(Constants.DEL_STATE);
					int tmp = levelService.updateLevelById(level);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteLevel", id, admin.getName(),
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
	
	@RequestMapping("/getAllLevel")
	@ResponseBody
	public Result getAllLevel(HttpServletResponse response){
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			List<Level> levels = levelService.getAllLevel();
			if(levels!=null && levels.size()>0){
				return new Result(200,"获取所有级别成功",levels);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1,"获取所有级别失败");
	}
	
	
}
