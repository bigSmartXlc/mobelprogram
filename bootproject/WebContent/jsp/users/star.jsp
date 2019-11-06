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
<title>明日之星列表</title>
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
			<div class="layui-form" id="table-list">
				<table id="newslist" lay-filter="test"></table>
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
	<!--Core Javascript -->
	<script>
		var popForm;
		$(function() {
			layui.use(['table','form'], function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;
				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../stars/findAllStar.action' //数据接口
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
						field : 'uid',
						title : '用户Id',
						width : 120
					}, {
						field : 'regtime',
						title : '时间',
						width : 120
					},{
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
							obj.del();
							delNews(data.id);
							layer.close(index);
						});
					}else if (obj.event === 'edit') {
						var index = layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 2,
							title : "修改专家信息",
							area : [ '100%', '100%' ],
							content: "./star-form.jsp",
							success:function(layero,index){
								 var body = layui.layer.getChildFrame('body', index);
								 var iframeWin = window[layero.find('iframe')[0]['name']];		
								  // 取到弹出层里的元素，并把编辑的内容放进去
					                body.find("#id").val(data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
					                body.find("#uid").val(data.uid);  
					                if(data.image!=null && data.image!=='undefined' && data.image !== ""){
					                	body.find("#preview").attr('src',"${ctx}"+data.image);	
					                	body.find("#image").val(data.image);
					                }   
					                body.find("#container").val(data.context);
					                //iframeWin.setUeConent(data.context);
					                body.find("#addNewsOperate").css('display','none');
					                body.find("#updateNewsOperate").css('display','block');
					                body.find("#upload").css('display','block');
					                // 记得重新渲染表单
					                iframeWin.layui.form.render();
							}
						});
					} 
				});
			});
		})

		function delNews(id){
			$.ajax({
				  type: 'POST',
				  url: '../stars/deleteStar.action',
				  data: {'id':id},
				  dataType:'json' ,
				  success: function(){
					  layer.msg("删除明日之星成功")
				  },error:function(){
					  lay.msg("删除明日之星失败");
				  }  
		     });
		}
		
	</script>
</body>

</html>