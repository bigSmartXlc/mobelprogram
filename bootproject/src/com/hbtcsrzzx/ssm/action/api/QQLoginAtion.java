package com.hbtcsrzzx.ssm.action.api;

import com.hbtcsrzzx.utils.RequestSourceUtils;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.utils.partyLogin.QQUrlUtil;
import com.hbtcsrzzx.utils.partyLogin.WeiBoUrlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

@Controller
@RequestMapping("/qq")
public class QQLoginAtion {


    @Value("${aes.loginkey}")
    private String loginkey;

    @Value("${qq.client_id}")
    private String client_id;

    @Value("${qq.redirect_uri}")
    private String redirect_uri;

    /*
     * qq登录跳转接口
     * @param response
     * @throws IOException
     */
    @RequestMapping("/authorize")
    public void authorize(HttpServletResponse response, HttpServletRequest request) throws IOException {
        String authorizeUrl = QQUrlUtil.AUTHORIZATION_CODE_URL;
        if (RequestSourceUtils.getRequestSource(request.getHeader("User-Agent"))) {
            //来自移动端
            authorizeUrl = authorizeUrl.replace("DISPLAY", QQUrlUtil.DISPLAY_MOBILE);
        } else {
            //来自pc端
            authorizeUrl = authorizeUrl.replace("DISPLAY", "");
        }
      String  redirectUri = redirect_uri;


        authorizeUrl = authorizeUrl.replace("CLIENT_ID", client_id);
        authorizeUrl = authorizeUrl.replace("REDIRECT_URI", redirect_uri);
        response.sendRedirect(authorizeUrl);
    }

    /**
     * 获取AccessToken
     */
    @RequestMapping("/getAccessToken")
    public Result getAccessToken(String code) throws Exception {

        System.out.println("用户授权码:"+code);

        return Result.ok();

    }
}
