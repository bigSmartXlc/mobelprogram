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
<title>公告列表</title>
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
			<!-- <div class="layui-row" id="popUpdateTest" style="display: none;">

				<div class="layui-col-md10">
					<form class="layui-form" lay-filter="formTestFilter"
						id="addAndUpdateEmployeeForm">
						<input type="text" name="id" style="display:none">
						<div class="layui-form-item">
							<label class="layui-form-label">标题：</label>
							<div class="layui-input-block">
								<input type="text" name="title" required lay-verify="required"
									placeholder="请输入公告标题" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">发布人：</label>
							<div class="layui-input-block">
								<input type="text" name="publisher" required
									lay-verify="required" placeholder="请输入公告发布人" autocomplete="off"
									class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">发布单位：</label>
							<div class="layui-input-block">
								<input type="text" name="issue" required lay-verify="required"
									placeholder="请输入公告发布单位" autocomplete="off" class="layui-input">
							</div>
						</div>


						<div class="layui-form-item">
							<label class="layui-form-label">日期：</label>
							<div class="layui-input-inline">
								<input type="text" class="layui-input" name="time" id="test1"
									placeholder="yyyy-MM-dd">
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">内容：</label>
							<div class="layui-input-block">
								<textarea class="layui-textarea " name="text" lay-verify="text"
									id="LAY_demo_editor"></textarea>
							</div>
						</div>


						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">相关门类：</label>
							<div class="layui-input-block">
								<textarea class="layui-textarea " name="relatedCategories"
									lay-verify="text" id="LAY_demo_editor"></textarea>
							</div>
						</div>

						<div class="layui-form-item layui-form-text">
							<label class="layui-form-label">相关科目：</label>
							<div class="layui-input-block">
								<textarea class="layui-textarea " name="relatedSubjects"
									lay-verify="text" id="LAY_demo_editor"></textarea>
							</div>
						</div>

						<div class="layui-form-item" style="padding-left: 10px;">
							<div class="layui-input-block" id="addNoticeOperate">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formAddNotice">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
							<div class="layui-input-block" id="updateNoticeOperate">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formUpdateNotice">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
							</div>
						</div>
					</form>
				</div>
			</div> -->

		</div>
	</div>
	<script type="text/html" id="barDemo">
  		<a  id="newscheck" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
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
		var options = "";
		$(function() {
			layui.use(['table','form'], function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;
				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../notices/findAllNotice.action' //数据接口
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
						field : 'title',
						title : '标题',
						width : 120
					}, {
						field : 'regtime',
						title : '日期',
						width : 120
					}, {
						field : 'publisher',
						title : '发布人',
						width : 120
					}, {
						field : 'publishCompany',
						title : '发布单位',
						width : 200
					}, {
						field : 'category',
						title : '相关门类',
						width : 200
					}, {
						field : 'subject',
						title : '相关科目',
						width : 200
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
							obj.del();
							delNews(data.id);
							layer.close(index);
						});
					} else if (obj.event === 'edit') {	
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 2,
							title : "修改公告信息",
							area : [ '100%', '100%' ],
							content :"./notice-form.jsp",
							success:function(layero,index){
								 getSubjects(data.category);
								 var body = layui.layer.getChildFrame('body', index);
								 var iframeWin = window[layero.find('iframe')[0]['name']];
								    body.find("#subject").append(options);
								    iframeWin.layui.form.render('select');
								  // 取到弹出层里的元素，并把编辑的内容放进去
					                body.find("#id").val(data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
					                body.find("#title").val(data.title);
					                body.find("#publisher").val(data.publisher);
					                body.find("#issue").val(data.publishCompany);
					                body.find("#category").val(data.category);
					                body.find("#subject").val(data.subject);
					                body.find("#introduction").val(data.introduction);
					                if(data.image!=='undefined' && data.image !== ""){
					                	body.find("#preview").attr('src',"${ctx}"+data.image);	
					                	body.find("#image").val(data.image);
					                }
					                body.find("#container").val(data.context);
					                body.find("#addNoticeOperate").css('display','none');
					                body.find("#updateNoticeOperate").css('display','block');
					                body.find("#upload").css('display','block');
					                // 记得重新渲染表单
					                iframeWin.layui.form.render();
							}
						});
					} else if (obj.event === 'detail') {
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 2,
							title : "修改中心信息",
							area : [ '100%', '100%' ],
							content :"./notice-form.jsp",
							success:function(layero,index){
								 getSubjects(data.category);
								 var body = layui.layer.getChildFrame('body', index);
								 var iframeWin = window[layero.find('iframe')[0]['name']];
								    body.find("#subject").append(options);
								    iframeWin.layui.form.render('select');
								  // 取到弹出层里的元素，并把编辑的内容放进去
					                body.find("#id").val(data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
					                body.find("#title").val(data.title);
					                body.find("#publisher").val(data.publisher);
					                body.find("#issue").val(data.publishCompany);
					                body.find("#category").val(data.category);
					                body.find("#subject").val(data.subject);
					                body.find("#introduction").val(data.introduction);
					                if(data.image!=='undefined' && data.image !== ""){
					                	body.find("#preview").attr('src',"${ctx}"+data.image);	
					                	body.find("#image").val(data.image);
					                }
					                body.find("#container").val(data.context);
					                body.find("#addNoticeOperate").css('display','none');
					                body.find("#updateNoticeOperate").css('display','none');
					                body.find("#upload").css('display','none');
					                // 记得重新渲染表单
					                iframeWin.layui.form.render();
							}
						});		
					}
				});
				
				/*form.on('submit(formAddNotice)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				    alert(formData);
				    $.ajax({
					 	  url : '../notices/addNotice.action',
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
				form.on('submit(formUpdateNotice)', function (data) {
				    //表单数据formData
				    var formData = data.field;
				    alert(formData);
				    $.ajax({
					 	  url : '../notices/updateNotice.action',
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
				});*/
			});

			$('.layui-btn').on('click', function() {
				var type = $(this).data('type');
			});
		})

		/* function setFormValue(data) {
			
			popForm.val("formTestFilter", {
				"id":data.id,
				"title" : data.title,
				"time" : data.time,
				"publisher" : data.publisher,
				"text" : data.text,
				"relatedCategories":data.relatedCategories,
				"relatedSubjects":data.relatedSubjects
			});

			popForm.render(null, 'formTestFilter');
		} */
		
		function addnews(){
			var index=layer.open({
				//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				type : 2,
				title : "查看公告信息",
				area : [ '100%', '100%' ],
				content :"./notice-form.jsp",
				success:function(){
					 var body = layui.layer.getChildFrame('body', index);
		                body.find("#addNoticeOperate").css('display','block');
		                body.find("#updateNoticeOperate").css('display','none');
		                body.find("#upload").css('display','block');
		                // 记得重新渲染表单
		                popForm.render();
				}
			});
		}
		function getSubjects(cname){
			$.ajax({
				  async:false,
				  type: 'POST',
				  url: '../subjects/getSubjectByCName.action',
				  data: {cname:cname},
				  dataType:'json' ,
				  success: function(data){
					  console.log(data);
					  if(data.code===200){
						  var list = data.rows;
						    options="";
							for ( var i in list) {
								options+=
										'<option value="'+list[i].name+'">'
												+ list[i].name
												+ '</option>';
							}
						return options;
					  }
				  },error:function(){
					  console.log("获取分类失败");
				  }  
		     });
		}
		function delNews(id){
			$.ajax({
				  type: 'POST',
				  url: '../notices/deleteNotice.action',
				  data: {'id':id},
				  dataType:'json' ,
				  success: function(){
					  if(data.code===200){
					  	layer.msg("删除公告成功");
					  }else{
						  layer.alert(data.msg);
					  }
				  },error:function(){
					  layer.msg("删除公告失败");
				  }  
		     });
		}
	</script>
</body>

</html>