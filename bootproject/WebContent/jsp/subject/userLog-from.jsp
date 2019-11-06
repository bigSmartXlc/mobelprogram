<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>news-form</title>
<script src="../../static/js/jquery.min.js" type="text/javascript"
    charset="utf-8"></script>
<script src="../../static/layui/layui.all.js" type="text/javascript"
    charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
    charset="utf-8"></script>

<link rel="stylesheet" type="text/css"
    href="../../static/layui/css/layui.css" />
    <script src="../../js/common.js"></script>
</head>
<body>
    <!--修改表单 -->
    <form class="layui-form" lay-filter="formTestFilter"
          id="addAndUpdateEmployeeForm">
        <input type="text" id="id" name="id" style="display: none" />
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" id="name" required lay-verify="required"
                       placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <select name="gender" id="gender" lay-verify="required" lay-filter="gender"
                        class="layui-input layui-unselect" id="gender">
                    <option value="0">女</option>
                    <option value="1">男</option>
                </select>

            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-block">
                <input type="text" name="phone" id="phone" required lay-verify="required"
                       placeholder="请输入手机号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">国籍</label>
            <div class="layui-input-block">
                <input type="text" name="nationality" id="nationality" required
                       lay-verify="required" placeholder="请输入国籍" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">民族</label>
            <div class="layui-input-block">
                <input type="text" name="nation" id="nation" required lay-verify="required"
                       placeholder="请输入民族" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">城市</label>
            <div class="layui-input-block" id="cityDiv">
                <select name="cityId" lay-verify="required" id="cityId" lay-filter="city"></select>
            </div>
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
                <input type="text" name="idCard" id="idCard" required lay-verify="required"
                       placeholder="请输入身份证号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="password" id="password" required
                       lay-verify="required" placeholder="请输入密码" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属单位</label>
            <div class="layui-input-block">
                <input type="text" name="unit" id="unit" required lay-verify="required"
                       placeholder="请输入所属单位" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家长姓名</label>
            <div class="layui-input-block">
                <input type="text" name="parentName" id="parentName" required
                       lay-verify="required" placeholder="请输入家长姓名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">家长电话</label>
            <div class="layui-input-block">
                <input type="text" name="familyPhone" id="familyPhone" required
                       lay-verify="required" placeholder="请输入家长电话" autocomplete="off"
                       class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">家庭住址</label>
            <div class="layui-input-block">
                <input type="text" name="familyAddress" id="familyAddress" required
                       lay-verify="required" placeholder="请输入家庭住址" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电子邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="mailbox" id="mailbox" required lay-verify="required"
                       placeholder="请输入电子邮箱" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">推荐人id</label>
            <div class="layui-input-block">
                <input type="text" name="recommender" id="recommender" required lay-verify="required"
                       placeholder="请输入推荐人id" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item" style="padding-left: 10px;">
            <div class="layui-input-block" id="addOperate">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formAdd">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-primary">关闭</button>
            </div>
            <div class="layui-input-block" id="updateOperate"
                 style="display: none">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formUpdate">提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-primary">关闭</button>
            </div>
        </div>
    </form>
    <script>
        $(function() {
            var form;
            layui.use(['form', 'layer',  'laydate'], function () {
                var layer = layui.layer;
                var upload = layui.upload;
                form = layui.form;
                var laydate = layui.laydate;

                laydate.render({
                    elem: '#birthdayStr'
                    ,trigger: 'click'

                });


                //初始化城市列表
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
                            var str = '<option value="">请选择</option>';
                            for (var i = 0; i < list.length; i++) {
                                str += '<option value="' + list[i].id + '">'
                                    + list[i].cityName + '</option>'
                            }
                            console.log(str);
                            $("#cityId").html(str);
                        }
                        form.render('select'); // 刷性select，显示出数据
                    }
                });

                //新增用户
                form.on('submit(formAdd)', function(data) {
                    //表单数据formData
                    var formData = data.field;
                    $.ajax({
                        url : '../userLogs/addUserLog.action',
                        data : data.field,
                        type : 'POST',
                        dataType : 'json',
                        success : function(data) {
                            if(data.code === 200){
                                layer.msg("新增用户成功", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();
                                });
                            }

                        },
                        error : function() {
                            alert("新增用户失败，请重新提交");
                        }
                    });
                    return false;
                });
                //修改用户
                form.on('submit(formUpdate)', function(data) {
                    //表单数据formData
                    var formData = data.field;
                    //   alert(formData);

                    $.ajax({
                        url : '../userLogs/updateUserLog.action',
                        data : data.field,
                        type : 'POST',
                        dataType : 'json',
                        success : function(data) {
                            if(data.code === 200){
                                layer.msg("更新用户成功", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                   parent.window.location.reload();
                                });
                            }
                        },
                        error : function() {
                            layer.msg("更新用户失败，请重新提交");
                        }
                    });
                    return false;
                });
            });

        });
    </script>
</body>
</html>