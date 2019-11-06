package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbtcsrzzx.ssm.po.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.EnrolScene;
import com.hbtcsrzzx.ssm.service.EnrolSceneService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/enrolScenes")
public class EnrolSceneAction {
	private final static String MODULE = "EnrolScene";
	@Autowired
	private EnrolSceneService enrolSceneService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/findAllEnrolScene")
	@ResponseBody
	public Map<String, Object> findAllEnrolScene(Integer page, Integer limit) {
		List<EnrolScene> enrolScenes = null;
		int res = -1;
		String msg = "";

		int count = 0;
		try {
			enrolScenes = enrolSceneService.findAllEnrolScene(page, limit);
			count = enrolSceneService.getCount();
			if (enrolScenes != null) {
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
		result.put("rows", enrolScenes);
		result.put("datasize", count);
		return result;
	}

	@RequestMapping("/addEnrolScene")
	@ResponseBody
	public Map<String, Object> addEnrolScene(HttpServletRequest request, EnrolScene enrolScene) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		int res = -1;
		try {
			if (admin != null && enrolScene != null) {
				int tmp = enrolSceneService.addEnrolScene(enrolScene);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addEnrolScene", -1, admin.getName(),
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

	@RequestMapping("/updateEnrolScene")
	@ResponseBody
	public Map<String, Object> updateEnrolScene(HttpServletRequest request, EnrolScene enrolScene) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && enrolScene != null) {
			try {
				int tmp = enrolSceneService.updateEnrolSceneById(enrolScene);

				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateEnrolScene", enrolScene.getId(),
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

	@ResponseBody
	@RequestMapping("/deleteEnrolScene")
	public Map<String, Object> deleteEnrolScene(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					EnrolScene enrolScene = enrolSceneService.findEnrolSceneById(id);
					enrolScene.setState(Constants.DEL_STATE);
					int tmp = enrolSceneService.updateEnrolSceneById(enrolScene);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteEnrolScene", id,
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

	/*@ResponseBody
	@RequestMapping("/findEnrolSceneBySceneName")
	public Result findEnrolSceneBySceneName(Integer id) {

		try {
			if (id != null && id != 0) {
				EnrolScene enrolScene = enrolSceneService.findEnrolSceneById(id);
				return new Result(200, "", enrolScene);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return new Result(-1, "查询出错");

	}
*/
	@ResponseBody
	@RequestMapping("/findEnrolSceneByDateId")
	public Result findEnrolSceneByDateId(Integer dateId,HttpServletResponse response) {

		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		try {

			EnrolScene scene = enrolSceneService.findEnrolSceneByDateId(dateId);
			
			return new Result(200, "", scene);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1, "查询出错");

	}
	
	
	@ResponseBody
	@RequestMapping("/findSceneBytestDate")
	public Result findSceneBytestDate(String testDate) {

		try {

			List<EnrolScene>  enrolScenes = enrolSceneService.findSceneBytestDate(testDate);
			
			return new Result(200, "", enrolScenes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1, "查询出错");

	}


	/**
	 * 获取城市列表 
	 * @return
	 */
	@RequestMapping("/findCityByTestDate")
	@ResponseBody
	public Result findCityByTestDate(String testDate,HttpServletResponse response){
		 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			List<City> citys  = enrolSceneService.findEnrolSceneByCity(testDate);
			return new Result(200, "获取城市列表成功", citys);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, e.getMessage());
		}

	}


	/**
	 * 根据城市,动态获取获取场次列表
	 * @return
	 */
	@RequestMapping("/findEnrolSceneByCityAndTestDate")
	@ResponseBody
	public Result findEnrolSceneByCityAndTestDate(String testDate,Integer cityId,HttpServletResponse response){
		 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			List<EnrolScene> enrolScenes  = enrolSceneService.findEnrolSceneByCityAndTestDate(testDate,cityId);
			return new Result(200, "获取场次列表成功", enrolScenes);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, e.getMessage());
		}

	}


	/**
	 * 根据场次,动态获取考场详细时间和详细地址信息
	 * @return
	 */
	@RequestMapping("/findEnrolSceneById")
	@ResponseBody
	public Result findEnrolSceneById(Integer id,HttpServletResponse response){
		 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			EnrolScene enrolScene  = enrolSceneService.findEnrolSceneById(id);
			return new Result(200, "获取场次成功", enrolScene);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(-1, e.getMessage());
		}

	}
}
