$(function(){
   //导航栏高度
  //  $(".silder").css("height",$(".main").css("height"));
   //获取个人信息
   personalinfo()
   //提成信息
   page(1,3);
   //考试信息
  //  showPage(1,2);
   //获取审核状态
   getteststatus()
   //获取可提现金额
   axios.defaults.withCredentials = true;
    axios.post("http://48y3pn.natappfree.cc/user/getCashSum.action").then(res=>{
            if(res.data.obj){
              $("#money").html(parseInt(res.data.obj)/100+'元');
            }else{
              $("#money").html(0+'元');
            }
  });
  // //请求个人资料
  function personalinfo(){
    axios.defaults.withCredentials = true;
    axios.post("http://48y3pn.natappfree.cc/user/getUserLog.action").then(res1=>{
        // console.log(res1);
        $("#userinfo #u_phone").html(res1.data.phone);
        $("#userinfo #u_name").html(res1.data.name);
        if(res1.data.gender==0){
          $("#userinfo #u_gender").html("女");
        }else{
          $("#userinfo #u_gender").html("男");
        }
        $("#userinfo #u_nationality").html(res1.data.nationality);
        $("#userinfo #u_nation").html(res1.data.nation);
        $("#userinfo #u_birthday").html(res1.data.birthday);
        $("#userinfo #u_idCard").html(res1.data.idCard);
        $("#userinfo #u_unit").html(res1.data.unit);
        $("#userinfo #u_parentName").html(res1.data.parentName);
        $("#userinfo #u_familyAddress").html(res1.data.familyAddress);
        $("#userinfo #u_mailbox").html(res1.data.mailbox);
        $("#userinfo #u_familyPhone").html(res1.data.familyPhone);
        $("#userinfo #u_city").html(res1.data.city.cityName);
        $("#userinfo #u_ownRecommendationCode").html(res1.data.ownRecommendationCode);
        })
  }
    //修改个人信息跳转
    $("#changegrxx").click(function(){
      layui.use('form', function(){
        var form = layui.form; 
        form.render();
        })
      $('.main_cont>li:last-child').show().siblings('li').hide();
    })
     
  //获取城市列表
  axios.defaults.withCredentials = true;
  axios.get("http://48y3pn.natappfree.cc/user/getCityAll.action").then(res=>{
  // console.log(res,$("#city_list"));
      var sub_arr=res.data.obj;
      var html="";
      for(var item of sub_arr){
          html+=`<option data-id=${item.id} value="${item.name}">${item.name}</option>`
      }
      $("#city_list").append(html);
      layui.use('form',function(){
      var form=layui.form;
      form.render("select");
})                
    })   
    //修改个人信息
    $("#amendment").click(function(){
      var user_name=username.value;
      var user_gender=$("#usergender option:selected").data("gender");;
      var user_country=usercountry.value;
      var user_family=userfamily.value;
      var user_city=$("#city_list option:selected").data("id");
      var user_birth=userbirth.value;
      var user_id=userid.value;
      var user_company=usercompany.value;
      var user_parents=userparents.value;
      var user_address=useraddress.value;
      var user_email=useremail.value;
      var family_tel=familytel.value;
      var dataStr = 'a=1';
      if(user_name){
        dataStr+='&name='+user_name
      }
      if(user_gender){
        dataStr+='&gender='+user_gender
      }
      if(user_country){
        dataStr+='&nationality='+user_country
      }
      if(user_family){
        dataStr+='&nation='+user_family
      }
      if(user_city){
        dataStr+='&cityId='+user_city
      }
      if(user_birth){
        dataStr+='&birthday='+user_birth
      }
      if(user_id){
        dataStr+='&idCard='+user_id
      }
      if(user_company){
        dataStr+='&unit='+user_company
      }
      if(user_parents){
        dataStr+='&parentName='+user_parents
      }
      if(user_address){
        dataStr+='&familyAddress='+user_address
      }
      if(user_email){
        dataStr+='&mailbox='+user_email
      }
      if(family_tel){
        dataStr+='&familyPhone='+family_tel
      }
      axios.defaults.withCredentials = true;
      axios.post("http://48y3pn.natappfree.cc/user/updateUserLog.action",dataStr).then(res=>{
        console.log(res);
        if(res.data.code==200){
          layer.msg('修改成功！', {icon: 6});
          personalinfo();
          $('.main_cont>li:first-child').show().siblings('li').hide();
        }else{
          layer.msg('修改失败！', {icon:5});
        }
    });
    })
      
  //点击列表切换右侧内容
    function s_menu(){
    $('.slide_menu>li').click(function(){
        var iVal = $(this).index();
        $('.main_cont>li').eq(iVal).show().siblings('li').hide();
    });
    }
     s_menu();
   
   
     //提现申请
    $("#tx_btn").click(function(){
      axios.defaults.withCredentials = true;
          axios.post("http://48y3pn.natappfree.cc/user/updatePresentationInformation.action").then(res=>{
              console.log(res);
              if(res.data.code==200){
                layer.msg('提现申请成功！', {icon: 6});
              }else{
                layer.msg('提现申请失败！', {icon: 5});
              }
          })
        
    })
   
  
    //请求用户提成信息
        var i;//当前页
        var pagetotal;//总页数
        //分页
        function page(pageto,status){
        i=pageto;
        axios.defaults.withCredentials = true;
        if(status!=0&&status!=1&&status!=2){
          var json1=`page=${pageto}&limit=2`
        }else{
          var json1=`page=${pageto}&limit=2&takeMoneyStatus=${status}`
        }
        axios.post("http://48y3pn.natappfree.cc/user/getPresentationInformationAllByUserLogId.action",json1).then(res=>{
            var newlistall=[];//获取数据
            // console.log(res);
            var newlist=res.data.obj.list;
            for(var item of newlist){
                newlistall.push(item);
            }
            // console.log(newlistall);
            pagetotal=res.data.obj.pages;
            var html='';
            for(var item of newlistall){
              if(item.takeMoneyStatus==0){item.takeMoneyStatus="未提现"}else if(item.takeMoneyStatus==1){item.takeMoneyStatus="提现中"}else if(item.takeMoneyStatus==2){item.takeMoneyStatus="已体现"}
                html+=`<tr id="info">
                            <td>${item.id}</td>
                            <td>${item.examineeName}</td>
                            <td>${item.evaluationContents}</td>
                            <td>${item.royaltyLevel}</td>
                            <td>${item.royaltyRatio}</td>
                            <td>${item.registrationCost}</td>
                            <td>${item.royaltyCost}</td>
                            <td>${item.takeMoneyStatus}</td>
                        </tr>`
            } 
            $("#pagelist").children().remove(); 
            // console.log(html);
            $("#pagelist").append(html);
            //判断是否有上一页
            var content='';
            if(i<=1){
                content+=`<li><a href="javascript:;">上一页</a></li>`
            }else{
                content+=`<li data-page=${i-1}><a href="javascript:;">上一页</a></li>`
            }
            content+=`<li><span>第<span>${i}</span>页/共<span>${pagetotal}</span>页</span></li>`
            //判断是否有下一页
            if(i>=pagetotal){
                content+=`<li><a href="javascript:;">下一页</a></li>`
            }else{
                content+=`<li data-page=${i+1}><a href="javascript:;">下一页</a></li>`
            }
            $("#page-turn").children().remove();
            $("#page-turn").append(content);       
        })
        }
        
        //事件绑定
        $("#page-turn").on("click","li",function(){
            if(tx_status.value=="未提现"){txstatus="0"}else if(tx_status.value=="提现中"){txstatus="1"}else if(tx_status.value=="已提现"){txstatus="2"}else if(tx_status.value=="全部"){txstatus=""};
            var numto=$(this).data("page");
            if(numto){
            page(numto,txstatus);
            }
        })

        //按提现状态搜索
        $("#search").click(function(){
        // 获取当前状态码
        var txstatus;
        if(tx_status.value=="未提现"){txstatus="0"}else if(tx_status.value=="提现中"){txstatus="1"}else if(tx_status.value=="已提现"){txstatus="2"}else if(tx_status.value=="全部"){txstatus=""};
                page(1,txstatus);
            })
    
    //获取审核状态
      function getteststatus(){
        axios.defaults.withCredentials = true;
        axios.post("http://48y3pn.natappfree.cc/user/isAuditStatus.action").then(res=>{
          // console.log(res);
          if(res.data.code==200){
            $("#teststatus").html("请填写真实信息，以方便审核通过");  
            $("#submit").removeClass("layui-btn-disabled");
            $("#jgsh").parent().css("display","block")
          }else if(res.data.code==-1){
            var teststatus;
            if(res.data.obj==0){teststatus="未审核"}else if(res.data.obj==1){teststatus="审核中"}else if(res.data.obj==2){teststatus="审核通过"}else if(res.data.obj==3){teststatus="审核未通过"}
              $("#teststatus span").html(teststatus);
              $("#submit").addClass("layui-btn-disabled");
              $("#jgsh").parent().css("display","none")
          }
        })
      }
   
  //   //机构审核
  layui.use('form', function(){
    var form = layui.form; 
    //只有执行了这一步，部分表单元素才会自动修饰成功
    //但是，如果你的HTML是动态生成的，自动渲染就会失效
    //因此你需要在相应的地方，执行下述方法来手动渲染，跟这类似的还有 element.init();
    form.render();

    form.on('submit(formDemo)', function(data){
       // console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
        //console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
        //console.log(data.field,"数据") //当前容器的全部表单字段，名值对形式：{name: value}
        console.log(data.field);
        var datainfo=JSON.stringify(data.field);
        console.log(datainfo);
        if($("#submit").hasClass("disabled")){
            layer.msg('当前状态不需要重新提交', {icon: 5});
        }else{
                axios.defaults.withCredentials = true;
                axios.post("http://48y3pn.natappfree.cc/user/applySubCertification.action",datainfo).then(res=>{
                  console.log(res,"结果");
                    if(res.data.code==200){
                        layer.msg('审核请求已发送', {icon:6});
                        $("#submit").addClass("layui-btn-disabled");
                    }
                })
        }
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
        });

      //发送短信验证码
      $("#person_num").click(function(){
            var i=60;
            $("#person_num").addClass("layui-btn-disabled")
            var timer=setInterval(function(){
                $("#person_num").html(i+'s')
                i--;     
                        },1000)
            setTimeout(function(){
                clearInterval(timer);
                $("#person_num").removeClass("layui-btn-disabled")
                $("#person_num").html("发送")
            },61000)
            var phone=$("input[name='personInChargePhone']").val();
            axios.defaults.withCredentials = true;
            axios.post("http://48y3pn.natappfree.cc/thirdParty/sendSms.action",`phone=${phone}`).then(res=>{
            console.log(res);
            if(res.data.code==200){
                layer.msg('短信发送成功！', {icon: 6});
            }else{
                layer.msg('短信发送失败！', {icon: 5});
            }
            })
        })
      //短信验证  
      $("input[name='testcode']").blur(function(){
        axios.defaults.withCredentials = true;
            axios.post("http://48y3pn.natappfree.cc/login/isSmsCode.action",`code=${$("input[name='testcode']").val()}&phone=${$("input[name='personInChargePhone']").val()}`).then(res=>{
                console.log(res);
                if(res.data.code==200){
                    layer.msg('短信验证成功！', {icon: 6});
                }else{
                    layer.msg('短信验证失败！', {icon: 5});
                    $("input[name='testcode']").val("");
                }
            })
        })
     //预览身份证照片
        function uploadfile1() {
        var reads = new FileReader();
        var file = document.getElementById('Picup').files[0];
        reads.readAsDataURL(file);
        reads.onload = function (e) {
            $("#id-up").attr("src",this.result) ;
        };
        }
        function uploadfile2() {
            var reads = new FileReader();
            var file = document.getElementById('Picdown').files[0];
            reads.readAsDataURL(file);
            reads.onload = function (e) {
                $("#id-down").attr("src",this.result) ;
            };
        }
    //上传图片
        function upload(img_name) {
            var ii = layer.load(2);
            let file = document.getElementById(img_name).files[0];
            let formData = new FormData();
            formData.append("file",file);
            const config = {
            headers: { "Content-Type": "multipart/form-data;boundary="+new Date().getTime() }
            };
            axios.defaults.withCredentials = true;
            axios.post("http://48y3pn.natappfree.cc/jsp/files/imgupload.action",formData,config)
            .then(res=>{
                if(res.data.code==200){
                    layer.close(ii);
                        if(img_name=="Picup"){
                            $("input[name='legalPersonIdImgJust']").val(res.data.data);
                            layer.msg('图片上传成功！', {icon: 6});
                        }else if(img_name=="Picdown"){
                            $("input[name='legalPersonIdImgBack']").val(res.data.data);
                            layer.msg('图片上传成功！', {icon: 6});
                        }
                }
            })
        }
        
        //监听图片加载
        $("#Picup").change(function(){
            uploadfile1();
              upload("Picup")
        })
        $("#Picdown").change(function(){
            uploadfile2();
              upload("Picdown");
        })
    })

  //   $("#submit").click(function(){
  //     if($("#submit").hasClass("disabled")){
  //       layer.msg('当前状态不需要重新提交', {icon: 5});
  //     }else{
  //       if($("input[name='testcode']").val()){
  //         var legalPerson=$("input[name='legalPerson']").val();
  //         var legalPersonPhone=$("input[name='legalPersonPhone']").val();
  //         var legalPersonId=$("input[name='legalPersonId']").val();
  //         var legalPersonIdImgJust=$("input[name='legalPersonIdImgJust']").data("url");
  //         var legalPersonIdImgBack=$("input[name='legalPersonIdImgBack']").data("url");
  //         var subName=$("input[name='subName']").val();
  //         var unifiedCreditCode=$("input[name='unifiedCreditCode']").val();
  //         var personInCharge=$("input[name='personInCharge']").val();
  //         var personInChargePhone=$("input[name='personInChargePhone']").val();
  //         var personInChargeId=$("input[name='personInChargeId']").val();
  //         var jsondata={legalPerson,legalPersonPhone,legalPersonId,legalPersonIdImgJust,legalPersonIdImgBack,subName,unifiedCreditCode,personInCharge,personInChargePhone,personInChargeId};
  //         axios.defaults.withCredentials = true;
  //         axios.post("http://48y3pn.natappfree.cc/user/applySubCertification.action",jsondata).then(res=>{
  //             //  console.log(res);
  //             if(res.data.code==200){
  //               layer.msg('审核请求已发送', {icon:6});
  //               $("#submit").addClass("disabled");
  //             }
  //          })
  //       }else{
  //         layer.msg('手机号验证未成功！请重试', {icon: 5});
  //         $("input[name='testcode']").val("");
  //       }
  //     }
  //   })
  //   //机构负责人手机号码验证
  //   //发送短信
  //   $("#person_num").click(function(){
  //       var phone=$("input[name='personInChargePhone']").val();
  // axios.defaults.withCredentials = true;
  //       axios.post("http://48y3pn.natappfree.cc/thirdParty/sendSms.action",`phone=${phone}`).then(res=>{
  //         console.log(res);
  //         if(res.data.code==200){
  //           layer.msg('短信发送成功！', {icon: 6});
  //       }else{
  //           layer.msg('短信发送失败！', {icon: 5});
  //       }
  //       })
  //   })
  //   //验证号码
  //   $("input[name='testcode']").blur(function(){
    // axios.defaults.withCredentials = true;
  //     axios.post("http://48y3pn.natappfree.cc/login/isSmsCode.action",`code=${$("input[name='testcode']").val()}&phone=${$("input[name='personInChargePhone']").val()}`).then(res=>{
  //         console.log(res);
  //         if(res.data.code==200){
  //             layer.msg('短信验证成功！', {icon: 6});
  //         }else{
  //             layer.msg('短信验证失败！', {icon: 5});
  //             $("input[name='testcode']").val("");
  //         }
  //     })
  // })
    
  
  // //审核电话号码格式
  //           $(".phonenumber").on("blur",function(){
  //             var legalphone=$(this).val();
  //             var test_phone=/^1[3-8]\d{9}$/i;
  //             if(test_phone.test(legalphone)==false){
  //                 layer.msg('手机号码格式错误！！！请重新输入', {icon:5})
  //                     $(this).val("");
  //             }
  //             event.stopPropagation();
  //         })
    
    
  //   //审核身份证号码格式
  //         $(".legalidcard").on("blur",function(){
  //             var p = /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/;
  //             var userID=$(this).val();
  //             if(p.test(userID)==false){
  //                 layer.msg('身份证号码格式错误！！！请重新输入', {icon: 5});
  //                     $(this).val("");
  //             }
  //             event.stopPropagation();
  //         })    
   
   
  //   //上传图片
  //   function upload(img_name) {
  //       var ii = layer.load(2);
  //       let file = document.getElementById(img_name).files[0];
  //       let formData = new FormData();
  //       formData.append("file",file);
  //       const config = {
  //       headers: { "Content-Type": "multipart/form-data;boundary="+new Date().getTime() }
  //       };
  //       axios.defaults.withCredentials = true;
  //       axios.post("http://48y3pn.natappfree.cc/jsp/files/imgupload.action",formData,config)
  //       .then(res=>{
  //           $(`#${img_name}`).attr("data-url",res.data.data);
  //           layer.close(ii);
  //           console.log($(`#${img_name}`).data("url"));
  //       })
  //      }
    
    
  //   //预览身份证照片
  //   function uploadfile1() {
  //     var reads = new FileReader();
  //     var file = document.getElementById('Picup').files[0];
  //     reads.readAsDataURL(file);
  //     reads.onload = function (e) {
  //         $("#id-up").attr("src",this.result) ;
  //     };
  //     }
  //     function uploadfile2() {
  //       var reads = new FileReader();
  //       var file = document.getElementById('Picdown').files[0];
  //       reads.readAsDataURL(file);
  //       reads.onload = function (e) {
  //           $("#id-down").attr("src",this.result) ;
  //       };
  //     }
   
      
      
  //     //监听图片加载
  //     $("#Picup").change(function(){
  //         uploadfile1();
  //         upload("Picup")
  //     })
  //     $("#Picdown").change(function(){
  //         uploadfile2();
  //         upload("Picdown");
  //     })

  //     //生成推荐二维码
  // axios.defaults.withCredentials = true;
  //   axios.post("").then(res=>{
  //     console.log(res);
  //     var data=res.data.referralCode;
  //     jQuery('#qrcodeCanvas').qrcode({
  //         render: "canvas",
  //         text: "http://www.hbtcsrzzx.com:8088/reg.html?referralCode="+data,
  //         width: "200",               //二维码的宽度
  //         height: "200",              //二维码的高度
  //         background: "#ffffff",      //二维码的后景色
  //         foreground: "#000000",      //二维码的前景色
  //         src: $("#preview").attr('src')//二维码中间的图片
  //     });
  // })




  //获取报名分类列表
  axios.defaults.withCredentials = true;
  axios.post("http://48y3pn.natappfree.cc//user/getCategoryAll.action").then(res=>{
        var sub_arr=res.data.obj;
        var html="";
        for(var item of sub_arr){
            html+=`<option value="${item.name}">${item.name}</option>`
        }
        $("select[name='category']").append(html);
        layui.use('form',function(){
        var form=layui.form;
        form.render("select");
  })                
      })   
  //渲染测评信息
      cpxx();
      $("#search_btn").click(function(){
          cpxx();
      })
      function cpxx(){
        var evaluationDate=$("input[name='evaluationDate']").val();
        var category=$("select[name='category']").val();
        var subject=$("input[name='subject']").val();
        var cityNameOrregion=$("input[name='cityNameOrregion']").val();
        var dataStr="";
        if(evaluationDate){
          dataStr+='&evaluationDate='+evaluationDate
        }
        if(category){
          dataStr+='&category='+category
        }
        if(subject){
          dataStr+='&subject='+subject
        }
        if(cityNameOrregion){
          dataStr+='&cityNameOrregion='+cityNameOrregion
        }
          axios.post("http://48y3pn.natappfree.cc/user/scene/getSceneAll.action",dataStr).then(res=>{
            // console.log(res);
            if(res.data.code==200){
              var data1=res.data.obj.list
              uploadtable(data1)
          }
        })
      };
          function uploadtable(data2){
            layui.use('table', function(){
                  var table = layui.table;
                  //展示已知数据
                  table.render({
                    elem: '#demo'
                    ,cols: [[ //标题栏
                      {field: 'id', title: '考场ID', width:90, sort: true, fixed: 'left'}
                      ,{field: 'evaluationAddress', title: '考试地点', width:90}
                      ,{field: 'category', title: '分类', width:90}
                      ,{field: 'subject', title: '科目', width:210} 
                      ,{field: 'evaluationDate', title: '考试日期', width:110, sort: true}
                      ,{field: 'evaluationTime', title: '考试时间', width: 160}
                      ,{field: 'sceneName', title: '考场名称', width:105, sort: true}
                      ,{field: 'cityName', title: '考试城市', width:105}
                      ,{field: 'region', title: '考试区域', width: 105, sort: true}
                    ]]
                    ,data:data2
                    ,even: true
                    ,page: true //是否显示分页
                  });
            });
          }
  })