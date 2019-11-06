package com.hbtcsrzzx.ssm.action.api;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.qcloudsms.SmsSingleSenderResult;
import com.hbtcsrzzx.ssm.po.Phone;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.utils.JedisPoolUtils;
import com.hbtcsrzzx.utils.PhoneFormatCheckUtils;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.utils.SmsUtils;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/thirdParty")
public class VerificationCodeAction {

	@RequestMapping(value = "/sendSms")
	@ResponseBody
	public Result sendSms(HttpServletRequest request,String phone,HttpServletResponse response) {
		//跨域数据传输
		/*response.setHeader("Access-Control-Allow-Methods", "OPTIONS,POST,GET");
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		//String phone = phones.getPhone();
		// 判断手机号格式
		if (!PhoneFormatCheckUtils.isChinaPhoneLegal(phone)) {
			return new Result(-1, "手机号格式有误");
		}

		String[] phoneNumbers = { phone };

		// 生成随机数
		String code = (int) (Math.random() * 10000) + "";
		String[] params = { code, "2" };
		SmsSingleSenderResult result = SmsUtils.sendSms(params, phoneNumbers);

		if (result != null && result.result == 0) {

			Jedis jedis = JedisPoolUtils.getJedis();
			jedis.hset(phone, "code", code);
			jedis.expire(phone, 2 * 60);
			jedis.close();
			return new Result(200, "短信发送成功");
		}

		return new Result(-1, "短信发送失败");
	}
}
