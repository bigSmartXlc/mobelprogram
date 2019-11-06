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
<title>网站后台</title>
<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
<link rel="stylesheet" type="text/css" href="../../static/css/admin.css" />
</head>
<%
	Admin admin = (Admin) session.getAttribute("admin");
%>
<body>
	<div class="page-content-wrap">
		<!-- <form class="layui-form" action=""> -->
			<div class="layui-form-item">
				<div class="layui-inline tool-btn">
					<button
						class="layui-btn layui-btn-small layui-btn-normal addBtn hidden-xs"
					 onclick="addnews()">
						<i class="layui-icon">&#xe654;</i>
					</button>
					<button
						class="layui-btn layui-btn-small layui-btn-warm listOrderBtn hidden-xs"
						data-url="/admin/category/listorderall.html">
						<i class="iconfont">&#xe656;</i>
					</button>
				</div>
				<div class="layui-inline">
					<input type="text" name="title" placeholder="请输入标题"
						autocomplete="off" class="layui-input">
				</div>
				<!-- <div class="layui-inline">
					<select name="category" lay-filter="status">
						<option value="0">主导航</option>
								<option value="010">关于我们</option>
								<option value="021">产品中心</option>
								<option value="021">新闻中心</option>
								<option value="021">业务范围</option>
								<option value="021">联系我们</option>
								<option value="021">在线留言</option>
					</select>
				</div> -->
				<button class="layui-btn layui-btn-normal" lay-submit="search">搜索</button>
			</div>
		<!-- </form> -->
		<div class="layui-form" id="table-list">
			<table class="layui-table" id="newslist" lay-filter="test" lay-even lay-skin="nob">
			</table>
		</div>

		<!-- <div class="layui-form" id="table-list">
					<table class="layui-table" lay-even lay-skin="nob">
						<colgroup>
							<col width="50">
							<col class="hidden-xs" width="50">
							<col class="hidden-xs" width="100">
							<col>
							<col width="80">
							<col width="150">
						</colgroup>
						<thead>
							<tr>
								<th><input type="checkbox" name="" lay-skin="primary" lay-filter="allChoose"></th>
								<th class="hidden-xs">ID</th>
								<th class="hidden-xs">排序</th>
								<th>菜单名称</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>首页</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>关于我们</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>产品中心</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>新闻中心</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>业务范围</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>联系我们</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
							<tr>
								<td><input type="checkbox" name="" lay-skin="primary" data-id="1"></td>
								<td class="hidden-xs">1</td>
								<td class="hidden-xs"><input type="text" name="title" autocomplete="off" class="layui-input" value="0" data-id="1"></td>
								<td>在线留言</td>
								<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
								<td>
									<div class="layui-inline">
										<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe654;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn" data-id="1" data-url="../menus/fmenu-add.jsp"><i class="layui-icon">&#xe642;</i></button>
										<button class="layui-btn layui-btn-mini layui-btn-danger del-btn" data-id="1" data-url="del.html"><i class="layui-icon">&#xe640;</i></button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>-->
	</div>
	<!--<td><button class="layui-btn layui-btn-mini layui-btn-normal table-list-status" data-status='1'>显示</button></td>
			<td>
				<div class="layui-inline">
					<button class="layui-btn layui-btn-mini layui-btn-normal  add-btn"><i class="layui-icon">&#xe654;</i></button>
					<button class="layui-btn layui-btn-mini layui-btn-normal  edit-btn"><i class="layui-icon">&#xe642;</i></button>
					<button class="layui-btn layui-btn-mini layui-btn-danger del-btn"><i class="layui-icon">&#xe640;</i></button>
				</div>
			</td> -->
	<script type="text/javascript" id="barDemo">	    
			{{# if(d.type==0) { }}
				<button class="layui-btn layui-btn-xs layui-btn-normal" data-status="0" lay-event="show">显示</button>
			{{# } else{ }}
				<button class="layui-btn layui-btn-xs layui-btn-warm" data-status="1" lay-event="show">隐藏</button>
			{{# }}}
			<button class="layui-btn layui-btn-xs layui-btn-normal  add-btn" lay-event="add"><i class="layui-icon">&#xe654;</i></button>
			<button class="layui-btn layui-btn-xs layui-btn-normal  edit-btn" lay-event="edit"><i class="layui-icon">&#xe642;</i></button>
			<button class="layui-btn layui-btn-xs layui-btn-danger del-btn" lay-event="del"><i class="layui-icon">&#xe640;</i></button>
		</script>
	<script src="../../static/js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../../static/layui/layui.js" type="text/javascript"
		charset="utf-8"></script>
	<script src="../../static/js/common.js" type="text/javascript"
		charset="utf-8"></script>
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
				url : '../menus/findAllMenu.action' //数据接口
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
					title : '菜单名',
					width : 120
				},{
					field : 'pname',
					title : '父菜单名',
					width : 120
				},{
					field : 'iurl',
					title : '显示的页面',
					width : 120
				},{
				    field : 'ourl',
					title : '链接',
					width : 120 
				},{
					field : 'type',
					title : '状态',
					width : 120
				},
				{
					fixed : 'right',
					title : '操作',
					width : 350,
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
				} else if (obj.event === 'add') {
					layer.open({
						//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						type : 2,
						title : "添加菜单",
						area : [ '70%', '70%' ],
						content : "./fmenu-add.jsp",
						success:function(layero,index){
							 var body = layui.layer.getChildFrame('body', index);
							 var iframeWin = window[layero.find('iframe')[0]['name']];
							    body.find("#pname").html();
							    body.find("#pname").html('<option value="'+data.name+'">'+data.name+'</option>');
				                //iframeWin.setUeConent(data.context);
				                body.find("#addNewsOperate").css('display','block');
				                body.find("#updateNewsOperate").css('display','none');
				                // 记得重新渲染表单
				                iframeWin.layui.form.render();
						}
					});
				} else if (obj.event === 'edit') {
					layer.open({
						//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
						type : 2,
						title : "修改菜单",
						area : [ '70%', '70%' ],
						content : "./fmenu-add.jsp",
						success:function(layero,index){
						     console.log(data);
							 var body = layui.layer.getChildFrame('body', index);
							 var iframeWin = window[layero.find('iframe')[0]['name']];
							    body.find("#id").val(data.id);
							    body.find("#pname").val(data.pname);
							    body.find("#name").val(data.name);
							    body.find("#iurl").val(data.iurl);
							    body.find("#pid").val(data.pid);
							    body.find("#ourl").val(data.ourl);
							    body.find("#icon").val(data.icon);
							    var _state=(data.type==1)?true:false;
								body.find("input[name=state][value=1]").attr("checked",_state);
								body.find("input[name=state][value=0]").attr("checked",!_state);
							    // body.find("#sstate").val();
				                //iframeWin.setUeConent(data.context);
				                body.find("#addNewsOperate").css('display','none');
				                body.find("#updateNewsOperate").css('display','block');
				                // 记得重新渲染表单
				                iframeWin.layui.form.render();
						}
					});
				}else if (obj.event === 'show') {
				    var _state = (data.type==0)?1:0;
				    var _this = $(this);
				    console.log(_state);
				    console.log(_this);
				    $.ajax({
					  type: 'POST',
					  url: '../menus/updateMenuType.action',
					  data: {id:data.id,state:_state},
					  dataType:'json' ,
					  success: function(data){
						  if(data.status===200){
							  layer.msg("菜单操作成功");
							  if(_state == 1) {
							       _this.removeClass('layui-btn-normal').addClass('layui-btn-warm').html('隐藏').attr('data-status', 2);
								} else if(status == 2) {
								   _this.removeClass('layui-btn-warm').addClass('layui-btn-normal').html('显示').attr('data-status', 1);
								}
						  }
					  },error:function(){
						  layer.msg("菜菜单失败");
					  }  
			     });
				}
			});
		});
	})

		
			function addnews(){
				layer.open({
					//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
					type : 2,
					title : "添加菜单信息",
					area : [ '70%', '70%' ],
					content : "./fmenu-add.jsp"
				});
			}
			
			function deleteNews(id){
				$.ajax({
					  type: 'POST',
					  url: '../menus/deleteMenu.action',
					  data: {'id':id},
					  dataType:'json' ,
					  success: function(data){
						  if(data.code===200){
							  layer.msg("删除菜单成功")  
						  }
					  },error:function(){
						  layer.msg("删除菜单失败");
					  }  
			     });
			}
	</script>
</body>
</html>