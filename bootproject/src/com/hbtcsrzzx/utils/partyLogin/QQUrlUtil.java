package com.hbtcsrzzx.utils.partyLogin;

public interface QQUrlUtil {

    //display取值
    /**
     * 移动终端的授权页面，适用于支持html5的手机。注：使用此版授权页请用 https://open.weibo.cn/oauth2/authorize 授权接口
     */
    String DISPLAY_MOBILE = "mobile";

    /**
     * 获取Authorization Code
     * GET
     */
    String AUTHORIZATION_CODE_URL = "https://graph.qq.com/oauth2.0/authorize?" +
            "response_type=code&client_id=CLIENT_ID&redirect_uri=REDIRECT_URI&state=STATE&display=DISPLAY";

    /**
     * 通过Authorization Code获取AccessToken
     * GET
     */
    String ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?" +
            "grant_type=authorization_code&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&code=CODE&redirect_uri=REDIRECT_URI";

    /**
     * （可选）权限自动续期，获取Access Token
     *  GET
     */
    String REFRESH_ACCESS_TOKEN_URL = "https://graph.qq.com/oauth2.0/token?" +
            "grant_type=refresh_token&client_id=CLIENT_ID&client_secret=CLIENT_SECRET&refresh_token=REFRESH_TOKEN";

    /**
     * PC网站接入时，获取到用户OpenID
     *  GET
     */
    String OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=ACCESS_TOKEN";

    /**
     * 获取用户信息
     *  GET
     */
    String GET_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?access_token=ACCESS_TOKEN&oauth_consumer_key=CLIENT_ID&openid=OPENID";
}
