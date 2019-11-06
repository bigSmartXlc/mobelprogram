$(function(){

    $.ajax({
        url:"module/carousel.html",
        type:"get",
        success:function(result){
        // console.log(result);
        $(result).replaceAll("#carousel");
        }
    });
})