package com.hbtcsrzzx.ssm.action.exception;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hbtcsrzzx.utils.Result;

@Component
public class ExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {

		logger.info("错误类型" + ex.getClass().getName());
		logger.info("handler执行器" + handler);

		//org.springframework.security.access.AccessDeniedException

		//判断ex是否是AccessDeniedException一个实例 也就是是否是自己本身或者是它的子类
		if(ex instanceof AccessDeniedException){

			throw new AccessDeniedException("权限不足");

		}
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		Result result = new Result(-1,"请求出错");
		String jsonString = JSON.toJSONString(result);
		try {
			response.getWriter().print(jsonString);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}


	//private String
}
