package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.BackstageTopPermission;
import com.hbtcsrzzx.ssm.service.BackstageTopPermissionService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jsp/backstageTopPermissions")
public class BackstageTopPermissionAction {



    private final static String MODULE = "BackstageTopPermission";
    @Autowired
    BackstageTopPermissionService backstageTopPermissionService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllBackstageTopPermission")
    public Result findAllBackstagePermissio(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        try {
            PageInfo<BackstageTopPermission> backstageTopPermissionPageInfo = backstageTopPermissionService.findAllBackstageTopPermission(page, limit);

            return Result.ok(backstageTopPermissionPageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.error("查询权限列表失败");

    }

    @RequestMapping("/addBackstageTopPermission")
    public Result addBackstageTopPermission(HttpServletRequest request, BackstageTopPermission backstageTopPermission) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        int res = -1;
        try {
            if (admin != null && backstageTopPermission != null) {

                backstageTopPermission.setState(Constants.NORMAL_STATE);
                backstageTopPermissionService.addBackstageTopPermission(backstageTopPermission);

                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addBackstageTopPermission", -1, admin.getName(),
                        Constants.ADMIN);

                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("新增权限失败");
    }

    @RequestMapping("/updateBackstageTopPermission")
    public Result updateBackstageTopPermission(HttpServletRequest request, BackstageTopPermission backstageTopPermission) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        try {
            if (admin != null && backstageTopPermission != null) {
                backstageTopPermissionService.updateBackstageTopPermission(backstageTopPermission);

                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateBackstageTopPermission", backstageTopPermission.getId(),
                        admin.getName(), Constants.ADMIN);

                return Result.ok();

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return Result.error("修改权限失败");


    }

    @RequestMapping("/deleteBackstageTopPermission")
    public Result deleteBackstageTopPermission(HttpServletRequest request, Integer id) throws Exception {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

            if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
                if (id != null && id > 0) {
                    BackstageTopPermission backstageTopPermission = backstageTopPermissionService.findBackstageTopPermissionById(id);
                    backstageTopPermission.setState(Constants.DEL_STATE);
                    backstageTopPermissionService.updateBackstageTopPermission(backstageTopPermission);
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteBackstageTopPermission", id, admin.getName(),
                            Constants.ADMIN);
                    return Result.ok();
                }
            }



        return Result.error("删除权限失败");
    }
}
