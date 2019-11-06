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
<title>分类列表</title>
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
			<form class="layui-form" action="">
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
					<!-- <div class="layui-inline">
								<select name="states" lay-filter="status">
									<option value="">请选择一个状态</option>
									<option value="010">正常</option>
									<option value="021">停止</option>
									<option value="0571">删除</option>
								</select>
							</div> -->
					<button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
				</div>
			</form>
			<div class="layui-form" id="table-list">
				<table id="newslist" lay-filter="test"></table>
			<!--修改表单 -->
			<div class="layui-row" id="popUpdateTest" style="display: none;">

				<div class="layui-col-md10">
					<form class="layui-form" lay-filter="formTestFilter"
						id="addAndUpdateEmployeeForm">
						<input type="text" id="id" name="id" style="display:none"/>
						<div class="layui-form-item">
							<label class="layui-form-label">分类名：</label>
							<div class="layui-input-block">
								<input type="text" name="cityName" required lay-verify="required"
									placeholder="请输入城市名" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item" style="padding-left: 10px;">
							<div class="layui-input-block" id="addOperate">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formAddCity">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								<button class="layui-btn layui-btn-primary">关闭</button>
							</div>
							<div class="layui-input-block" id="updateOperate" style="display:none">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formUpdateCity" >提交</button>
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
  		<a  id="newsedit" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>		
		<a  id="newsdel" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	<%}%>
	</script>
	<script src="../../static/js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>

	<script src="../../static/layui/layui.all.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../../static/js/common.js" type="text/javascript"
		charset="utf-8"></script>
	<!--Core Javascript -->
	<script>
		var popForm;
		$(function() {
			layui.use('table', function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;
				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../citys/findAllCity.action' //数据接口
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
						field : 'cityName',
						title : '城市名',
						width : 120
					},
					{
						fixed : 'right',
						title:'操作',
						width : 178,
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
							deleteNews(data.id);
							obj.del();
							layer.close(index);
						});
					} else if (obj.event === 'edit') {
						$("#addOperate").css("display","none");
						$("#updateOperate").css("display","block");
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 1,
							title : "修改分类信息",
							area : [ '70%', '70%' ],
							content : $("#popUpdateTest").html()
						});
						setFormValue(data);//动态向表单赋值	
					}
				});
				form.on('submit(formAddCity)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				    console.log(formData);
				 
				    $.ajax({
					 	  url : '../citys/addCity.action',
					 	  data : data.field,
					 	  type : 'POST',
					 	  dataType:'json',
					 	  success : function(data) {	 
						 	   	if(data.code==200){
						 	   		layer.msg("新增分类成功",function() {
                                        // 获得frame索引
                                        var index = parent.layer
                                                .getFrameIndex(window.name);
                                        //关闭当前frame
                                        parent.layer.close(index);
                                        //修改成功后刷新父界面
                                        window.location.reload();
                                    });  
						 	   
						 	   	}					 
					 		},
					 	   error : function() {
					 	      layer.msg("新增分类失败，请重新提交");
					 		}
					  });
				    return false;
				});
				form.on('submit(formUpdateCity)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				  
				    $.ajax({
					 	  url : '../citys/updateCity.action',
					 	  data : data.field,
					 	  type : 'POST',
					 	  dataType:'json',
					 	  success : function(data) {
					 	     if(data.code==200){
					 			layer.msg("更新分类成功",function() {
                                    // 获得frame索引
                                    var index = parent.layer
                                            .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    window.location.reload();
                                });
					 			
					 		    
					 	     }
					 		},
					 	   error : function() {
					 	      layer.msg("更新分类失败，请重新提交");
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

		function setFormValue(data) {
			console.log("data:"+data);
			popForm.val("formTestFilter", {
				"id":data.id,
				"cityName" : data.cityName
			});
			popForm.render(null, 'formTestFilter');
		}
		
		function addnews(){
			$("#addOperate").css("display","block");
			$("#updateOperate").css("display","none");
			layer.open({
				//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				type : 1,
				title : "查看新闻信息",
				area : [ '100%', '100%' ],
				content : $("#popUpdateTest").html()
			});
		}
		function deleteNews(id){
			$.ajax({
				  type: 'POST',
				  url: '../citys/deleteCity.action',
				  data: {'id':id},
				  dataType:'json' ,
				  success: function(data){
					  if(data.code === 200){
						  layer.alert("删除分类成功")  
					  }
				  },error:function(){
					  layer.alert("删除分类失败");
				  }  
		     });
		}
	</script>
</body>

</html>