<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>institution-form</title>

    <link rel="stylesheet" type="text/css"
          href="../../static/layui/css/layui.css"/>
</head>
<body>
<!--绑定用户 -->

<form class="layui-form" lay-filter="formTestFilter"
      id="addAndUpdateEmployeeForm">
    <input type="text" id="id" name="id" style="display:none"/>
    <div class="layui-form-item">
        <label class="layui-form-label">业务员名称：</label>
        <div class="layui-input-block">
            <select name="salesmanId" id="userSalesmanId">

            </select>
        </div>
    </div>

    <div class="layui-form-item" id="institutionUser">
        <label class="layui-form-label">机构用户名称：</label>
        <div class="layui-input-block" id="divInstitutionUser">
            <select name="institutionUserId" id="institutionUserId" lay-verify="required">

            </select>
        </div>
    </div>
    <div class="layui-form-item" style="padding-left: 10px;">
        <div class="layui-input-block" id="saveOperate">
            <button class="layui-btn layui-btn-normal" lay-submit
                    lay-filter="formSave">提交
            </button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            <button class="layui-btn layui-btn-primary">关闭</button>
            <button class="layui-btn layui-btn-danger" lay-submit
                    lay-filter="formDelSalesman">删除业务员账号
            </button>
            <button class="layui-btn layui-btn-danger" lay-submit
                    lay-filter="formDelInstitutions">删除机构账号
            </button>
        </div>

    </div>
</form>
<script src="../../static/js/jquery.min.js" type="text/javascript"
        charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
        charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
        charset="utf-8"></script>
<script>
    $(function () {
        var form;
        layui.use(['form'], function () {
            form = layui.form;


            institutionUserAll(form);
            salesmanUserAll(form);
            form.on('submit(formSave)', function (data) {



                if (data.field.salesmanId != null && data.field.salesmanId != '' && data.field.salesmanId != 'undefined') {
                    //alert(1);
                    $.ajax({

                        type: 'post',
                        url: '../institutions/saveBackstageUserAndInstitution.action',
                        data: {
                            'institutionId': data.field.id
                            , 'backstageUser': data.field.salesmanId
                            ,'roleName':'SALESMAN'
                        },
                        dataType: 'json',
                        success: function (res) {


                            if (res.code === 200) {
                                layer.msg("绑定用户成功", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();

                                })
                            } else {
                                layer.msg("绑定用户失败", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();

                                })
                               // alert("绑定用户失败")
                            }
                        },
                        error: function (e) {

                        }
                    })
                }


                if (data.field.institutionUserId != null && data.field.institutionUserId != '' && data.field.institutionUserId != 'undefined') {
                    $.ajax({

                        type: 'get',
                        url: '../institutions/saveBackstageUserAndInstitution.action',
                        data: {
                            'institutionId': data.field.id
                            , 'backstageUser': data.field.institutionUserId,
                            'roleName': 'INSTITUTION'
                        },
                        dataType: 'json',
                        success: function (res) {

                            if (res.code === 200) {
                                layer.msg("绑定用户成功", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();

                                })
                            } else {
                                layer.msg("绑定用户失败", function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                        .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();

                                })
                                // alert("绑定用户失败")
                            }
                        },
                        error: function (e) {

                        }
                    })

                }

                return false;
            })

            form.on('submit(formDelSalesman)', function (data) {

                $.ajax({
                    type: 'get',
                    url: '../institutions/delUserByInstitutionId.action',
                    data: {'id':data.field.id,
                        'userId':data.field.salesmanId
                    },
                    dataType: 'json',
                    success: function (res) {

                        if(res.code === 200){
                            layer.msg("删除成功", function() {
                                // 获得frame索引
                                var index = parent.layer
                                    .getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //修改成功后刷新父界面
                                parent.window.location.reload();

                            });
                        }else{
                            layer.msg("删除失败")
                        }

                    },
                    error: function (e) {

                    }

                })
                return false;
            });
            form.on('submit(formDelInstitutions)', function (data) {

                $.ajax({
                    type: 'post',
                    url: '../institutions/delUserByInstitutionId.action',
                    data: {'id':data.field.id,
                        'userId':data.field.saveInstitutionUserId
                    },
                    dataType: 'json',
                    success: function (res) {
                        if(res.code === 200){
                            layer.msg("删除成功", function() {
                                // 获得frame索引
                                var index = parent.layer
                                    .getFrameIndex(window.name);
                                //关闭当前frame
                                parent.layer.close(index);
                                //修改成功后刷新父界面
                                parent.window.location.reload();

                            });
                        }else{
                            layer.msg("删除失败")
                        }

                    },
                    error: function (e) {

                    }

                })
                return false;
            });
        })
    })

    //加载所有机构用户
    function institutionUserAll(form) {

        $.ajax({
            type: 'post',
            url: '../backstageUsers/findBackstageUserAllByInstitution.action',
            data: {},
            dataType: 'json',
            success: function (res) {

                var users = res.obj;

                var str = '<option value="">请选择</option>';
                users.forEach(function (item, index) {
                    console.log(item + ":" + index);

                    str += '<option value="' + item.id + '">' + item.name + '</option>'
                })
                console.log(str);
                $("#institutionUserId").html(str);
                form.render(); // 刷性select，显示出数据
            },
            error: function (e) {

            }

        })
    }


    //加载所有业务员用户
    function salesmanUserAll(form) {

        $.ajax({
            type: 'post',
            url: '../backstageUsers/findBackstageUsersByRoleName.action',
            data: {
                'roleName': 'SALESMAN'
                , 'page': 1
                , 'limit': 20
            },
            dataType: 'json',
            success: function (res) {

                var users = res.obj.list;

                var str = '<option value="">请选择</option>';
                users.forEach(function (item, index) {
                    console.log(item + ":" + index);

                    str += '<option value="' + item.id + '">' + item.name + '</option>'
                })
                console.log(str);
                $("#userSalesmanId").html(str);
                form.render(); // 刷性select，显示出数据
            },
            error: function (e) {

            }

        })
    }
</script>
</body>
</html>