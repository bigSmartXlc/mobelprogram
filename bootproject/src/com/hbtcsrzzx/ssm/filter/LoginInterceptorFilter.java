package com.hbtcsrzzx.ssm.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hbtcsrzzx.utils.CookieUtil;
import com.hbtcsrzzx.utils.JedisPoolUtils;

import redis.clients.jedis.Jedis;

public class LoginInterceptorFilter implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
		
		if(request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return false;
        }
		// 获取请求的RUi:去除http:localhost:8080这部分剩下的
		String uri = request.getRequestURI();
		System.out.println("uri:" + uri);
		if (uri.equals("/user/addUserLog.action")||uri.equals("/enroll/findEnrolExamineeByExaminationCard.action")) {

			return true;
		}

		String token = CookieUtil.getCookieValue(request, "loginToken", "utf-8");

		if (token != null && token != "") {
			Jedis jedis = JedisPoolUtils.getJedis();
			String userLogJson = jedis.get(token);
			if (userLogJson != null && userLogJson != "") {
				return true;
			}

		}

		// 不符合条件的给出提示信息，并转发到登录页面
		response.sendRedirect(request.getContextPath() + "/login.jsp");
		return false;
	}

}
