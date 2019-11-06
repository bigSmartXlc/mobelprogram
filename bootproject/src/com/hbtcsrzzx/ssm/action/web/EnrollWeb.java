package com.hbtcsrzzx.ssm.action.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hbtcsrzzx.ssm.dao.mapper.ExamineePayLogMapper;
import com.hbtcsrzzx.ssm.po.EnrolExaminee;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.EnrolExamineeService;
import com.hbtcsrzzx.utils.JedisPoolUtils;
import com.hbtcsrzzx.utils.PhoneFormatCheckUtils;
import com.hbtcsrzzx.utils.Result;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/enroll")
public class EnrollWeb {

	Jedis jedis = JedisPoolUtils.getJedis();
	@Autowired
	private EnrolExamineeService examineeService;



	@Autowired
	private UserWeb userWeb;

	/**
	 *
	 * @param enrolExaminee
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addEnrolExaminee")
	@ResponseBody
	public Result addEnrolExaminee(EnrolExaminee enrolExaminee, HttpServletRequest request,
								   HttpServletResponse response) {

		// 跨域数据传输
	/*	response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		try {

			enrolExaminee.setUserLogPhone(getUserPhone(request));

			int i = examineeService.addEnrolExaminee(enrolExaminee);
			if(i>0){
			 return  new Result(200,"报考信息填写成功",i);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(-1, "报考信息填写失败，请重试");

	}

	/**
	 * 查询当前用户的所有考生信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/findEnrolExamineeByPhone")
	@ResponseBody
	public Result findEnrolExamineeByPhone(HttpServletRequest request, HttpServletResponse response) {
		// 跨域数据传输
		/*response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		try {
			String phone = getUserPhone(request);
			List<EnrolExaminee> examinees = examineeService.findEnrolExamineeByPhone(phone);
			if (examinees == null || examinees.size() <= 0) {
				return new Result(-1, "该用户没有对应的考生");
			}
			return new Result(200, "查询考生信息成功", examinees);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Result(-1, "查询考生信息失败");
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
	 * 根据准考证，查询考生成绩
	 * 
	 * @param examinationCard
	 * @return
	 */
	@RequestMapping("/findEnrolExamineeByExaminationCard")
	@ResponseBody
	public Result findEnrolExamineeByExaminationCard(String examinationCard,HttpServletResponse response) {
		// 跨域数据传输
	/*	response.setHeader("Access-Control-Allow-Origin", "http://dashenbangmang.com");
		response.setHeader("Access-Control-Allow-Credentials", "true");*/
		if (StringUtils.isEmpty(examinationCard)) {
			return new Result(-1, "准考证不能为空");
		}
		try {
			EnrolExaminee examinee = examineeService.findEnrolExamineeByExaminationCard(examinationCard);
			if (examinee == null) {
				return new Result(-1, "找不到对应的考生");
			}
			return new Result(200, "", examinee);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Result(-1, "查询出错");
	}
}
