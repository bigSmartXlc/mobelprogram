package com.hbtcsrzzx.ssm.service.pay.impl;

import com.hbtcsrzzx.ssm.service.pay.WeixinPayService;
import com.hbtcsrzzx.utils.HttpClient;
import com.hbtcsrzzx.utils.weixin.WXPayUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@SuppressWarnings("all")
public class WeixinPayServiceImpl implements WeixinPayService {

	@Value("${wechat.pay.appid}")
	private String appid;

	@Value("${wechat.pay.partner}")
	private String partner;

	@Value("${wechat.pay.partnerkey}")
	private String partnerkey;

	@Value("${wechat.pay.notifyurl}")
	private String notifyurl;

	@Override
	public Map createNative(String out_trade_no, String total_fee) {

		// 向微信发送的参数
		Map<String, String> param = new HashMap();

		param.put("appid", appid);// 公众账号ID
		param.put("mch_id", partner);// 商户号
		param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串
		param.put("body", "报考费用");// 商品描述
		param.put("out_trade_no", out_trade_no);// 商户订单号
		param.put("total_fee", total_fee);// 标价金额
		param.put("spbill_create_ip", "127.0.0.1");// 终端IP
		param.put("notify_url", notifyurl);// 通知地址
		param.put("trade_type", "NATIVE");// 交易类型

		try {
			System.out.println("partnerkey:" + partnerkey);
			// 将map集合转换成xml发送
			String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);

			HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/unifiedorder");
			client.setHttps(true);// 是否是https协议
			client.setXmlParam(xmlParam);// 是否是https协议
			client.post();// 执行post请求
			String xmlResult = client.getContent(); // 获取结果
			System.out.println("获取到的结果:" + xmlResult);

			// 将结果集转换成map
			Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);

			// 返回客户端的信息
			Map<String, String> map = new HashMap<String, String>();
			map.put("code_url", mapResult.get("code_url"));// 支付地址
			map.put("total_fee", total_fee);// 总金额
			map.put("out_trade_no", out_trade_no);// 订单号

			return map;

		} catch (Exception e) {

			e.printStackTrace();
			return new HashMap<>();
		}

	}

	@Override
	public Map<String, String> queryPayStatus(String out_trade_no) {
		// 向微信发送的参数
		Map<String, String> param = new HashMap();
		param.put("appid", appid);// 公众账号ID
		param.put("mch_id", partner);// 商户号
		param.put("out_trade_no", out_trade_no);// 商户订单号
		param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串

		try {
			// 将map集合转换成xml发送,这里会自动生成签名
			String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);

			HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/orderquery");
			client.setHttps(true);// 是否是https协议
			client.setXmlParam(xmlParam);// 是否是https协议
			client.post();// 执行post请求
			String xmlResult = client.getContent(); // 获取结果
			System.out.println("获取到的结果:" + xmlResult);

			// 将结果集转换成map
			Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);

			// Map map = new HashMap();
			// map.put("result_code", mapResult.get("mapResult"));

			return mapResult;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public Map closePay(String out_trade_no) {
		// 向微信发送的参数
		Map<String, String> param = new HashMap();
		param.put("appid", appid);// 公众账号ID
		param.put("mch_id", partner);// 商户号
		param.put("out_trade_no", out_trade_no);// 商户订单号
		param.put("nonce_str", WXPayUtil.generateNonceStr());// 随机字符串

		// 将map集合转换成xml发送,这里会自动生成签名
		try {
			String xmlParam = WXPayUtil.generateSignedXml(param, partnerkey);
			HttpClient client = new HttpClient("https://api.mch.weixin.qq.com/pay/closeorder");
			client.setHttps(true);// 是否是https协议
			client.setXmlParam(xmlParam);// 是否是https协议
			client.post();// 执行post请求
			String xmlResult = client.getContent(); // 获取结果
			System.out.println("获取到的结果:" + xmlResult);
			// 将结果集转换成map
			Map<String, String> mapResult = WXPayUtil.xmlToMap(xmlResult);

			// Map map = new HashMap();
			// map.put("result_code", mapResult.get("mapResult"));

			return mapResult;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}