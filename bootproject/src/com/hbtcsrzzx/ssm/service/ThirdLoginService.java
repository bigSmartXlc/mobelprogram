package com.hbtcsrzzx.ssm.service;

import com.hbtcsrzzx.ssm.po.ThirdLogin;

public interface ThirdLoginService {

    /**
     * 根据用户id和第三方应用标识,获取第三方用户信息
     * @param openid
     * @return
     */
    ThirdLogin findThirdLoginByOpenid(String openid,String platform);

    /**
     * 新增用户信息,返回主键信息
     * @param openid
     * @param platform
     * @return
     */
    Integer insertThirdLogin(ThirdLogin thirdLogin);

    ThirdLogin findThirdLoginByid(Integer thirdLoginId);
}
