package com.hbtcsrzzx.ssm.action.pay;

import com.hbtcsrzzx.ssm.action.web.UserWeb;
import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.ssm.service.pay.WeixinPayService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.PhoneFormatCheckUtils;
import com.hbtcsrzzx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class ExamineePay {

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private EnrolExamineeService examineeService;


    @Autowired
    private UserWeb userWeb;

    /**
     * 根据考生id,查询考生报考费用,生成订单
     *
     * @param enrolExamineeId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/createNative")
    public Result registrationFee(Integer enrolExamineeId, HttpServletRequest request) {

        try {


            EnrolExaminee examinee = examineeService.findEnrolExamineeById(enrolExamineeId);
            Long orderId = examinee.getOrderId();
            if (Constants.PAY_COST == examinee.getPayStatus()) {
                return new Result(-1, "考生已缴费");
            }

            Map map = weixinPayService.createNative(orderId + "", examinee.getCost() + "");

            if(map.get("code_url") == null){
                return  new Result(-1,"订单已支付,30分钟之内考生状态会修改");
            }
            return new Result(200, "创建二维码成功", map);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(-1, "创建二维码失败");
    }

    /**
     * 获取登录人手机号
     *
     * @param request
     * @return
     */
    private String getUserPhone(HttpServletRequest request) {
        // 取出当前登录人信息
        String token = userWeb.findCookieToken(request);
        UserLog userLog = userWeb.findUserLogRedis(token);
        if (!PhoneFormatCheckUtils.isChinaPhoneLegal(userLog.getPhone())) {
            throw new RuntimeException("手机号格式有误");
        }
        return userLog.getPhone();
    }

    /**
     * 查询支付状态
     *
     * @param out_trade_no
     * @return
     */
    @RequestMapping("/queryPayStatus")
    @ResponseBody
    public Result queryPayStatus(String out_trade_no) {

        Result result = null;
        EnrolExaminee enrolExaminee = new EnrolExaminee();
        enrolExaminee.setId(Integer.valueOf(out_trade_no));
        int x = 0;
        // 循环读取订单状态
        while (true) {

            Map<String, String> map = weixinPayService.queryPayStatus(out_trade_no);
            if (map == null) {
                result = new Result(-1, "支付出错,没有找到该订单");
                break;
            }
            // .equals()
            if ("SUCCESS".equals(map.get("trade_state"))) {
                result = new Result(200, "支付成功");
                enrolExaminee.setPayStatus(Constants.PAY_SUCCESS);
                examineeService.updateEnrolExaminee(enrolExaminee);
                System.out.println("查询订单接口修改考生信息了");
                break;

            }
            // 间隔时间,轮询读取订单状态
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
            x++;
            if (x > 6) {

                // 1.调用微信的关闭订单接口
                Map<String, String> payresult = weixinPayService.closePay(out_trade_no);

                if (!"SUCCESS".equals(payresult.get("return_code"))) {
                    // 如果返回结果是正常关闭
                    if ("ORDERPAID".equals(payresult.get("err_code"))) {
                        // 表示用户已支付,但是订单已关闭
                        result = new Result(200, "支付成功");
                        System.out.println("用户已支付,但是订单已关闭");
                        // 修改用户状态
                        enrolExaminee.setPayStatus(Constants.PAY_SUCCESS);
                        examineeService.updateEnrolExaminee(enrolExaminee);
                    }

                }

                //用户未支付
                //.equals()
                if (!"SUCCESS".equals(map.get("trade_state"))) {


                    result = new Result(-1, "订单已关闭");
                    System.out.println("订单已关闭");
                    //将考生的状态置为 删除状态
                    enrolExaminee.setState(Constants.DEL_STATE);
                    examineeService.updateEnrolExaminee(enrolExaminee);
                }


                break;
            }

            System.out.println("轮询订单次数:" + x);
        }

        return result;

    }


}
