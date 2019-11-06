package com.hbtcsrzzx.ssm.action.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.CookieUtil;
import com.hbtcsrzzx.utils.JedisPoolUtils;
import com.hbtcsrzzx.utils.Result;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/user")
public class UserWeb {
	@Autowired
	UserLogService userLogService;

	Jedis jedis = JedisPoolUtils.getJedis();

	@RequestMapping("/addUserLog")
	@ResponseBody
	public Result addUserLog(UserLog userLog, HttpServletResponse response) {
		// 跨域数据传输
	/*	response.setHeader("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		String code = userLog.getCode();
		try {
			String codeRedis = jedis.hget(userLog.getPhone(), "code");
			if (StringUtils.isEmpty(codeRedis)) {
				return new Result(-1, "请先发送验证码");
			}
			// 验证验证码内容是否正确
			if (!codeRedis.equals(code)) {
				return new Result(-1, "验证码输入有误");
			}
			// 添加用户
			if (userLog != null) {
				userLogService.addUserLog(userLog);
				return new Result(200, "添加成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Result(-1, "添加失败");
	}

	@RequestMapping("/updateUserLog")
	@ResponseBody
	public Map<String, Object> UpdateUserLog(HttpServletRequest request, UserLog userLog,
			HttpServletResponse response) {
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		int res = -1;
		String msg = "";
		try {
			String token = this.findCookieToken(request);
			UserLog userLog2 = this.findUserLogRedis(token);
			userLog.setId(userLog2.getId());
			int tmp = userLogService.updateUserLogById(userLog);
			if (tmp > 0) {
				res = 200;
				msg = "更新用户成功";
			} else {
				msg = "更新用户有误";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			msg = "操作有误";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		// JSONArray json = JSONArray.fromObject(result);
		return result;
	}

	public Map<String, Object> deleteUserLog(HttpServletRequest request, Integer id) {
		int res = -1;
		String msg = "";
		try {
			if (id != null && id > 0) {
				UserLog userLog = userLogService.findUserLogById(id);
				userLog.setState(Constants.DEL_STATE);
				int tmp = userLogService.updateUserLogById(userLog);
				if (tmp > 0) {
					res = 200;
					msg = "注销用户";
				}
			} else {
				msg = "此分类不存在";
			}

		} catch (Exception e) {
			e.printStackTrace();
			msg = "没有权限";
		}

		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", res);
		result.put("msg", msg);
		return result;
	}

	/**
	 * 查看用户信息,并从缓存中更新用户
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/findLoginUser")
	@ResponseBody
	public Result findLoginUser(HttpServletRequest request, HttpServletResponse response) {
		// 跨域数据传输
		response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		try {
			String token = this.findCookieToken(request);
			UserLog userLog = this.findUserLogRedis(token);

			userLog = userLogService.findUserLogByPhone(userLog.getPhone());
			if (userLog != null) {

				return new Result(200, null, userLog);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1, "没有该用户", null);
	}

	/**
	 * 从cookie取出用户登录凭证
	 */

	public String findCookieToken(HttpServletRequest request) {
		String token = CookieUtil.getCookieValue(request, "loginToken", "utf-8");
		if (token == null && token == "") {
			throw new RuntimeException("用户信息不存在");
		}
		return token;
	}

	/**
	 * 从redis中取出数据
	 * 
	 * @param token
	 * @return
	 */
	public UserLog findUserLogRedis(String token) {
		String userLogJson = jedis.get(token);
		// System.out.println("userLogJson:" + userLogJson);

		if (userLogJson == null || userLogJson == "") {
			throw new RuntimeException("redis中数据为空");
		}
		UserLog userLog = JSONObject.parseObject(userLogJson, UserLog.class);

		return userLog;

	}
}
