<%@page import="com.hbtcsrzzx.utils.ConfigConsts.Constants" %>
<%@page import="com.hbtcsrzzx.ssm.po.Admin" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="iframe-h">

<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <title>管理员列表</title>
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
                            onclick="add()">
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
        <div class="layui-form" id="table-list">
            <table id="newslist" lay-filter="test"></table>

        </div>

        <!--修改表单 -->
        <div class="layui-row" id="popUpdateTest" style="display: none;">

            <div class="layui-col-md10">
                <form class="layui-form" lay-filter="formTestFilter"
                      id="addAndUpdateEmployeeForm">
                    <input type="text" style="display:none" name="id" id="id"/>
                    <div class="layui-form-item">
                        <label class="layui-form-label">顶级权限列表</label>
                        <div class="layui-input-block">
                            <select name="upperId" id="permissionList" lay-filter="aihao">
                            </select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">权限名称：</label>
                        <div class="layui-input-block">
                            <input type="text" name="permissionName" id="permissionName" required lay-verify="required"
                                   placeholder="请输入权限名称" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item" style="padding-left: 10px;">
                        <div class="layui-input-block" id="addOperate">
                            <button class="layui-btn layui-btn-normal" lay-submit
                                    lay-filter="formAdd">提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            <button class="layui-btn layui-btn-primary">关闭</button>
                        </div>
                        <div class="layui-input-block" id="updateOperate" style="display:none">
                            <button class="layui-btn layui-btn-normal" lay-submit
                                    lay-filter="formUpdate">提交
                            </button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                            <button class="layui-btn layui-btn-primary">关闭</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>
</div>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
    var x = 1;
    $(function () {
        layui.use(['table', 'form'], function () {
            var table = layui.table;
            var form = layui.form;
            popForm = layui.form;
            findAllBackstageTopPermission(form);
            //第一个实例
            table.render({
                elem: '#newslist',
                height: 312,
                url: '../backstagePermissions/findAllBackstagePermission.action' //数据接口
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
                    },
                    {
                        //field: 'backstageTopPermission.menuName',
                        title: '权限分类',
                        width: 120,
                        templet: '<div>{{createrFormat(d.backstageTopPermission.menuName) }}</div>'
                    }
                    , {
                        field: 'permissionName',
                        title: '权限名',
                        width: 120
                    },
                    {
                        fixed: 'right',
                        title: '操作',
                        width: 200,
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
                        obj.del();
                        delNews(data.id);
                        layer.close(index);
                    });
                } else if (obj.event === 'edit') {
                    $("#updateOperate").css("display", "block");
                    $("#addOperate").css("display", "none");

                    layer.open({
                        //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                        type: 1,
                        title: "修改管理员信息",
                        area: ['70%', '70%'],
                        content: $("#popUpdateTest").html()
                    });


                    setFormValue(data);//动态向表单赋值


                }
            });
            form.on('submit(formAdd)', function (data) {
                //表单数据formData
                var formData = data.field;
                //alert(formData);
                $.ajax({
                    url: '../backstagePermissions/addBackstagePermission.action',
                    data: data.field,
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            layer.msg("保存成功", function () {
                                // 获得frame索引
                                var index = parent.layer
                                    .getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //修改成功后刷新父界面
                                window.location.reload();
                            })
                        } else {
                            layer.msg("保存失败");
                        }

                    },
                    error: function () {
                        layer.msg("新增失败，请重新提交");
                    }
                });
                return false;
            });
            form.on('submit(formUpdate)', function (data) {
                //表单数据formData
                var formData = data.field;

                $.ajax({
                    url: '../backstagePermissions/updateBackstagePermission.action',
                    data: data.field,
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code == 200) {
                            layer.msg("保存成功", function () {
                                // 获得frame索引
                                var index = parent.layer
                                    .getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //修改成功后刷新父界面
                                window.location.reload();
                            })
                        } else {
                            layer.msg("保存失败");
                        }

                    },
                    error: function () {
                        layer.msg("保存失败，请重新提交");
                    }
                });
                return false;
            });
        });

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');
            //active[type] ? active[type].call(this) : '';
        });
    })

    function setFormValue(data) {

       // alert(data.backstageTopPermission.menuName);

        popForm.val("formTestFilter", {
            "id": data.id,
            "permissionName": data.permissionName,
            "upperId":data.backstageTopPermission.id

        });

        popForm.render(null, 'formTestFilter');
    }

    function add() {
        $("#updateOperate").css("display", "none");
        $("#addOperate").css("display", "block");

        layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 1,
            title: "添加管理员",
            area: ['70%', '70%'],
            content: $("#popUpdateTest").html()
        });
        popForm.render();
    }

    function delNews(id) {
        $.ajax({
            type: 'POST',
            url: '../backstagePermissions/deleteBackstagePermission.action',
            data: {'id': id},
            dataType: 'json',
            success: function () {

                layer.msg("删除成功")
            }, error: function () {
                lay.msg("删除失败");
            }
        });
    }

    function createrFormat(data) {
        return data;
    }

    /**
     * 获取权限分类列表
     */
    function findAllBackstageTopPermission(form) {


        $.ajax({

            type: 'get',
            data: {page: 1, limit: 200},
            dataType: 'json',
            url: '../backstageTopPermissions/findAllBackstageTopPermission.action',
            async: false,
            success: function (res) {
                var list = res.obj.list;
                console.log(list);
                var str = ' <option value=""></option>';
                for (var i in list) {
                    // console.log(item);

                    str += '<option value="' + list[i].id + '">'+list[i].menuName+'</option>';

                }
                $('#permissionList').html(str);
                form.render();
            },
            error: function (e) {

            }
        });
    }
</script>
</body>

</html>