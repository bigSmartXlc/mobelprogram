<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>后台登录</title>
    <link rel="stylesheet" type="text/css" href="../../static/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/login.css"/>
</head>

<body>
<div class="m-login-bg">
    <div class="m-login">
        <h3>后台系统登录</h3>
        <div class="m-login-warp">
            <form class="layui-form" action="../admin/login.action" method="post">
                <div class="layui-form-item">
                    <input type="text" name="username" required lay-verify="required" placeholder="用户名"
                           autocomplete="off" class="layui-input">
                </div>
                <div class="layui-form-item">
                    <input type="password" name="password" required lay-verify="required" placeholder="密码"
                           autocomplete="off" class="layui-input">
                </div>
                <!-- <div class="layui-form-item">
                    <div class="layui-inline">
                        <input type="text" name="verity" required lay-verify="required" placeholder="验证码" autocomplete="off" class="layui-input">
                    </div>
                    <div class="layui-inline">
                        <img class="verifyImg" onclick="this.src=this.src+'?c='+Math.random();" src="../../static/images/login/yzm.jpg" />
                    </div>
                </div> -->
                <div class="layui-form-item m-login-btn">
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-normal" type="submit">登录</button>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn layui-btn-primary">取消</button>
                    </div>
                </div>

                <span id="error"></span>
            </form>
        </div>
        <p class="copyright">Copyright 2019 by beiming</p>
    </div>
</div>
<%--
		<form action="http://localhost:8080/jsp/admin/login.action" method="post" >

			<input type="text" name="username">
			<input type="text" name="password">
			<input type="submit" value="登录">


		</form>--%>
<script src="../../static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="/js/common.js" type="text/javascript" charset="utf-8"></script>
<script>
    layui.use(['form', 'layedit', 'laydate'], function () {
        var form = layui.form,
            layer = layui.layer;


        //自定义验证规则
        form.verify({
            username: function (value) {
                if (value.length < 5) {
                    return '用户名至少得5个字符啊';
                }
            },
            password: [/(.+){6,20}$/, '密码必须6到12位'],
            //verity: [/(.+){6}$/, '验证码必须是6位'],
        });


        //监听提交
        /*form.on('submit(login)', function() {
            var data = {
                 username:$('input[name="username"]').val(),
                 password:$('input[name="password"]').val()
            };



             $.ajax({
                   url : '../admins/login.action',
                   data : data,
                   type : 'POST'
              });
            /!* $.ajax({
                   url : '../admins/login.action',
                   data : data,
                   type : 'POST',
                   dataType:'json',
                   success : function(data) {
                           if(data.code < 0){
                                layer.msg("登录失败，请检查");
                           }else{
                               layer.msg("登录成功");
                                window.location.href="./home.jsp";
                           }
                     },
                    error : function() {
                       layer.msg("登录失败，请重新提交");
                     }
              });*!/

        });*/

    });


    $(function () {
        var error = getUrlParam(error);

        if (error != null && error != '' && error != 'undefined') {

            $('#error').html("账号或密码错误");
        }
    })

</script>
</body>

</html>