package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.service.BackstageUserService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.ssm.po.BackstageUser;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/backstageUsers")
public class BackstageUserAction {


    private final static String MODULE = "BackstageUser";


    @Autowired
    BackstageUserService backstageUserService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllBackstageUser")
    @ResponseBody
    public Map<String, Object> findAllBackstageUser(Model model, Integer page, Integer limit) {
        List<BackstageUser> BackstageUserList = null;
        int res = -1;
        String msg = "";
        try {
            BackstageUserList = backstageUserService.findAllBackstageUser(page, limit);
            if (BackstageUserList != null) {
                res = 200;
            } else {
                msg = "操作有误";
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg = e.getMessage();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", res);
        result.put("message", msg);
        result.put("datasize", backstageUserService.getCount());
        result.put("rows", BackstageUserList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/addBackstageUser")
    @ResponseBody
    public Map<String, Object> addBackstageUser(HttpServletRequest request, BackstageUser BackstageUser) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");


        int res = -1;
        try {
            if (admin != null && BackstageUser != null) {

                BackstageUser.setState(Constants.NORMAL_STATE);
                int tmp = backstageUserService.addBackstageUser(BackstageUser);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addBackstageUser", -1, admin.getName(),
                            Constants.ADMIN);
                    res = 200;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/updateBackstageUser")
    @ResponseBody
    public Result updateBackstageUser(HttpServletRequest request, BackstageUser backstageUser) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        //登录用户可以修改自己信息
        if (backstageUser.getUsername().equals(username)) {
            return updateUser(request, backstageUser, username);
        }

        //管理员用户可以修改其他用户信息
        if (admin != null && backstageUser != null) {
            return updateUser(request, backstageUser, username);

        } else {
            return new Result(-1, "权限不足");
        }

    }

    @RequestMapping("/deleteBackstageUser")
    @ResponseBody
    public Map<String, Object> deleteBackstageUser(HttpServletRequest request, Integer id) {
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    BackstageUser backstageUser = backstageUserService.findBackstageUserById(id);
                    backstageUser.setState(Constants.DEL_STATE);
                    int tmp = backstageUserService.updateBackstageUser(backstageUser);
                    if (tmp > 0) {
                        res = 200;
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteBackstageUser", id, admin.getName(),
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
        return result;
    }


    //保存角色
    @RequestMapping("/saveUserAndRole")
    @ResponseBody
    public Result saveUserAndRole(HttpServletRequest request, @RequestParam("id") Integer userId, @RequestParam(value = "multi[]",required = false) Integer[] multi) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        try {
            if (admin != null) {

                int i = backstageUserService.saveUserAndRole(userId, multi);
                if (i == 1) {
                    return new Result(200, "保存角色成功");
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return new Result(-1, "保存角色失败");
    }


    //根据用户id 查询对应角色信息
    @RequestMapping("/findBackstageUserById")
    @ResponseBody
    public Result findBackstageUserById(@RequestParam("id") Integer userId) {

        try {
            BackstageUser backstageUsers = backstageUserService.findBackstageUserById(userId);

            return new Result(200, "查询用户成功", backstageUsers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(-1, "查询用户失败");
    }


    //获取登录用户
    @RequestMapping("/obtainLoginBackstageUser")
    @ResponseBody
    public Result obtainLoginBackstageUser(HttpServletRequest request) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();


        if (StringUtils.isEmpty(username)) {

            return new Result(-1, "用户未登录");
        }
         
        /*if(admin!= null){
        	
        }*/


        BackstageUser backstageUser = backstageUserService.findBackstageUserByUserName(Constants.DEL_STATE, username);
        List<BackstageRole> backstageRoles = backstageUser.getBackstageRoles();
        for (BackstageRole backstageRole : backstageRoles) {
            //登录角色中带有管理员
            if (backstageRole.getRoleName().equals("ADMIN")) {

                Admin admin2 = new Admin();
                admin2.setAuth(Constants.SUPER_ADMIN);
                admin2.setName(backstageUser.getUsername());
                request.getSession().setAttribute("admin", admin2);
                return new Result(200, "管理员用户登录", backstageUser);
            }

        }


        return new Result(200, "普通用户登录", backstageUser);
    }

    /**
     * 查询某个角色的用户集合
     *
     * @return
     */
    @RequestMapping("/findBackstageUsersByRoleName")
    @ResponseBody
    public Result findBackstageUsersByRoleName(String roleName, Integer page, Integer limit) {

        try {
            PageInfo<BackstageUser> backstageUsersPageInfo = backstageUserService.findBackstageUsersByRoleName(roleName, page, limit);
            return new Result(200, "获取用户列表成功", backstageUsersPageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(-1, "获取用户列表失败");
    }


    @RequestMapping("/findBackstageUserAllByInstitution")
    @ResponseBody
    public Result findBackstageUserAllByInstitution() {

        try {
            List<BackstageUser> backstageUsers = backstageUserService.findBackstageUserAllByInstitution();
            return new Result(200, "查询成功", backstageUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(200, "查询失败");
        }
    }


    private Result updateUser(HttpServletRequest request, BackstageUser backstageUser, String username) {
        try {

            int tmp = backstageUserService.updateBackstageUser(backstageUser);
            if (tmp > 0) {
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateBackstageUser", backstageUser.getId(),
                        username, Constants.ADMIN);
                return new Result(200, "修改用户成功");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return new Result(-1, "修改用户失败");
    }


    @RequestMapping("/success")
    public void loginSuccess(HttpServletResponse response, HttpServletRequest request) throws IOException {

        // request.getSession().setAttribute("permissions","用户管理");
        HttpSession session = request.getSession();
        Map map = new HashMap();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        BackstageUser backstageUser = backstageUserService.findBackstageUserByUserName(Constants.DEL_STATE, username);

        List<BackstageRole> roles = backstageUser.getBackstageRoles();
        for (BackstageRole role : roles) {
            List<BackstagePermission> permission = role.getBackstagePermission();
            for (BackstagePermission backstagePermission : permission) {

               map.put(backstagePermission.getPermissionName(),backstagePermission.getPermissionName());
            }
        }
        map.put("userId",backstageUser.getId());

        session.setAttribute("permission", map);
        Map permission = (Map) session.getAttribute("permission");
        System.out.println(permission);
        response.sendRedirect("/jsp/admin/home.jsp");
    }


}
