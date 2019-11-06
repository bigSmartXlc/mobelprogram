package com.hbtcsrzzx.ssm.action;

import com.alibaba.fastjson.JSON;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.queryVo.ChildPermission;
import com.hbtcsrzzx.ssm.po.queryVo.Permission;
import com.hbtcsrzzx.ssm.service.BackstageRoleService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/backstageRoles")
public class BackstageRoleAction {


    private final static String MODULE = "BackstageRole";
    @Autowired
    BackstageRoleService backstageRoleService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllBackstageRole")
    @ResponseBody
    public Map<String, Object> findAllBackstageRole(Model model, Integer page, Integer limit) {
        List<BackstageRole> BackstageRoleList = null;
        int res = -1;
        String msg = "";
        try {
            BackstageRoleList = backstageRoleService.findAllBackstageRole(page, limit);
            if (BackstageRoleList != null) {
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
        result.put("datasize", backstageRoleService.getCount());
        result.put("rows", BackstageRoleList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/addBackstageRole")
    @ResponseBody
    public Map<String, Object> addBackstageRole(HttpServletRequest request, BackstageRole backstageRole) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        int res = -1;
        try {
            if (admin != null && backstageRole != null) {

                backstageRole.setState(Constants.NORMAL_STATE);
                int tmp = backstageRoleService.addBackstageRole(backstageRole);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addBackstageRole", -1, admin.getName(),
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

    @RequestMapping("/updateBackstageRole")
    @ResponseBody
    public Map<String, Object> updateBackstageRole(HttpServletRequest request, BackstageRole backstageRole) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";

        if (admin != null && backstageRole != null) {
            try {

                int tmp = backstageRoleService.updateBackstageRole(backstageRole);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateBackstageRole", backstageRole.getId(),
                            admin.getName(), Constants.ADMIN);
                    res = 200;
                }
            } catch (Exception e) {

                e.printStackTrace();
                msg = e.getMessage();
            }
        } else {
            msg = "此分类不存在";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/deleteBackstageRole")
    @ResponseBody
    public Map<String, Object> deleteBackstageRole(HttpServletRequest request, Integer id) {
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    BackstageRole backstageRole = backstageRoleService.findBackstageRoleById(id);
                    backstageRole.setState(Constants.DEL_STATE);
                    int tmp = backstageRoleService.updateBackstageRole(backstageRole);
                    if (tmp > 0) {
                        res = 200;
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteBackstageRole", id, admin.getName(),
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


    /**
     * 保存角色对应的权限
     */
   /* @RequestMapping("/saveRoleAndPermission")
    @ResponseBody
    public Result saveRoleAndPermission(HttpServletRequest request, @RequestParam("id") Integer roleId, @RequestParam(value = "multi[]",required = false) Integer[] multi){
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if(admin != null){
            backstageRoleService.saveRoleAndPermission(roleId,multi);
            return Result.ok();
        }
        return Result.error("添加失败,权限不足");
    }*/
    @RequestMapping("/findRoleById")
    @ResponseBody
    public Result findRoleById(@RequestParam("roleId") Integer id) {
        BackstageRole backstageRole = backstageRoleService.findBackstageRoleById(id);
        return Result.ok(backstageRole);

    }

    @RequestMapping("/saveRoleAndPermission")
    @ResponseBody
    public Result saveRoleAndPermission(Integer roleId, String permissions) {

        List<Integer> permissionIds = new ArrayList();

        List<Permission> permissionList = JSON.parseArray(permissions, Permission.class);
        for (Permission permission : permissionList) {
            for (ChildPermission childPermission : permission.getChildren()) {
                permissionIds.add(childPermission.getId());
            }
        }
        backstageRoleService.saveRoleAndPermission(roleId,permissionIds);
        return Result.ok();

    }


}
