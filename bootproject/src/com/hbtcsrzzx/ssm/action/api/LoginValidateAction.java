package com.hbtcsrzzx.ssm.action.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.utils.CookieUtil;
import com.hbtcsrzzx.utils.CusAccessObjectUtil;
import com.hbtcsrzzx.utils.JedisPoolUtils;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.utils.UUIDUtil;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/login")
public class LoginValidateAction {

	@Autowired
	UserLogService userLogService;

	/**
	 * 根据cookie中的token，判断用户是否已经登录
	 * 
	 * @param request
	 * @return
	 **/

	@RequestMapping("/getLoginUser")
	@ResponseBody
	public Result getLoginUser(HttpServletRequest request, HttpServletResponse response) {

		// 跨域数据传输
		/*response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		try {
			// 获取cookie中token
			String token = CookieUtil.getCookieValue(request, "loginToken", "utf-8");
			if (token != null && token != "") {
				// 从redis中获取用户信息
				UserLog userLog = findUserLogRedis(token);
				// 查询最新的用户数据
				userLog = userLogService.findUserLogByPhone(userLog.getPhone());

				return new Result(200,"用户已登陆",userLog);
			}

			// 根据获取的结果，返回结果
		} catch (Exception e) {

			e.printStackTrace();
			return Result.error("登录出错");

		}
		return Result.error("用户未登录");
	}

	@RequestMapping("/userLogin")
	@ResponseBody
	public Result userLogin(String phone, String password, HttpServletRequest request, HttpServletResponse response) {

		// 跨域数据传输
		/*response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		/*
		 * String phone = userLogs.getPhone(); String password =
		 * userLogs.getPassword();
		 */
		try {
			boolean flag = userLogService.getIsUserLog(phone, password);
			if (flag) {
				String ipAddress = CusAccessObjectUtil.getIpAddress(request);
				// 插入登录日志
				userLogService.addUserLogRecord(ipAddress, phone);
				String token = UUIDUtil.getUUID2();
				// 并向此客户端发送cookie
				CookieUtil.setCookie(request, response, "loginToken", token, 60 * 30, "utf-8");
				// 将用户信息放入缓存
				Jedis jedis = JedisPoolUtils.getJedis();
				UserLog userLog = userLogService.findUserLogByPhone(phone);
				String userLogJsonStr = JSON.toJSONString(userLog);
				jedis.set(token, userLogJsonStr);
				System.out.println("从redis中取出数据" + jedis.get(token));

				// 设置过期时间
				jedis.expire(token, 60 * 30);
				jedis.close();
				return new Result(200,"登录成功");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Result(-1, "登录失败");

	}

	/**
	 * 从redis中取出数据
	 * 
	 * @param token
	 * @return
	 */
	private UserLog findUserLogRedis(String token) {

		Jedis jedis = JedisPoolUtils.getJedis();

		String userLogJson = jedis.get(token);
		System.out.println("userLogJson:" + userLogJson);

		if (userLogJson == null || userLogJson == "") {
			throw new RuntimeException("redis中数据为空");
		}
		UserLog userLog = JSONObject.parseObject(userLogJson, UserLog.class);

		return userLog;

	}
}
