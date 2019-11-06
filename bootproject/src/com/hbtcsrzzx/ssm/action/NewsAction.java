package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.NewsWithBLOBs;
import com.hbtcsrzzx.ssm.service.NewsService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/newss")
public class NewsAction {
	private final static String MODULE = "NewsAction";
	@Autowired
	NewsService newsService;
	@Autowired
	SyslogService syslogService;

	@RequestMapping("/addNews")
	@ResponseBody
	public Map<String, Object> addNews(HttpServletRequest request, String title, String author, String source,
			String date, String context, String key, String category, String subject,String image,String introduction) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		NewsWithBLOBs news = new NewsWithBLOBs();
		news.setAuthor(author);
		news.setDate(date);
		news.setSource(source);
		news.setTitle(title);
		news.setCategory(category);
		news.setSubject(subject);
		news.setContext(context);
		news.setNewskey(key);
		news.setIntroduction(introduction);
		news.setRegtime(DateUtils.getCurrent());
		news.setState(Constants.NORMAL_STATE);
		news.setImage(image);
		int res = -1;
		String msg = "";
		try {
			int tmp = newsService.addNews(news);
			if (tmp > 0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addNews", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			} else {
				msg = "添加新闻失败";
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

	@RequestMapping("/updateNews")
	@ResponseBody
	public Map<String, Object> updateNews(HttpServletRequest request, Integer id, String title, String author,
			String source, String date, String context, String key, String category, String subject,String image,String introduction) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin!=null &&id != null && id > 0) {
			NewsWithBLOBs news;
			try {
				news = newsService.findNewsById(id);
				if (author != null)
					news.setAuthor(author);
				if (date != null)
					news.setDate(date);
				if (source != null)
					news.setSource(source);
				if (title != null)
					news.setTitle(title);
				if (category != null)
					news.setCategory(category);
				if (subject != null)
					news.setSubject(subject);
				if (context != null)
					news.setContext(context);
				if (key != null)
					news.setNewskey(key);
				if(image!=null)
					news.setImage(image);
				if(introduction!=null)
					news.setIntroduction(introduction);
				int tmp = newsService.updateNewsById(news);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateNews",news.getId(),admin.getName(),Constants.ADMIN);
					res = 200;
				} else {
					msg = "没有此新闻";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			msg = "操作失败,请刷新页面";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	@RequestMapping("/deleteNews")
	@ResponseBody
	public Map<String, Object> deleteNews(HttpServletRequest request, @RequestParam("id") Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
			try {
				NewsWithBLOBs news = newsService.findNewsById(id);
				news.setState(Constants.DEL_STATE);
				int tmp = newsService.updateNewsById(news);
				if (tmp > 0) {
					syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteNews", news.getId(),
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

	@RequestMapping("/findNewsById")
	@ResponseBody
	public Map<String, Object> findNewsById(Integer id) {
		NewsWithBLOBs news = null;
		int res = -1;
		String msg = "";
		try {
			news = newsService.findNewsById(id);
			if (news != null) {
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
		result.put("rows", news);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllNews")
	@ResponseBody
	public Map<String, Object> findAllNews(Model model, @RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit) {
		List<NewsWithBLOBs> newsList = null;
		int res = -1;
		String msg = "";
		try {
			newsList = newsService.findAllNews(page, limit);
			if (newsList != null) {
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
		result.put("datasize", newsService.getCount(Constants.NORMAL_STATE));
		result.put("rows", newsList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	/**
	 * 查询需要的
	 * @param model
	 * @param page
	 * @param limit
	 * @return
	 */
	@RequestMapping("/findAllNewsByTitle")
	@ResponseBody
	public Map<String, Object> findAllNewsByTitle(Model model, @RequestParam("page") Integer page,
			@RequestParam("limit") Integer limit) {
		List<NewsWithBLOBs> newsList = null;
		int res = -1;
		String msg = "";
		try {
			newsList = newsService.findAllNewsByTitle(page, limit);
			if (newsList != null) {
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
		result.put("datasize", newsService.getCount(Constants.NORMAL_STATE));
		result.put("rows", newsList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
