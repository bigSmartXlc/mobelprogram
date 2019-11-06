package com.hbtcsrzzx.ssm.action;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.Admin;
import com.hbtcsrzzx.ssm.po.Content;
import com.hbtcsrzzx.ssm.service.ContentService;
import com.hbtcsrzzx.ssm.service.SyslogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/jsp/contents")
public class ContentAction {


    private final static String MODULE = "Content";
    @Autowired
    private ContentService contentService;

    @Autowired
    SyslogService syslogService;


    @RequestMapping("/addContent")
    @ResponseBody
    public Result addContent(HttpServletRequest request, Content content) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");

        try {
            if (admin != null && content != null) {
                contentService.addContent(content);
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "addContent", -1, admin.getName(), Constants.ADMIN);
                return Result.ok();
            } else {

                return Result.error("权限不足,添加失败");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return Result.error();
    }

    @RequestMapping("/findAllContent")
    @ResponseBody
    public Result findAllContent(Model model, Integer page, Integer limit) {
        try {
            PageInfo<Content> pageInfo = contentService.findAllContent(page, limit);
            return Result.ok(pageInfo);

        } catch (Exception e) {
            e.printStackTrace();

        }

        return Result.error("查询出错");
    }


    @RequestMapping("/updateContent")
    @ResponseBody
    public Result updateContent(HttpServletRequest request, Content content) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");


        if (admin != null && content != null && content.getId() > 0) {
            try {

                contentService.updateContent(content);
                int contentId = Math.toIntExact(content.getId());
                syslogService.addSyslog(request.getRemoteAddr(), MODULE, "updateContent", contentId, admin.getName(), Constants.ADMIN);

                return Result.ok();
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

            return Result.error("权限不足,添加失败");
        }
        return Result.error();
    }

    @RequestMapping("/deleteContent")
    @ResponseBody
    public Result deleteContent(HttpServletRequest request, Long id) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {
            try {
                if (id != null && id > 0) {
                    Content content = contentService.findContentById(id);
                    content.setState(Constants.DEL_STATE);
                    contentService.updateContent(content);
                    int contentId = Math.toIntExact(id);
                    syslogService.addSyslog(request.getRemoteAddr(), MODULE, "deleteContent", contentId, admin.getName(), Constants.ADMIN);

                    return Result.ok();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            return Result.error("权限不足,添加失败");
        }

        return Result.error();

    }


    @RequestMapping("/findContentByCitegoryId")
    public Result findContentByCitegoryId(Integer citegoryId) {

        try {
            List<Content> contents =  contentService.findContentByCitegoryId(citegoryId);
            return Result.ok(contents);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Result.error();
    }
}
