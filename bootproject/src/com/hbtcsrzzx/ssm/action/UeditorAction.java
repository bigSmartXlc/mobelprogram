package com.hbtcsrzzx.ssm.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hbtcsrzzx.ssm.plugin.ueditor.ActionEnter;

@Controller
@RequestMapping("/jsp/ueditor")
public class UeditorAction {
	@RequestMapping("/config")
	public void config(HttpServletRequest request,HttpServletResponse response)
	{		
		response.setContentType("application/json");
		String rootPath = request.getSession().getServletContext().getRealPath("/WEB-INF/");
		try
		{
			String exec = new ActionEnter(request, rootPath).exec();
			PrintWriter writer = response.getWriter();
			writer.write(exec);
			writer.flush();
			writer.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
