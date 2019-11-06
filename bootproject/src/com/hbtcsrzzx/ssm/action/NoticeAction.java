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
import com.hbtcsrzzx.ssm.po.NoticeWithBLOBs;
import com.hbtcsrzzx.ssm.service.NoticeService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/notices")
public class NoticeAction {
	private final static String MODULE = "NoticeAction";
	@Autowired
	NoticeService noticeService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addNotice")
	@ResponseBody
	public Map<String, Object> addNotice(HttpServletRequest request, String title, String publisher, String issue,
			String context, String category, String subject, String image, Integer type,String introduction) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		NoticeWithBLOBs notice = new NoticeWithBLOBs();
		notice.setPublishCompany(issue);
		notice.setPublisher(publisher);
		notice.setCategory(category);
		notice.setSubject(subject);
		notice.setContext(context);
		notice.setIntroduction(introduction);
		notice.setTitle(title);
		notice.setState(Constants.NORMAL_STATE);
		notice.setImage(image);
		notice.setRegtime(DateUtils.getCurrent());
		notice.setType(type);
		int res = -1;
		String msg = "";
		try {
			int tmp = noticeService.addNotice(notice);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addCategory", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加公告失败";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/updateNotice")
	@ResponseBody
	public Map<String, Object> UpdateNotice(HttpServletRequest request, Integer id, String title, String publisher,
			String issue, String context, String category, String subject, String image, Integer type,String introduction) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin!=null && id != null && id > 0) {
			NoticeWithBLOBs notice;
			try {
				notice = noticeService.findNoticeById(id);
				if (notice != null) {
					if (publisher != null)
						notice.setPublisher(publisher);
					if (category != null)
						notice.setCategory(category);
					if (subject != null)
						notice.setSubject(subject);
					if (context != null)
						notice.setContext(context);
					if (issue != null)
						notice.setPublishCompany(issue);
					if (title != null)
						notice.setTitle(title);
					if (image != null)
						notice.setImage(image);
					if (type != null)
						notice.setType(type);
					if(introduction!=null)
						notice.setIntroduction(introduction);
					int tmp = noticeService.updateNoticeById(notice);
					if (tmp > 0) {
						syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateExpert", notice.getId(),
								admin.getName(), Constants.ADMIN);
						res = 200;
					} else {
						msg = "操作错误";
					}
				} else {
					msg = "没有此公告";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "没有此公告";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/deleteNotice")
	@ResponseBody
	public Map<String, Object> deleteNotice(HttpServletRequest request, Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				NoticeWithBLOBs notice = noticeService.findNoticeById(id);
				notice.setState(Constants.DEL_STATE);
				int tmp = noticeService.updateNoticeById(notice);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteNotice", notice.getId(),
							admin.getName(), Constants.ADMIN);
					res = 200;
				} else {
					msg = "操作有误";
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
	
	@RequestMapping("/findNoticeById")
	@ResponseBody
	public Map<String, Object> findNoticeById(Integer id) {
		NoticeWithBLOBs notice = null;
		int res = -1;
		String msg = "";
		try {
			notice = noticeService.findNoticeById(id);
			if (notice != null) {
				res = 200;
			} else {
				msg = "操作有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		result.put("rows", notice);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllNotice")
	@ResponseBody
	public Map<String, Object> FindAllNotice(Model model, Integer page, Integer limit) {
		List<NoticeWithBLOBs> NoticeList = null;
		int res = -1;
		String msg = "";
		try {
			NoticeList = noticeService.findAllNotice(page, limit);
			if (NoticeList != null) {
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
		result.put("datasize", noticeService.getCount(Constants.NORMAL_STATE));
		result.put("rows", NoticeList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
