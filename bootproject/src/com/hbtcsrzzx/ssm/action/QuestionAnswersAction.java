package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.QuestionAnswers;
import com.hbtcsrzzx.ssm.service.QuestionAnswersService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

@Controller
@RequestMapping("/jsp/questionAnswerss")
public class QuestionAnswersAction {

	private final static String MODULE = "QuestionAnswers";
	@Autowired
	QuestionAnswersService questionAnswersService;

	@Autowired
	SyslogService syslogService;

	@RequestMapping("/findAllQuestionAnswers")
	@ResponseBody
	public Map<String, Object> findAllQuestionAnswers(Integer page, Integer limit) {
		List<QuestionAnswers> questionAnswersList = null;
		int res = -1;
		String msg = "";
		try {
			questionAnswersList = questionAnswersService.findAllQuestionAnswers(page, limit);
			if (questionAnswersList != null) {
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
		result.put("datasize", questionAnswersService.getCount());
		result.put("rows", questionAnswersList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/addQuestionAnswers")
	@ResponseBody
	public Map<String, Object> addQuestionAnswers(HttpServletRequest request, QuestionAnswers questionAnswers) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");

		int res = -1;
		try {
			if (admin != null && questionAnswers != null) {
				int tmp = questionAnswersService.addEnrolQuestionAnswers(questionAnswers);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addQuestionAnswers", -1, admin.getName(),
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

	@RequestMapping("/updateQuestionAnswers")
	@ResponseBody
	public Map<String, Object> updateQuestionAnswers(HttpServletRequest request, QuestionAnswers questionAnswers) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && questionAnswers != null) {
			try {

				int tmp = questionAnswersService.updateQuestionAnswers(questionAnswers);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateQuestionAnswers",
							questionAnswers.getId(), admin.getName(), Constants.ADMIN);
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

	@RequestMapping("/deleteQuestionAnswers")
	@ResponseBody
	public Map<String, Object> deleteQuestionAnswers(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				if (id != null && id > 0) {
					QuestionAnswers questionAnswers = questionAnswersService.findQuestionAnswersById(id);
					questionAnswers.setState(Constants.DEL_STATE);
					int tmp = questionAnswersService.updateQuestionAnswers(questionAnswers);
					if (tmp > 0) {
						res = 200;
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteQuestionAnswers", id,
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
		return result;
	}

	@RequestMapping("/findQuestionAnswersById")
	@ResponseBody
	public Map<String, Object> findQuestionAnswersById(@RequestParam(required = true) Integer id) {
		int res = -1;
		String msg = "";
		QuestionAnswers questionAnswers = null;
		try {
			if (id != null && id > 0) {
				 questionAnswers = questionAnswersService.findQuestionAnswersById(id);
				res = 200;
			} else {
				msg = "id值有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", questionAnswers);
		return result;
	}

}
