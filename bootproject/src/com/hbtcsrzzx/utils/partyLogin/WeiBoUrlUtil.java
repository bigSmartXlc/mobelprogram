package com.hbtcsrzzx.utils.partyLogin;


public interface WeiBoUrlUtil {

    //display取值
    /**
     * 默认的授权页面，适用于web浏览器
     */
    String DISPLAY_DEFAULT = "default";
    /**
     * 移动终端的授权页面，适用于支持html5的手机。注：使用此版授权页请用 https://open.weibo.cn/oauth2/authorize 授权接口
     */
    String DISPLAY_MOBILE = "mobile";
    /**
     * wap版授权页面，适用于非智能手机。
     */
    String DISPLAY_WAP = "wap";
    /**
     * 默客户端版本授权页面，适用于PC桌面应用。
     */
    String DISPLAY_CLIENT = "client";
    /**
     * 默认的站内应用授权页，授权后不返回access_token，只刷新站内应用父框架。
     */
    String DISPLAY_APPONWEIBO = "apponweibo";


    /**
     * 获取用户授权Token
     */
    String AUTHORIZE = "https://api.weibo.com/oauth2/authorize?client_id=APPKEY&redirect_uri=REDIRECT_URI&display=DEFAULT&scope=all";

    /**
     *
     */
    String AUTHORIZE_MOBILE = "https://open.weibo.cn/oauth2/authorize?client_id=APPKEY&redirect_uri=REDIRECT_URI&display=DEFAULT&scope=all";
    /**
     * 获取授权过的Access Token
     */
    String ACCESS_TOKEN ="https://api.weibo.com/oauth2/access_token";


    /**
     * 授权信息查询接口
     */
    String GET_TOKEN_INFO = "https://api.weibo.com/oauth2/get_token_info";

    /**
     *获取微博用户url
     */
    String SHOW_USER = "https://api.weibo.com/2/users/show.json?access_token=ACCESS_TOKEN&uid=UID";




}
