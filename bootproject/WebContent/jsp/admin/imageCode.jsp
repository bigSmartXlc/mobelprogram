<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<img src="/img/getImage.action" id="img"/>
<form action="/img/getCode.action" method="get">

    <input name="imgCode">
    <input type="submit" value="提交">

</form>
</body>
<script src="/js/jquery.min.js"></script>
<script>
   /*$.ajax({
        type:"get",
        utl:"/img/getImage.action",
        success:function (res) {
            //consloe.log(res);
            //$('#img').attr("src","data:image/png; base64,"+res);
        }
    })*/
</script>
</html>
