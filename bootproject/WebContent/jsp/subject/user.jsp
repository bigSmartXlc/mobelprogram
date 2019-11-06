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
	Admin admin = (Admin) session.getAttribute("admin");
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
						<input type="text" id="id" name="id" style="display: none" />
						<div class="layui-form-item">
							<label class="layui-form-label">姓名</label>
							<div class="layui-input-block">
								<input type="text" name="name" required lay-verify="required"
									placeholder="请输入姓名" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">性别</label>
							<div class="layui-input-block">
								<select name="gender" lay-verify="required" lay-filter="gender"
									class="layui-input layui-unselect" id="gender">
									<option value="0">男</option>
									<option value="1">女</option>
								</select>

							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">手机号</label>
							<div class="layui-input-block">
								<input type="text" name="phone" required lay-verify="required"
									placeholder="请输入手机号" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">国籍</label>
							<div class="layui-input-block">
								<input type="text" name="nationality" required
									lay-verify="required" placeholder="请输入国籍" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">民族</label>
							<div class="layui-input-block">
								<input type="text" name="nation" required lay-verify="required"
									placeholder="请输入民族" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">城市</label>
							<div class="layui-input-block" id="cityDiv"></div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">出生日期</label>
							<div class="layui-input-block">
								<input type="date" name="birthdayStr" required
									lay-verify="required" placeholder="请输入出生日期" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">身份证号</label>
							<div class="layui-input-block">
								<input type="text" name="identity" required
									lay-verify="required" placeholder="请输入身份证号" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">密码</label>
							<div class="layui-input-block">
								<input type="text" name="password" required
									lay-verify="required" placeholder="请输入密码" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">所属单位</label>
							<div class="layui-input-block">
								<input type="text" name="unit" required lay-verify="required"
									placeholder="请输入所属单位" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">家长姓名</label>
							<div class="layui-input-block">
								<input type="text" name="parentName" required
									lay-verify="required" placeholder="请输入家长姓名" autocomplete="off"
									class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">家长电话</label>
							<div class="layui-input-block">
								<input type="text" name="parentPhone" required
									lay-verify="required" placeholder="请输入家长电话" autocomplete="off"
									class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">家庭住址</label>
							<div class="layui-input-block">
								<input type="text" name="address" required lay-verify="required"
									placeholder="请输入家庭住址" autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">电子邮箱</label>
							<div class="layui-input-block">
								<input type="text" name="eMail" required lay-verify="required"
									placeholder="请输入电子邮箱" autocomplete="off" class="layui-input">
							</div>
						</div>



						<div class="layui-form-item" style="padding-left: 10px;">
							<div class="layui-input-block" id="addOperate">
								<button class="layui-btn layui-btn-normal" lay-submit
									lay-filter="formAddSubject">提交</button>
								<button type="reset" class="layui-btn layui-btn-primary">重置</button>
								<button class="layui-btn layui-btn-primary">关闭</button>
							</div>
							<div class="layui-input-block" id="updateOperate"
								style="display: none">
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
 <%if (admin != null && admin.getAuth() == Constants.SUPER_ADMIN) {%>
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
			layui.use([ 'table', 'form', 'layer' ], function() {
				var table = layui.table;
				var form = layui.form;
				popForm = layui.form;
				selectCategory();
				 /*动态监听用户所选下拉框
			    form.on('select(city)', function(data) {
                    alert(1);
                    console.log(data.elem); //得到select原始DOM对象
                    console.log(data.value); //得到被选中的值
                    console.log(data.othis); //得到美化后的DOM对象
                }); */
				//第一个实例
				table.render({
					elem : '#newslist',
					height : 312,
					url : '../gusers/findAllGUser.action' //数据接口
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
						title : '姓名',
						width : 120
					}, {
						field : 'genderStr',
						title : '性别',
						width : 120
					}, {
						field : 'nationality',
						title : '国籍',
						width : 120
					}, {
						field : 'nation',
						title : '民族',
						width : 120
					}, {
						field : 'city',
						title : '城市',
						width : 120
					}, {
						field : 'birthdayStr',
						title : '出生日期',
						width : 200
					}, {
						field : 'identity',
						title : '身份证号',
						width : 280
					}, {
						field : 'unit',
						title : '所属单位',
						width : 120
					}

					, {
						field : 'parentName',
						title : '家长姓名',
						width : 120
					}, {
						field : 'address',
						title : '家庭住址',
						width : 120
					}, {
						field : 'parentPhone',
						title : '家长电话',
						width : 120
					}, {
						field : 'eMail',
						title : '电子邮箱',
						width : 120
					}, {
						fixed : 'right',
						title : '操作',
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
							deleteNews(data.id);
							layer.close(index);
						});
					} else if (obj.event === 'edit') {
						$("#addOperate").css("display", 'none');
						$("#updateOperate").css("display", 'block');
						layer.open({
							//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
							type : 1,
							title : "修改用户信息",
							area : [ '70%', '70%' ],
							content : $("#popUpdateTest").html()
						});
						//console.log("data:" + data);
						//data.birthday = getMyDate(data.birthday);
						setFormValue(data);
						//setFormValue(data, $("#newsImg"));//动态向表单赋值	
					} /* else if (obj.event === 'detail') {
																																						$("#addOperate").css("display", 'none');
																																			} */
				});
				form.on('submit(formAddSubject)', function(data) {
					//表单数据formData
					var formData = data.field;
					$.ajax({
						url : '../gusers/addGUser.action',
						data : data.field,
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							layer.msg("新增用户成功", function() {
								// 获得frame索引
								var index = parent.layer
										.getFrameIndex(window.name);
								//关闭当前frame
								parent.layer.close(index);
								//修改成功后刷新父界面
								window.location.reload();
							});
						},
						error : function() {
							alert("新增用户失败，请重新提交");
						}
					});
					return false;
				});
				form.on('submit(formUpdateSubject)', function(data) {
					//表单数据formData
					var formData = data.field;
					//   alert(formData);

					$.ajax({
						url : '../gusers/updateGUser.action',
						data : data.field,
						type : 'POST',
						dataType : 'json',
						success : function(data) {
							layer.msg("更新用户成功", function() {
								// 获得frame索引
								var index = parent.layer
										.getFrameIndex(window.name);
								//关闭当前frame
								parent.layer.close(index);
								//修改成功后刷新父界面
								window.location.reload();
							});
						},
						error : function() {
							layer.msg("更新用户失败，请重新提交");
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
				"id" : data.id,
				"name" : data.name,
				"nationality" : data.nationality,
				"gender" : data.gender,
				"phone" : data.phone,
				"nation" : data.nation,
				"city" : data.city,
				"birthday" : data.birthday,
				"identity" : data.identity,
				"password" : data.password,
				"unit" : data.unit,
				"parentName" : data.parentName,
				"address" : data.address,
				"eMail" : data.eMail,
				"parentPhone" : data.parentPhone,
				"birthdayStr" : data.birthdayStr

			});

			popForm.render(null, 'formTestFilter');
		}

		function addnews() {
			$("#addOperate").css("display", 'block');
			$("#updateOperate").css("display", 'none');

			layer.open({
				//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
				type : 1,
				title : "添加用户信息",
				area : [ '70%', '70%' ],
				content : $("#popUpdateTest").html()
			});
			popForm.render(null, 'formTestFilter');
		}
		function deleteNews(id) {
			$.ajax({
				type : 'POST',
				url : '../gusers/deleteGUser.action',
				data : {
					'id' : id
				},
				dataType : 'json',
				success : function(res) {
					if (res === 200) {
						layer.msg("删除用户成功")
					}
				},
				error : function() {
					layer.msg("删除用户失败");
				}
			});
		}

		function selectCategory() {
			$
					.ajax({
						url : "../citys/findAllCity.action",
						type : "GET",
						data : {
							"page" : 1,
							"limit" : 200
						},
						dataType : "json",
						success : function(res) {
							if (res.status === 200) {
								//console.log("res:"+res.rows.cityName )
								var list = res.rows;
								var str = '<select name="city" lay-verify="required" id="city" lay-filter="city">';
								for (var i = 0; i < list.length; i++) {
									str += '<option value="'+list[i].cityName+'">'
											+ list[i].cityName + '</option>'
								}
								str += '</select>';
								console.log(str);
								$("#cityDiv").html(str);
							}
							popForm.render('select'); // 刷性select，显示出数据
						}
					});
		}

		//毫秒转换成日期格式
		function getMyDate(str) {
			var oDate = new Date(str), oYear = oDate.getFullYear(), oMonth = oDate
					.getMonth() + 1, oDay = oDate.getDate(),

			oTime = oYear + '-' + addZero(oMonth) + '-' + addZero(oDay);
			return oTime;
		}
		
		
	</script>
</body>

</html>