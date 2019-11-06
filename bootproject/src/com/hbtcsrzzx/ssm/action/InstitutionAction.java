package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hbtcsrzzx.ssm.po.*;
import com.hbtcsrzzx.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.service.InstitutionService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.DateUtils;

@Controller
@RequestMapping("jsp/institutions")
public class InstitutionAction {
    @Autowired
    InstitutionService institutionService;
    @Autowired
    SyslogService syslogService;


    private final static String MODULE = "InstitutionAction";

    @RequestMapping("/addInstitution")
    @ResponseBody
    public Map<String, Object> addInstitution(HttpServletRequest request, String name, String image, String addr,
                                              String phone, String category, String subject, String email, String introduction, String context) {
        InstitutionWithBLOBs institution = new InstitutionWithBLOBs();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        institution.setAddr(addr);
        institution.setPhone(phone);
        institution.setImage(image);
        institution.setRegtime(DateUtils.getCurrent());
        institution.setState(Constants.NORMAL_STATE);
        institution.setCategory(category);
        institution.setEmail(email);
        institution.setIntroduction(introduction);
        institution.setContext(context);
        institution.setName(name);
        institution.setSubject(subject);
        int res = -1;
        String msg = "";
        try {
            int tmp = institutionService.addInstitution(institution);
            if (tmp > 0) {
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addCategory", -1,//institution.getId(),
                        admin.getName(), Constants.ADMIN);
                res = 200;
            } else {
                msg = "添加机构失败";
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

    @RequestMapping("/updateInstitution")
    @ResponseBody
    public Map<String, Object> updateInstitution(HttpServletRequest request, Integer id, String name, String image, String introduction, String phone,
                                                 String category, String subject, String email, String addr, String context, String institutionalNature) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";
        if (id != null) {
            InstitutionWithBLOBs institution;
            try {
                institution = institutionService.findInstitutionById(id);
                if (institution != null) {
                    if (addr != null)
                        institution.setAddr(addr);
                    if (phone != null)
                        institution.setPhone(phone);
                    if (image != null)
                        institution.setImage(image);
                    if (category != null)
                        institution.setCategory(category);
                    if (email != null)
                        institution.setEmail(email);
                    if (introduction != null)
                        institution.setIntroduction(introduction);
                    if (context != null)
                        institution.setContext(context);
                    if (name != null)
                        institution.setName(name);
                    if (subject != null)
                        institution.setSubject(subject);
                    if (StringUtils.isNotEmpty(institutionalNature)) {
                        institution.setInstitutionalNature(institutionalNature);
                    }
                    institution.setRegtime(DateUtils.getCurrent());
                    institution.setState(Constants.NORMAL_STATE);
                    try {
                        int tmp = institutionService.updateInstitutionById(institution);
                        if (tmp > 0) {
                            syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateInstitution", institution.getId(),
                                    admin.getName(), Constants.ADMIN);
                            res = 200;
                        } else {
                            msg = "更新机构失败";
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                } else {
                    msg = "没有此机构记录";
                }
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } else {
            msg = "更新机构失败";
        }

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @RequestMapping("/deleteInstitution")
    @ResponseBody
    public Map<String, Object> deleteInstitution(HttpServletRequest request, Integer id) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";
        if (admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                InstitutionWithBLOBs institution = institutionService.findInstitutionById(id);
                if (institution != null) {
                    institution.setState(Constants.DEL_STATE);
                    int tmp = institutionService.updateInstitutionById(institution);
                    if (tmp > 0) {
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteInstitution", institution.getId(),
                                admin.getName(), Constants.ADMIN);
                        res = 200;
                    } else {
                        msg = "机构删除失败";
                    }
                } else {
                    msg = "没有此机构";
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

    @RequestMapping("/findInstitutionByAddr")
    @ResponseBody
    public Map<String, Object> findInstitutionByAddr(HttpServletRequest request, Integer page, Integer limit, String city) {
        List<Institution> institutionList = null;
        int res = -1;
        String msg = "";
        try {
            institutionList = institutionService.findByAddr(city, page, limit);
            if (institutionList != null) {
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
        result.put("datasize", institutionService.getCountByAddr(city));
        result.put("rows", institutionList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }


    @RequestMapping("/findInstitutionById")
    @ResponseBody
    public Map<String, Object> findInstitutionById(HttpServletRequest request, Integer id) {
        InstitutionWithBLOBs institution = null;
        int res = -1;
        String msg = "";
        try {
            institution = institutionService.findInstitutionById(id);
            if (institution != null) {
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
        result.put("rows", institution);
        return result;
    }

    @RequestMapping("/findAllInstitution")
    @ResponseBody
    public Map<String, Object> FindAllInstitution(HttpServletRequest request, Integer page, Integer limit) {
        List<InstitutionWithBLOBs> institutionList = null;
        int res = -1;
        String msg = "";
        try {
            institutionList = institutionService.findAllInstitution(page, limit);
            if (institutionList != null) {
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
        result.put("datasize", institutionService.getCount(Constants.NORMAL_STATE));
        result.put("rows", institutionList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }


    /**
     * 分页查询机构的基本信息
     *
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllInstitutionBasicInformation")
    @ResponseBody
    public Map<String, Object> findAllInstitutionBasicInformation(HttpServletRequest request, Integer page, Integer limit) {
        List<InstitutionWithBLOBs> institutionList = null;
        int res = -1;
        String msg = "";
        try {
            institutionList = institutionService.findAllInstitutionBasicInformation(page, limit);
            if (institutionList != null) {
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
        result.put("datasize", institutionService.getCount(Constants.NORMAL_STATE));
        result.put("rows", institutionList);
        return result;
    }


    /**
     * 保存机构关联的用户账号
     *
     * @backstageUserAndInstitution 机构id 与用户id
     */
    @RequestMapping("/saveBackstageUserAndInstitution")
    @ResponseBody
    public Result saveBackstageUserAndInstitution(BackstageUserAndInstitution backstageUserAndInstitution, String roleName) {
        try {
            institutionService.saveBackstageUserAndInstitution(backstageUserAndInstitution, roleName);
            return new Result(200, "保存成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, "保存失败");

        }

    }

    /**
     * 根据机构id,查询用户列表
     *
     * @param institutionId 机构id
     * @return 用户列表
     */
    @RequestMapping("/findBackstageUserByInstitutionId")
    @ResponseBody
    public Result findBackstageUserByInstitutionId(@RequestParam("id") Integer institutionId) {
        try {
            List<BackstageUser> backstageUsers = institutionService.findBackstageUserByInstitutionId(institutionId);
            return new Result(200, "查询用户列表成功", backstageUsers);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, "查询用户列表失败");

        }

    }


    /**
     * 删除机构绑定用户用户
     *
     * @param institutionId 机构id
     * @return 结果
     */
    @RequestMapping("/delUserByInstitutionId")
    @ResponseBody
    public Result delSalesmanUserByInstitutionId(@RequestParam("id") Integer institutionId, Integer userId) {

        try {
            institutionService.delSalesmanUserByInstitutionId(institutionId, userId);
            return new Result(200, "删除成功");
        } catch (Exception e) {
            return new Result(-1, "删除失败");

        }
    }

    /**
     * 首页展示机构信息,优化查询内容
     * @param request
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAllInstitutionOptimize")
    @ResponseBody
    public Map<String, Object> findAllInstitutionOptimize(HttpServletRequest request, Integer page, Integer limit) {
        List<Institution> institutionList = null;
        int res = -1;
        String msg = "";
        try {
            institutionList = institutionService.findAllInstitutionOptimize(page, limit);
            if (institutionList != null) {
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
        result.put("datasize", institutionService.getCount(Constants.NORMAL_STATE));
        result.put("rows", institutionList);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }
}
