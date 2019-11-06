package com.hbtcsrzzx.utils;

import java.io.IOException;

import org.json.JSONException;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

public class SmsUtils {

	// 短信应用 SDK AppID
	static final int appid = 1400209539;
	// 短信应用 SDK AppKey
	static final String appkey = "bbcdb01295332dbf7b054b7bf719b70e";

	// 短信模板 ID，需要在短信应用中申请
	static final int templateId = 334085;
	// 签名
	static final String smsSign = "湖北省艺术特长生测评认证";

	/**
	 * @param params
	 *            短信的内容
	 * @param phoneNumbers
	 *            手机号
	 */
	public static SmsSingleSenderResult sendSms(String[] params, String[] phoneNumbers) {
		SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
		try {
			SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0], templateId, params, smsSign, "",
					""); // 签名参数未提供或者为空时，会使用默认签名发送短信

			return result;
		} catch (JSONException e) {
			// HTTP 响应码错误
			e.printStackTrace();
		} catch (HTTPException e) {
			// JSON 解析错误
			e.printStackTrace();
		} catch (IOException e) {
			// 网络 IO 错误
			e.printStackTrace();
		}
		return null;
	}

}
