/**
 * 加载新闻
 * */

//var path="/portalweb1";
var path = "";
var expertRows = null;
var expertIdArr = [];
var teacherRows = null;
var teacherIdArr = [];
var userRows = null;
var userIdArr = [];
//新闻图id
var institutionId = 2;
//轮播图
var shufflingImgId = 1;

/**
 * 加载新闻
 */

$(function () {
    var count = 4;
    $.ajax({
        type: 'POST',
        url: 'http://48y3pn.natappfree.cc/jsp/newss/findAllNewsByTitle.action',
        data: {
            page: 1, limit: count
        },
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.status === 200) {
                var list = data.rows;
                var len = list.length;
                len = (count > len) ? len : count;

                //加载新闻展示图
                $.ajax({
                    type: 'post',
                    data: {'citegoryId':institutionId},
                    datatype: 'json',
                    url: 'http://48y3pn.natappfree.cc/jsp/contents/findContentByCitegoryId.action',
                    success: function (res) {

                        console.log(res);
                        if (res.code === 200) {

                            if (len > 0) {
                                $("#index-news-div").append('<h4 class="news-img-up">' + list[0].title + '</h4>');
                                $("#index-news-div").append('<p style="text-indent: 2em; letter-spacing: 1px; padding-right: 20px;">' +
                                    list[0].introduction + '<a href="' + "./news-details.html?id=" + list[0].id + '"><span style="color: #ad1312">【详情】<span></a></p>');

                               var str ='';

                               for (var i = 0 ; i< res.obj.length ;  i++){
                                   str  += '<img onclick="imgZoomIn(this.src)"; class="img-thumbnail news-img" style="height:120px;width:180px;" src="http://48y3pn.natappfree.cc'+res.obj[i].pic+'">';
                               }

                                $("#index-news-div").append('<center>' +str+ '</center>');
                            }
                            for (var i = 1; i < len; i++) {
                                $("#index-news-div").append('<h5 class="news-img-btm"><a href="' +
                                    "./news-details.html?id=" + list[i].id + '">' + list[i].title + '</a></h5>');
                            }

                        }

                    },
                    error: function (e) {

                    }


                })

            }
        },
        error: function () {
            //  layer.msg("获取新闻失败");
        }
    });
});

/**
 * 加载轮播图
 */
$(function () {

    $.ajax({
        type: 'post',
        data: {'citegoryId':shufflingImgId},
        datatype: 'json',
        url: 'http://48y3pn.natappfree.cc/jsp/contents/findContentByCitegoryId.action',
        success: function (res) {

            console.log(res);
            if (res.code === 200) {

                var list = res.obj;
                var str = '';
                var imgStr = '';
                for (var i = 0; i < list.length; i++) {
                    if (i == 0) {
                        str += '<li data-target="#myCarousel" data-slide-to="' + i + '" class="active"></li>';
                        imgStr += '<div class="item active">' +
                            '<a href="' + list[i].url + '"><img style="width:100%" src="http://48y3pn.natappfree.cc' + list[i].pic + '" alt="First slide"></a>' +
                            '</div>';
                    }else{
                        str += '<li data-target="#myCarousel" data-slide-to="' + i + '" style="margin-left: 15px;"></li>';
                        imgStr += '<div class="item">' +
                            '<a href="' + list[i].url + '"><img style="width:100%;" src="http://48y3pn.natappfree.cc' + list[i].pic + '" alt="First slide"></a>' +
                            '</div>';
                    }
                }

                $('#carousel-indicators').html(str);
                $('#carousel-inner').html(imgStr);
            }
        },
        error: function (e) {
        }
    })

});
Array.prototype.contains = function (obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}

function replaceExpert() {
    var count = 8;
    var rd = Math.floor(Math.random() * count);
    var list = expertRows;
    var i = 0;
    if (list != null && list.length > 0) {
        do {
            i = Math.floor(Math.random() * (list.length));
        } while (expertIdArr.contains(i))
        //alert(rd+":"+i);
        expertIdArr[rd] = i;
        $("#expert-div-0" + rd).html('<center><a href="expert-details.html?id=' + list[i].id + '"><img style="height:300px;width:230px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
            '<div class="container expert-font"><div class="expert-font-div1">' +
            list[i].name + '</div><div class="expert-font-div2">' +
            '<div class="expert-font-div3">Advisor of NTECSTA</div>' +
            '<div class="expert-font-div4">湖北省中心顾问</div>' +
            '</div></div></center>');
    }
}

/**
 *加载专家
 * */
$(function () {
    var count = 8;
    $.ajax({
        type: 'POST',
        url: 'http://48y3pn.natappfree.cc/jsp/experts/getAllExpert.action',
        data: {
            num: count
        },
        dataType: 'json',
        success: function (data) {
            if (data.status === 200) {
                var list = data.rows;
                expertRows = data.erows;
                var len = list.length;
                len = (count > len) ? len : count;
                var htm = "";
                htm += '<div class="row expert-row">';
                for (var i = 0; i < len; i++) {
                    expertIdArr[i] = list[i].id;
                    if (i == count / 2) {
                        htm += '</div><div class="row expert-row">';
                    }
                    htm += '<div class="col-lg-3 col-md-4 col-sm-6 col-xs-12 expert-div" id="expert-div-0' + i + '">' +
                        '<center><a href="expert-details.html?id=' + list[i].id + '"><img style="height:300px;width:230px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
                        '<div class="container expert-font"><div class="expert-font-div1">' +
                        list[i].name + '</div><div class="expert-font-div2">' +
                        '<div class="expert-font-div3">Advisor of NTECSTA</div>' +
                        '<div class="expert-font-div4">湖北省中心顾问</div>' +
                        '</div></div></center></div>';
                }
                htm += "</div>";
                $("#index-expert-div").append(htm);
                self.setInterval("replaceExpert()", 5000);
            }
        },
        error: function () {
            //  layer.msg("获取专家失败");
        }
    });
})


function replaceUser() {

    var count = 6;
    var rd = Math.floor(Math.random() * count);
    var list = userRows;
    var i = 0;
    if (list != null && list.length > 0) {
        do {
            i = Math.floor(Math.random() * (list.length));
        } while (userIdArr.contains(i))
        //alert(rd+":"+i);
        userIdArr[rd] = i;
        $("#stu-div-0" + rd).html('<center><a href="student-details.html?id=' + list[i].id + '"><img class="img-thumbnail" style="height:320px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
            '<div class="stu-font"><p class="english">Quanlified Student</p><p>' +
            '认证特长生</p><p>' + list[i].name + '</p></div></center>');
    }
}

/**
 * 加载学生
 * */
$(function () {
    var count = 6;
    $.ajax({
        type: 'POST',
        url: 'http://48y3pn.natappfree.cc/jsp/users/getAllUser.action',
        data: {
            num: count
        },
        dataType: 'json',
        success: function (data) {
            if (data.status === 200) {
                var list = data.rows;
                userRows = data.urows;
                var len = list.length;
                len = (count > len) ? len : count;
                var html = '';
                html += '<div class="row stu-row">';
                for (var i = 0; i < len; i++) {
                    userIdArr[i] = list[i].id;
                    if (i == count / 2) {
                        html += '</div><div class="row stu-row">';
                    }
                    html += '<div class="col-lg-3 col-md-4 col-sm-6 col-xs-6 " id="stu-div-0' + i + '">' +
                        '<center><a href="student-details.html?id=' + list[i].id + '"><img class="img-thumbnail" style="height:320px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
                        '<div class="stu-font"><p class="english">Quanlified Student</p><p>' +
                        '认证特长生</p><p>' + list[i].name + '</p></div></center></div>';
                }
                html += '</div>';
                self.setInterval("replaceUser()", 5000);
                $("#index-student-div").append(html);
            }
        },
        error: function () {
            // layer.msg("获取学生失败");
        }
    });
})

function replaceTeacher() {
    var count = 6;
    var rd = Math.floor(Math.random() * count);
    var list = teacherRows;
    var i = 0;
    if (list != null && list.length > 0) {
        do {
            i = Math.floor(Math.random() * (list.length));
        } while (teacherIdArr.contains(i))
        teacherIdArr[rd] = i;
        $("#tea-div-0" + rd).html('<center><a href="teacher-details.html?id=' + list[i].id + '"><img class="img-thumbnail" style="height:320px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
            '<div class="t-font"><p class="english">Qualified Teacher</p><p>' +
            '认证教师</p><p>' + list[i].name + '</p></div></center>');
    }
}

/**
 * 加载老师
 * */
$(function () {
    var count = 6;
    $.ajax({
        type: 'POST',
        url: 'http://48y3pn.natappfree.cc/jsp/teachers/getAllTeacher.action',
        data: {
            num: count
        },
        dataType: 'json',
        success: function (data) {
            if (data.status === 200) {
                var list = data.rows;
                teacherRows = data.trows;
                var len = list.length;
                len = (count > len) ? len : count;
                var htm = '<div class="row t-row">';
                for (var i = 0; i < len; i++) {
                    teacherIdArr[i] = list[i].id;
                    if (i == count / 2) {
                        htm += '</div><div class="row t-row">';
                    }
                    htm += '<div class="col-lg-4" id="tea-div-0' + i + '">' +
                        '<center><a href="teacher-details.html?id=' + list[i].id + '"><img class="img-thumbnail" style="height:320px;" src="http://48y3pn.natappfree.cc' + path + list[i].image + '" alt=""></a>' +
                        '<div class="t-font"><p class="english">Qualified Teacher</p><p>' +
                        '认证教师</p><p>' + list[i].name + '</p></div></center></div>';
                }
                htm += "</div>"
                $("#index-teacher-div").append(htm);
                self.setInterval("replaceTeacher()", 5000);
            }
        },
        error: function () {
            //  layer.msg("获取老师失败");
        }
    });
})
/**
 * 加载机构
 * */
$(function () {
    var count = 6;
    $.ajax({
        type: 'POST',
        url: 'http://48y3pn.natappfree.cc/jsp/institutions/findAllInstitutionBasicInformation.action',
        data: {
            page: 1, limit: count
        },
        dataType: 'json',
        success: function (data) {
            if (data.status === 200) {
                var list = data.rows;
                console.log(list);
                var len = list.length;
                len = (count > len) ? len : count;
                var htm = '<div class="row t-row">';
                for (var i = 0; i < len; i++) {
                    if (i == count / 2) {
                        htm += '</div><div class="row t-row">';
                    }
                    htm += '<div class="col-lg-4 index-inst-div" style="overflow: hidden;"><center><div class="inst-div" style="position:relative"><img src="http://48y3pn.natappfree.cc' + path +
                        list[i].image + '" class="inst-img img-thumbnail" style="height:320px;"/><div class="rsp"></div>' +
                        '<div class="text"><a href="institution-details.html?id=' + list[i].id + '"><center class="contact">' +
                        '<h4>' + list[i].name + '</h4><p class="telephone">' + list[i].phone + '</p><p class="address">' +
                        list[i].addr + '</p></center></a></div></center></div>';
                }
                $("#index-institution-div").append(htm);
                $(".hovertreeaction .rsp").hide();
                setrsp();
            }
        },
        error: function () {
            //  layer.msg("获取机构失败");
        }
    });
})

function setrsp() {
    var tmp = $(".inst-img").css('width');
    //alert(tmp);
    var imgwidth = tmp + 10;
    $(".hovertreeaction  .index-inst-div").hover(function () {
        $(".inst-div").css('width', imgwidth);
        $(".rsp").css('width', imgwidth);
        $(this).find(".rsp").stop().fadeTo(imgwidth, 0.5)
        $(this).find(".text").stop().animate({left: '0'}, {duration: imgwidth})
    }, function () {
        $(".inst-div").css('width', imgwidth);
        $(".rsp").css('width', imgwidth);
        $(this).find(".rsp").stop().fadeTo(imgwidth, 0)
        $(this).find(".text").stop().animate({left: '' + imgwidth}, {duration: "fast"})
        $(this).find(".text").animate({left: '-' + imgwidth}, {duration: 0})
    });
}