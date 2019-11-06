package com.hbtcsrzzx.ssm.task;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import com.hbtcsrzzx.ssm.po.ExamineePayLog;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.ssm.service.ExamineePayLogService;
import com.hbtcsrzzx.ssm.service.pay.WeixinPayService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.ExportSQLUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class EnrolExamineeTask {

    @Autowired
    private EnrolExamineeService enrolExamineeService;

    @Autowired
    private WeixinPayService weixinPayService;

    @Autowired
    private ExamineePayLogService examineePayLogService;

    /**
     * 定时查询考生是否支付
     */
    @Scheduled(cron = "0 * * * * *")
    public void checkExamineePay() {

        long dateTime = System.currentTimeMillis() - (30 * 60 * 1000);

        //查询超时，未支付的考生
        List<EnrolExaminee> enrolExaminees = enrolExamineeService.findEnrolExamineeByTimeout(Constants.NOT_PAY_COST, new Date(dateTime));

        for (EnrolExaminee enrolExaminee : enrolExaminees) {

            //查询支付状态
            Map<String, String> map = weixinPayService.queryPayStatus(enrolExaminee.getOrderId() + "");

            if (map == null) {//查不到该考生的订单
                enrolExaminee.setState(Constants.DEL_STATE);
                enrolExamineeService.updateEnrolExaminee(enrolExaminee);
                continue;
            }
            if ("SUCCESS".equals(map.get("trade_state"))) {//该考生已支付
                enrolExaminee.setPayStatus(Constants.PAY_SUCCESS);
                enrolExamineeService.updateEnrolExaminee(enrolExaminee);
                continue;
            }

            // 未支付，调用微信的关闭订单接口
            Map<String, String> payresult = weixinPayService.closePay(enrolExaminee.getOrderId() + "");
            //如果关闭订单失败
            if (!"SUCCESS".equals(payresult.get("return_code"))) {
                // 如果返回结果是订单已支付，不能发起关单
                if ("ORDERPAID".equals(payresult.get("err_code"))) {
                    // 修改用户状态为支付
                    enrolExaminee.setPayStatus(Constants.PAY_SUCCESS);
                    enrolExamineeService.updateEnrolExaminee(enrolExaminee);
                }

            }

            //用户未支付
            if (!"SUCCESS".equals(map.get("trade_state"))) {
                //将考生的状态置为 删除状态
                enrolExaminee.setState(Constants.DEL_STATE);
                enrolExamineeService.updateEnrolExaminee(enrolExaminee);
                //将支付日志设置为订单超时
                ExamineePayLog examineePayLog = examineePayLogService.findExamineePayLogByOrderId(enrolExaminee.getOrderId());
                examineePayLog.setTradeState(Constants.TRADE_STATE_TIMEOUT);
                examineePayLogService.updateExamineePayLog(examineePayLog);

            }

        }

    }


}
