package com.hbtcsrzzx.ssm.action;

import java.util.ArrayList;
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
import com.hbtcsrzzx.ssm.po.Menu;
import com.hbtcsrzzx.ssm.service.MenuService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
@Controller
@RequestMapping("jsp/menus")
public class MenuAction {
	private final static String MODULE = "MenuAction";
	@Autowired
	MenuService menuService;
	@Autowired
	SyslogService syslogService;
	@RequestMapping("/addMenu")
	@ResponseBody
	public Map<String, Object> addMenu(HttpServletRequest request,String name, String pname, Integer pid,String ourl,
			String iurl,Integer state,String icon,String openStyle,Integer pos) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Menu menu = new Menu();
		menu.setIcon(icon);
		menu.setIurl(iurl);
		menu.setOurl(ourl);
		menu.setOpenStyle(openStyle);
		menu.setPid(pid);
		menu.setName(name);
		menu.setPname(pname);
		menu.setType(state);
		menu.setPos(pos);
		menu.setState(Constants.NORMAL_STATE);
		int res = -1;
		String msg = "";
		try {
			int tmp = menuService.addMenu(menu);
			if(tmp>0) {
				syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addMenu", -1, admin.getName(),
						Constants.ADMIN);
				res = 200;
			}else {
				msg = "添加菜单失败";
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

	
	@RequestMapping("/updateMenu")
	@ResponseBody
	public Map<String, Object> UpdateMenu(HttpServletRequest request,Integer id,String name, String pname, Integer pid,String ourl,
			String iurl,Integer type,String icon,String openStyle,Integer pos) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg = "";
		if(id!=null && id>0) {
			Menu menu;
			try {
				menu = menuService.findMenuById(id);
				if(icon!=null)
					menu.setIcon(icon);
				if(iurl!=null)
					menu.setIurl(iurl);
				if(ourl!=null)
					menu.setOurl(ourl);
				if(openStyle!=null)
					menu.setOpenStyle(openStyle);
				if(pid!=null)
					menu.setPid(pid);
				if(name!=null)
					menu.setName(name);
				if(pname!=null)
					menu.setPname(pname);
				if(type!=null)
					menu.setType(type);
				if(pos!=null)
					menu.setPos(pos);
				int tmp = menuService.updateMenuById(menu);
				if(tmp>0) {
					syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateMenu",menu.getId(),admin.getName(),Constants.ADMIN);
					res=200;
				}else {
					msg="菜单更新失败";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			msg="操作失败";
		}
		try {
			
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
	
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public Map<String, Object> deleteMenu(HttpServletRequest request, Integer id) {
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		int res = -1;
		String msg="";
		if(admin!= null && admin.getAuth() ==Constants.SUPER_ADMIN) {
		try {
			Menu menu = menuService.findMenuById(id);
			menu.setState(Constants.DEL_STATE);
			int tmp = menuService.updateMenuById(menu);
			if(tmp>0) {
				 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"deleteMenu",menu.getId(),admin.getName(),Constants.ADMIN);
				 res= 200;
			 }else {
				 msg="操作有误";
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
		result.put("msg", msg);
		return result;
	}
	
	///通过菜单所在的位置获取菜单列表
	@RequestMapping("/getMenuByLc")
	@ResponseBody
	public Map<String, Object> getMenuByLc(String location) {
		Map<String,List> menumap = new HashMap<>();
		int res=-1;
		String msg="";
		try {
			List<Menu> list = null;
			list = menuService.findMenuByLocation(location);
			if(list !=null) {
				for(Menu menu:list) {
					String pname = menu.getPname();
					if(!menumap.containsKey(pname)) {
						menumap.put(pname,new ArrayList<Menu>());
					}
					menumap.get(pname).add(menu);
				}
				res= 200;
			}else {
				msg="操作有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("rows", menumap);
		return result;
	}

	
	@RequestMapping("/getMenuByPname")
	@ResponseBody
	public Map<String, Object> getMenuByPname(String pname) {
		List<Menu> MenuList = null;
		int res=-1;
		String msg="";
		try {
			MenuList = menuService.findMenuByPname(pname);
			if(MenuList !=null) {
				res= 200;
			}else {
				msg="操作有误";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		result.put("rows", MenuList);
		return result;
	}
	
	@RequestMapping("/getAllMenu")
	@ResponseBody
	public Map<String, Object> getAllMenu() {
		List<Menu> MenuList = null;
		int res=-1;
		String msg="";
		try {
			MenuList = menuService.getAllMenu();
			if(MenuList !=null) {
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
		result.put("message", msg);
		result.put("rows", MenuList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/updateMenuType")
	@ResponseBody
	public Map<String,Object> updateMenuType(HttpServletRequest request,Integer id,Integer state){
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		int res = -1;
		String msg="";
		if(admin!=null && id!=null && state!=null) {
			Menu menu;
			try {
				menu = menuService.findMenuById(id);
				if(menu != null) {
						menu.setType(state);
						int tmp = menuService.updateMenuById(menu);
						if(tmp>0) {
							 syslogService.addSyslog(request.getRemoteAddr(),MODULE,"updateMenuType",menu.getId(),admin.getName(),Constants.ADMIN);
							 res= 200;
						 }else {
							 msg="操作有误";
						 }	
				}else {
					msg="操作有误";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			msg="请刷新页面，重新登陆";
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", res);
		result.put("message", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
	
	@RequestMapping("/findAllMenu")
	@ResponseBody
	public Map<String, Object> FindAllMenu(Model model, Integer page, Integer limit) {
		List<Menu> MenuList = null;
		int res=-1;
		String msg="";
		try {
			MenuList = menuService.findAllMenu(page, limit);
			if(MenuList !=null) {
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
		result.put("message", msg);
		result.put("datasize", menuService.getCount(Constants.NORMAL_STATE));
		result.put("rows", MenuList);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}
}
