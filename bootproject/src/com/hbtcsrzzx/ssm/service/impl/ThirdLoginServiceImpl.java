package com.hbtcsrzzx.ssm.service.impl;

import com.hbtcsrzzx.ssm.dao.mapper.ThirdLoginMapper;
import com.hbtcsrzzx.ssm.po.ThirdLogin;
import com.hbtcsrzzx.ssm.service.ThirdLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ThirdLoginServiceImpl implements ThirdLoginService {
    @Autowired
    private ThirdLoginMapper thirdLoginMapper;

    @Override
    public ThirdLogin findThirdLoginByOpenid(String openid, String platform) {

        ThirdLogin thirdLogin = thirdLoginMapper.findThirdLoginByOpenid(openid, platform);
        return thirdLogin;
    }

    @Override
    public Integer insertThirdLogin(ThirdLogin thirdLogin) {


        int i = thirdLoginMapper.insertSelective(thirdLogin);
        if (i != 1) {
            throw new RuntimeException("新增第三方用户失败");
        }
        Integer id = thirdLogin.getId();

        if (id == null) {
            throw new RuntimeException("新增时,没有返回id");
        }
        return id;
    }

    @Override
    public ThirdLogin findThirdLoginByid(Integer thirdLoginId) {


        return  thirdLoginMapper.selectByPrimaryKey(thirdLoginId);
    }
}
