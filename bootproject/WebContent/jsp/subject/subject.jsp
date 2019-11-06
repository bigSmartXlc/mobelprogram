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
</head>
<%
	Admin admin = (Admin)session.getAttribute("admin");
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
						<input type="text" id="id" name="id" style="display:none"/>
						<div class="layui-form-item">
							<label class="layui-form-label">科目名</label>
							<div class="layui-input-block">
								<input type="text" name="name" required lay-verify="required"
									placeholder="请输入科目名" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">分类</label>
							<div class="layui-input-block" id="categoryDiv">
								
							</div>
						</div>
						<div class="layui-form-item" style="padding-left: 10px;">
							<div class="layui-input-block" id="addOperate">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formAddSubject">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								<button class="layui-btn layui-btn-primary">关闭</button>
							</div>
							<div class="layui-input-block" id="updateOperate" style="display:none">
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
 <%
	  if(admin!=null && admin.getAuth()==Constants.SUPER_ADMIN){
	     %>
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
	<script src="../../js/common.js" type="text/javascript"
        charset="utf-8"></script>
	<!--Core Javascript -->
	<script>
		var popForm;
		$(function() {
			layui.use(['table','form','layer'], function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;
				selectCategory();
				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../subjects/findAllSubject.action' //数据接口
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
						title : '科目名',
						width : 120
					}, {
						title : '分类名',
						width : 120,
						templet : '<div>{{createrFormat(d.category.name)}}</div>'
					},{
						fixed : 'right',
						width : 178,
						title:'操作',
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
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 1,
							title : "修改科目信息",
							area : [ '70%', '70%' ],
							content : $("#popUpdateTest").html()
						});
						setFormValue(data, $("#newsImg"));//动态向表单赋值	
					} /* else if (obj.event === 'detail') {
						$("#addOperate").css("display", 'none');
						$("#updateOperate").css("display", 'none');
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 1,
							title : "查看科目信息",
							area : [ '70%', '70%' ],
							content : $("#popUpdateTest").html()
						});
						setFormValue(data);//动态向表单赋值	
					} */
				});
				form.on('submit(formAddSubject)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				    $.ajax({
					 	  url : '../subjects/addSubject.action',
					 	  data : data.field,
					 	  type : 'POST',
					 	  dataType:'json',
					 	  success : function(data) {	 
					 	   		layer.msg("新增科目成功");
					 		},
					 	   error : function() {
					 			alert("新增科目失败，请重新提交");
					 		}
					  });
				    return false;
				});
				form.on('submit(formUpdateSubject)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				 //   alert(formData);
				    $.ajax({
					 	  url : '../subjects/updateSubject.action',
					 	  data : data.field,
					 	  type : 'POST',
					 	  dataType:'json',
					 	  success : function(data) {	 
					 	   		layer.msg("更新科目成功");
					 		},
					 	   error : function() {
					 		    layer.msg("更新科目失败，请重新提交");
					 		}
					  });
				    return false;
				});				
			});
			
			$('.layui-btn').on('click', function() {
				var type = $(this).data('type');
				//active[type] ? active[type].call(this) : '';
			});
		})

		function setFormValue(data, imgobj) {

			popForm.val("formTestFilter", {
				"id":data.id,
				"name" : data.name,
				"category" : data.cid
			});

			popForm.render(null, 'formTestFilter');
		}
		
		function addnews(){
			$("#addOperate").css("display", 'block');
			$("#updateOperate").css("display", 'none');
			
			layer.open({
				//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				type : 1,
				title : "添加科目信息",
				area : [ '70%', '70%' ],
				content : $("#popUpdateTest").html()
			});
			popForm.render(null, 'formTestFilter');
		}
		function deleteNews(id){
			$.ajax({
				  type: 'POST',
				  url: '../subjects/deleteSubject.action',
				  data: {'id':id},
				  dataType:'json' ,
				  success: function(res){
					  if(res===200){
						  layer.msg("删除课目成功")  
					  }
				  },error:function(){
					  layer.msg("删除科目失败");
				  }  
		     });
		}
		
		function selectCategory(){
			 $.ajax({
					url:"../categorys/findAllCategory.action",
				    type:"GET",
				    data:{"page":1,"limit":200},
				    dataType:"json",
				    success:function(res){
						if(res.status === 200){
							var list = res.rows;
							var str='<select name="category" lay-verify="required" id="category">';
							for(var i=0;i<list.length;i++){
								str += '<option value="'+list[i].id+'">'+list[i].name+'</option>'
							}	
							str+='</select>';
							console.log(str);
							$("#categoryDiv").html(str);
						}
			            popForm.render('select');                       // 刷性select，显示出数据
 		         }
			});
		}
	</script>
</body>

</html>