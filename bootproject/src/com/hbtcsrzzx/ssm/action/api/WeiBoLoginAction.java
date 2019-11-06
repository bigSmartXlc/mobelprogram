package com.hbtcsrzzx.ssm.action.api;

import com.hbtcsrzzx.ssm.po.ThirdLogin;
import com.hbtcsrzzx.ssm.po.UserLog;
import com.hbtcsrzzx.ssm.service.ThirdLoginService;
import com.hbtcsrzzx.ssm.service.UserLogService;
import com.hbtcsrzzx.ssm.service.WeiBoLoginService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import com.hbtcsrzzx.utils.RequestSourceUtils;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.utils.StatusCode;
import com.hbtcsrzzx.utils.encryption.AES;
import com.hbtcsrzzx.utils.partyLogin.WeiBoUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@RestController
@RequestMapping("/weibo")
public class WeiBoLoginAction {

    @Autowired
    private WeiBoLoginService weiBoLoginService;
    @Autowired
    private ThirdLoginService thirdLoginService;

    @Autowired
    private UserLogService userLogService;

    @Value("${aes.loginkey}")
    private String loginkey;

    @Value("${weibo.client_id}")
    private String client_id;

    @Value("${weibo.redirect_uri}")
    private String redirect_uri;

    /**
     * 登录跳转接口
     * @param response
     * @throws IOException
     */
    @RequestMapping("/authorize")
    public void authorize(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String authorizeUrl ="";
       if(RequestSourceUtils.getRequestSource(request.getHeader("User-Agent"))) {
           //来自移动端
           authorizeUrl = WeiBoUrlUtil.AUTHORIZE_MOBILE;
           authorizeUrl = authorizeUrl.replace("DEFAULT", WeiBoUrlUtil.DISPLAY_MOBILE);
       }else{
           //来自pc端
           authorizeUrl = WeiBoUrlUtil.AUTHORIZE;
           authorizeUrl = authorizeUrl.replace("DEFAULT", WeiBoUrlUtil.DISPLAY_DEFAULT);
       }
        authorizeUrl = authorizeUrl.replace("APPKEY", client_id);
        authorizeUrl = authorizeUrl.replace("REDIRECT_URI", redirect_uri);
        response.sendRedirect(authorizeUrl);

    }


    /**
     * 获取用户唯一票据
     *
     * @param code 授权码
     * @return
     */
    @RequestMapping("/getAccessToken")
    public Result getAccessToken(String code) throws Exception {
        String encryptAccessToken = weiBoLoginService.getAccessToken(code, loginkey);
        if (encryptAccessToken == null) {
            return Result.error("微博用户授权失败");
        }
        return Result.ok("微博用户授权成功", encryptAccessToken);
    }


    /**
     * 根据唯一票据查询用户是否存在,存在返回用户信息,不存在,返回用户尚未绑定网站用户,并返回第三方登录信息
     *
     * @param accessToken
     * @return
     */
    @RequestMapping("/isUserExist")
    public Result isUserExist(String accessToken) {


        try {
            //解密
            String decryptAccessToken = AES.Decrypt(accessToken, loginkey);
            //获取用户唯一id
            String openid = weiBoLoginService.getTokenInfo(decryptAccessToken);
            if (openid == null) {
                return Result.error(-1, "用户不存在,或授权码出错");
            }
            //查询该用户是否是第一次登录
            ThirdLogin thirdLogin = thirdLoginService.findThirdLoginByOpenid(openid, Constants.WeiBo);
            //第一次登录
            if (thirdLogin == null) {
                String screenName = weiBoLoginService.getWeiBoUserName(openid, decryptAccessToken);
                ThirdLogin newthirdLogin = new ThirdLogin();
                newthirdLogin.setOpenid(openid);
                newthirdLogin.setPlatform(Constants.WeiBo);
                newthirdLogin.setOpenname(screenName);
                newthirdLogin.setAccessToken(decryptAccessToken);
                newthirdLogin.setCreatetime(new Date());
                newthirdLogin.setUpdatetime(new Date());
                newthirdLogin.setLogintime(new Date());
                Integer thirdLoginId = thirdLoginService.insertThirdLogin(newthirdLogin);

                ThirdLogin loginByid = thirdLoginService.findThirdLoginByid(thirdLoginId);
                return new Result(StatusCode.THIRDPARTYERROR, "用户第一次登录,未绑定网站用户", newthirdLogin);
            }

            //查询该用户是否绑定网站用户
            Integer userId = thirdLogin.getUserId();
            //没绑定,返回第三方用户信息,以及错误码
            if (userId == null || userId <= 0) {
                return new Result(StatusCode.THIRDPARTYERROR, "用户未绑定网站用户", thirdLogin);
            }

            //绑定,查询用户信息
            UserLog userLog = userLogService.findUserLogById(userId);

            return Result.ok("查询用户信息成功", userLog);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error(-1, "请求出错");

    }


}
