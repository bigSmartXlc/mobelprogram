$(document).ready(function() {
    $(".mynav_a").click(function() {
	var arr = document.getElementsByClassName("mynav_a");
	for (var i = 0; i < arr.length; i++) {
	    var tmp = arr[i].getAttribute("data-toggle");
	    if (tmp !== null && tmp !== 'undefined' && tmp != this) {
		$(arr[i]).next().collapse('hide');
	    }
	}
    });
    // var height = $("#mytop").offset().top + $("#mytop").height();
    // if (height >= $("#myCarousel").height()) {
	// $("#mytop").css("background-color", "rgba(0,0,0,0.5)");
    // } else {
	// $("#mytop").css("background-color", "rgba(0,0,0,0)")
    // }
    // $(window).scroll(function() {
	// var height = $("#mytop").offset().top + $("#mytop").height();
	// if (height >= $("#myCarousel").height()) {
	//     $("#mytop").css("background-color", "rgba(0,0,0,0.5)");
	// } else {
	//     $("#mytop").css("background-color", "rgba(0,0,0,0)")
	// }
	
    // });
});

function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}


function login(){
    window.location.href="./login.html";
}  

/*$(function() {
    $.ajax({
	type : 'POST',
	url : '../menus/getMenuByLc.action',
	data : {
	    'location' : "首页菜单"
	},
	dataType : 'json',
	success : function(data) {
	    if (data.code === 200) {
		var list = data.rows;
		for(var key in list){
		    if(key === "顶级菜单"){
			$("")
		    }
		}
	    }
	},
	error:function() {
	    layer.msg("获取菜单失败");
	}
    });
})*/
