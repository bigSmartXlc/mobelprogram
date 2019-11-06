package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Distribution;
import com.hbtcsrzzx.ssm.service.DistributionService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/jsp/distributions")
public class DistributionAction {


    private final static String MODULE = "Distribution";
    @Autowired
    DistributionService distributionService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllDistribution")
    @ResponseBody
    public Result findAllDistribution(Integer page, Integer limit) {
        PageInfo<Distribution> pageInfo = null;
        try {
            pageInfo = distributionService.findAllDistribution(page, limit);
            return Result.ok(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error("查询失败");

    }

    @RequestMapping("/addDistribution")
    @ResponseBody
    public Result addDistribution(HttpServletRequest request, Distribution distribution) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");


        try {
            if (admin != null && distribution != null) {

                distribution.setState(Constants.NORMAL_STATE);
                distributionService.addDistribution(distribution);
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addDistribution", -1, admin.getName(),
                        Constants.ADMIN);
                return Result.ok();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error("新增失败");
    }

    @RequestMapping("/updateDistribution")
    @ResponseBody
    public Result updateDistribution(HttpServletRequest request, Distribution distribution) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        if (admin != null && distribution != null) {
            try {

                distributionService.updateDistribution(distribution);
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateDistribution", distribution.getId(),
                        admin.getName(), Constants.ADMIN);
                return Result.ok();
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return Result.error("修改失败");
    }

    @RequestMapping("/deleteDistribution")
    @ResponseBody
    public Result deleteDistribution(HttpServletRequest request, Integer id) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    Distribution distribution = distributionService.findDistributionById(id);
                    distribution.setState(Constants.DEL_STATE);
                    distributionService.updateDistribution(distribution);
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteDistribution", id, admin.getName(),
                            Constants.ADMIN);
                    return Result.ok();

                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return Result.error("添加失败");
    }


}
