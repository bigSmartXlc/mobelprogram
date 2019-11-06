package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.EvaluationTime;
import com.hbtcsrzzx.ssm.service.EvaluationTimeService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/evaluationTimes")
public class EvaluationTimeAction {

	private final static String MODULE = "EvaluationTime";

	@Autowired
	EvaluationTimeService evaluationTimeService;

	@Autowired
	SyslogService syslogService;

	@RequestMapping("/findAllEvaluationTime")
	@ResponseBody
	public Map<String, Object> findAllEvaluationTime(Integer page, Integer limit) {
		List<EvaluationTime> EvaluationTimeList = null;
		int res = -1;
		String msg = "";
		try {
			EvaluationTimeList = evaluationTimeService.findAllEvaluationTime(page, limit);
			if (EvaluationTimeList != null) {
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
		result.put("datasize", evaluationTimeService.getCount(Constants.NORMAL_STATE));
		result.put("rows", EvaluationTimeList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
