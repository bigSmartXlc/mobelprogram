package com.hbtcsrzzx.ssm.action.api;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.utils.PDFTemplateUtil;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private EnrolExamineeService enrolExamineeService;

    @RequestMapping("/export")
    @ResponseBody
    public void exportPdf(HttpServletResponse response, Integer id, String imgName) throws Exception {
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            // 模板中的数据，实际运用从数据库中查询
            Map<String, Object> data = new HashMap<>();
            EnrolExaminee examinee = enrolExamineeService.findEnrolExamineeById(id);
            data.put("examinee", examinee);
            data.put("imgName", imgName);

           /* String ftlName = PDFTemplateUtil.getExamineeCardFtl(data, "accreditation-template.ftl");

            if (StringUtils.isEmpty(ftlName)) {
                throw new RuntimeException("模板文件创建失败");
            }
*/
            baos = PDFTemplateUtil.createPDF(data, "accreditation-template.ftl");
            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
            response.setContentType("application/x-msdownload");
            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
            String fileName = URLEncoder.encode("全国艺术特长生测评认证准考证_"+examinee.getName()+".pdf", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            out = response.getOutputStream();
            baos.writeTo(out);
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出失败：" + e.getMessage());
        } finally {
            if (baos != null) {
                baos.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }



}
