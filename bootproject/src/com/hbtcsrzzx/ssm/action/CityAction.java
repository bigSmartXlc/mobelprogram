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
import com.hbtcsrzzx.ssm.po.City;

import com.hbtcsrzzx.ssm.service.CityService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/citys")

public class CityAction {
	
	private final static String MODULE = "City";
	@Autowired
	CityService cityService;
	
	@Autowired
	SyslogService syslogService;

	
	@RequestMapping("/addCity")
	@ResponseBody
	public Map<String, Object> addCity(HttpServletRequest request, String cityName) {
		City city = new City();	
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		
		int res = -1;
		try {
			if(admin!=null && cityName != null) {
				city.setCityName(cityName);
				city.setState(Constants.NORMAL_STATE);
				int tmp = cityService.addCity(city);
				if(tmp > 0){
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"addCity",-1,admin.getName(),Constants.ADMIN);
					res=200;
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
	
	@RequestMapping("/findAllCity")
	@ResponseBody
	public Map<String, Object> findAllCity(Model model, Integer page, Integer limit) {
		List<City> cityList = null;
		int res= -1;
		String msg="";
		try {
			cityList = cityService.findAllCity(page, limit);
			if(cityList != null) {
				res = 200;
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
		result.put("datasize", cityService.getCount(Constants.NORMAL_STATE));
		result.put("rows", cityList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	
	
	@RequestMapping("/updateCity")
	@ResponseBody
	public Map<String, Object> updateCity(HttpServletRequest request,Integer id, String cityName) {
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		City city = null;
		if(admin!=null && id!=null && id>0) {
			try {
				city = cityService.findCityById(id);
				if(cityName != null) {
					city.setCityName(cityName);
				}
				int tmp = cityService.updateCityById(city);
				if(tmp > 0){
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateCity",city.getId(),admin.getName(),Constants.ADMIN);
					res=200;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}else {
			msg = "此分类不存在";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	
	@RequestMapping("/deleteCity")
	@ResponseBody
	public Map<String, Object> deleteCity(HttpServletRequest request,Integer id) {
		int res = -1;
		String msg="";
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		if(admin!=null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if(id!=null&&id>0) {
					City city =cityService.findCityById(id);
					city.setState(Constants.DEL_STATE);
					int tmp = cityService.updateCityById(city);
					if(tmp>0) {
						res=200;
						syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteCity",id,admin.getName(),Constants.ADMIN);
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
