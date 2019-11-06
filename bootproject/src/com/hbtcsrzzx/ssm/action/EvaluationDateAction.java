package com.hbtcsrzzx.ssm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbtcsrzzx.ssm.po.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.service.EvaluationDateService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;

@Controller
@RequestMapping("/jsp/evaluationDates")
public class EvaluationDateAction {
    private final static String MODULE = "EvaluationDate";
    @Autowired
    EvaluationDateService evaluationDateService;
    @Autowired
    SyslogService syslogService;

    @RequestMapping("/findAllEvaluationDate")
    @ResponseBody
    public Map<String, Object> findAllEvaluationDate(Integer page, Integer limit) {
        List<EvaluationDate> evaluationDateList = null;
        int res = -1;
        String msg = "";
        try {
            evaluationDateList = evaluationDateService.findAllEvaluationDate(page, limit);
            if (evaluationDateList != null) {
                res = 200;
            } else {
                msg = "操作有误";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", res);
        result.put("message", msg);
        result.put("datasize", evaluationDateService.getCount());
        result.put("rows", evaluationDateList);
        return result;
    }

    @RequestMapping("/addEvaluationDate")
    @ResponseBody
    public Map<String, Object> addEvaluationDate(HttpServletRequest request, EvaluationDate evaluationDate) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        int res = -1;
        String msg = "";
        try {
            if (admin != null && evaluationDate != null) {
                evaluationDate.setState(Constants.NORMAL_STATE);
                int tmp = evaluationDateService.addEvaluationDate(evaluationDate);
                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addEvaluationDate", -1, admin.getName(),
                            Constants.ADMIN);
                    res = 200;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            msg=e.getMessage();
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        result.put("msg", msg);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }


    @RequestMapping("/updateEvaluationDate")
    @ResponseBody
    public Map<String, Object> updateEvaluationDate(HttpServletRequest request, EvaluationDate evaluationDate) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int res = -1;
        String msg = "";
        if (admin != null && evaluationDate != null) {
            try {
                int tmp = evaluationDateService.updateEvaluationDateById(evaluationDate);


                if (tmp > 0) {
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateEvaluationDate", evaluationDate.getId(), admin.getName(), Constants.ADMIN);
                    res = 200;
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            msg = "此分类不存在";
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("code", res);
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }

    @ResponseBody
    @RequestMapping("/deleteEvaluationDate")
    public Map<String, Object> deleteEvaluationDate(HttpServletRequest request, Integer id) {
        int res = -1;
        String msg = "";
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    EvaluationDate evaluationDate = evaluationDateService.findEvaluationDateById(id);
                    evaluationDate.setState(Constants.DEL_STATE);
                    int tmp = evaluationDateService.updateEvaluationDateById(evaluationDate);
                    if (tmp > 0) {
                        res = 200;
                        syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteEvaluationDate", id, admin.getName(), Constants.ADMIN);
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
        // JSONArray json = JSONArray.fromObject(result);
        return result;
    }


    /**
     * @param lid 级别id
     * @param sid 科目id
     * @param cid 类别id
     * @return
     */
    @ResponseBody
    @RequestMapping("/findEnrolDate")
    public Result findEnrolDate(Integer lid, Integer sid, Integer cid, HttpServletResponse response) {
        // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            List<EvaluationDate> evaluationDates = evaluationDateService.findEnrolDate(lid, sid, cid);
            return new Result(200, "", evaluationDates);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(-1, "找不到对应的时间");
    }


    @RequestMapping("/findLevelByCidAndSid")
    @ResponseBody
    public Result findLevelByCidAndSid(Integer cid, Integer sid, HttpServletResponse response) {
        // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            List<Level> levels = evaluationDateService.findLevelByCidAndSid(cid, sid);

            return new Result(200, "获取所有级别成功", levels);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result(-1, "获取所有级别失败");
    }

    @ResponseBody
    @RequestMapping("/findTestDateGroupBy")
    public Result findTestDateGroupBy() {

        try {
            List<EvaluationDate> evaluationDates = evaluationDateService.findTestDateGroupBy();
            return new Result(200, "", evaluationDates);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(-1, "找不到对应的时间");
    }

    /**获取考试级别
     * @param cname 分类名称
     * @param sname 科目名称
     * @return
     */
    @RequestMapping("/findLevelByCnameAndname")
    @ResponseBody
    public Result findLevelByCnameAndname(String cname, String sname,HttpServletResponse response) {

    	 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            List<Level> levels = evaluationDateService.findEvaluationDateByLid(cname, sname);

            return new Result(200, "获取所有级别成功", levels);

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, "获取所有级别失败");
        }


    }


    /**
     * 获取所有可选的分类名
     * @return
     */
    @RequestMapping("/findCategoryGroupBy")
    @ResponseBody
    public Result findCategoryGroupBy(HttpServletResponse response) {
        // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
           List<Category> categorys  =  evaluationDateService.findCategoryGroupBy();
            return new Result(200, "获取所有级别成功", categorys);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, e.getMessage());
        }


    }

    /**
     *根据分类名称,查询时间信息里面对应的科目
     * @param cname
     * @return
     */
    @RequestMapping("/findSubjectByCname")
    @ResponseBody
    public Result findSubjectByCname(String cname,HttpServletResponse response){
    	 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            List<Subject> subjects =   evaluationDateService.findSubjectByCname(cname);
            return new Result(200, "获取所有科目成功", subjects);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, e.getMessage());

        }

    }

    /**
     * 获取考试日期
     * @param lname
     * @param cname
     * @param sname
     * @return
     */
    @RequestMapping("/findEvaluationDateByLnameAndCnameAndSname")
    @ResponseBody
    public Result findEvaluationDateByLnameAndCnameAndSname(String lname ,String cname ,String sname,HttpServletResponse response){
    	 // 跨域数据传输
        response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        try {
            List<EvaluationDate> evaluationDates =
                    evaluationDateService.findEvaluationDateByLnameAndCnameAndSname(lname,cname,sname);
            return new Result(200, "获取所有时间成功", evaluationDates);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(-1, e.getMessage());
        }

    }
    
    /**
    *
    * 根据级别id 分类id 科目id 日期查 询对应的时间
    *
    * @return
    */
   @RequestMapping("/findEvaluationDateByLidAndCidAndSidAndTestDate")
   @ResponseBody
   public Result findEvaluationDateByLidAndCidAndSidAndTestDate(Integer lid,Integer sid,Integer cid,String testDate){

       try {
           List<EvaluationDate> evaluationDates =
                   evaluationDateService.findEvaluationDateByLidAndCidAndSidAndTestDate(lid,sid,cid,testDate);
           return new Result(200, "获取所有时间成功", evaluationDates);
       } catch (Exception e) {
           e.printStackTrace();
           return new Result(-1, e.getMessage());
       }

   }

}
