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
<title>系统日志</title>
<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../../static/css/admin.css" />
</head>

<body>
	<div class="wrap-container clearfix">
		<div class="column-content-detail">
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
		</div>
	</div>
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
					url : '../syslogs/findAllSyslog.action' //数据接口
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
						title : '记录id',
						width : 120
					}, {
						field : 'ip',
						title : 'IP',
						width : 120
					}, {
						field : 'module',
						title : '模块',
						width : 120
					}, {
						field : 'submodule',
						title : '子模块',
						width : 200
					},{
						field : 'logtime',
						title : '记录时间',
						width : 200
					},{
						field : 'type',
						title : '操作人类型',
						width : 200
					}  ] ],
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
			
			});

		})
	</script>
</body>

</html>