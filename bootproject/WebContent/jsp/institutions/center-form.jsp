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
	<script type="text/javascript"
	src="../../static/lib/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
	src="../../static/lib/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
	src="../../static/lib/ueditor/ueditor_my.js"></script>
<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
</head>
<body>
	<!--修改表单 -->
	<form class="layui-form">
		<input type="text" name="id" id="id" style="display: none">
		<div class="layui-form-item">
			<label class="layui-form-label">中心名称：</label>
			<div class="layui-input-block">
				<input type="text" name="name" id="name" required lay-verify="required"
					placeholder="请输入中心名称" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">地址：</label>
			<div class="layui-input-block">
				<input type="text" name="addr"  id ="addr" required lay-verify="required"
					placeholder="地址" autocomplete="off" class="layui-input">
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
			<input type="text" style="display:none" name="image" id="image">
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">电话：</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" name="phone" id="phone"
					placeholder="">
			</div>
		</div>

		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">简介：</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea" name="introduction" id="introduction" lay-verify="text"></textarea>
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">邮箱：</label>
			<div class="layui-input-block">
				<input type="text" name="email" id="email" required
					lay-verify="required" placeholder="请输入邮箱" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">内容：</label>
			<div class="layui-input-block">
				<script id="context" name="context" type="text/plain">
      			</script>
			</div>
		</div>
		<div class="layui-form-item" style="padding-left: 10px;">
			<div class="layui-input-block" id="operate">
				<div class="layui-input-block" id="addNewsOperate">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="formAddNews" id="addNews">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
				<div class="layui-input-block" id="updateNewsOperate"
					style="display: none">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="formUpdateNews" id="updateNews">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
			</div>
		</div>
	</form>
	<input type="type" id="container" style="display:none"/>
	<script>
	   var ue = UE.getEditor('context');
		 ue.addListener("ready", function () {
			var con = $("#container").val();
			console.log(con);
	        if(con != null && con != 'undefined'&& con!='')
	         	ue.setContent(con);
	    });
		$(function() {
			var form ;
				layui.use([ 'form', 'layer', 'upload' ], function() {
					var layer = layui.layer;
					var upload = layui.upload;
					form = layui.form;
					var $ = layui.jquery;
					upload.render({
						elem : '#upload',
						url :  "../files/imgupload.action",
						//选择文件后的回调
						choose : function(obj) {
							obj.preview(function(index, file, result) {
								$('#preview').attr('src', result);
							})
						},
						//操作成功的回调
						done : function(res, index, upload) {
							if(res.code < 0){
								layer.msg("上传失败,请重新上传");
								return;
							}
							console.log(res);
							$("#image").val(res.data);
						},
						//上传错误回调
						error : function(index, upload) {
							layer.alert('上传失败！' + index);
						}
					});
				});
			
				 	form.on('submit(formAddNews)',function(){
						var data = {
							 name : $('input[name="name"]').val(),
							 addr : $('input[name="addr"]').val(),
							 introduction : $('textarea[name="introduction"]').val(),
							 phone : $('input[name="phone"]').val(),
							 email : $('input[name="email"]').val(),
						     image : $('input[name="image"]').val(),
						     context:getUeContent()
						};	
						console.log(data);
						$.ajax({
							  url : '../centers/addCenter.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {
						 		  if(data.code <0){
						 			  layer.msg("新增中心失败");
						 			  return;
						 		  }
						 	   		layer.msg("新增中心成功");
						 		},
						 	   error : function() {
						 			alert("新增中心失败，请重新提交");
						 		}
						});
						return false;
					})
							
					form.on('submit(formUpdateNews)',function(){
						var data = {
								 id : $('input[name="id"]').val(),
								 name : $('input[name="name"]').val(),
								 addr : $('input[name="addr"]').val(),
								 introduction : $('textarea[name="introduction"]').val(),
								 phone : $('input[name="phone"]').val(),
								 email : $('input[name="email"]').val(),
							     image : $('input[name="image"]').val(),
							     context:getUeContent()
						};	
						
						$.ajax({
							  url : '../centers/updateCenter.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {	
						 		  if(data.code < 0){
						 			  layer.msg("更新中心失败");
						 		  }
						 	   		layer.msg("更新中心成功");
						 		},
						 	   error : function() {
						 			alert("更新中心失败，请重新提交");
						 		}
						});
						return false;
					})
			});
		function getUeContent() {
			if (ue.hasContents()) {
				return ue.getContent();
			}
		}
		function setUeContent(html){
			ue.setContent(html);
		}
	</script>
</body>
</html>