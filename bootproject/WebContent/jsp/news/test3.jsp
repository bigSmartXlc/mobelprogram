<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../../static/js/jquery.min.js" type="text/javascript"
	charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
	charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
	charset="utf-8"></script>
<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
</head>
<body>
	<form class="layui-form">
    <div class="layui-form-item">
        <label class="layui-form-label">尾灯名称</label>
        <div class="layui-input-block">
            <input type="text" name="name" required lay-verify="required" placeholder="请输入尾灯名称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="button" class="layui-btn" id="upload">
                <i class="layui-icon">&#xe67c;</i>上传图标
            </button>
            <br><br>
            <div style="width:200px;height:200px;border:3px solid #0099CC;border-radius: 5px;padding: 3px;">
                <img style="max-width: 200px;max-height:200px;" id="preview">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" id="commit" onclick="return false">立即提交</button>
        </div>
    </div>
</form>

	<button onclick="show()">显示</button>
	<script>
	function show(){
		$("#addAndUpdateEmployeeForm").css('display','none');
	}
	layui.use(['form', 'layer', 'upload'], function () {
        var layer = layui.layer;
        var upload = layui.upload;
        var $ = layui.jquery;

        upload.render({
            elem: '#upload',
            url: '{:U("addTL")}',
          //  auto: false,//选择文件后不自动上传
            bindAction: '#commit',
            //上传前的回调
            before: function () {
                this.data = {
                    name: $('input[name="name"]').val()
                }
            },
            //选择文件后的回调
            choose: function (obj) {
                obj.preview(function (index, file, result) {
                    $('#preview').attr('src', result);
                })
            },
            //操作成功的回调
            done: function (res, index, upload) {
                var code = res.code === 0 ? 1 : 2;
                layer.alert(res.msg, {icon: code}, function () {
                    parent.window.location.reload();
                })
            },
            //上传错误回调
            error: function (index, upload) {
                layer.alert('上传失败！' + index);
            }
        });
    })
	</script>
</body>
</html>