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
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>考生管理</title>
    <link rel="stylesheet" type="text/css"
          href="../../static/layui/css/layui.css"/>
    <link rel="stylesheet" type="text/css" href="../../static/css/admin.css"/>

</head>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<body>

<form class="layui-form" onsubmit="return functionName();">
    <div class="demoTable">
        搜索考生姓名：
        <div class="layui-inline">
            <input class="layui-input" name="" id="demoReload" autocomplete="off">
        </div>

        <div class="layui-inline">
            <select name="searchCity" id="searchCity" lay-verify="required"
                    class="layui-input layui-unselect">

            </select>
        </div>

        <div class="layui-inline">
            <select name="" id="searchAuditStatus" lay-verify="required"
                    class="layui-input layui-unselect">
                <option value="">审核状态</option>
                <option value="0">未审核</option>
                <option value="1">审核通过</option>
                <option value="2">审核未通过</option>
            </select>
        </div>

        <div class="layui-inline">
            <div class="layui-input-inline">
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="searchDate" placeholder="请选择日期">
                </div>
            </div>
        </div>
        <button class="layui-btn" data-type="reload">搜索</button>
    </div>
</form>


<!-- <div class="layui-btn-group demoTable2">
 
  <button class="layui-btn" data-type="getCheckLength">获取选中数目</button>
  <button class="layui-btn" data-type="isAll">验证是否全选</button>
</div> -->

<table id="newslist" class="layui-hide" lay-filter="test"></table>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>

    <%if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {%>
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="examine">审核</a>
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="export">导出</a>
    <%}%>
</script>
<script src="../../static/js/jquery.min.js" type="text/javascript"
        charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../js/common.js"></script>

<!--Core Javascript -->
<script>
    var popForm;
    $(function () {
        layui.use(['table', 'laydate', 'form', 'laydate'],
            function () {
                var table = layui.table;
                var form = layui.form;
                popForm = layui.form;
                var laydate = layui.laydate;
                selectCity();
                //selectDate();
                laydate.render({
                    elem: '#searchDate'
                });
                //第一个实例
                table.render({
                    elem: '#newslist',
                    height: 312,
                    url: '../enrolExaminees/findAllEnrolExaminee.action' //数据接口
                    ,
                    page: true //开启分页
                    ,

                    cols: [[
                        //{type: 'radio'},//表头
                        {
                            field: 'id',
                            title: 'ID',
                            width: 80,
                            sort: true

                        },
                        {
                            field: 'name',
                            title: '姓名',
                            width: 120
                        },
                        {
                            field: 'genderStr',
                            title: '性别',
                            width: 120
                        },
                        {
                            field: 'nationality',
                            title: '国籍',
                            width: 120
                        },
                        {
                            field: 'nation',
                            title: '民族',
                            width: 120
                        },
                        {
                            field: 'examinationCard',
                            title: '准考证号',
                            width: 120
                        },
                        {
                            field: 'idCard',
                            title: '身份证号',
                            width: 120
                        },
                        {
                            field: 'unit',
                            title: '所在单位',
                            width: 120
                        },
                        {
                            field: 'category',
                            title: '报名分类',
                            width: 120
                        },
                        {
                            field: 'subject',
                            title: '报名科目',
                            width: 120
                        },
                        {
                            field: 'level',
                            title: '报名级别',
                            width: 120
                        },
                        {
                            field: 'recommendUnit',
                            title: '推荐单位',
                            width: 120
                        },
                        {
                            field: 'payStatusStr',
                            title: '缴费状态',
                            width: 120
                        },
                        {
                            field: 'auditStatusStr',
                            title: '审核状态',
                            width: 120
                        },
                        {
                            field: 'cost',
                            title: '报考费用',
                            width: 120
                        }
                        ,
                        {
                            field: 'examinationResultsStr',
                            title: '考试结果',
                            width: 120
                        },
                        {
                            title: '城市',
                            width: 120,
                            templet: '<div>{{createrFormat(d.enrolScene.city.cityName)}}</div>'
                        },
                        {
                            title: '场次',
                            width: 120,
                            templet: '<div>{{createrFormat(d.enrolScene.scene)}}</div>'
                        },
                        {

                            title: '测评日期',
                            width: 120,
                            templet: '<div>{{Format(d.enrolScene.evaluationDate.testDate,"yyyy-MM-dd")}}</div>'
                        }
                        ,
                        {

                            title: '测评时间',
                            width: 120,
                            templet: '<div>{{createrFormat(d.enrolScene.evaluationDate.timeFrame)}}</div>'
                        }
                        ,
                        {

                            title: '详细地址',
                            width: 120,
                            templet: '<div>{{createrFormat(d.enrolScene.detailedAddress)}}</div>'
                        }, {
                            fixed: 'right',
                            title: '操作',
                            width: 260,
                            align: 'center',
                            toolbar: '#barDemo'
                        }]],
                    id: 'testReload',
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
                    var testDate = Format(data.enrolScene.evaluationDate.testDate, "yyyy-MM-dd");
                    var city = createrFormat(data.enrolScene.city.id);
                    var detailedAddress = createrFormat(data.enrolScene.detailedAddress);
                    var timeFrame = createrFormat(data.enrolScene.evaluationDate.timeFrame)


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
                            area: ['100%', '100%'],
                            content: "./examinee-from.jsp",
                            success: function (layero, index) {

                                var body = layui.layer.getChildFrame(
                                    'body', index);
                                var iframeWin = window[layero
                                    .find('iframe')[0]['name']];
                                // body.find("#sid").append(options);
                                iframeWin.layui.form.render('select');
                                //向修改页的下拉框赋值
                                //var testDateHtml = $('#searchDate').html();
                                body.find("#testDate").html('<option value="' + testDate + '">' + testDate + '</option>');

                                var searchCity = $('#searchCity').html();
                                body.find("#city").html(searchCity);

                                getSubjects(data.category);
                                body.find("#subject").html(options);

                                getScenes();
                                body.find("#enrolSceneId").html(sceneOptions);
                                setTimeout(function () {

                                    // 取到弹出层里的元素，并把编辑的内容放进去
                                    fromAssignment(body, data, testDate, city, detailedAddress, timeFrame);
                                    //    iframeWin.setUeConent(data.context);
                                    body.find("#addNewsOperate").css(
                                        'display', 'none');
                                    body.find("#updateNewsOperate").css(
                                        'display', 'block');
                                    // 记得重新渲染表单
                                    iframeWin.layui.form.render();
                                }, 300);
                                /*  setTimeout(function(){
                                  body.find("#sid").val(data.sid);
                                  iframeWin.layui.form.render();
                                 },300); */

                            }
                        });
                    } else if (obj.event === 'detail') {
                        layer.open({
                            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                            type: 2,
                            title: "修改时间",
                            area: ['100%', '100%'],
                            content: "./examinee-from.jsp",
                            success: function (layero, index) {

                                var body = layui.layer.getChildFrame(
                                    'body', index);
                                var iframeWin = window[layero
                                    .find('iframe')[0]['name']];

                                iframeWin.layui.form.render('select');
                                //向修改页的下拉框赋值
                                // var testDateHtml = $('#searchDate').html();
                                body.find("#testDate").html('<option value="' + testDate + '">' + testDate + '</option>');

                                var searchCity = $('#searchCity').html();
                                body.find("#city").html(searchCity);

                                getSubjects(data.category);
                                body.find("#subject").html(options);

                                getScenes();
                                body.find("#enrolSceneId").html(sceneOptions);
                                setTimeout(function () {

                                    // 取到弹出层里的元素，并把编辑的内容放进去
                                    fromAssignment(body, data, testDate, city, detailedAddress, timeFrame);

                                    body.find("#addNewsOperate").css(
                                        'display', 'none');
                                    body.find("#updateNewsOperate").css(
                                        'display', 'none');
                                    iframeWin.layui.$('input').attr('disabled', true);
                                    //iframeWin.layui.$('select').attr('disabled',true);

                                    // 记得重新渲染表单
                                    iframeWin.layui.form.render();
                                }, 300);


                            }
                        });
                    } else if (obj.event === 'examine') {
                        layer.open({
                            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                            type: 2,
                            title: "审核",
                            area: ['100%', '100%'],
                            content: "./examinee-from.jsp",
                            success: function (layero, index) {

                                var body = layui.layer.getChildFrame(
                                    'body', index);
                                var iframeWin = window[layero
                                    .find('iframe')[0]['name']];

                                iframeWin.layui.form.render('select');
                                //向修改页的下拉框赋值
                                var testDateHtml = $('#searchDate').html();
                                body.find("#testDate").html(testDateHtml);

                                var searchCity = $('#searchCity').html();
                                body.find("#city").html(searchCity);

                                getSubjects(data.category);
                                body.find("#subject").html(options);

                                getScenes();
                                body.find("#enrolSceneId").html(sceneOptions);
                                setTimeout(function () {

                                    // 取到弹出层里的元素，并把编辑的内容放进去
                                    fromAssignment(body, data, testDate, city, detailedAddress, timeFrame);

                                    body.find("#addNewsOperate").css(
                                        'display', 'none');
                                    body.find("#updateNewsOperate").css(
                                        'display', 'none');
                                    body.find("#examineNewsOperate").css(
                                        'display', 'block');
                                    iframeWin.layui.$('input').attr('disabled', true);
                                    //iframeWin.layui.$('select').attr('disabled',true);
                                    // 记得重新渲染表单
                                    iframeWin.layui.form.render();
                                }, 300);


                            }
                        });
                    } else if (obj.event === 'export') {

                        window.location.href = "../../pdf/export.action?id=" + data.id;

                    }

                });


                //执行搜索的表格重载
                var $ = layui.$, active = {


                    reload: function () {
                        //alert(1);
                        var demoReload = $('#demoReload');
                        var searchCity = $('#searchCity');
                        var searchAuditStatus = $('#searchAuditStatus');
                        var searchDate = $('#searchDate');

                        //执行重载
                        table.reload(
                            'testReload',
                            {
                                page: {
                                    curr: 1
                                    //重新从第 1 页开始
                                },
                                where: {

                                    examineeName: demoReload
                                        .val(),
                                    searchCity: searchCity
                                        .val(),
                                    searchAuditStatus: searchAuditStatus.val(),
                                    searchDate: searchDate.val()

                                }
                            }, 'data');

                    }
                };

                $('.demoTable .layui-btn').on(
                    'click',
                    function () {
                        var type = $(this).data('type');
                        active[type] ? active[type]
                            .call(this) : '';

                    });

            });
    });

    function addnews() {
        var index = layer.open({
            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
            type: 2,
            title: "查看老师信息",
            area: ['100%', '100%'],
            content: "./evaluation-from.jsp",
            success: function () {
                var body = layui.layer.getChildFrame('body', index);
                // 取到弹出层里的元素，并把编辑的内容放进去
                body.find("#addNewsOperate").css('display', 'block');
                body.find("#updateNewsOperate").css('display', 'none');
                // 记得重新渲染表单
                popForm.render();
            }
        });
    }

    function getSubjects(cname) {
        $.ajax({
            async: false,
            type: 'get',
            url: '../subjects/getSubjectByCName.action',
            data: {
                "cname": cname
            },
            dataType: 'json',
            success: function (data) {
                console.log("data:" + data);

                if (data.code === 200) {
                    var list = data.rows;

                    options = "<option value=''></option>";
                    for (var i in list) {
                        console.log("list[i].id" + list[i].name);
                        options += '<option value="' + list[i].name + '">'
                            + list[i].name + '</option>';
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
            type: 'POST',
            url: '../enrolExaminees/deleteEnrolExaminee.action',
            data: {
                'id': id
            },
            dataType: 'json',
            success: function () {
                layer.msg("删除考生成功")
            },
            error: function () {
                lay.msg("删除考生失败");
            }
        });
    }

    //加载城市信息
    function selectCity() {
        $.ajax({
            url: "../citys/findAllCity.action",
            type: "GET",
            data: {
                "page": 1,
                "limit": 200
            },
            dataType: "json",
            success: function (res) {
                if (res.status === 200) {
                    //console.log("res:"+res.rows.cityName )
                    var list = res.rows;

                    // var str = '<select name="cityId" lay-verify="required" id="city" lay-filter="city">';
                    var str = '<option value="">城市</option>';
                    for (var i = 0; i < list.length; i++) {
                        str += '<option value="' + list[i].id + '">'
                            + list[i].cityName + '</option>'
                    }
                    str += '</select>';
                    console.log(str);
                    $('#searchCity').html(str);
                }
                popForm.render('select'); // 刷性select，显示出数据
            }
        });
    }

    //加载日期信息
    /*    function selectDate() {
        $.ajax({
            url: "../evaluationDates/findTestDateGroupBy.action",
            type: "GET",

            success: function (res) {
                if (res.code === 200) {
                    //console.log("res:"+res.rows.cityName )
                    var list = res.obj;

                    // var str = '<select name="cityId" lay-verify="required" id="city" lay-filter="city">';
                    var str = '<option value="">日期</option>';
                    for (var i = 0; i < list.length; i++) {
                        str += '<option value="' + Format(list[i].testDate, "yyyy-MM-dd") + '">'
                            + Format(list[i].testDate, "yyyy-MM-dd") + '</option>'
                    }
                    str += '</select>';
                    console.log(str);
                    $('#searchDate').html(str);
                }
                popForm.render('select'); // 刷性select，显示出数据
            }
        });
    }*/

    //加载场次信息
    function getScenes() {
        $.ajax({
            async: false,
            type: 'get',
            url: '../enrolScenes/findAllEnrolScene.action',
            data: {
                "page": 1,
                "limit": 200
            },
            dataType: 'json',
            success: function (data) {

                if (data.status === 200) {
                    var list = data.rows;

                    sceneOptions = "<option value=''></option>";
                    for (var i in list) {
                        console.log("list[i].id" + list[i].id);
                        sceneOptions += '<option value="' + list[i].id + '">'
                            + list[i].scene + '</option>';
                    }
                    console.log("options" + sceneOptions);
                    return sceneOptions;
                } else {
                    layer.alert(data.msg);
                }
            },
            error: function () {
                console.log("获取分类失败");
            }
        });
    }

    //抽取重复的表单赋值代码
    function fromAssignment(body, data, testDate, city, detailedAddress, timeFrame) {
        body.find("#id").val(data.id); //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
        body.find("#name").val(data.name);
        body.find("#gender").val(data.gender);
        body.find("#nationality").val(data.nationality);
        body.find("#nation").val(data.nation);
        body.find("#examinationCard").val(data.examinationCard);
        body.find("#idCard").val(data.idCard);
        body.find("#unit").val(data.unit);
        body.find("#evaluationAddress").val(data.evaluationAddress);
        body.find("#category").val(data.category);
        body.find("#subject").val(data.subject);
        body.find("#level").val(data.level);
        body.find("#enrolSceneId").val(data.enrolSceneId);
        body.find("#recommendUnit").val(data.recommendUnit);
        body.find("#examSiteNumber").val(data.examSiteNumber);
        body.find("#seatNumber").val(data.seatNumber);
        body.find("#payStatus").val(data.payStatus);
        body.find("#auditStatus").val(data.auditStatus);
        body.find("#takeCareMatters").val(data.takeCareMatters);
        body.find("#testDate").val(testDate);
        body.find("#city").val(city);
        body.find("#detailedAddress").val(detailedAddress);
        body.find("#cost").val(data.cost);
        body.find("#timeFrame").val(timeFrame);
    }

    //阻止form提交
    function functionName() {
        return false;
    }

</script>

</body>

</html>