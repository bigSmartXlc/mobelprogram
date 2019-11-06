package com.hbtcsrzzx.ssm.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface WeiBoLoginService {
    /**
     * 获取用户的唯一票据
     * @param code  用户授权码
     * @return
     */
    String getAccessToken(String code,String loginkey) throws  Exception;

    /**
     *  根据唯一票据 获取用户id
     * @param accessToken
     * @return
     */
    String getTokenInfo(String accessToken) throws Exception;

    /**
     * 获取微博用户信息
     * @param openid
     * @param decryptAccessToken
     * @return
     */
    String getWeiBoUserName(String openid, String decryptAccessToken) throws IOException, ParseException;


    /**
     * 根据用户id,获取用户信息
     */


}
