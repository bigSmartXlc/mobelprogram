package com.hbtcsrzzx.ssm.action.web;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.hbtcsrzzx.ssm.po.queryVo.ImageCode;
import com.hbtcsrzzx.ssm.service.utils.KaptchaConfig;
import com.hbtcsrzzx.utils.CusAccessObjectUtil;
import com.hbtcsrzzx.utils.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/img")
public class ImgCode {

    @Autowired
    private KaptchaConfig kaptchaConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/getImage")
    public void  getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        DefaultKaptcha defaultKaptcha = kaptchaConfig.getDefaultKaptcha();
        //生成验证码字符串
        String code = defaultKaptcha.createText();
        //使用生产的验证码字符串返回一个BufferedImage对象
        BufferedImage image = defaultKaptcha.createImage(code);


        //存储验证码并设置过期时间
        String addr = CusAccessObjectUtil.getIpAddress(request);
        redisTemplate.boundValueOps(addr).set(code, 60L, TimeUnit.SECONDS);

        ImageIO.write(image, "JPEG", response.getOutputStream());
        //return imageCode;
    }

    @RequestMapping("/getCode")
    @ResponseBody
    public Result getCode(HttpServletRequest request, String imgCode) {

        String addr = CusAccessObjectUtil.getIpAddress(request);
        String code = (String) redisTemplate.boundValueOps(addr).get();

        //验证码不存在 ,说明已过期
        if (StringUtils.isEmpty(code)) {

            return Result.error("验证码超时");
        }


        if (code.equals(imgCode)) {
            return Result.ok();
        }

        return Result.error("验证码输入错误");

    }
}
