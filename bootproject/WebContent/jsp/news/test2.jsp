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
	<form class="layui-form"  id="addAndUpdateEmployeeForm" style="display: none;">
		<input type="text" name="id" style="display: none;" />
		<div class="layui-form-item">
			<label class="layui-form-label">标题：</label>
			<div class="layui-input-block">
				<input type="text" name="title" required lay-verify="required"
					placeholder="请输入文章标题" autocomplete="off" class="layui-input">
			</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">来源：</label>
			<div class="layui-input-block">
				<input type="text" name="source" required lay-verify="required"
					placeholder="请输入文章来源" autocomplete="off" class="layui-input">
			</div>
		</div>
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">内容：</label>
			<div class="layui-input-block">
				<textarea class="layui-textarea " name="text" lay-verify="text"
					id="LAY_demo_editor"></textarea>
			</div>
		</div>


		<div class="layui-form-item">
			<label class="layui-form-label">日期：</label>
			<div class="layui-input-inline">
				<input type="text" class="layui-input" name="date" id="test1"
					placeholder="yyyy-MM-dd" >
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button type="button" class="layui-btn" id="upload">
					<i class="layui-icon">&#xe67c;</i>上传图标
				</button>
				<br> <br>
				<div
					style="width: 200px; height: 200px; border: 3px solid #0099CC; border-radius: 5px; padding: 3px;">
					<img style="max-width: 200px; max-height: 200px;" id="preview">
				</div>
			</div>
		</div>
		
		<div class="layui-form-item">
			<label class="layui-form-label">作者：</label>
			<div class="layui-input-inline">
				<input type="text" name="author" required lay-verify="required"
					placeholder="请输入文章作者" class="layui-input">
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
		$("#addAndUpdateEmployeeForm").css('display','block');
	}
	$(function () {
		layui.use(
			['table','form','upload','element','laydate'],
			function () {
				var table = layui.table;
				var form = layui.form;
				var upload = layui.upload;
				var elem = layui.element;
				var laydate = layui.laydate;
				popForm = layui.form;
				//第一个实例
				table.render({
					elem: '#newslist',
					height: 312,
					url: '../newss/findAllNews.action' //数据接口
						,
					page: true //开启分页
						,
					cols: [
						[ //表头
							{
								field: 'id',
								title: 'ID',
								width: 80,
								sort: true,
								fixed: 'left'
							}, {
								field: 'title',
								title: '标题',
								width: 120
							}, {
								field: 'date',
								title: '日期',
								width: 120
							}, {
								field: 'author',
								title: '作者',
								width: 120
							}, {
								field: 'source',
								title: '来源',
								width: 200
							}, {
								field: 'text',
								title: '内容',
								width: 200
							}, {
								fixed: 'right',
								width: 178,
								align: 'center',
								toolbar: '#barDemo'
							}
						]
					],
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
				table.on(
					'tool(test)',
					function (obj) {
						var data = obj.data;
						if (obj.event === 'del') {
							layer
								.confirm(
									'真的删除行么',
									function (
										index) {
										obj
											.del();
										delNews(data.id);
										layer
											.close(index);
									});
						} else if (obj.event === 'edit') {
							$("#operate")
								.html(
									'<button class="layui-btn layui-btn-normal" lay-submit' +
									'lay-filter="formDemo" onclick="editnews()">提交</button>' +
									'<button type="reset" class="layui-btn layui-btn-primary">重置</button>');
							layer
								.open({
									//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
									type: 1,
									title: "修改新闻信息",
									area: [
										'70%',
										'70%'
									],
									content: $(
											"#popUpdateTest")
										.html()
								});
							setFormValue(data,
								$("#newsImg")); //动态向表单赋值	
						} else if (obj.event === 'detail') {
							$("#operate").html('');
							layer
								.open({
									//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
									type: 1,
									title: "查看新闻信息",
									area: [
										'70%',
										'70%'
									],
									content: $(
											"#popUpdateTest")
										.html()
								});
							setFormValue(data); //动态向表单赋值	
						}
					});
				
				  laydate.render({
				    elem: '#test1'
				    ,theme: 'molv'
				  });
				upload.render({
					elem : '#upload',
					url : '{:U("addTL")}',
					//auto : false,//选择文件后不自动上传
					
					//上传前的回调
					before : function() {
						obj.preview(function(index, file, result) {
							$('#preview').attr('src', result);
						})
					},
					//操作成功的回调
					done : function(res, index, upload) {
						var code = res.code === 0 ? 1 : 2;
						layer.alert(res.msg, {
							icon : code
						}, function() {
							parent.window.location.reload();
						})
					},
					//上传错误回调
					error : function(index, upload) {
						layer.alert('上传失败！' + index);
					}
				});
					
					form.on('submit(formAddNews)', function (data) {
					    //表单数据formData
					    var formData = data.field;
					    alert(formData);
					    $.ajax({
						 	  url : '../newss/addNews.action',
						 	  data : data.field,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {	 
						 	   		layer.msg("新增公告成功");
						 		},
						 	   error : function() {
						 			alert("新增公告失败，请重新提交");
						 		}
						  });
					});
					form.on('submit(formUpdateNews)', function (data) {
					    //表单数据formData
					    var formData = data.field;
					    alert(formData);
					    $.ajax({
						 	  url : '../newss/updateNews.action',
						 	  data : data.field,
						 	  type : 'POST',
						 	  dataType:'json',
						 	  success : function(data) {	 
						 	   		layer.msg("更新公告成功");
						 		},
						 	   error : function() {
						 			alert("更新公告失败，请重新提交");
						 		}
						  });
					});
			});

		$('.layui-btn').on('click', function () {
			var type = $(this).data('type');
			//active[type] ? active[type].call(this) : '';
		});

	})
		/*layui.use([ 'form', 'layer', 'upload' ], function() {
			var layer = layui.layer;
			var upload = layui.upload;
			var $ = layui.jquery;

			upload.render({
				elem : '#upload',
				url : '{:U("addTL")}',
				auto : false,//选择文件后不自动上传
				bindAction : '#commit',
				//上传前的回调
				before : function() {
					this.data = {
						name : $('input[name="name"]').val()
					}
				},
				//选择文件后的回调
				choose : function(obj) {
					obj.preview(function(index, file, result) {
						$('#preview').attr('src', result);
					})
				},
				//操作成功的回调
				done : function(res, index, upload) {
					var code = res.code === 0 ? 1 : 2;
					layer.alert(res.msg, {
						icon : code
					}, function() {
						parent.window.location.reload();
					})
				},
				//上传错误回调
				error : function(index, upload) {
					layer.alert('上传失败！' + index);
				}
			});
		})*/
	</script>
</body>
</html>