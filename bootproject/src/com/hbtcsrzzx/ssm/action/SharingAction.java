package com.hbtcsrzzx.ssm.action;

import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Sharing;
import com.hbtcsrzzx.ssm.service.SharingService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/jsp/sharings")
public class SharingAction {


    private final static String MODULE = "Sharing";
    @Autowired
    SharingService sharingService;

    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllSharing")
    @ResponseBody
    public Map<String, Object> findAllSharing(Model model, Integer page, Integer limit) {
        List<Sharing> sharingList = null;
        int res = -1;
        String msg = "";
        try {
            sharingList = sharingService.findAllSharing(page, limit);
            if (sharingList != null) {
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
        result.put("datasize", sharingService.getCount());
        result.put("rows", sharingList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/addSharing")
    @ResponseBody
    public Map<String, Object> addSharing(HttpServletRequest request, Sharing sharing) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");

        int res = -1;
        try {
            if (admin != null && sharing != null) {

                sharing.setState(Constants.NORMAL_STATE);
                int tmp = sharingService.addSharing(sharing);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addSharing", -1, admin.getName(),
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

    @RequestMapping("/updateSharing")
    @ResponseBody
    public Map<String, Object> updateSharing(HttpServletRequest request,  Sharing sharing) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";

        if (admin != null && sharing != null) {
            try {

                int tmp = sharingService.updateSharing(sharing);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateSharing", sharing.getId(),
                            admin.getName(), Constants.ADMIN);
                    res = 200;
                }
            } catch (Exception e) {

                e.printStackTrace();
                msg= e.getMessage();
            }
        } else {
            msg = "此分类不存在";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/deleteSharing")
    @ResponseBody
    public Map<String, Object> deleteSharing(HttpServletRequest request, Integer id) {
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    Sharing sharing = sharingService.findSharingById(id);
                    sharing.setState(Constants.DEL_STATE);
                    int tmp = sharingService.updateSharing(sharing);
                    if (tmp > 0) {
                        res = 200;
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteSharing", id, admin.getName(),
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





}
