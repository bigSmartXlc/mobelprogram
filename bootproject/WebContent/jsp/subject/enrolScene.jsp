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
    <title>场次列表</title>
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
                <button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
            </div>
        </form>
        <div class="layui-form" id="table-list">
            <table id="newslist" lay-filter="test"></table>
        </div>
    </div>
</div>
<script type="text/html" id="barDemo">
    <%if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {%>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <%}%>
</script>
<script src="../../static/js/jquery.min.js" type="text/javascript"
        charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../js/common.js" type="text/javascript"
        charset="utf-8"></script>
<!--Core Javascript -->
<script>
    var popForm;
    $(function () {
        layui
            .use(
                'table',
                function () {
                    var table = layui.table;
                    var form = layui.form;
                    popForm = layui.form;

                    //第一个实例
                    table
                        .render({
                            elem: '#newslist',
                            height: 312,
                            url: '../enrolScenes/findAllEnrolScene.action' //数据接口
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
                                    field: 'scene',
                                    title: '场次',
                                    width: 120
                                },
                                {
                                    title: '城市',
                                    width: 120,
                                    templet: '<div>{{createrFormat(d.city.cityName)}}</div>'
                                },
                                {
                                    title: '时间',
                                    width: 120,
                                    templet: '<div>{{Format(d.evaluationDate.testDate,"yyyy-MM-dd")}}</div>'
                                },
                                {
                                    field: 'detailedAddress',
                                    title: '详细地址',
                                    width: 240
                                }, {
                                    fixed: 'right',
                                    width: 178,
                                    title: '操作',
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
                                    "code": res.status, //解析接口状态
                                    "msg": res.message, //解析提示文本
                                    "count": res.datasize, //解析数据长度
                                    "data": res.rows
                                    //解析数据列表
                                };
                            }
                        });
                    table.on('tool(test)', function (obj) {
                        var data = obj.data;
                        console.log(data);
                        if (obj.event === 'del') {
                            layer.confirm('真的删除行么', function (index) {
                                obj.del();
                                delNews(data.id);
                                layer.close(index);
                            });
                        } else if (obj.event === 'edit') {

                            layer.open({
                                //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                                type: 2,
                                title: "修改时间",
                                area: [
                                    '100%',
                                    '100%'],
                                content: "./enrolScene-from.jsp",
                                success: function (layero, index) {
                                    //getSubjects(data.cid);
                                    var body = layui.layer.getChildFrame('body', index);
                                    var iframeWin = window[layero.find('iframe')[0]['name']];

                                    iframeWin.layui.form.render('select');


                                    setTimeout(function () {
                                        var testDate = Format(data.evaluationDate.testDate, "yyyy年MM月dd日");
                                        var timeFrame = data.evaluationDate.timeFrame;




                                        // 取到弹出层里的元素，并把编辑的内容放进去
                                        body.find("#id").val(data.id); //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
                                        body.find("#scene").val(data.scene);
                                        body.find("#cityId").val(data.cityId);
                                        body.find("#dateId").val(data.dateId);
                                        body.find("#level").val(data.evaluationDate.lid);
                                        body.find("#subject").val(data.evaluationDate.sid);
                                        body.find("#category").val(data.evaluationDate.cid);
                                        body.find("#detailedAddress").val(data.detailedAddress);
                                        body.find("#testDate").html('<option value="">'+testDate+'</option>');
                                        body.find("#timeFrame").html('<option value="">'+timeFrame+'</option>');

                                        body.find("#addOperate").css('display', 'none');
                                        body.find("#updateOperate").css('display', 'block');
                                        // 记得重新渲染表单
                                        iframeWin.layui.form.render();
                                    }, 300);

                                }
                            });
                        }
                    });
                });

        $('.layui-btn').on('click', function () {
            var type = $(this).data('type');

        });
    })

    function addnews() {
        var index = layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 2,
            title: "查看老师信息",
            area: ['100%', '100%'],
            content: "./enrolScene-from.jsp",
            success: function () {
                var body = layui.layer.getChildFrame('body', index);
                // 取到弹出层里的元素，并把编辑的内容放进去
                body.find("#addOperate").css('display', 'block');
                body.find("#updateNewsOperate").css('display', 'none');
                body.find("#upload").css('display', 'none');
                // 记得重新渲染表单
                popForm.render();
            }
        });
    }

    function getSubjects(cid) {
        $.ajax({
            async: false,
            type: 'get',
            url: '../subjects/getSubjectByCid.action',
            data: {
                "cid": cid
            },
            dataType: 'json',
            success: function (data) {
                console.log("data:" + data);

                if (data.code === 200) {
                    var list = data.rows;

                    options = "";
                    for (var i in list) {
                        console.log("list[i].id" + list[i].id);
                        options +=
                            '<option value="' + list[i].id + '">'
                            + list[i].name
                            + '</option>';
                    }
                    console.log("options" + options);
                    return options;
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function () {
                console.log("获取分类失败");
            }
        });
    }

    function delNews(id) {
        $.ajax({
            type : 'POST',
            url : '../enrolScenes/deleteEnrolScene.action',
            data : {
                'id' : id
            },
            dataType : 'json',
            success : function(res) {
                if (res === 200) {
                    layer.msg("删除场次成功")
                }
            },
            error : function() {
                layer.msg("删除场次失败");
            }
        });
    }


</script>
</body>

</html>