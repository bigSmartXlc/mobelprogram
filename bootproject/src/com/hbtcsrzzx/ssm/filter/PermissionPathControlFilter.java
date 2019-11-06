package com.hbtcsrzzx.ssm.filter;

import com.hbtcsrzzx.utils.ConfigConsts.PermissionListConstant;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PermissionPathControlFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Map map = (Map) request.getSession().getAttribute("permission");
        String regexUser;
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();


        //说明用户已登录
        if (map != null) {

            //放行用户界面
            String regexlogin = "^/jsp/admin/.*";
            //放行上传文件
            String regexFileUpload = "^/jsp/files/.*";
            if (uri.equals("/jsp/error/403.jsp")
                    || uri.matches(regexlogin)
                    || uri.equals("/jsp/backstageUsers/obtainLoginBackstageUser.action")
                    || uri.equals("/jsp/backstageUsers/updateBackstageUser.action")
                    || uri.matches(regexFileUpload)
                    || uri.equals("/jsp/returnQuery/rebate.jsp")
                    || uri.equals("/jsp/returnQuery/rebate-title.jsp")
                    || (uri+"?"+queryString).contains(("/jsp/queryRoyalty/queryRoyalty.action?id="+map.get("userId")))
                    ) {
                //放行
                filterChain.doFilter(request, response);
                return;
            }



            if (PermissionListConstant.USER_MANAGEMENT.equals(map.get(PermissionListConstant.USER_MANAGEMENT))) {
                //放行用户界面以及后台子模块
                regexUser = "^/jsp/(users|stars|teachers|experts|backstageUsers|backstageRoles|backstagePermissions|backstageTopPermissions)/.*";

                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (PermissionListConstant.MENU_MANAGEMENT.equals(map.get(PermissionListConstant.MENU_MANAGEMENT))) {
                regexUser = "^/jsp/menus/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (PermissionListConstant.CONTENT_MANAGEMENT.equals(map.get(PermissionListConstant.CONTENT_MANAGEMENT))) {

                //放行内容管理子分类及后台子模块


                regexUser = "^/jsp/(center|newss|notices|questionAnswerss|contents)/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (PermissionListConstant.SYSTEM_SETUP.equals(map.get(PermissionListConstant.SYSTEM_SETUP))) {
                regexUser = "^/jsp/(sys|syslogs)/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (PermissionListConstant.ORGANIZATIONAL_MANAGEMENT.equals(map.get(PermissionListConstant.ORGANIZATIONAL_MANAGEMENT))) {
                //放行机构管理子分类及后台子模块
                regexUser = "^/jsp/(institutions|centers)/.*";

                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }

            }
            if (PermissionListConstant.DIVIDED_MANAGEMENT.equals(map.get(PermissionListConstant.DIVIDED_MANAGEMENT))) {


                regexUser = "^/jsp/(distributions|sharings)/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            if (PermissionListConstant.REGISTRATION_MANAGEMENT.equals(map.get(PermissionListConstant.REGISTRATION_MANAGEMENT))) {

                regexUser = "^/jsp/(subject|userLogs|enrolExaminees|levels|evaluationDates|citys|enrolScenes|costs|categorys|subjects)/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }

            if (PermissionListConstant.REBATES_QUERY.equals(map.get(PermissionListConstant.REBATES_QUERY))) {

                regexUser = "^/jsp/(returnQuery|queryRoyalty)/.*";
                if (uri.matches(regexUser)) {
                    //放行
                    filterChain.doFilter(request, response);
                    return;
                }
            }
            regexUser = "^/jsp/.*";
            //如果不满足请求
            if (uri.matches(regexUser)) {
                response.sendRedirect("/jsp/error/403.jsp");
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
