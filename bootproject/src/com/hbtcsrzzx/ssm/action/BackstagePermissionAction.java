package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.queryVo.Permission;
import com.hbtcsrzzx.ssm.service.BackstagePermissionService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RequestMapping("/jsp/backstagePermissions")
@Controller
public class BackstagePermissionAction {


    private final static String MODULE = "BackstagePermission";
    @Autowired
    BackstagePermissionService backstagePermissionService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllBackstagePermission")
    @ResponseBody
    public Result findAllBackstagePermissio(@RequestParam(value = "page", required = true, defaultValue = "1") Integer page,
                                            @RequestParam(value = "limit", required = true, defaultValue = "10") Integer limit) {
        try {
            PageInfo<BackstagePermission> backstagePermissionPageInfo = backstagePermissionService.findAllBackstagePermission(page, limit);

            return Result.ok(backstagePermissionPageInfo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.error("查询权限列表失败");

    }

    @RequestMapping("/addBackstagePermission")
    @ResponseBody
    public Result addBackstagePermission(HttpServletRequest request, BackstagePermission backstagePermission) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        int res = -1;
        try {
            if (admin != null && backstagePermission != null) {

                backstagePermission.setState(Constants.NORMAL_STATE);
                backstagePermissionService.addBackstagePermission(backstagePermission);

                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addBackstagePermission", -1, admin.getName(),
                        Constants.ADMIN);

                return Result.ok();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("新增权限失败");
    }

    @RequestMapping("/updateBackstagePermission")
    @ResponseBody
    public Result updateBackstagePermission(HttpServletRequest request, BackstagePermission backstagePermission) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        try {
            if (admin != null && backstagePermission != null) {
                backstagePermissionService.updateBackstagePermission(backstagePermission);

                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateBackstagePermission", backstagePermission.getId(),
                        admin.getName(), Constants.ADMIN);

                return Result.ok();

            }
        } catch (Exception e) {

            e.printStackTrace();
        }

        return Result.error("修改权限失败");


    }

    @RequestMapping("/deleteBackstagePermission")
    @ResponseBody
    public Result deleteBackstagePermission(HttpServletRequest request, Integer id) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        try {
            if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
                if (id != null && id > 0) {
                    BackstagePermission backstagePermission = backstagePermissionService.findBackstagePermissionById(id);
                    backstagePermission.setState(Constants.DEL_STATE);
                    backstagePermissionService.updateBackstagePermission(backstagePermission);
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteBackstagePermission", id, admin.getName(),
                            Constants.ADMIN);
                    return Result.ok();
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Result.error("删除权限失败");
    }


    /**
     * 查询权限列表
     */
    @RequestMapping("/findBackstagePermissionList")
    @ResponseBody
    public Result findBackstagePermissionList() {

        List<Permission> permissions = backstagePermissionService.findBackstagePermissionList();

        return Result.ok(permissions);
    }
}
