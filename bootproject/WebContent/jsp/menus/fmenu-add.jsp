<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>网站后台</title>
<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../../static/css/admin.css" />
</head>
<body>
	<div class="wrap-container">
		<form class="layui-form" style="width: 90%; padding-top: 20px;">
			<input type="text" id="id" name="id" style="display:none"/>
			<div class="layui-form-item">
				<label class="layui-form-label">上级：</label>
				<div class="layui-input-block">
					<select name="pname" id="pname" lay-verify="required">
						<option value=""></option>
						<option value="顶级菜单">顶级菜单</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">菜单名称：</label>
				<div class="layui-input-block">
					<input type="text" name="name" id="name" required lay-verify="required"
						placeholder="请输入菜单名称" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">信息ID：</label>
				<div class="layui-input-block">
					<input type="text" name="pid" id="pid"
						placeholder="请输入信息ID" autocomplete="off" class="layui-input">
				</div>
			</div>
		    <div class="layui-form-item">
				<label class="layui-form-label">显示的页面：</label>
				<div class="layui-input-block">
					<select name="iurl" id="iurl" lay-verify="required">
						<option value="首页菜单">首页菜单</option>
						<option value="专家菜单">专家菜单</option>
						<option value="中心菜单">中心菜单</option>
						<option value="机构菜单">机构菜单</option>
						<option value="老师菜单">老师菜单</option>
						<option value="学生菜单">学生菜单</option>
						<option value="新闻菜单">新闻菜单</option>
						<option value="公告菜单">公告菜单</option>
						<option value="明日之星菜单">明日之星菜单</option>
					</select>
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">链接：</label>
				<div class="layui-input-block">
					<input type="text" name="ourl" id="ourl" 
						placeholder="请输入链接" autocomplete="off" class="layui-input">
				</div>
			</div>
			<!-- <div class="layui-form-item">
				<label class="layui-form-label">位置：</label>
				<div class="layui-input-block">
					<input type="text" name="pos" required lay-verify="required"
						placeholder="请输入方法" autocomplete="off" class="layui-input">
				</div>
			</div>-->
			<div class="layui-form-item">
				<label class="layui-form-label">图标：</label>
				<div class="layui-input-block">
					<input type="text" name="icon" id="icon"
						placeholder="请输入图标" autocomplete="off" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">状态：</label>
				<div class="layui-input-block">
					<input type="radio" name="state" value="0" title="显示" checked>
					<input type="radio" name="state" value="1" title="隐藏">
				</div>
			</div>
			<!--  <div class="layui-form-item layui-form-text">
				<label class="layui-form-label">备注</label>
				<div class="layui-input-block">
					<textarea name="desc" placeholder="请输入内容" class="layui-textarea"></textarea>
				</div>
			</div>-->
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
	</div>
	<input type="text"  id="sstate" style="display:none"/>
    <script src="../../static/js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../../static/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script>
		//Demo
		//$("input[name=sex][value=1]").attr("checked", data.UserSex == 1 ? true : false);
		$(function() {
			layui.use([ 'form', 'layer', 'upload' ], function() {
					var layer = layui.layer;
					var upload = layui.upload;
					form = layui.form;
					var $ = layui.jquery;
					$.ajax({
						url : '../menus/getAllMenu.action',
						data : {},
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							console.log(data);
							if (data.status == 200) {
								var list = data.rows;
								for ( var i in list) {
									$("#pname").append(
											'<option value="'+list[i].name+'">'
													+ list[i].name + '</option>');
								}
								form.render();
							}
						},
						error : function() {
							alert("获取菜单失败");
						}
					});
					 form.on('submit(formAddNews)',function(){
						var data = {
							 name : $('input[name="name"]').val(),
							 pname : $('select[name="pname"]').val(),
							 iurl : $('select[name="iurl"]').val(),
							 ourl : $('input[name="ourl"]').val(),
							 state : $("input[name=state][value=0]").attr("checked")?0:1,
							 pid : $('input[name="pid"]').val(),
							 icon : $('input[name="icon"]').val()
						};	
						console.log(data);
						$.ajax({
							  url : '../menus/addMenu.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {
						 		  if(data.code <0){
						 			  layer.msg("新增菜单失败");
						 			  return;
						 		  }
						 	   		layer.msg("新增菜单成功");
						 		},
						 	   error : function() {
						 			alert("新增菜单失败，请重新提交");
						 		}
						});
						return false;
					})
							
					form.on('submit(formUpdateNews)',function(){
						var data = {
								 id:$('input[name="id"]').val(),
								 name : $('input[name="name"]').val(),
								 pname : $('select[name="pname"]').val(),
								 iurl : $('select[name="iurl"]').val(),
								 ourl : $('input[name="ourl"]').val(),
								 state : $("input[name=state][value=0]").attr("checked")?0:1,
								 pid : $('input[name="pid"]').val(),
								 icon : $('input[name="icon"]').val()
						};	
						
						$.ajax({
							  url : '../menus/updateMenu.action',
						 	  data : data,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {	
						 		  if(data.code < 0){
						 			  layer.msg("更新菜单失败");
						 		  }
						 	   		layer.msg("更新菜单成功");
						 		},
						 	   error : function() {
						 			alert("更新菜单失败，请重新提交");
						 		}
						});
						return false;
					})
				});
			});
	</script>
</body>

</html>