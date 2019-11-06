<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>institution-form</title>


<link rel="stylesheet" type="text/css"
	href="../../static/layui/css/layui.css" />
</head>
<body>


<table class="layui-hide" id="test"></table>

<script src="../../static/js/jquery.min.js" type="text/javascript"
		charset="utf-8"></script>

<script src="../../static/layui/layui.all.js" type="text/javascript"
		charset="utf-8"></script>
<script src="../../static/js/common.js" type="text/javascript"
		charset="utf-8"></script>
<script>

    $(function () {

       var indexUserId =  location.href.lastIndexOf('?');
        var userId = "";
       if(indexUserId!= -1){
           //获取角色名称
           userId =  location.href.substring(location.href.lastIndexOf('?'));
	   }else{
           //如果没有传递参数
           userId = "?id=-1";
	   }

       // alert();
        layui.use('table', function () {
            var table = layui.table;

            //第一个实例
            table.render({
                elem: '#test',
                height: 312,
                url: '../queryRoyalty/queryRoyalty.action' + userId //数据接口
                ,
                page: true //开启分页
                ,
                cols: [[
                    // {field:'id', width:80, title: 'ID', sort: true}
                     {field:'name', width:180, title: '考生姓名'}
                    ,{field:'cost', width:80, title: '费用'}
                    ,{field:'returnRatio', width:80, title: '返利率'}
                    ,{ width:180, title: '返利金额', templet: '<div><span style="color: red">{{createrFormat(d.cost,d.returnRatio)}}</span></div>'}



                ]],
                response: {
                    statusCode: 200
                    //重新规定成功的状态码为 200，table 组件默认为 0
                },
                parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                    console.log(res);
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.msg, //解析提示文本
                        "count": res.obj.total, //解析数据长度
                        "data": res.obj.list //解析数据列表

                    };
                }
            })
        });
	});
    function createrFormat(o,b) {

        o = o*b/100;
        return o;
    }
</script>
</body>
</html>