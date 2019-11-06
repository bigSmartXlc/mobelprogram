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
import com.hbtcsrzzx.ssm.po.CenterWithBLOBs;
import com.hbtcsrzzx.ssm.service.CenterService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("/jsp/centers")
public class CenterAction {
    private final static String MODULE = "CenterAction";
    @Autowired
    CenterService centerService;
    @Autowired
    SyslogService syslogService;

    @RequestMapping("/addCenter")
    @ResponseBody
    public Map<String, Object> addCenter(HttpServletRequest request, String name, String image, String addr,
                                         String phone, String context, String email, String introduction) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";
        if (admin != null) {
            CenterWithBLOBs Center = new CenterWithBLOBs();
            Center.setEmail(email);
            Center.setImage(image);
            Center.setName(name);
            Center.setPhone(phone);
            Center.setAddr(addr);
            Center.setContext(context);
            Center.setIntroduction(introduction);
            Center.setRegtime(DateUtils.getCurrent());
            Center.setState(Constants.NORMAL_STATE);

            try {
                int tmp = centerService.addCenter(Center);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addCenter", -1, admin.getName(),
                            Constants.ADMIN);
                    res = 200;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        } else {
            msg = "请刷新页面";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        return result;
    }

    @RequestMapping("/updateCenter")
    @ResponseBody
    public Map<String, Object> updateCenter(HttpServletRequest request, Integer id, String name, String image, String addr,
                                            String phone, String context, String email, String introduction) {
        CenterWithBLOBs Center;
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        try {
            Center = centerService.findCenterById(id);
            if (Center != null) {
                if (name != null) {
                    Center.setName(name);
                }

                if (phone != null) {
                    Center.setPhone(phone);
                }

                if (addr != null) {
                    Center.setAddr(addr);
                }

                if (context != null) {
                    Center.setContext(context);
                }

                if (email != null) {
                    Center.setEmail(email);
                }

                if (introduction != null) {
                    Center.setIntroduction(introduction);
                }

                if (image != null) {
                    Center.setImage(image);
                }

                int tmp = centerService.updateCenterById(Center);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateCenter", Center.getId(), admin.getName(),
                            Constants.ADMIN);
                    res = 200;
                }
            } else {
                msg = "没有此中心";
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/deleteCenter")
    @ResponseBody
    public Map<String, Object> deleteCenter(HttpServletRequest request, Integer id) {
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id > 0) {
                    CenterWithBLOBs center = centerService.findCenterById(id);
                    center.setState(Constants.DEL_STATE);
                    int tmp = centerService.updateCenterById(center);
                    if (tmp > 0) {
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteCenter", id, admin.getName(),
                                Constants.ADMIN);
                        res = 200;
                    }
                } else {
                    msg = "没有此中心";
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
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/findCenterById")
    @ResponseBody
    public Map<String, Object> findCenterById(Integer id) {
        CenterWithBLOBs center = null;
        int res = -1;
        String msg = "";
        try {
            center = centerService.findCenterById(id);
            if (center != null) {
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
        result.put("rows", center);
        return result;
    }

    @RequestMapping("/getAllCenter")
    @ResponseBody
    public Map<String, Object> getAllCenter() {
        List<CenterWithBLOBs> CenterList = null;
        int res = -1;
        String msg = "";
        try {
            CenterList = centerService.getAllCenter();
            if (CenterList != null) {
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
        result.put("datasize", centerService.getCount(Constants.NORMAL_STATE));
        result.put("rows", CenterList);

        return result;
    }

    @RequestMapping("/findAllCenter")
    @ResponseBody
    public Map<String, Object> findAllCenter(Model model, Integer page, Integer limit) {
        List<CenterWithBLOBs> CenterList = null;
        int res = -1;
        String msg = "";
        try {
            CenterList = centerService.findAllCenter(page, limit);
            if (CenterList != null) {
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
        result.put("datasize", centerService.getCount(Constants.NORMAL_STATE));
        result.put("rows", CenterList);

        return result;
    }
}
