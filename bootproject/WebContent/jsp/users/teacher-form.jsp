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
			<label class="layui-form-label">姓名：</label>
			<div class="layui-input-block">
				<input type="text" name="name" id="name" required lay-verify="required"
					placeholder="请输入姓名" autocomplete="off" class="layui-input">
			</div>
		</div>
        <div class="layui-form-item">
			<label class="layui-form-label">手机号：</label>
			<div class="layui-input-block">
				<input type="text" name="phone" id="phone" required lay-verify="required"
					placeholder="请输入手机号" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">出生地址：</label>
			<div class="layui-input-block">
				<input type="text" name="birthplace"  id ="birthplace" required lay-verify="required"
					placeholder="出生地址" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">公司：</label>
			<div class="layui-input-block">
				<input type="text" class="layui-input" name="company" id="company" 
					placeholder="公司">
			</div>
		</div>
		
		<!-- <div class="layui-form-item">
			<label class="layui-form-label">地址：</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" name="addr" id="addr" 
					placeholder="地址">
			</div>
		</div> -->
		
		<div class="layui-form-item">
			<label class="layui-form-label">分类：</label>
			<div class="layui-input-block">
				<select name="category" id="category" lay-filter="category">
					<option value=""></option>
				</select>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">科目：</label>
			<div class="layui-input-block">
				<select name="subject" id="subject">
					<option value=""></option>
				</select>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">受教育水平：</label>
			<div class="layui-input-block">
					<select name="eduLevel" id="eduLevel">
						<option value=""></option>
						<option value="小学">小学</option>
						<option value="中学">中学</option>
						<option value="高中">高中</option>
						<option value="大学">大学</option>
						<option value="研究生">研究生</option>
					</select>
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">简介：</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea " name="introduction" id="introduction" lay-verify="text"
					id="LAY_demo_editor"></textarea>
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
			<label class="layui-form-label">邮箱：</label>
			<div class="layui-input-block">
				<input type="text" name="email" id="email" required lay-verify="required"
					placeholder="请输入邮箱" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">行业：</label>
			<div class="layui-input-block">
				<input type="text" name="direction" id="direction" required lay-verify="required"
					placeholder="请输入行业" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">资质：</label>
			<div class="layui-input-block">
				<input type="text" name="cert" id="cert" required lay-verify="required"
					placeholder="请输入资质，例如  硬笔书法 5级" class="layui-input">
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
	var ue = UE.getEditor('context',{zIndex: 100});
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
					$.ajax({
						url : '../categorys/getAllCategory.action',
						data : {},
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							console.log(data);
							if (data.status == 200) {
								var list = data.rows;
								for ( var i in list) {
									$("#category").append(
											'<option data-id='+list[i].id+' value="'+list[i].name+'">'
													+ list[i].name + '</option>');
								}
								form.render();
							}
						},
						error : function() {
							alert("获取分类失败");
						}
					});
				});
			
				 	form.on('submit(formAddNews)',function(){
						var data = {
								 name : $('input[name="name"]').val(),
								 phone : $('input[name="phone"]').val(),
								 company : $('input[name="company"]').val(),
								 birthplace : $('input[name="birthplace"]').val(),
								 category : $('select[name="category"]').val(),
								 subject : $('select[name="subject"]').val(),	
								 eduLevel : $('select[name="eduLevel"]').val(),
								 direction : $('input[name="direction"]').val(),
								 cert : $('input[name="cert"]').val(),
								 email : $('input[name="email"]').val(),
								 introduction : $('textarea[name="introduction"]').val(),
							     image : $('input[name="image"]').val(),
							     context:getUeContent()
						};	
						//console.log(data);
						$.ajax({
							  url : '../teachers/addTeacher.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {
						 		  if(data.code <0){
						 			  layer.msg("新增老师失败");
						 			  return;
						 		  }
						 	   		layer.msg("新增老师成功");
						 		},
						 	   error : function() {
						 			alert("新增老师失败，请重新提交");
						 		}
						});
						return false;
					})
							
					form.on('submit(formUpdateNews)',function(){
						var data = {
								 id : $('input[name="id"]').val(),
								 name : $('input[name="name"]').val(),
								 phone : $('input[name="phone"]').val(),
								 company : $('input[name="company"]').val(),
								 birthplace : $('input[name="birthplace"]').val(),
								 category : $('select[name="category"]').val(),
								 subject : $('select[name="subject"]').val(),	
								 eduLevel : $('select[name="eduLevel"]').val(),
								 direction : $('input[name="direction"]').val(),
								 cert : $('input[name="cert"]').val(),
								 email : $('input[name="email"]').val(),
								 introduction : $('textarea[name="introduction"]').val(),
							     image : $('input[name="image"]').val(),
							     context:getUeContent()
						};	
						
						$.ajax({
							  url : '../teachers/updateTeacher.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {	
						 		  if(data.code < 0){
						 			  layer.msg("更新老师失败");
						 		  }
						 	   		layer.msg("更新老师成功");
						 		},
						 	   error : function() {
						 			alert("更新老师失败，请重新提交");
						 		}
						});
						return false;
					})
					 form.on('select(category)',function(data){
							//alert(123);
							var cid = $(data.elem).find("option:selected").attr("data-id");
							//alert(cid);
							if(cid!= null && cid !='undefined' && cid!=''){
								$.ajax({
									  url : '../subjects/getSubjectByCid.action',
								 	  data : {cid:cid},
								 	  type : 'POST',
								 	  dataType:'json',
								 	  success : function(data) {	
								 		 console.log(data);
											if (data.code == 200) {
												var list = data.rows;
												$("#subject").html("");
												$("#subject").append('<option value=""></option>');
												for ( var i in list) {
													$("#subject").append(
															'<option value="'+list[i].name+'">'
																	+ list[i].name
																	+ '</option>');
												}
												form.render();
											}else{
												layer.msg(data.msg);
											}
								 		},
								 	   error : function() {
								 			layer.msg("获取分类失败");
								 		}
								});	
							}
						return false;
					});
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