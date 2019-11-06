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
							<label class="layui-form-label">场次</label>
							<div class="layui-input-block">
								<input type="text" name="scene" required lay-verify="required"
									placeholder="请输入场次" autocomplete="off" class="layui-input">
							</div>
						</div>

						<div class="layui-form-item">
							<label class="layui-form-label">城市</label>
							<div class="layui-input-block" id="cityDiv"></div>
						</div>
						 <div class="layui-form-item">
                            <label class="layui-form-label">分类</label>
                            <div class="layui-input-block" >
                             <select name="category" id="category" lay-filter="category">
                             </select>
                            </div>
                        </div> 
                         <div class="layui-form-item">
                            <label class="layui-form-label">科目</label>
                            <div class="layui-input-block" >
                            <select name="subject" id="subject" lay-verify="required" lay-filter="subject" >
                            </select>
                            </div>
                        </div> 
                         <div class="layui-form-item">
                            <label class="layui-form-label">级别</label>
                            <div class="layui-input-block" >
                              <select name="level" id="level" lay-verify="required" lay-filter="level"  
                               class="layui-input layui-unselect">
                             </select>
                            </div>
                        </div> 
                         <div class="layui-form-item">
                            <label class="layui-form-label">日期</label>
                            <div class="layui-input-block">
                             <select name="testDate" id="testDate" lay-verify="required"
			                    class="layui-input layui-unselect" lay-filter="testDate"  >
			                  </select>
                            </div>
                        </div> 
						 <div class="layui-form-item">
							<label class="layui-form-label">时间</label>
							<div class="layui-input-block" id="dateDiv"></div>
						</div> 
						<div class="layui-form-item">
                            <label class="layui-form-label">详细地址</label>
                            <div class="layui-input-block">
                                <input type="text" name="detailedAddress" required lay-verify="required"
                                    placeholder="请输入详细地址" autocomplete="off" class="layui-input">
                            </div>
                        </div>						<div class="layui-form-item" style="padding-left: 10px;">
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
	<script src="../../js/common.js">
		
	</script>
	<!--Core Javascript -->
	<script>
		var popForm;
		$(function() {
			layui
					.use(
							[ 'table', 'form', 'layer' ],
							function() {
								var table = layui.table;
								var form = layui.form;
								popForm = layui.form;
								var $ = layui.jquery;
								selectCity();
								selectDate();
								//第一个实例
								table
										.render({
											elem : '#newslist',
											height : 312,
											url : '../enrolScenes/findAllEnrolScene.action', //数据接口

											page : true,//开启分页

											cols : [ [ //表头
													{
														field : 'id',
														title : 'ID',
														width : 80,
														sort : true,
														fixed : 'left'
													},
													{
														field : 'scene',
														title : '场次',
														width : 120
													},
													{
														title : '城市',
														width : 120,
														templet : '<div>{{createrFormat(d.city.cityName)}}</div>'
													},
													{
														title : '时间',
														width : 120,
														templet : '<div>{{Format(d.evaluationDate.testDate,"yyyy-MM-dd")}}</div>'
													},
													{
														field : 'detailedAddress',
														title : '详细地址',
														width : 240
													}, {
														fixed : 'right',
														width : 178,
														title : '操作',
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
								//获取分类列表
							       $.ajax({
						                url: '../evaluationDates/findCategoryGroupBy.action',
						                data: {},
						                type: 'POST',
						                dataType: 'json',
						                success: function (data) {
						                    if (data.code === 200) {
						                        var list = data.obj;
						                        for (var i in list) {
						                            $("#category").append(
						                                '<option data-id=' + list[i].name + ' value="' + list[i].name + '">'
						                                + list[i].name + '</option>');
						                        }
						                        form.render();
						                    }
						                },
						                error: function () {
						                    alert("获取分类失败");
						                }
						            });

								table
										.on(
												'tool(test)',
												function(obj) {
													var data = obj.data;
													if (obj.event === 'del') {
														layer
																.confirm(
																		'真的删除行么',
																		function(
																				index) {
																			obj
																					.del();
																			deleteNews(data.id);
																			layer
																					.close(index);
																		});
													} else if (obj.event === 'edit') {
														$("#addOperate").css(
																"display",
																'none');
														$("#updateOperate")
																.css("display",
																		'block');
														layer
																.open({
																	//layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
																	type : 1,
																	title : "修改科目信息",
																	area : [
																			'70%',
																			'70%' ],
																	content : $(
																			"#popUpdateTest")
																			.html()
																});
														setFormValue(data,
																$("#newsImg"));//动态向表单赋值 
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
								form
										.on(
												'submit(formAddSubject)',
												function(data) {
													//表单数据formData
													var formData = data.field;
													$
															.ajax({
																url : '../enrolScenes/addEnrolScene.action',
																data : data.field,
																type : 'POST',
																dataType : 'json',
																success : function(
																		data) {
																	layer
																			.msg(
																					"新增场次成功",
																					function() {
																						// 获得frame索引
																						var index = parent.layer
																								.getFrameIndex(window.name);
																						//关闭当前frame
																						parent.layer
																								.close(index);
																						//修改成功后刷新父界面
																						window.location
																								.reload();

																					});
																},
																error : function() {
																	alert("新增场次失败，请重新提交");
																}
															});
													return false;
												});
								form.on('submit(formUpdateSubject)',
												function(data) {
													//表单数据formData
													var formData = data.field;
													//   alert(formData);
													$
															.ajax({
																url : '../enrolScenes/updateEnrolScene.action',
																data : data.field,
																type : 'POST',
																dataType : 'json',
																success : function(
																		data) {
																	layer
																			.msg(
																					"更新场次成功",
																					function() {
																						// 获得frame索引
																						var index = parent.layer
																								.getFrameIndex(window.name);
																						//关闭当前frame
																						parent.layer
																								.close(index);
																						//修改成功后刷新父界面
																						window.location
																								.reload();

																					});
																},
																error : function() {
																	layer
																			.msg("更新场次失败，请重新提交");
																}
															});
													return false;
												});
								
								
						        //根据科目动态加载级别
						        form.on('select(subject)', function (data) {

						            var sname = data.value;
						            //alert($("#category").val());
						            //alert(sname);
						            var cname = $("#category").val();

						            if (cname != null && cname != 'undefined' && cname != '' && cname != null && cname != 'undefined' && cname != '') {
						                $.ajax({
						                    url: '../evaluationDates/findLevelByCnameAndname.action',
						                    data: {
						                        'cname': cname,
						                        'sname': sname
						                    },
						                    type: 'POST',
						                    dataType: 'json',
						                    success: function (data) {
						                        //alert(data);
						                        console.log(data);
						                        if (data.code == 200) {
						                            var list = data.obj;

						                            $("#level").removeAttr('disabled');
						                            $("#level").html("");
						                            $("#level").append('<option value="">请选择</option>');
						                            for (var i in list) {
						                                $("#level").append(
						                                    '<option  value="' + list[i].level + '">'
						                                    + list[i].level
						                                    + '</option>');
						                            }
						                            form.render();

						                        } else {
						                            layer.msg(data.msg);
						                        }
						                    },
						                    error: function () {
						                        layer.msg("获取级别失败");
						                    }
						                });
						            }
						            return false;
						        });

						        //根据级别动态加载日期
						        form.on('select(level)', function (data) {

						            var lname = data.value;
						            //alert($("#category").val());

						            var cname = $("#category").val();
						            var sname = $("#subject").val();

						            if (cname != null && cname != 'undefined' && cname != '' && sname != null && sname != 'undefined' && sname != ''
						                && lname != null && lname != 'undefined' && lname != '') {
						                $.ajax({
						                    url: '../evaluationDates/findEvaluationDateByLnameAndCnameAndSname.action',
						                    data: {
						                        'cname': cname,
						                        'sname': sname,
						                        'lname': lname
						                    },
						                    type: 'POST',
						                    dataType: 'json',
						                    success: function (data) {
						                        //alert(data);
						                        console.log(data);
						                        if (data.code == 200) {
						                            var list = data.obj;

						                            $("#testDate").removeAttr('disabled');
						                            $("#testDate").html("");
						                            $("#testDate").append('<option value="">请选择</option>');
						                            for (var i in list) {
						                                $("#testDate").append(
						                                    '<option  value="' + Format(list[i].testDate, "yyyy-MM-dd") + '">'
						                                    + Format(list[i].testDate, "yyyy-MM-dd")
						                                    + '</option>');
						                            }
						                            form.render();

						                        } else {
						                            layer.msg(data.msg);
						                        }
						                    },
						                    error: function () {
						                        layer.msg("获取日期失败");
						                    }
						                });
						            }
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
				"scene" : data.scene,
				"cityId" : data.cityId,
				"dateId" : data.dateId,
				"detailedAddress" : data.detailedAddress
			});

			popForm.render(null, 'formTestFilter');
		}

		function addnews() {
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
		function deleteNews(id) {
			$.ajax({
				type : 'POST',
				url : '../enrolScenes/deleteEnrolScene.action',
				data : {
					'id' : id
				},
				dataType : 'json',
				success : function(res) {
					if (res === 200) {
						layer.msg("删除场次成功")
					}
				},
				error : function() {
					layer.msg("删除场次失败");
				}
			});
		}

		function selectCity() {
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
								var list = res.rows;
								var str = '<select name="cityId" lay-verify="required" id="cityId">';
								for (var i = 0; i < list.length; i++) {
									str += '<option value="'+list[i].id+'">'
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

		function selectDate() {
			$
					.ajax({
						url : "../evaluationDates/findAllEvaluationDate.action",
						type : "GET",
						data : {
							"page" : 1,
							"limit" : 200
						},
						dataType : "json",
						success : function(res) {
							if (res.status === 200) {
								var list = res.rows;
								var str = '<select name="dateId" lay-verify="required" id="dateId">';
								for (var i = 0; i < list.length; i++) {
									str += '<option value="'+list[i].id+'">'
											+ Format(list[i].testDate,
													'yyyy-MM-dd') + '</option>'
								}
								str += '</select>';
								console.log(str);
								$("#dateDiv").html(str);
							}
							popForm.render('select'); // 刷性select，显示出数据
						}
					});
		}
	</script>
</body>

</html>