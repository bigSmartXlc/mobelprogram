<%@page import="com.hbtcsrzzx.utils.ConfigConsts.Constants" %>
<%@page import="com.hbtcsrzzx.ssm.po.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="iframe-h">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>分类列表</title>
    <link rel="stylesheet" type="text/css"
          href="../../static/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/admin.css"/>
</head>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<body>
<div class="wrap-container clearfix">
    <div class="column-content-detail">
        <form class="layui-form" action="">
            <div class="layui-form-item">
                <div class="layui-inline tool-btn">
                    <button class="layui-btn layui-btn-small layui-btn-normal"
                            onclick="addnews()">
                        <i class="layui-icon">&#xe654;</i>
                    </button>
                    <button class="layui-btn layui-btn-small layui-btn-danger delBtn"
                            data-url="article-add.html">
                        <i class="layui-icon">&#xe640;</i>
                    </button>
                    <button
                            class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs"
                            data-url="article-add.html">
                        <i class="iconfont">&#xe656;</i>
                    </button>
                </div>
                <div class="layui-inline">
                    <input type="text" name="title" required lay-verify="required"
                           name="title" placeholder="请输入标题" autocomplete="off"
                           class="layui-input">
                </div>
                <!-- <div class="layui-inline">
                            <select name="states" lay-filter="status">
                                <option value="">请选择一个状态</option>
                                <option value="010">正常</option>
                                <option value="021">停止</option>
                                <option value="0571">删除</option>
                            </select>
                        </div> -->
                <button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
            </div>
        </form>

        <table id="newslist" lay-filter="test"></table>


    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <%if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {%>
    <a id="newsedit" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a id="newsdel" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <%}%>
</script>
<script src="../../static/js/jquery.min.js" type="text/javascript"
        charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
        charset="utf-8"></script>
<!--Core Javascript -->
<script>
    var popForm;
    $(function () {
        layui.use(['table', 'upload'], function () {
            var table = layui.table;
            var form = layui.form;
            popForm = layui.form;
            var $ = layui.jquery
                , upload = layui.upload;

            //第一个实例
            table.render({
                elem: '#newslist',
                height: 312,
                url: '../contents/findAllContent.action' //数据接口
                ,
                page: true //开启分页
                ,
                cols: [[ //表头
                    {
                        field: 'id',
                        title: 'ID',
                        width: 80,
                        sort: true,
                        fixed: 'left'
                    }, {
                        field: 'categoryStr',
                        title: '内容类目',
                        width: 120
                    }
                    , {
                        field: 'title',
                        title: '内容标题',
                        width: 120
                    }
                    , {
                        field: 'url',
                        title: '链接',
                        width: 120
                    }
                    , {
                        field: 'pic',
                        title: '图片绝对路径',
                        width: 120
                    }
                    , {
                        field: 'sortOrder',
                        title: '排序',
                        width: 120
                    }
                    , {
                        fixed: 'right',
                        title: '操作',
                        width: 178,
                        align: 'center',
                        toolbar: '#barDemo'
                    }]],
                response: {
                    statusCode: 200
                    //重新规定成功的状态码为 200，table 组件默认为 0
                },
                parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                    console.log(res);
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.obj.total, //解析数据长度
                        "data": res.obj.list
                        //解析数据列表
                    };
                }
            });

            table.on('tool(test)', function (obj) {
                var data = obj.data;
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        deleteNews(data.id);
                        obj.del();
                        layer.close(index);
                    });
                } else if (obj.event === 'edit') {

                    layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 2,
                        title: "修改图片信息",
                        area: ['100%', '100%'],
                        content: './content-form.jsp',
                        success: function (layero, index) {
                            var body = layui.layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            iframeWin.layui.form.render('select');
                            body.find("#id").val(data.id);
                            body.find("#categoryId").val(data.categoryId);
                            body.find("#title").val(data.title);
                            body.find("#url").val(data.url);
                            body.find("#preview").attr('src', data.pic);
                            body.find("#pic").val(data.pic);
                            body.find("#sortOrder").val(data.sortOrder);
                            body.find("#addOperate").css('display', 'none');
                            body.find("#updateOperate").css('display', 'block');
                            body.find("#upload").css('display', 'block');
                            // 记得重新渲染表单
                            iframeWin.layui.form.render();
                        }
                    });
                } else if (obj.event === 'detail') {

                    layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 2,
                        title: "查看图片信息",
                        area: ['100%', '100%'],
                        content: './content-form.jsp',
                        success: function (layero, index) {
                            var body = layui.layer.getChildFrame('body', index);
                            var iframeWin = window[layero.find('iframe')[0]['name']];
                            iframeWin.layui.form.render('select');
                            body.find("#id").val(data.id);
                            body.find("#categoryId").val(data.categoryId);
                            body.find("#title").val(data.title);
                            body.find("#url").val(data.url);
                            body.find("#preview").attr('src', data.pic);
                            body.find("#pic").val(data.pic);
                            body.find("#sortOrder").val(data.sortOrder);
                            body.find("#absolutePic").val(data.pic);
                            body.find("#readonlyPic").css('display', 'block');
                            body.find("#addOperate").css('display', 'none');
                            body.find("#updateOperate").css('display', 'none');
                            body.find("#upload").css('display', 'none');
                            // 记得重新渲染表单
                            iframeWin.layui.form.render();
                        }
                    });

                }
            });


        });

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            //active[type] ? active[type].call(this) : '';
        });
    })


    function addnews() {

        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 2,
            title: "新增图片展示",
            area: ['100%', '100%'],
            content: "./content-form.jsp",
            success: function (layero, index) {
                var body = layui.layer.getChildFrame('body', index);
                var iframeWin = window[layero.find('iframe')[0]['name']];
                body.find("#addNewsOperate").css('display', 'block');
                body.find("#updateNewsOperate").css('display', 'none');
                body.find("#upload").css('display', 'block');
                // 记得重新渲染表单
                iframeWin.layui.form.render();
            }
        });

    }

    function deleteNews(id) {
        $.ajax({
            type: 'POST',
            url: '../contents/deleteContent.action',
            data: {
                'id': id
            },
            dataType: 'json',
            success: function (data) {
                if (data.code === 200) {
                    layer.alert("删除分类成功")
                }
            },
            error: function () {
                layer.alert("删除分类失败");
            }
        });
    }
</script>

</body>

</html>