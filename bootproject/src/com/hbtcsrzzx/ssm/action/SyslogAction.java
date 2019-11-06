package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Syslog;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/syslogs")
public class SyslogAction {
	@Autowired
	SyslogService syslogService;
	@RequestMapping("/findAllSyslog")
	@ResponseBody
	public Map<String, Object> findAllSysLog(Model model, Integer page, Integer limit) {
		List<Syslog> SyslogList = null;
		try {
			SyslogList = syslogService.findAllSyslog(page, limit);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", 200);
		result.put("message", "");
		result.put("datasize",syslogService.getCount(Constants.NORMAL_STATE));
		result.put("rows", SyslogList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
