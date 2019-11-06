<%@page import="com.hbtcsrzzx.utils.ConfigConsts.Constants"%>
<%@page import="com.hbtcsrzzx.ssm.po.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="iframe-h">

<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
    content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>科目列表</title>
<link rel="stylesheet" type="text/css"
    href="../../static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../../static/css/admin.css" />
<script src="../../js/common.js"></script>
</head>
<%
    Admin admin = (Admin) session.getAttribute("admin");
%>
<body>
    <div class="wrap-container clearfix">
        <div class="column-content-detail">
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
            <div class="layui-form" id="table-list">
                <table id="newslist" lay-filter="test"></table>
                <!--  <div class="page-wrap">
                    <ul class="pagination">
                        <li  ><a id="page_prev" href="#">«</a></li>
                        <li class="active"><span>1</span></li>
                                <li>
                                    <a href="#">2</a>
                                </li>
                        <li ><a id="page_next" href="#">»</a></li>
                    </ul>
                </div>-->
            </div>

            <!--修改表单 -->
            <div class="layui-row" id="popUpdateTest" style="display: none;">

                <div class="layui-col-md10">
                    <form class="layui-form" lay-filter="formTestFilter"
                        id="addAndUpdateEmployeeForm">
                        <input type="text" id="id" name="id" style="display: none" />
                        <div class="layui-form-item">
                            <label class="layui-form-label">姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="name" required lay-verify="required"
                                    placeholder="请输入姓名" autocomplete="off" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">性别</label>
                            <div class="layui-input-block">
                                <select name="gender" lay-verify="required" lay-filter="gender"
                                    class="layui-input layui-unselect" id="gender">
                                    <option value="0">女</option>
                                    <option value="1">男</option>
                                </select>

                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">手机号</label>
                            <div class="layui-input-block">
                                <input type="text" name="phone" required lay-verify="required"
                                    placeholder="请输入手机号" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">国籍</label>
                            <div class="layui-input-block">
                                <input type="text" name="nationality" required
                                    lay-verify="required" placeholder="请输入国籍" autocomplete="off"
                                    class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">民族</label>
                            <div class="layui-input-block">
                                <input type="text" name="nation" required lay-verify="required"
                                    placeholder="请输入民族" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">城市</label>
                            <div class="layui-input-block" id="cityDiv"></div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">出生日期：</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="birthdayStr" name="birthdayStr" placeholder="yyyy-MM-dd">
                                </div>
                            </div>

                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">身份证号</label>
                            <div class="layui-input-block">
                                <input type="text" name="idCard" required lay-verify="required"
                                    placeholder="请输入身份证号" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码</label>
                            <div class="layui-input-block">
                                <input type="text" name="password" required
                                    lay-verify="required" placeholder="请输入密码" autocomplete="off"
                                    class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属单位</label>
                            <div class="layui-input-block">
                                <input type="text" name="unit" required lay-verify="required"
                                    placeholder="请输入所属单位" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">家长姓名</label>
                            <div class="layui-input-block">
                                <input type="text" name="parentName" required
                                    lay-verify="required" placeholder="请输入家长姓名" autocomplete="off"
                                    class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">家长电话</label>
                            <div class="layui-input-block">
                                <input type="text" name="familyPhone" required
                                    lay-verify="required" placeholder="请输入家长电话" autocomplete="off"
                                    class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">家庭住址</label>
                            <div class="layui-input-block">
                                <input type="text" name="familyAddress" required
                                    lay-verify="required" placeholder="请输入家庭住址" autocomplete="off"
                                    class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">电子邮箱</label>
                            <div class="layui-input-block">
                                <input type="text" name="mailbox" required lay-verify="required"
                                    placeholder="请输入电子邮箱" autocomplete="off" class="layui-input">
                            </div>
                        </div>



                        <div class="layui-form-item" style="padding-left: 10px;">
                            <div class="layui-input-block" id="addOperate">
                                <button class="layui-btn layui-btn-normal" lay-submit
                                    lay-filter="formAddSubject">提交</button>
                                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                <button class="layui-btn layui-btn-primary">关闭</button>
                            </div>
                            <div class="layui-input-block" id="updateOperate"
                                style="display: none">
                                <button class="layui-btn layui-btn-normal" lay-submit
                                    lay-filter="formUpdateSubject">提交</button>
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
    <a  class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    <a  class="layui-btn layui-btn-normal layui-btn-xs" lay-event="rebate">返利</a>
 <%if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {%>
        <a  class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>  
        <a  class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
        var laydate;
        $(function() {
            layui.use([ 'table', 'form', 'layer' ,'laydate'], function() {
                var table = layui.table;
                var form = layui.form;
                popForm = layui.form;
                laydate = layui.laydate;



                //第一个实例
                table.render({

                    elem : '#newslist',
                    height : 312,
                    url : '../userLogs/findAllUserLog.action' //数据接口
                    ,
                    page : true //开启分页
                    ,
                    cols : [ [ //表头
                    {
                        field : 'id',
                        title : 'ID',
                        width : 80,
                        sort : true,
                        fixed : 'left'
                    }, {
                        field : 'name',
                        title : '姓名',
                        width : 120
                    }, {
                        field : 'genderStr',
                        title : '性别',
                        width : 120
                    }, {
                        field : 'nationality',
                        title : '国籍',
                        width : 120
                    }, {
                        field : 'nation',
                        title : '民族',
                        width : 120
                    }, {
                        title : '城市',
                        width : 120,
                        templet : '<div>{{createrFormat(d.city.cityName)}}</div>'
                    }, {
                        title : '出生日期',
                        width : 200,
                        templet : '<div>{{Format(d.birthday,"yyyy-MM-dd")}}</div>'
                    }, {
                        field : 'idCard',
                        title : '身份证号',
                        width : 280
                    }, {
                        field : 'unit',
                        title : '所属单位',
                        width : 120
                    }

                    , {
                        field : 'parentName',
                        title : '家长姓名',
                        width : 120
                    }, {
                        field : 'familyAddress',
                        title : '家庭住址',
                        width : 120
                    }, {
                        field : 'familyPhone',
                        title : '家长电话',
                        width : 120
                    }, {
                        field : 'mailbox',
                        title : '电子邮箱',
                        width : 120
                    }, {
                        fixed : 'right',
                        title : '操作',
                        width : 220,
                        align : 'center',
                        toolbar : '#barDemo'
                    } ] ],
                    response : {
                        statusCode : 200
                    //重新规定成功的状态码为 200，table 组件默认为 0
                    },
                    parseData : function(res) { //将原始数据解析成 table 组件所规定的数据
                        console.log(res);
                        return {
                            "code" : res.status, //解析接口状态
                            "msg" : res.message, //解析提示文本
                            "count" : res.datasize, //解析数据长度
                            "data" : res.rows
                        //解析数据列表
                        };

                    }
                });




                table.on('tool(test)', function(obj) {
                    var data = obj.data;
                    if (obj.event === 'del') {
                        layer.confirm('真的删除行么', function(index) {
                            obj.del();
                            deleteNews(data.id);
                            layer.close(index);
                        });
                    } else if (obj.event === 'edit') {
                        $("#addOperate").css("display", 'none');
                        $("#updateOperate").css("display", 'block');

                        var index = layer.open({
                            type : 2,
                            title : "修改用户信息",
                            area : [ '100%', '100%' ],
                            content :'./userLog-from.jsp',
                            success:function (layero, index) {

                                var body = layui.layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']];

                                console.log(data);
                                iframeWin.layui.form.render('select');
                                var birthdayStr = Format(data.birthday,"yyyy-MM-dd");


                                //alert(data.city.id);
                                setTimeout(function () {
                                    body.find("#id").val(data.id);
                                    body.find("#name").val(data.name);
                                    body.find("#nationality").val(data.nationality);
                                    body.find("#gender").val(data.gender);
                                    body.find("#phone").val(data.phone);
                                    body.find("#nation").val(data.nation);
                                    body.find("#cityId").val(data.city.id);
                                    body.find("#birthday").val(data.birthday);
                                    body.find("#idCard").val(data.idCard);
                                    body.find("#password").val(data.password);
                                    body.find("#unit").val(data.unit);
                                    body.find("#parentName").val(data.parentName);
                                    body.find("#familyAddress").val(data.familyAddress);
                                    body.find("#familyPhone").val(data.familyPhone);
                                    body.find("#mailbox").val(data.mailbox);
                                    body.find("#birthdayStr").val(birthdayStr);
                                    body.find("#recommender").val(data.recommender);
                                    body.find("#addOperate").css(
                                        'display', 'none');
                                    body.find("#updateOperate").css(
                                        'display', 'block');
                                    // 记得重新渲染表单
                                    iframeWin.layui.form.render();
                                },300)





                            }
                        });
                        
                    }  else if (obj.event === 'detail') {

                        var index = layer.open({
                            type : 2,
                            title : "查看用户信息",
                            area : [ '100%', '100%' ],
                            content :'./userLog-from.jsp',
                            success:function (layero, index) {

                                var body = layui.layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']];

                                console.log(data);
                                iframeWin.layui.form.render('select');
                                var birthdayStr = Format(data.birthday,"yyyy-MM-dd");


                                 //alert(data.city.id);
                                setTimeout(function () {
                                    body.find("#id").val(data.id);
                                    body.find("#name").val(data.name);
                                    body.find("#nationality").val(data.nationality);
                                    body.find("#gender").val(data.gender);
                                    body.find("#phone").val(data.phone);
                                    body.find("#nation").val(data.nation);
                                    body.find("#cityId").val(data.city.id);
                                    body.find("#birthday").val(data.birthday);
                                    body.find("#idCard").val(data.idCard);
                                    body.find("#password").val(data.password);
                                    body.find("#unit").val(data.unit);
                                    body.find("#parentName").val(data.parentName);
                                    body.find("#familyAddress").val(data.familyAddress);
                                    body.find("#familyPhone").val(data.familyPhone);
                                    body.find("#mailbox").val(data.mailbox);
                                    body.find("#birthdayStr").val(birthdayStr);
                                    body.find("#recommender").val(data.recommender);
                                    body.find("#addOperate").css(
                                        'display', 'none');
                                    body.find("#updateOperate").css(
                                        'display', 'none');
                                    // 记得重新渲染表单
                                    iframeWin.layui.form.render();
                                },300)





                            }
                        });

                    } else if(obj.event === 'rebate'){
                       // alert(data.id);
                        layer.open({
                            type : 2,
                            title : "查询用户返利信息",
                            area : [ '100%', '100%' ],
                            content :'userLog-rebate.jsp?userId='+data.id
                        })
                    }
                });


            });

            $('.layui-btn').on('click', function() {
                var type = $(this).data('type');
                //active[type] ? active[type].call(this) : '';
            });
        })

        function setFormValue(data) {
            var birthdayStr = Format(data.birthday,"yyyy-MM-dd");
            popForm.val("formTestFilter", {
                "id" : data.id,
                "name" : data.name,
                "nationality" : data.nationality,
                "gender" : data.gender,
                "phone" : data.phone,
                "nation" : data.nation,
                "cityId" : data.cityId,
                "birthday" : data.birthday,
                "idCard" : data.idCard,
                "password" : data.password,
                "unit" : data.unit,
                "parentName" : data.parentName,
                "familyAddress" : data.familyAddress,
                "mailbox" : data.mailbox,
                "familyPhone" : data.familyPhone,
                "birthdayStr" : birthdayStr

            });

            popForm.render(null, 'formTestFilter');
        }

        function addnews() {
            var index = layer.open({

                type: 2,
                title: "新增用户",
                area: ['100%', '100%'],
                content: "./userLog-from.jsp",
                success: function () {
                    var body = layui.layer.getChildFrame('body', index);
                    // 取到弹出层里的元素，并把编辑的内容放进去
                    body.find("#addOperate").css('display', 'block');
                    body.find("#updateOperate").css('display', 'none');
                    // 记得重新渲染表单
                    popForm.render();
                }
            })
        }
        function deleteNews(id) {
            $.ajax({
                type : 'POST',
                url : '../userLogs/deleteUserLog.action',
                data : {
                    'id' : id
                },
                dataType : 'json',
                success : function(res) {
                    if (res === 200) {
                        layer.msg("删除用户成功")
                    }
                },
                error : function() {
                    layer.msg("删除用户失败");
                }
            });
        }


 
    </script>
</body>

</html>