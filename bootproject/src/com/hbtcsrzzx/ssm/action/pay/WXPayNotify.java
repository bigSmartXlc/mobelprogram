package com.hbtcsrzzx.ssm.action.pay;

import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import com.hbtcsrzzx.ssm.po.ExamineePayLog;
import com.hbtcsrzzx.ssm.po.UserAndExaminee;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.ssm.service.ExamineePayLogService;
import com.hbtcsrzzx.ssm.service.UserAndExamineeService;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.WebUtils;
import com.hbtcsrzzx.utils.XmlUtils;
import com.hbtcsrzzx.utils.weixin.WXPayUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@RequestMapping("/WXNotify")
@Controller
public class WXPayNotify {

    @Value("${wechat.pay.partnerkey}")
    private String partnerkey;

    @Autowired
    private EnrolExamineeService enrolExamineeService;

    @Autowired
    private UserLogService userLogService;


    @Autowired
    private UserAndExamineeService userAndExamineeService;


    @Autowired
    private ExamineePayLogService examineePayLogService;

    @RequestMapping("/findStatus")
    @ResponseBody
    public void findStatus(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String xml;
        try {
            xml = WebUtils.getBody(request.getInputStream());
            Map<String, String> notifyMap = XmlUtils.toMap(xml.getBytes("utf-8"), "utf-8");
            System.out.println("notifyMap" + notifyMap);

            // 签名认证
            if (WXPayUtil.isSignatureValid(xml, partnerkey)) {
//				.equals()
                if ("SUCCESS".equals(notifyMap.get("return_code"))) {

                    //订单号
                    String out_trade_no = notifyMap.get("out_trade_no");

                    EnrolExaminee enrolExaminee = enrolExamineeService.findEnrolExamineeByOrderId(Long.valueOf(out_trade_no));
                    //修改缴费字段
                    enrolExaminee.setPayStatus(Constants.PAY_COST);
                    //更新考生信息
                    enrolExamineeService.updateEnrolExaminee(enrolExaminee);

                    //更新支付日志
                    ExamineePayLog examineePayLog = examineePayLogService.findExamineePayLogByOrderId(Long.valueOf(out_trade_no));
                    examineePayLog.setPayTime(new Date());
                    examineePayLog.setPayType(Constants.PAY_TYPE_WX);
                    examineePayLog.setTradeState(Constants.TRADE_STATE_YES);
                    examineePayLogService.updateExamineePayLog(examineePayLog);
                    //创建用户考生基本信息
                    UserAndExaminee userAndExaminee = new UserAndExaminee();
                    userAndExaminee.setExamineeId(enrolExaminee.getId());
                    userAndExaminee.setState(Constants.NORMAL_STATE);
                    userAndExaminee.setCreateTime(new Date());
                    userAndExaminee.setUpdateTime(new Date());

                    //新增提成信息,增加用户id和考生id
                    UserLog userLog1 = userLogService.findUserLogByPhone(enrolExaminee.getUserLogPhone());
                    //查询推荐人不为空 并且推荐时间小于一个月的
                    if (StringUtils.isNotEmpty(userLog1.getRecommender()) && System.currentTimeMillis() <= (userLog1.getRecommendedTime().getTime() + Constants.MONTH)) {
                        //新增一级推荐用户
                        UserLog userLog2 = userLogService.findUserLogById(Integer.parseInt(userLog1.getRecommender()));
                        userAndExaminee.setUserId(userLog2.getId());
                        userAndExaminee.setRecommenderLevel(Constants.RECOMMENDATION_LEVEL_1);
                        userAndExamineeService.addUserAndExaminee(userAndExaminee);

                        if (StringUtils.isNotEmpty(userLog2.getRecommender()) && System.currentTimeMillis() <= (userLog2.getRecommendedTime().getTime() + Constants.MONTH)) {
                            //新增2级推荐用户
                            UserLog userLog3 = userLogService.findUserLogById(Integer.parseInt(userLog2.getRecommender()));
                            userAndExaminee.setUserId(userLog3.getId());
                            userAndExaminee.setRecommenderLevel(Constants.RECOMMENDATION_LEVEL_2);

                            userAndExamineeService.addUserAndExaminee(userAndExaminee);
                        }
                    }


                    System.out.println("回调地址修改考生信息了");
                    //告知微信,不用继续掉回调地址了
                    Map<String, String> result = new HashedMap();
                    result.put("return_code", notifyMap.get("return_code"));
                    result.put("return_msg", "OK");
                    String paramXml = XmlUtils.toXml(result);
                    System.out.println("paramXml:" + paramXml);
                    BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
                    out.write(paramXml.getBytes());
                    out.flush();
                    out.close();
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}
