<%@page import="com.hbtcsrzzx.utils.ConfigConsts.Constants" %>
<%@page import="com.hbtcsrzzx.ssm.po.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<c:set var="ctx" scope="session" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../../static/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/admin.css"/>
    <%--  <%
          Admin admin = (Admin)session.getAttribute("admin");
          session.setMaxInactiveInterval(60*60);
          if(admin == null){
              %> 
              <script>
                  window.location.href="./login.jsp";
              </script>
              <%
          }
      %>--%>
</head>
<body>


<div class="main-layout" id='main-layout'>
<!--侧边栏-->
<div class="main-layout-side">
<div class="m-logo">
</div>
<ul class="layui-nav layui-nav-tree" lay-filter="leftNav">

<c:if test="${sessionScope.permission['菜单管理'] eq '菜单管理'}">

    <li class="layui-nav-item layui-nav-itemed my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe607;</i>菜单管理</a>
        <dl class="layui-nav-child">
            <!--  <dd><a href="javascript:;" data-url="../menus/bmenu.jsp" data-id='1' data-text="后台菜单"><span class="l-line"></span>后台菜单</a></dd> -->
            <dd><a href="javascript:;" data-url="../menus/fmenu.jsp" data-id='2' data-text="前台菜单"><span
                    class="l-line"></span>前台菜单</a></dd>
        </dl>
    </li>
</c:if>
<c:if test="${sessionScope.permission['内容管理'] eq '内容管理'}">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>内容管理</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../center/news.jsp" data-id='3' data-text="新闻管理"><span
                    class="l-line"></span>新闻管理</a></dd>
            <dd><a href="javascript:;" data-url="../center/notice.jsp" data-id='4' data-text="公告管理"><span
                    class="l-line"></span>公告管理</a></dd>
            <dd><a href="javascript:;" data-url="../center/questionsAnswers.jsp" data-id="31"
                   data-text="问答管理"><span class="l-line"></span>问答管理</a></dd>
            <dd><a href="javascript:;" data-url="../center/content.jsp" data-id="32"
                   data-text="展示图片管理"><span class="l-line"></span>展示图片管理</a></dd>
        </dl>
    </li>
</c:if>
<c:if test="${sessionScope.permission['用户管理'] eq '用户管理'}">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>用户管理</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../users/user.jsp" data-id='5' data-text="学生管理"><span
                    class="l-line"></span>学生管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/star.jsp" data-id='6' data-text="明日之星管理"><span
                    class="l-line"></span>明日之星管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/teacher.jsp" data-id='7'
                   data-text="老师管理"><span
                    class="l-line"></span>老师管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/expert.jsp" data-id='8' data-text="专家管理"><span
                    class="l-line"></span>专家管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/backstage-user.jsp" data-id='26'
                   data-text="后台用户管理"><span class="l-line"></span>后台用户管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/backstage-role.jsp" data-id='27'
                   data-text="后台角色管理"><span class="l-line"></span>后台角色管理</a></dd>
            <dd><a href="javascript:;" data-url="../users/backstage-permission.jsp" data-id='33'
                   data-text="后台菜单列表"><span class="l-line"></span>后台菜单列表</a></dd>
            <dd><a href="javascript:;" data-url="../users/backstage-top-permission.jsp" data-id='35'
                   data-text="后台顶级菜单"><span class="l-line"></span>后台顶级菜单</a></dd>
                <%-- <dd><a href="javascript:;" data-url="../users/admin.jsp" data-id='9' data-text="管理员管理"><span class="l-line"></span>管理员管理</a></dd>--%>

        </dl>
    </li>
</c:if>
<c:if test="${sessionScope.permission['报名管理'] eq '报名管理'}">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>报名管理</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../subject/userLog.jsp" data-id="16" data-text="用户管理"><span
                    class="l-line"></span>用户管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/examinee.jsp" data-id="17"
                   data-text="考生管理"><span class="l-line"></span>考生管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/level.jsp" data-id="18" data-text="级别管理"><span
                    class="l-line"></span>级别管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/evaluationDate.jsp" data-id="19"
                   data-text="时间管理"><span class="l-line"></span>时间管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/city.jsp" data-id="20" data-text="城市管理"><span
                    class="l-line"></span>城市管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/enrolScene.jsp" data-id="21"
                   data-text="场次管理"><span class="l-line"></span>场次管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/cost.jsp" data-id="22" data-text="费用管理"><span
                    class="l-line"></span>费用管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/category.jsp" data-id="12"
                   data-text="分类管理"><span class="l-line"></span>分类管理</a></dd>
            <dd><a href="javascript:;" data-url="../subject/subject.jsp" data-id="13" data-text="科目管理"><span
                    class="l-line"></span>科目管理</a></dd>
                <%--<dd><a href="javascript:;" data-url="" data-id="23" data-text="根据城市导出准考证"><span class="l-line"></span>根据城市导出准考证</a></dd>--%>
        </dl>
    </li>
</c:if>
<c:if test="${sessionScope.permission['机构管理'] eq '机构管理'}">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>机构管理</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../institutions/center.jsp" data-id='10' data-text="中心管理"><span
                    class="l-line"></span>中心管理</a></dd>
            <dd><a href="javascript:;" data-url="../institutions/institution.jsp" data-id='11' data-text="合作机构管理"><span
                    class="l-line"></span>合作机构管理</a></dd>
            <dd><a href="javascript:;" data-url="../institutions/ImportInstitution.jsp" data-id='9'
                   data-text="合作机构导入"><span class="l-line"></span>合作机构导入</a></dd>
        </dl>
    </li>
</c:if>

<c:if test="${sessionScope.permission['分成管理'] eq '分成管理'}">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>分成管理</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../distributions/sharing.jsp" data-id='23'
                   data-text="分成管理"><span class="l-line"></span>分成管理</a></dd>
            <dd><a href="javascript:;" data-url="../distributions/distribution.jsp" data-id='34'
                   data-text="分销比例管理"><span class="l-line"></span>分销比例管理</a></dd>

        </dl>
    </li>
</c:if>
<security:authorize access="hasAnyRole('ROLE_ADMIN')">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>返点查询</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" data-url="../returnQuery/rebate.jsp?roleName=SALESMAN" data-id='28'
                   data-text="业务员返点查询"><span class="l-line"></span>业务员返点查询</a></dd>
            <dd><a href="javascript:;" data-url="../returnQuery/rebate.jsp?roleName=INSTITUTION"
                   data-id='29' data-text="机构返点查询"><span class="l-line"></span>机构返点查询</a></dd>
        </dl>
    </li>
</security:authorize>

<security:authorize access="hasAnyRole('ROLE_INSTITUTION','ROLE_SALESMAN')">
    <li class="layui-nav-item my-nav-item">
        <a href="javascript:;"><i class="iconfont">&#xe608;</i>返点查询</a>
        <dl class="layui-nav-child">
            <dd><a href="javascript:;" id="query" data-url="../returnQuery/rebate-title.jsp" data-id='30'
                   data-text="查询"><span class="l-line"></span>查询</a></dd>
        </dl>
    </li>
</security:authorize>
<!-- <li class="layui-nav-item my-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe608;</i>分类管理</a>
<dl class="layui-nav-child">
<dd><a href="javascript:;" data-url="../subject/category.jsp" data-id="12" data-text="分类管理"><span class="l-line"></span>分类管理</a></dd>
<dd><a href="javascript:;" data-url="../subject/subject.jsp" data-id="13" data-text="科目管理"><span class="l-line"></span>科目管理</a></dd>
</dl>
</li> -->
<!-- <li class="layui-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe604;</i>推荐位管理</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe60c;</i>友情链接</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe60a;</i>RBAC</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i>邮件系统</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe60d;</i>生成静态</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;"><i class="iconfont">&#xe600;</i>备份管理</a>
</li>
<li class="layui-nav-item">
<a href="javascript:;" data-url="admin-info.html" data-id='5' data-text="个人信息"><i class="iconfont">&#xe606;</i>个人信息</a>
</li> -->

<c:if test="${sessionScope.permission['系统设置'] eq '系统设置'}">
    <li class="layui-nav-item my-nav-item">
    <a href="javascript:;"><i class="iconfont">&#xe60b;</i>系统设置</a>
    <dl class="layui-nav-child">
    <dd><a href="javascript:;" data-url="system.html" data-id='14' data-text="系统设置"><span
    class="l-line"></span>系统设置</a></dd>

    <dd><a href="javascript:;" data-url="../sys/syslog.jsp" data-id='15' data-text="系统日志"><span
    class="l-line"></span>系统日志</a></dd>

    </dl>
    </li>
</c:if>
    </ul>
    </div>
    <!--右侧内容-->
    <div class="main-layout-container">
    <!--头部-->
    <div class="main-layout-header">
    <div class="menu-btn" id="hideBtn">
    <a href="javascript:;">
    <span class="iconfont">&#xe60e;</span>
    </a>
    </div>
    <ul class="layui-nav" lay-filter="rightNav">
    <!-- <li class="layui-nav-item"><a href="javascript:;" data-url="email.html" data-id='4' data-text="邮件系统"><i class="iconfont">&#xe603;</i></a></li>-->
    <li class="layui-nav-item">
    <a href="javascript:;" onclick="adminInfoBtn()" style="color:#000;"><span id="name"></span></a>
    </li>
    <li class="layui-nav-item"><a href="javascript:;" onclick="exitBtn()" style="color:#000;">退出</a></li>
    </ul>
    </div>
    <!--主体内容-->
    <div class="main-layout-body">
    <!--tab 切换-->
    <div class="layui-tab layui-tab-brief main-layout-tab" lay-filter="tab" lay-allowClose="true">
    <ul class="layui-tab-title">
    <li class="layui-this welcome">后台主页</li>
    </ul>
    <div class="layui-tab-content">
    <div class="layui-tab-item layui-show" style="background: #f5f5f5;">
    <!--1-->
    <iframe src="welcome.jsp" width="100%" height="100%" name="iframe" scrolling="auto"
    class="iframe" framborder="0"></iframe>
    <!--1end-->
    </div>
    </div>
    </div>
    </div>

    </div>
    <!--遮罩-->
    <div class="main-mask">

    </div>
    </div>
    <script src="../../static/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../static/js/common.js" type="text/javascript" charset="utf-8"></script>
    <script src="../../static/js/main.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">


    var scope = {
    link: './welcome.jsp'
    };
    var user;
    $(function () {
    $(".my-nav-item").click(function () {
    var lis = document.getElementsByClassName('my-nav-item');//数组
    var lisLen = lis.length;
    for (var i = 0; i < lisLen; i++) {
    if (lis[i] !== this) {
    lis[i].className = 'layui-nav-item my-nav-item';
    }
    }
    });
    $.ajax({
    type: "post",
    data: {},
    datatype: 'json',
    url: '../backstageUsers/obtainLoginBackstageUser.action',
    success: function (data) {
    console.log(data);
    if (data.code === 200) {
    user = data.obj;
    $('#name').html(data.obj.name);
    $('#query').attr("data-url", "../returnQuery/rebate-title.jsp?id=" + data.obj.id);
    }
    }
    })
    });

    function exitBtn() {
    /* $.ajax({
    type: 'POST',
    url: '../admins/outLogin.action',
    data: {},
    dataType:'json' ,
    success: function(data){
    //windows.location.href="./login.jsp";
    },error:function(){
    //
    }
    });*/
    window.location.href = './logout.action';
    }

    function adminInfoBtn() {
    layer.open({
    //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
    type: 2,
    title: "个人信息",
    area: ['70%', '70%'],
    content: "./admin-info.jsp",
    success: function (layero, index) {
    var body = layui.layer.getChildFrame('body', index);
    var iframeWin = window[layero.find('iframe')[0]['name']];
    body.find("#id").val(user.id);
    body.find("#username").val(user.username);
    body.find("#contact").val(user.contact);
    body.find("#name").val(user.name);
    body.find('input[name="username"]').val(user.username);
    body.find("#preview").attr('src', user.userLogo);

    iframeWin.layui.form.render();
    }
    });

    }
    </script>


    </body>
    </html>
