package com.hbtcsrzzx.ssm.filter;

import com.hbtcsrzzx.utils.CookieUtil;
import com.hbtcsrzzx.utils.JedisPoolUtils;
import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserlogFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String regexUser = "^/user/.*";
        String regexEnroll = "^/enroll/.*";
        String uri = request.getRequestURI();
        if (uri.equals("/user/addUserLog.action")||uri.equals("/enroll/findEnrolExamineeByExaminationCard.action")) {
            //放行
            filterChain.doFilter(request, response);
            return;
        }


        if (uri.matches(regexUser)|| uri.matches(regexEnroll)) {
            String token = CookieUtil.getCookieValue(request, "loginToken", "utf-8");

            if (StringUtils.isEmpty(token)) {
                System.out.println("该用户cookie没有token");
                response.sendRedirect("/login.jsp");
                return;
            }

            Jedis jedis = JedisPoolUtils.getJedis();
            String userLogJson = jedis.get(token);

            if(StringUtils.isEmpty(userLogJson)){
                System.out.println("该用户redis没有token,登陆超时");
                response.sendRedirect("/login.jsp");
                return;
            }
        }
        //放行
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
