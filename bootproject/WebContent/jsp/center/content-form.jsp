<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>institution-form</title>
    <script src="../../static/js/jquery.min.js" type="text/javascript"
            charset="utf-8"></script>

    <script src="../../static/layui/layui.all.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="../../static/js/common.js" type="text/javascript"
            charset="utf-8"></script>

    <link rel="stylesheet" type="text/css"
          href="../../static/layui/css/layui.css"/>
</head>
<body>
<!--修改表单 -->
<div class="layui-row" id="popUpdateTest">

    <div class="layui-col-md10">
        <form class="layui-form" lay-filter="formTestFilter"
              id="addAndUpdateEmployeeForm">
            <input type="text" id="id" name="id" style="display: none"/>

            <div class="layui-form-item">
                <label class="layui-form-label">图片展示位置</label>
                <div class="layui-input-block">
                    <select name="categoryId" id="categoryId" lay-filter="aihao">
                        <option value=""></option>
                        <option value="1">轮播图</option>
                        <option value="2">新闻图</option>

                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">内容标题：</label>
                <div class="layui-input-block">
                    <input type="text" name="title" id="title"
                           placeholder="请输入内容标题,如不需要可不填"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">链接：</label>
                <div class="layui-input-block">
                    <input type="text" name="url" id="url"
                           placeholder="请输入链接,如没有可不填"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item" id="readonlyPic" style="display: none">
                <label class="layui-form-label">图片绝对路径：</label>
                <div class="layui-input-block">
                    <input type="text" id="absolutePic"

                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">排序：</label>
                <div class="layui-input-block">
                    <input type="number" name="sortOrder" id="sortOrder" required lay-verify="required"
                           placeholder="请输入排序优先级，1为最高"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="upload">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                    <br> <br>
                    <div
                            style="width: 200px; height: 200px; border: 3px solid #0099CC; border-radius: 5px; padding: 3px;">
                        <img style="max-width: 200px; max-height: 200px;" id="preview">
                    </div>
                </div>
                <input type="text" style="display:none" name="pic" id="pic">
            </div>
            <div class="layui-form-item" style="padding-left: 10px;">
                <div class="layui-input-block" id="addOperate">
                    <button class="layui-btn layui-btn-normal" lay-submit
                            lay-filter="formAdd">提交
                    </button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    <button class="layui-btn layui-btn-primary">关闭</button>
                </div>

                <div class="layui-input-block" id="updateOperate"
                     style="display: none">
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
<input type="type" id="container" style="   display:none"/>
<script>

    $(function () {
        var form;
        layui.use(['form', 'layer', 'upload'], function () {

            var layer = layui.layer;
            var upload = layui.upload;
            form = layui.form;
            var $ = layui.jquery;


            //普通图片上传
            var uploadInst = upload.render({
                elem: '#upload'
                , url: '../files/imgupload.action'
                , before: function (obj) {
                    //预读本地文件示例，不支持ie8
                    obj.preview(function (index, file, result) {
                        console.log(obj);
                        $('#preview').attr('src', result); //图片链接（base64）
                    });
                }
                , done: function (res) {
                    //如果上传失败
                    if (res.code === -1) {
                        layer.msg('上传失败');
                        return;
                    }
                    //上传成功
                    $("#pic").val(res.data);
                }
                , error: function () {
                    //演示失败状态，并实现重传
                    var demoText = $('#demoText');
                    demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
                    demoText.find('.demo-reload').on('click', function () {
                        uploadInst.upload();
                    });
                }
            });
        });

        //新增
        form.on('submit(formAdd)', function () {
            var data = {
                id: $('#id').val(),
                title: $('#title').val(),
                categoryId: $('#categoryId').val(),
                url: $('#url').val(),
                sortOrder: $('#sortOrder').val(),
                pic: $('#pic').val(),
            };
            console.log(data);
            $.ajax({
                url: '../contents/addContent.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("新增图片失败");
                        return;
                    }
                    layer.msg("新增图片成功", function() {
                        // 获得frame索引
                        var index = parent.layer
                            .getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        //修改成功后刷新父界面
                        parent.window.location.reload();

                    });
                },
                error: function () {
                    alert("新增图片失败，请重新提交");
                }
            });
            return false;
        })

        //修改
        form.on('submit(formUpdate)', function () {
            var data = {
                id: $('#id').val(),
                title: $('#title').val(),
                categoryId: $('#categoryId').val(),
                url: $('#url').val(),
                sortOrder: $('#sortOrder').val(),
                pic: $('#pic').val(),
            };

            $.ajax({
                url: '../contents/updateContent.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("更新图片失败");
                    }
                    layer.msg("更新图片成功", function() {
                        // 获得frame索引
                        var index = parent.layer
                            .getFrameIndex(window.name);
                        //关闭当前frame
                        parent.layer.close(index);
                        //修改成功后刷新父界面
                       parent.window.location.reload();

                    });
                },
                error: function () {
                    alert("更新图片失败，请重新提交");
                }
            });
            return false;
        })

    });





</script>
</body>
</html>