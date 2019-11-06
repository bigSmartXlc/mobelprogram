<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>用户信息</title>
    <link rel="stylesheet" type="text/css" href="../../static/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/admin.css"/>
</head>
<body>
<div class="layui-tab page-content-wrap">
    <ul class="layui-tab-title">
        <li class="layui-this">修改资料</li>
        <li>修改密码</li>
        <li>推荐码</li>
    </ul>
    <div class="layui-tab-content">
        <div class="layui-tab-item layui-show">
            <form class="layui-form" style="width: 90%;padding-top: 20px;">

                <input type="text" name="id" id="id" disabled autocomplete="off" class="layui-input layui-disabled"
                       style="display: none">

                <div class="layui-form-item">
                    <label class="layui-form-label">账号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" id="username" disabled autocomplete="off"
                               class="layui-input layui-disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">联系方式：</label>
                    <div class="layui-input-block">
                        <input type="text" name="contact" id="contact" required lay-verify="required"
                               placeholder="请输入联系方式" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">用户名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" id="name" required lay-verify="required" placeholder="请输入用户名"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button type="button" class="layui-btn" id="upload">
                            <i class="layui-icon">&#xe67c;</i>上传头像
                        </button>
                        <br> <br>
                        <div
                                style="width: 200px; height: 200px; border: 3px solid #0099CC; border-radius: 5px; padding: 3px;">
                            <img style="max-width: 200px; max-height: 200px;" id="preview">
                        </div>
                    </div>
                    <input type="text" style="display:none" name="userLogo" id="image">
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminInfo">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-tab-item">
            <form class="layui-form" v style="width: 90%;padding-top: 20px;">
                <div class="layui-form-item">
                    <label class="layui-form-label">账号：</label>
                    <div class="layui-input-block">
                        <input type="text" name="username" disabled autocomplete="off"
                               class="layui-input layui-disabled">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">新密码：</label>
                    <div class="layui-input-block">
                        <input type="password" name="password2" required lay-verify="required" placeholder="请输入密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">重复密码：</label>
                    <div class="layui-input-block">
                        <input type="password" name="password3" id="password" required lay-verify="required"
                               placeholder="请输入密码"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" lay-submit lay-filter="adminPassword">立即提交</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="layui-tab-item">
            <div id="qrcodeCanvas"></div>
        </div>
    </div>
</div>
<script src="../../static/layui/layui.js" type="text/javascript" charset="utf-8"></script>
<script src="../../static/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script>
    //Demo
    layui.use(['form', 'element', 'upload'], function () {
        var form = layui.form;
        var element = layui.element;
        var upload = layui.upload;


        upload.render({
            elem: '#upload',
            url: "../files/imgupload.action",
            size: 6000,//限制文件大小，单位 KB
            //选择文件后的回调
            choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#preview').attr('src', result);
                })
            },
            //操作成功的回调
            done: function (res, index, upload) {
                if (res.code < 0) {
                    layer.msg("上传失败,请重新上传");
                    return;
                }
                console.log(res);
                $("#image").val(res.data);
            },
            //上传错误回调
            error: function (index, upload) {
                layer.alert('上传失败！' + index);
            }
        });
        form.render();

        //监听信息提交
        form.on('submit(adminInfo)', function (data) {
            //alert(1);

            var data = {
                id: $('#id').val(),
                username: $('#username').val(),
                name: $('#name').val(),
                contact: $('#contact').val(),
                userLogo: $('#image').val()
            };

            $.ajax({
                url: '../backstageUsers/updateBackstageUser.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    if (data.code < 0) {
                        layer.msg("更新用户失败");
                    } else {
                        layer.msg("更新用户成功")
                        // location.href="/jsp/admin/home.jsp"
                        window.open('/jsp/admin/home.jsp', '_blank');

                    }

                },
                error: function () {
                    alert("更新用户失败，请重新提交");
                }
            });
            return false;
        });
        //监听密码提交
        form.on('submit(adminPassword)', function (data) {
            var psw = $('input[name="password2"]').val();
            var rpsw = $('input[name="password3"]').val();


            if (psw === rpsw) {
                var data = {
                    id: $('#id').val(),
                    password: $('#password').val(),
                    username: $('#username').val()
                }

                $.ajax({
                    url: '../backstageUsers/updateBackstageUser.action',
                    data: data,
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code < 0) {
                            layer.msg("更新密码失败");
                        } else {
                            layer.msg("更新密码成功", function () {
                                // 获得frame索引
                                var index = parent.layer
                                    .getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //修改成功后刷新父界面
                                window.location.reload();
                            });
                        }

                    },
                    error: function () {
                        alert("更新密码失败，请重新提交");
                    }
                });
            } else {
                layer.alert("两次密码不一致，请检查");
            }
            return false;
        });
    });
</script>

<script type="text/javascript" src="/js/orcode/jquery.qrcode.js"></script>
<script type="text/javascript" src="/js/orcode/qrcode.js"></script>
<script type="text/javascript" src="/js/orcode/utf.js"></script>
<script type="text/javascript">


    setTimeout(function () {
        jQuery('#qrcodeCanvas').qrcode({
            render: "canvas",
            text: "http://www.hbtcsrzzx.com/login.jsp?backstageUserId="+$('#id').val(),
            width: "200",               //二维码的宽度
            height: "200",              //二维码的高度
            background: "#ffffff",      //二维码的后景色
            foreground: "#000000",      //二维码的前景色
            src: $("#preview").attr('src')             //二维码中间的图片
        });
    },1000)

</script>
</body>
</html>