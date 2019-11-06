<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
          href="../../static/layui/css/layui.css"/>
    <script type="text/javascript" src="../../js/common.js"></script>
    <style type="text/css">
    </style>
</head>
<body>
<!--修改表单 -->
<form class="layui-form" lay-filter="formTestFilter" id="popUpdateTest">
    <input type="text" name="id" id="id" style="display: none">
    <div class="layui-form-item">
        <label class="layui-form-label">姓名：</label>
        <div class="layui-input-block">
            <input type="text" name="name" id="name" required
                   lay-verify="required" placeholder="请输入姓名" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">性别：</label>
        <div class="layui-input-block">
            <select name="gender" lay-verify="required" lay-filter="gender"
                    class="layui-input layui-unselect" id="gender">
                <option value="0">女</option>
                <option value="1">男</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">报考费用：</label>
        <div class="layui-input-block">
            <input type="text" name="cost" id="cost" required
                   lay-verify="required" placeholder="请输入费用" autocomplete="off"
                   class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">国籍：</label>
        <div class="layui-input-block">
            <input type="text" name="nationality" id="nationality" required
                   lay-verify="required" placeholder="请输入国籍" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">民族：</label>
        <div class="layui-input-block">
            <input type="text" name="nation" id="nation" required
                   lay-verify="required" placeholder="请输入民族" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">准考证号：</label>
        <div class="layui-input-block">
            <input type="text" name="examinationCard" id="examinationCard"
                   required lay-verify="required" placeholder="请输入准考证号"
                   autocomplete="off" class="layui-input">
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">身份证号：</label>
        <div class="layui-input-block">
            <input type="text" name="idCard" id="idCard" required
                   lay-verify="required" placeholder="请输入身份证号" autocomplete="off"
                   class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所在单位：</label>
        <div class="layui-input-block">
            <input type="text" name="unit" id="unit" required
                   lay-verify="required" placeholder="请输入所在单位" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">报名分类：</label>
        <div class="layui-input-block">
            <select name="category" id="category" lay-filter="category" lay-verify="required">
            </select>
        </div>
    </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报名科目：</label>
        <div class="layui-input-block">
            <select name="subject" id="subject" lay-verify="required" lay-filter="subject" disabled>
            </select>
        </div>
    </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">报名级别：</label>
        <div class="layui-input-block">
            <select name="level" id="level" lay-verify="required" lay-filter="level"  disabled
                    class="layui-input layui-unselect">
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">日期：</label>
        <div class="layui-input-block">
            <select name="testDate" id="testDate" lay-verify="required"
                    class="layui-input layui-unselect" lay-filter="testDate"  disabled >
            </select>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">城市：</label>
        <div class="layui-input-block">
            <select name="city" id="city" lay-filter="city"  disabled lay-verify="required"
                    class="layui-input ">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">场次信息：</label>
        <div class="layui-input-block" id="enrolSceneIdDiv">
            <select name="enrolSceneId" id="enrolSceneId" lay-filter="enrolSceneId"  disabled
                    lay-filter="enrolSceneId">
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">时间：</label>
        <div class="layui-input-block">
            <input type="text" name="timeFrame" id="timeFrame"
                   required lay-verify="required" placeholder="请输入详细时间"
                   autocomplete="off" class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">详细地址：</label>
        <div class="layui-input-block">
            <input type="text" name="detailedAddress" id="detailedAddress"
                   required lay-verify="required" placeholder="请输入详细地址"
                   autocomplete="off" class="layui-input" disabled="disabled">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">推荐单位：</label>
        <div class="layui-input-block">
            <input type="text" name="recommendUnit" id="recommendUnit" required
                   lay-verify="required" placeholder="请输入推荐单位" autocomplete="off"
                   class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">缴费状态：</label>
        <div class="layui-input-block">
            <select name="payStatus" id="payStatus" lay-verify="required"
                    class="layui-input layui-unselect">
                <option value="0">未缴费</option>
                <option value="1">已缴费</option>
            </select>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">审核状态：</label>
        <div class="layui-input-block">
            <select name="auditStatus" id="auditStatus" lay-verify="required"
                    class="layui-input layui-unselect">
                <option value="0">未审核</option>
                <option value="1">审核通过</option>
                <option value="2">审核未通过</option>
            </select>
        </div>
    </div>


    <div class="layui-form-item" style="padding-left: 10px;">
        <div class="layui-input-block" id="operate">
            <div class="layui-input-block" id="addNewsOperate">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formAddNews" id="addNews">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-input-block" id="updateNewsOperate"
                 style="display: none">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formUpdateNews" id="updateNews">提交
                </button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
            <div class="layui-input-block" id="examineNewsOperate"
                 style="display: none">
                <button class="layui-btn layui-btn-normal" lay-submit
                        lay-filter="formExamineYes" id="examineYes">审核通过
                </button>
                <button class="layui-btn layui-btn-danger" lay-submit
                        lay-filter="formExamineNo" id="examineNo">审核未通过
                </button>
            </div>
        </div>
    </div>
</form>
<input type="type" id="container" style="display: none"/>
<script>
    $(function () {

        var form;
        layui.use(['form', 'layer', 'upload', 'laydate'], function () {

            var layer = layui.layer;
            var upload = layui.upload;
            form = layui.form;
            var $ = layui.jquery;
            var laydate = layui.laydate;

            selectLevel(form);

            laydate.render({
                elem: '#date',
                theme: 'molv',
                type: 'date',
                format: 'yyyy-MM-dd'
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
        });

        form.on('submit(formAddNews)', function () {

            var data = {
                testDateStr: $('input[name="testDateStr"]').val(),
                cid: $('select[name="cid"]').val(),
                lid: $('select[name="lid"]').val(),
                sid: $('select[name="sid"]').val()
            };
            //console.log(data);
            $.ajax({
                url: '../evaluationDates/addEvaluationDate.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("新增失败");
                        return;
                    }
                    layer.msg("新增成功");
                },
                error: function () {
                    alert("新增考生`失败，请重新提交");
                }
            });
            return false;
        });
        form.on('submit(formExamineYes)', function () {
            var data = {
                auditStatus: 1,
                id: $('#id').val(),
            };
            $.ajax({
                url: '../enrolExaminees/examine.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("提交失败");
                        return;
                    }
                    layer.msg("提交成功");
                },
                error: function () {
                    alert("提交失败，请重新提交");
                }
            });
            return false;
        });
        form.on('submit(formExamineNo)', function () {
            var data = {
                auditStatus: 2,
                id: $('#id').val(),
            };
            $.ajax({
                url: '../enrolExaminees/examine.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("提交失败");
                        return;
                    }
                    layer.msg("提交成功");
                },
                error: function () {
                    alert("提交失败，请重新提交");
                }
            });
            return false;
        })

        form.on('submit(formUpdateNews)', function () {
            var data = {
                id: $('#id').val(),
                name: $('#name').val(),
                gender: $('#gender').val(),
                nationality: $('#nationality').val(),
                nation: $('#nation').val(),
                examinationCard: $('#examinationCard').val(),
                idCard: $('#idCard').val(),
                unit: $('#unit').val(),
                evaluationAddress: $('#evaluationAddress').val(),
                category: $('#category').val(),
                subject: $('#subject').val(),
                level: $('#level').val(),
                recommendUnit: $('#recommendUnit').val(),
                enrolSceneId: $('#enrolSceneId').val(),
                examSiteNumber: $('#examSiteNumber').val(),
                seatNumber: $('#seatNumber').val(),
                takeCareMatters: $('#takeCareMatters').val()
            };

            $.ajax({
                url: '../enrolExaminees/updateEnrolExaminee.action',
                data: data,
                type: 'POST',
                dataType: 'json',
                success: function (data) {
                    if (data.code < 0) {
                        layer.msg("更新考生失败");
                    }
                    layer.msg("更新考生成功",
                        function () {
                            // 获得frame索引
                            var index = parent.layer
                                .getFrameIndex(window.name);
                            //关闭当前frame
                            parent.layer.close(index);
                            //修改成功后刷新父界面
                            parent.window.location.reload();
                        });
                },
                error: function () {
                    alert("更新考生失败，请重新提交");
                }
            });
            return false;
        });

        //根据分类类目动态加载科目
        form.on('select(category)', function (data) {

            var cname = data.value;
            if (cname != null && cname != 'undefined' && cname != '') {
                $.ajax({
                    url: '../evaluationDates/findSubjectByCname.action',
                    data: {
                        'cname': cname
                    },
                    type: 'POST',
                    dataType: 'json',
                    success: function (data) {
                        console.log(data);
                        if (data.code == 200) {
                            var list = data.obj;

                            $("#subject").removeAttr('disabled');
                            $("#subject").html("");
                            $("#subject").append('<option value="">请选择</option>');
                            for (var i in list) {
                                $("#subject").append(
                                    '<option  value="' + list[i].name + '">'
                                    + list[i].name
                                    + '</option>');
                            }
                            form.render();

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



        //根据时间动态生成 城市信息
        form.on(
            'select(testDate)',
            function (data) {

                var testDate = data.value;
                if (testDate != null && testDate != 'undefined'
                    && testDate != '') {
                    $.ajax({
                        async: false,
                        url: '../enrolScenes/findCityByTestDate.action',
                        data: {
                            'testDate': testDate
                        },
                        type: 'POST',
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data.code == 200) {
                                var list = data.obj;
                                //var str = '<select name="enrolSceneId" id="enrolSceneId" lay-filter="enrolSceneId">';

                                $("#city").removeAttr('disabled');
                                var str = '<option value="" selected>请选择</option>';
                                for (var i = 0; i < list.length; i++) {
                                    str += '<option value="' + list[i].id + '">' + list[i].cityName + '</option>';
                                }

                                // str += '</select>';
                                console.log(str);
                                $("#city").html(str);


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

        //根据城市 动态生成场次信息
        form.on(
            'select(city)',
            function (data) {

                var cityId = data.value;
                var testDate = $('#testDate').val();
                if (testDate != null && testDate != 'undefined'
                    && testDate != '' && cityId != null && cityId != 'undefined'
                && cityId != ''  ) {
                    $.ajax({
                        async: false,
                        url: '../enrolScenes/findEnrolSceneByCityAndTestDate.action',
                        data: {
                            'testDate': testDate,
                            'cityId': cityId
                        },
                        type: 'POST',
                        dataType: 'json',
                        success: function (data) {
                            console.log(data);
                            if (data.code == 200) {
                                var list = data.obj;
                                //var str = '<select name="enrolSceneId" id="enrolSceneId" lay-filter="enrolSceneId">';

                                $("#enrolSceneId").removeAttr('disabled');
                                var str = '<option value="" selected>请选择</option>';
                                for (var i = 0; i < list.length; i++) {
                                    str += '<option value="' + list[i].id + '">' + list[i].scene + '</option>';
                                }

                                // str += '</select>';
                                console.log(str);
                                $("#enrolSceneId").html(str);


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

        //根据场次id 动态加载详细地址与时间
        form.on(
            'select(enrolSceneId)',
            function (data) {

                var sceneId = data.value;
                if (sceneId != null && sceneId != 'undefined'
                    && sceneId != '') {
                    $
                        .ajax({
                            async: false,
                            url: '../enrolScenes/findEnrolSceneById.action',
                            data: {
                                'id': sceneId
                            },
                            type: 'POST',
                            dataType: 'json',
                            success: function (data) {
                                console.log(data);
                                if (data.code == 200) {
                                    var list = data.obj;


                                    $("#detailedAddress").val(list.detailedAddress);
                                    $("#timeFrame").val(list.evaluationDate.timeFrame);

                                    form.render();

                                } else {
                                    layer.msg(data.msg);
                                }
                            },
                            error: function () {
                                layer.msg("获取地址时间失败");
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
                    var str = '<option value=""></option>';
                    for (var i = 0; i < list.length; i++) {
                        str += '<option value="' + list[i].level + '">'
                            + list[i].level + '</option>'
                    }
                    str += '</select>';
                    console.log(str);
                    $("#level").html(str);
                }
                form.render('select'); //刷性select，显示出数据
            }
        });
    }
</script>
</body>
</html>