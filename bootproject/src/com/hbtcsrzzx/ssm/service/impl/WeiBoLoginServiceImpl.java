package com.hbtcsrzzx.ssm.service.impl;

import com.alibaba.fastjson.JSON;
import com.hbtcsrzzx.ssm.service.WeiBoLoginService;
import com.hbtcsrzzx.utils.HttpClient;
import com.hbtcsrzzx.utils.Result;
import com.hbtcsrzzx.utils.encryption.AES;
import com.hbtcsrzzx.utils.partyLogin.WeiBoUrlUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeiBoLoginServiceImpl implements WeiBoLoginService {


    @Value("${weibo.client_id}")
    private String client_id;

    @Value("${weibo.client_secret}")
    private String client_secret;

    @Value("${weibo.grant_type}")
    private String authorization_code;

    @Value("${weibo.redirect_uri}")
    private String redirect_uri;


    @Override
    public String getAccessToken(String code, String loginkey) throws Exception {
        Map<String, String> mapJson = null;

        Map<String, String> map = new HashMap<String, String>();
        map.put("code", code);
        map.put("client_id", client_id);
        map.put("client_secret", client_secret);
        map.put("grant_type", authorization_code);
        map.put("redirect_uri", redirect_uri);
        HttpClient client = new HttpClient(WeiBoUrlUtil.ACCESS_TOKEN);
        // 是否是https协议
        client.setHttps(true);
        //请求参数
        client.setParameter(map);
        // 执行post请求
        client.post();
        // 获取结果
        String accessToken = client.getContent();
        mapJson = JSON.parseObject(accessToken, Map.class);

        //获取用户唯一票据,并加密
        String accessTokenStr = mapJson.get("access_token");
        String encryptAccessToken = AES.Encrypt(accessTokenStr, loginkey);
        return encryptAccessToken;

    }

    @Override
    public String getTokenInfo(String accessToken) throws Exception {


        Map<String, String> map = new HashMap<String, String>();
        map.put("access_token", accessToken);

        HttpClient client = new HttpClient(WeiBoUrlUtil.GET_TOKEN_INFO);
        client.setHttps(true);
        //请求参数
        client.setParameter(map);
        // 执行post请求
        client.post();
        // 获取结果
        String userContentJson = client.getContent();

        Map mapJson = JSON.parseObject(userContentJson, Map.class);

        String userId = String.valueOf(mapJson.get("uid"));
        if (StringUtils.isEmpty(userId)) {
            throw new RuntimeException("找不到该微博用户id");
        }
        return userId;
    }

    @Override
    public String getWeiBoUserName(String openid, String decryptAccessToken) throws IOException, ParseException {

        String showUser = WeiBoUrlUtil.SHOW_USER;
        showUser = showUser.replace("ACCESS_TOKEN", decryptAccessToken);
        showUser = showUser.replace("UID", openid);
        HttpClient client = new HttpClient(showUser);
        client.get();
        // 获取结果
        String userContentJson = client.getContent();
        Map mapJson = JSON.parseObject(userContentJson, Map.class);
        String screenName = (String) mapJson.get("screen_name");
        if (StringUtils.isEmpty(screenName)) {
            throw new RuntimeException("找不到该微博用户名称");
        }
        return screenName;
    }
}
