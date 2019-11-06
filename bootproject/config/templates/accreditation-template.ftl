<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>全国艺术特长生测评认证准考证</title>

    <style>
        body {
            font-family: SimSun;
        }

        table[

        0
        ]
        {
            border-spacing: 10px 20px
        ;

        }
        table [

        0
        ]
        td {
            border: 0px solid #EEEEEE;
        }

        div {
            width: 80px;
            height: 99px;
            border: 1px;
            background: #817d82;
        }
    </style>
</head>
<body>

<h1 style="text-align:center ">全国艺术特长生测评认证准考证</h1>

<table cellpadding="0" cellspacing="0" border="0" width="100%">
    <tr>
        <td width="50%" height="33">姓名:${examinee.name}</td>
        <td width="50%" height="33">准考证号:${examinee.examinationCard}</td>
        <td rowspan="3">
            <div>

            <img src="../images/${(imgName)!}" width="80" height="99" />
            </div>
        </td>
    </tr>

    <tr>
        <td width="50%" height="33">性别:${examinee.genderStr}</td>
        <td width="50%" height="33">身份证号:${examinee.idCard}</td>
    </tr>


    <tr>
        <td width="50%" height="33">国籍:${examinee.nationality}</td>
        <td width="50%" height="33">民族:${examinee.nation}</td>
    </tr>


    <tr>
        <td width="50%" height="33">城市:${(examinee.enrolScene.city.cityName)!}</td>
        <td width="50%" height="33">所属单位:${examinee.unit}</td>
    </tr>
    <tr>
        <td width="50%" height="33">测评日期:${examinee.enrolScene.evaluationDate.testDate?string("yyyy-MM-dd")}</td>
        <td width="50%" height="33">测评地点:${(examinee.enrolScene.detailedAddress)!}</td>
    </tr>

</table>

<br/>
<br/>
<table border="1px" width="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td height="40">测评科目</td>
        <td height="40">测评类别</td>
        <td height="40">测评时间</td>
    </tr>

    <tr>
        <td height="40">${examinee.subject}</td>
        <td height="40">${examinee.category}</td>
        <td height="40">  ${(examinee.enrolScene.evaluationDate.timeFrame)!}</td>
    </tr>
</table>

<h3 style="text-align:center ">注意事项</h3>
<p>1. 测评报名、准考证打印及成绩查询的唯一网址为全国艺术特长生测评认证湖北省中心官方网站（www.hbtcsrzzx.com），考生应避免登录其他网站进行上述操作，以免泄漏个人信息；</p>
<p> 2. 考场采用专业设备对测评进行全程监测，对利用通讯设备、专用作弊工具等非法传输测评试题的违法违纪行为，中心在严肃查处的同时，将向公安机关反映；</p>
<p> 3. 发现未经授权的机构及个人举办相关培训及出版相关辅导材料、以测评名义进行诈骗、向考生售卖无线接听设备和专用作弊工具等非法行为，可向湖北省中心或公安机关举报；</p>
<p> 4. 照片由湖北省中心于测评当日统一采集；</p>
<p> 5. 考生需在湖北省中心官方网站（www.hbtcsrzzx.com）填写报名信息表格并确认无误后提交，待审核通过后可查看报名信息，因考生自身原因，信息填写出现问题的，湖北省中心不承担责任；</p>
<p> 6. 经线下指定机构报名参加测评的考生，由机构确保报名信息填写完整无误后批量上传至湖北省中心官方网站（www.hbtcsrzzx.com）进行审核；</p>
<p> 7. 测评过程中，发现其他考生有拍题、作弊等行为，可向现场监考、巡考老师或湖北省中心举报；</p>
<p> 8. 参加测评时，考生须持纸质准考证和有效身份证明证件。有效身份证明证件须与报名时所填证件保持一致；</p>
<p> 9. 考生进入考场须按照监考老师要求依次就座；</p>
<p> 10. 测评时桌面只允许放置准考证、身份证明证件及草稿纸等测评相关物品，随身物品须放置在考场指定位置，请考生不要将贵重物品带入考场，以防丢失。</p>


</body>
</html>