<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>notice-form</title>
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
			<label class="layui-form-label">标题：</label>
			<div class="layui-input-block">
				<input type="text" name="title" id="title" required
					lay-verify="required" placeholder="请输入公告标题" autocomplete="off"
					class="layui-input">
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">发布人：</label>
			<div class="layui-input-block">
				<input type="text" name="publisher" id="publisher" required
					lay-verify="required" placeholder="请输入发布人" class="layui-input"
					autocomplete="off" required>
			</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">发布单位：</label>
			<div class="layui-input-block">
				<input type="text" name="issue" id="issue" required lay-verify="required"
					placeholder="请输入公告发布单位" autocomplete="off" class="layui-input">
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
			<input type="text" style="display: none" name="image" id="image">
		</div>
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
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">简要：</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea" name="introduction" id="introduction" lay-verify="text"></textarea>
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
				<div class="layui-input-block" id="addNoticeOperate">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="formAddNotice" id="addNews">提交</button>
					<button type="reset" class="layui-btn layui-btn-primary">重置</button>
				</div>
				<div class="layui-input-block" id="updateNoticeOperate"
					style="display: none">
					<button class="layui-btn layui-btn-normal" lay-submit
						lay-filter="formUpdateNotice" id="updateNews">提交</button>
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
			var form;
			layui.use([ 'form', 'layer', 'upload'], function() {
				var layer = layui.layer;
				var upload = layui.upload;
				form = layui.form;
				var $ = layui.jquery;
				upload.render({
					elem : '#upload',
					url : "../files/imgupload.action",
					//选择文件后的回调
					choose : function(obj) {
						obj.preview(function(index, file, result) {
							$('#preview').attr('src', result);
						})
					},
					//操作成功的回调
					done : function(res, index, upload) {
						if (res.code < 0) {
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

			form.on('submit(formAddNotice)', function() {
				var data = {
					title : $('input[name="title"]').val(),
					publisher : $('input[name="publisher"]').val(),
					issue : $('input[name="issue"]').val(),
					image : $('input[name="image"]').val(),
					category:$('select[name="category"]').val(),
					subject:$('select[name="subject"]').val(),
					introduction : $('textarea[name="introduction"]').val(),
					context:getUeContent()				
				};
				console.log(data);
				$.ajax({
					url : '../notices/addNotice.action',
					data : data,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.code === 200) {
							layer.msg("新增公告成功");
						}else{
							layer.msg(data.msg);
						}
					},
					error : function() {
						alert("新增公告失败，请重新提交");
					}
				});
				return false;
			})

			form.on('submit(formUpdateNotice)', function() {
				var data = {
						id: $('input[name="id"]').val(),
						title : $('input[name="title"]').val(),
						publisher : $('input[name="publisher"]').val(),
						issue : $('input[name="issue"]').val(),
						image : $('input[name="image"]').val(),
						category:$('select[name="category"]').val(),
						introduction : $('textarea[name="introduction"]').val(),
						subject:$('select[name="subject"]').val(),
						context:getUeContent()				
					};

				$.ajax({
					url : '../notices/updateNotice.action',
					data : data,
					type : 'POST',
					dataType : 'json',
					success : function(data) {
						if (data.code === 200) {
							layer.msg("更新公告成功");
						}else{
							layer.msg(data.msg);
						}
					},
					error : function() {
						alert("更新公告失败，请重新提交");
					}
				});
				return false;
			})
			

		   form.on('select(category)',function(data){
					//alert(123);
					console.log(data.elem); //得到select原始DOM对象
  					console.log(data.value); //得到被选中的值
  					console.log(data.othis); //得到美化后的DOM对象
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