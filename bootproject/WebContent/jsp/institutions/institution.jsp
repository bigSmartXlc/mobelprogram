<%@page import="com.hbtcsrzzx.utils.ConfigConsts.Constants"%>
<%@page import="com.hbtcsrzzx.ssm.po.Admin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" scope="session" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="iframe-h">

<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<title>机构列表</title>
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
	<script type="text/html" id="barDemo">
  		<a  id="newscheck" class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
	<%
		 if(admin!=null && admin.getAuth()==Constants.SUPER_ADMIN){
		%>  
  		<a  id="newsedit" class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>		
		<a  id="newsdel" class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
		<a   id="newsadd" class="layui-btn layui-btn-normal layui-btn-xs" lay-event="addUser">绑定用户</a>
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
			layui.use('table', function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;

				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../institutions/findAllInstitution.action' //数据接口
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
						title : '机构名',
						width : 120
					}, {
						field : 'introduction',
						title : '简介',
						width : 120
					}, {
						field : 'addr',
						title : '地址',
						width : 120
					}, {
						field : 'phone',
						title : '电话',
						width : 200
					},{
						field : 'category',
						title : '分类',
						width : 120
					}, {
						field : 'subject',
						title : '科目', 
						width : 200
					},{
						field : 'email',
						title : '邮箱',
						width : 120
					},  {
						fixed : 'right',
						width : '240',
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
						   getSubjects(data.category);
						   layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 2,
							title : "修改机构信息",
							area : [ '100%', '100%' ],
							content :"./institution-form.jsp",
							success:function(layero,index){
								 var body = layui.layer.getChildFrame('body', index);
								 var iframeWin = window[layero.find('iframe')[0]['name']];
								    body.find("#subject").append(options);
								    iframeWin.layui.form.render('select');
								  // 取到弹出层里的元素，并把编辑的内容放进去
					                body.find("#id").val(data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
					                body.find("#phone").val(data.phone);
					                body.find("#addr").val(data.addr);
					                body.find("#name").val(data.name);
					                body.find("#email").val(data.email);					             
					                body.find("#category").val(data.category);
					                body.find("#introduction").val(data.introduction);
					                body.find("#subject").val(data.subject);
					                //iframeWin.setUeConent(data.context);
					                body.find("#container").val(data.context);
                                    body.find("#institutionalNature").val(data.institutionalNature);
					                if(data.image!=='undefined' && data.image !== ""){
					                	body.find("#preview").attr('src',"${ctx}"+data.image);	
					                	body.find("#image").val(data.image);
					                }
					                body.find("#addNewsOperate").css('display','none');
					                body.find("#updateNewsOperate").css('display','block');
					                body.find("#upload").css('display','block');
					                // 记得重新渲染表单
					                iframeWin.layui.form.render();
							}
						});
					} else if (obj.event === 'detail') {
						  getSubjects(data.category);
						  layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 2,
							title : "查看机构信息",
							area : [ '100%', '100%' ],
							content :"./institution-form.jsp",
							success:function(layero,index){
								 var body = layui.layer.getChildFrame('body', index);
								 var iframeWin = window[layero.find('iframe')[0]['name']];
								 body.find("#subject").append(options);
								    iframeWin.layui.form.render('select');
								  // 取到弹出层里的元素，并把编辑的内容放进去
					               body.find("#id").val(data.id);  //将选中的数据的id传到编辑页面的隐藏域，便于根据ID修改数据
					                body.find("#phone").val(data.phone);
					                body.find("#addr").val(data.addr);
					                body.find("#name").val(data.name);
					                body.find("#email").val(data.email);					             
					                body.find("#category").val(data.category);
					                body.find("#introduction").val(data.introduction);
					                body.find("#subject").val(data.subject);
					                if(data.image!=='undefined' && data.image !== ""){
					                	body.find("#preview").attr('src',"${ctx}"+data.image);	
					                	body.find("#image").val(data.image);
					                }
					                //iframeWin.setUeConent(data.context);
					                body.find("#container").val(data.context);
					                body.find("#addNewsOperate").css('display','none');
					                body.find("#updateNewsOperate").css('display','none');
					                body.find("#upload").css('display','none');
					                // 记得重新渲染表单
					              iframeWin.layui.form.render();
							}
						});
					}else if (obj.event === 'addUser') {
					  //  alert(1);

                        $("#saveOperate").css("display","block");
                        layer.open({
                            //layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
                            type : 2,
                            title : "保存用户信息信息",
                            area : [ '70%', '70%' ],
                            content :"./institution-addUser.jsp",

                            success:function(layero,index){
                                var body = layui.layer.getChildFrame('body', index);
                                var iframeWin = window[layero.find('iframe')[0]['name']];
                                iframeWin.layui.form.render('select');
                                body.find("#id").val(data.id);

                                $.ajax({
									type:"post",
									data:{'id':data.id},
                                    dataType:'json',
									url:'../institutions/findBackstageUserByInstitutionId.action',
									success:function (res) {
									 if(res.code===200){
                                         var users =  res.obj;
                                         users.forEach(function (item,index) {
                                             console.log(item);
                                             var roles =   item.backstageRoles;
                                             if(roles.length === 1){
                                                 if( roles[0].roleName === 'INSTITUTION'){

                                                     //body.find("#institutionUserId").val(item.id);
													 var str = '<select name="saveInstitutionUserId" id="institutionUserId" lay-verify="required" disabled>'

                                                     str += '<option value="'+item.id+'">'+item.name+'</option>';

													 str +='</select>';
                                                     body.find("#divInstitutionUser").html(str);

                                                 }
                                                 if( roles[0].roleName === 'SALESMAN'){
                                                     //alert(item.id);
                                                     body.find("#userSalesmanId").val(item.id);
                                                 }
                                             }
                                         })
                                         // 记得重新渲染表单
                                         iframeWin.layui.form.render();

                                     }

                                    },
									error:function (e) {

                                    }

								});

                            }

                        });

					}
				});
			});

			$('.layui-btn').on('click', function() {
				var type = $(this).data('type');
				//active[type] ? active[type].call(this) : '';
			});
		})
		
		function addnews(){
			index = layer.open({
				//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				type : 2,
				title : "添加机构信息",
				area : [ '100%', '100%' ],
				content :"./institution-form.jsp",
				success:function(){
					 var body = layui.layer.getChildFrame('body', index);
		                body.find("#addNewsOperate").css('display','block');
		                body.find("#updateNewsOperate").css('display','none');
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
					  }else{
						  layer.alert(data.msg);
					  }
				  },error:function(){
					  console.log("获取机构失败");
				  }  
		     });
		}
		
		function delNews(id){
			$.ajax({
				  type: 'POST',
				  url: '../institutions/deleteInstitution.action',
				  data: {'id':id},
				  dataType:'json' ,
				  success: function(data){
					  if(data.code===200){
					  	  layer.msg("删除机构成功")
					  }else{
						  layer.alert(data.msg);
					  }
				  },error:function(){
					  layer.alert("删除机构失败");
				  }  
		     });
		}


	</script>
</body>

</html>
