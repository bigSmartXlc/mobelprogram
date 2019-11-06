<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>news-form</title>
<script src="../../static/js/jquery.min.js" type="text/javascript"
    charset="utf-8"></script>
<script src="../../static/layui/layui.all.js" type="text/javascript"
    charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
    charset="utf-8"></script>
<script type="text/javascript"
    src="../../static/lib/ueditor/ueditor.config.js"></script>
<script type="text/javascript"
    src="../../static/lib/ueditor/ueditor.all.js"></script>
<script type="text/javascript"
    src="../../static/lib/ueditor/ueditor_my.js"></script>
<link rel="stylesheet" type="text/css"
    href="../../static/layui/css/layui.css" />
</head>
<body>
    <!--修改表单 -->
    <form class="layui-form" lay-filter="formTestFilter" id="popUpdateTest">
        <input type="text" name="id" id="id" style="display: none">
        <div class="layui-form-item">
            <label class="layui-form-label">日期：</label>
            <div class="layui-input-block">
                <input type="text" class="layui-input" name="testDateStr" id="date"
                    placeholder="yyyy年MM月dd " autocomplete="off" required>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">级别</label>
            <div class="layui-input-block" id="levelDiv"></div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">分类：</label>
            <div class="layui-input-block">
                <select name="cid" id="cid" lay-filter="category">
                    <option value=""></option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科目：</label>
            <div class="layui-input-block">
                <select name="sid" id="sid">
                    <option value=""></option>
                </select>
            </div>
        </div>
       <div class="layui-inline">
	      <label class="layui-form-label">时间范围</label>
		      <div class="layui-input-inline">
		        <input type="text" class="layui-input" id="timeFrame" name="timeFrame" placeholder=" - ">
		      </div>
        </div>
        <div class="layui-form-item" style="padding-left: 10px;">
            <div class="layui-input-block" id="operate">
                <div class="layui-input-block" id="addNewsOperate">
                    <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formAddNews" id="addNews">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
                <div class="layui-input-block" id="updateNewsOperate"
                    style="display: none">
                    <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formUpdateNews" id="updateNews">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                </div>
            </div>
        </div>
    </form>
    <input type="type" id="container" style="display: none" />
    <script>
        $(function() {
            var form;
            layui.use([ 'form', 'layer', 'upload', 'laydate' ], function() {
                var layer = layui.layer;
                var upload = layui.upload;
                form = layui.form;
                var $ = layui.jquery;
                var laydate = layui.laydate;
                selectLevel(form);
                laydate.render({
                    elem : '#date',
                    theme : 'molv',
                    type : 'date',
                    format : 'yyyy年MM月dd日'
                });
            
                //时间范围
                laydate.render({
                  elem: '#timeFrame'
                  ,type: 'time'
                  ,range: true
                  ,trigger: 'click'
                });
                $.ajax({
                    url : '../categorys/getAllCategory.action',
                    data : {},
                    type : 'POST',
                    dataType : 'json',
                    success : function(data) {
                        //console.log(data);
                        if (data.status == 200) {
                            var list = data.rows;
                            for ( var i in list) {
                                $("#cid").append(
                                        '<option data-id='+list[i].id+' value="'+list[i].id+'">'
                                                + list[i].name + '</option>');
                            }
                            form.render();
                        }
                    },
                    error : function() {
                        alert("获取分类失败");
                    }
                });
            });

            form.on('submit(formAddNews)', function() {
            
                var data = {
                    testDateStr : $('input[name="testDateStr"]').val(),
                    cid : $('select[name="cid"]').val(),
                    lid : $('select[name="lid"]').val(),
                    sid : $('select[name="sid"]').val(),
                    timeFrame:$('#timeFrame').val()
                };
                //console.log(data);
                $.ajax({
                    url : '../evaluationDates/addEvaluationDate.action',
                    data : data,
                    type : 'POST',
                    dataType : 'json',
                    success : function(data) {
                        if (data.code < 0) {
                            layer.msg("新增失败，"+data.msg);
                            return;
                        }
                        layer.msg("新增成功", function() {
                            // 获得frame索引
                            var index = parent.layer
                                    .getFrameIndex(window.name);
                            //关闭当前frame
                           parent.layer.close(index);
                            //修改成功后刷新父界面
                            parent.window.location.reload();

                        });
                    },
                    error : function() {
                        alert("新增时间失败，请重新提交");
                    }
                });
                return false;
            })

            form.on('submit(formUpdateNews)', function() {
                var data = {
                        id: $('input[name="id"]').val(),
                        testDateStr : $('input[name="testDateStr"]').val(),
                        cid : $('select[name="cid"]').val(),
                        lid : $('select[name="lid"]').val(),
                        sid : $('select[name="sid"]').val(),
                        timeFrame:$('#timeFrame').val()
                    };

                $.ajax({
                    url : '../evaluationDates/updateEvaluationDate.action',
                    data : data,
                    type : 'POST',
                    dataType : 'json',
                    success : function(data) {
                        if (data.code < 0) {
                            layer.msg("更新时间失败,"+data.msg);
                            
                        }else{
                        	
                        	   layer.msg("更新时间成功", function() {
                                   // 获得frame索引
                                    var index = parent.layer
                                            .getFrameIndex(window.name);
                                    //关闭当前frame
                                    parent.layer.close(index);
                                    //修改成功后刷新父界面
                                    parent.window.location.reload();

                                });	
                        }
                     
                    },
                    error : function() {
                        alert("更新时间失败，请重新提交");
                    }
                });
                return false;
            })
            form.on('select(category)', function(data) {
                //alert(123);
                var cid = $(data.elem).find("option:selected").attr("data-id");
                //alert(cid);
                if (cid != null && cid != 'undefined' && cid != '') {
                    $.ajax({
                        url : '../subjects/getSubjectByCid.action',
                        data : {
                            cid : cid
                        },
                        type : 'POST',
                        dataType : 'json',
                        success : function(data) {
                            console.log(data);
                            if (data.code == 200) {
                                var list = data.rows;
                                
                                $("#sid").html("");
                                //$("#sid").append('<option value=""></option>');
                                for ( var i in list) {
                                    $("#sid").append(
                                            '<option value="'+list[i].id+'">'
                                                    + list[i].name
                                                    + '</option>');
                                }
                                form.render();

                            } else {
                                layer.msg(data.msg);
                            }
                        },
                        error : function() {
                            layer.msg("获取分类失败");
                        }
                    });
                }
                return false;
            });
        });
        function getUeContent() {
            if (ue.hasContents()) {
                return ue.getContent();
            }
        }
        function setUeContent(html) {
            ue.setContent(html);
        }
        //所有级别信息
        function selectLevel(form) {
            $
                    .ajax({
                        url : "../levels/findAllLevel.action",
                        type : "GET",
                        data : {
                            "page" : 1,
                            "limit" : 200
                        },
                        dataType : "json",
                        success : function(res) {
                            if (res.status === 200) {
                                var list = res.rows;
                                var str = '<select name="lid" lay-verify="required" id="lid">';
                                for (var i = 0; i < list.length; i++) {
                                    str += '<option value="'+list[i].id+'">'
                                            + list[i].level + '</option>'
                                }
                                str += '</select>';
                                console.log(str);
                                $("#levelDiv").html(str);
                            }
                            form.render('select'); //刷性select，显示出数据
                        }
                    });
        }
    </script>
</body>
</html>