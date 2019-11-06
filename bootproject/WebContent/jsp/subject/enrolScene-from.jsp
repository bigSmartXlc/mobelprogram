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

<link rel="stylesheet" type="text/css"
    href="../../static/layui/css/layui.css" />
    <script src="../../js/common.js"></script>
</head>
<body>
    <!--修改表单 -->
    <form class="layui-form" lay-filter="formTestFilter"
          id="saveSceneForm">
        <input type="text" id="id" name="id" style="display: none"/>
        <div class="layui-form-item">
            <label class="layui-form-label">场次</label>
            <div class="layui-input-block">
                <input type="text" name="scene" id="scene" required lay-verify="required"
                       placeholder="请输入场次" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">城市</label>
            <div class="layui-input-block" id="cityDiv"></div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">分类</label>
            <div class="layui-input-block">
                <select name="category" id="category" lay-filter="category">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">科目</label>
            <div class="layui-input-block">
                <select name="subject" id="subject" lay-verify="required" lay-filter="subject" disabled>

                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">级别</label>
            <div class="layui-input-block">
                <select name="level" id="level" lay-verify="required" lay-filter="level"  disabled
                        class="layui-input layui-unselect">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">日期</label>
            <div class="layui-input-block">
                <select name="testDate" id="testDate" lay-verify="required"
                        class="layui-input layui-unselect" lay-filter="testDate"  disabled>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间</label>
            <div class="layui-input-block" >
                <select name="timeFrame" id="timeFrame" lay-verify="required"
                        class="layui-input layui-unselect" lay-filter="timeFrame"   disabled>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">详细地址</label>
            <div class="layui-input-block">
                <input type="text" name="detailedAddress" id="detailedAddress" required lay-verify="required"
                       placeholder="请输入详细地址" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item" style="padding-left: 10px;">
            <div class="layui-input-block" id="addOperate">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formAddScene">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-primary">关闭</button>
            </div>
            <div class="layui-input-block" id="updateOperate"
                 style="display: none">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formUpdateScene">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button class="layui-btn layui-btn-primary">关闭</button>
            </div>
        </div>
    </form>
    <input type="type" id="container" style="display: none" />
    <input type="type" id="dateId" style="display: none" />
    <script>
        $(function() {
            var form;
            layui.use([ 'form', 'layer', 'upload', 'laydate' ], function() {
                var layer = layui.layer;
                var upload = layui.upload;
                form = layui.form;
                var $ = layui.jquery;
                var laydate = layui.laydate;
                selectCity(form);
                selectSubject(form);
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
                    url: '../evaluationDates/findCategoryGroupBy.action',
                    data: {},
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        if (data.code === 200) {
                            var list = data.obj;
                            for (var i in list) {
                                $("#category").append(
                                    '<option data-id=' + list[i].id + ' value="' + list[i].id + '">'
                                    + list[i].name + '</option>');
                            }
                            form.render();
                        }
                    },
                    error: function () {
                        alert("获取分类失败");
                    }
                });
            });
            //根据分类类目动态加载科目
            form.on('select(category)', function (data) {

                var cid = data.value;
                if (cid != null && cid != 'undefined' && cid != '') {
                    $.ajax({
                        url: '../subjects/getSubjectByCid.action',
                        data: {
                            'cid': cid
                        },
                        type: 'POST',
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data.code == 200) {
                                var list = data.rows;

                                $("#subject").removeAttr('disabled');
                                $("#subject").html("");
                                $("#subject").append('<option value="">请选择</option>');
                                for (var i in list) {
                                    $("#subject").append(
                                        '<option  value="' + list[i].id + '">'
                                        + list[i].name
                                        + '</option>');
                                }
                                form.render('select');


                            } else {
                                layer.msg(data.msg);
                            }
                        },
                        error: function () {
                            layer.msg("获取分类失败");
                        }
                    });
                }
                return false;
            });

            //根据科目动态加载级别
            form.on('select(subject)', function (data) {

                var sid = data.value;
                //alert($("#category").val());
                //alert(sname);
                var cid = $("#category").val();

                if (cid != null && cid != 'undefined' && cid != '' && sid != null && sid != 'undefined' && sid != '') {
                    $.ajax({
                        url: '../evaluationDates/findLevelByCidAndSid.action',
                        data: {
                            'cid': cid,
                            'sid': sid
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
                                        '<option  value="' + list[i].id + '">'
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

                var lid = data.value;
                //alert($("#category").val());

                var cid = $("#category").val();
                var sid = $("#subject").val();

                if (lid != null && lid != 'undefined' && lid != '' && cid != null && cid != 'undefined' && cid != ''
                    && sid != null && sid != 'undefined' && sid != '') {
                    $.ajax({
                        url: '../evaluationDates/findEnrolDate.action',
                        data: {
                            'lid': lid,
                            'cid': cid,
                            'sid': sid
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

            //根据日期动态生成 时间
            form.on('select(testDate)',function (data) {

                var testDate = data.value;
                var cid = $('#category').val();
                var sid = $('#subject').val();
                var lid = $('#level').val();
                if (testDate != null && testDate != 'undefined'
                    && testDate != '') {
                    $.ajax({
                        async: false,
                        url: '../evaluationDates/findEvaluationDateByLidAndCidAndSidAndTestDate.action',
                        data: {
                            'testDate': testDate,
                            'lid':lid,
                            'sid':sid,
                            'cid':cid
                        },
                        type: 'POST',
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data.code == 200) {
                                var list = data.obj;
                                //var str = '<select name="enrolSceneId" id="enrolSceneId" lay-filter="enrolSceneId">';

                                $("#timeFrame").removeAttr('disabled');
                                var str = '<option value="">请选择</option>';
                                for (var i = 0; i < list.length; i++) {
                                    str += '<option value="' + list[i].id + '">' + list[i].timeFrame + '</option>';
                                }

                                // str += '</select>';
                                console.log(str);
                                $("#timeFrame").html(str);


                                form.render();

                            } else {
                                layer.msg(data.msg);
                            }
                        },
                        error: function () {
                            layer.msg("获取城市列表失败");
                        }
                    });
                }
                return false;
            });

            //根据选取的时间,获取选取的时间id
            form.on('select(timeFrame)',function (data) {

                $('#dateId').val(data.value);
            })
            //新增场次
            form.on('submit(formAddScene)', function() {
            
                var data = {
                    scene: $('#scene').val(),
                    cityId: $('#cityId').val(),
                    dateId: $('#dateId').val(),
                    detailedAddress: $('#detailedAddress').val(),
                };
                //console.log(data);
                $.ajax({
                    url : '../enrolScenes/addEnrolScene.action',
                    data : data,
                    type : 'POST',
                    dataType : 'json',
                    success : function(data) {
                        if (data.code < 0) {
                            layer.msg("新增失败");
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
                        alert("新增场次失败，请重新提交");
                    }
                });
                return false;
            });

            //修改场次
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
                            layer.msg("更新时间失败");
                        }
                        layer.msg("更新时间成功", function() {
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
                        alert("更新时间失败，请重新提交");
                    }
                });
                return false;
            })

        });



        //获取所有城市信息
        function selectCity(form) {
            $
                .ajax({
                    url: "../citys/findAllCity.action",
                    type: "post",
                    data: {
                        "page": 1,
                        "limit": 200
                    },
                    dataType: "json",
                    success: function (res) {
                        if (res.status === 200) {
                            var list = res.rows;
                            var str = '<select name="cityId" lay-verify="required" id="cityId">';
                            for (var i = 0; i < list.length; i++) {
                                str += '<option value="' + list[i].id + '">'
                                    + list[i].cityName + '</option>'
                            }
                            str += '</select>';
                            console.log(str);
                            $("#cityDiv").html(str);
                        }
                        form.render('select'); // 刷性select，显示出数据
                    }
                });
        }


        //获取所有科目信息
        function selectSubject(form) {
            $.ajax({
                url: "../subjects/findAllSubject.action",
                type: "GET",
                data: {
                    "page": 1,
                    "limit": 200
                },
                dataType: "json",
                success: function (res) {
                    if (res.status === 200) {
                        var list = res.rows;


                        $("#subject").html("");
                        $("#subject").append('<option value="">请选择</option>');
                        for (var i in list) {
                            $("#subject").append(
                                '<option  value="' + list[i].id + '">'
                                + list[i].name
                                + '</option>');
                        }

                    }
                    form.render('select'); // 刷性select，显示出数据
                }
            });
        }


        //获取所有级别信息
        function selectLevel(form) {
            $.ajax({
                url: "../levels/findAllLevel.action",
                type: "GET",
                data: {
                    "page": 1,
                    "limit": 200
                },
                dataType: "json",
                success: function (res) {
                    if (res.status === 200) {
                        var list = res.rows;


                        $("#level").html("");
                        $("#level").append('<option value="">请选择</option>');
                        for (var i in list) {
                            $("#level").append(
                                '<option  value="' + list[i].id + '">'
                                + list[i].level
                                + '</option>');
                        }

                    }
                    form.render('select'); // 刷性select，显示出数据
                }
            });
        }

      
    </script>
</body>
</html>