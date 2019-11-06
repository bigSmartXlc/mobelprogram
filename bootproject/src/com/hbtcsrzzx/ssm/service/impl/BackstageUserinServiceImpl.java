package com.hbtcsrzzx.ssm.service.impl;


import com.hbtcsrzzx.ssm.dao.mapper.BackstageUserMapper;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageUser;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@Service("backstageUserinService")
@Transactional(rollbackFor = Exception.class)
public class BackstageUserinServiceImpl implements UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    HttpServletRequest request;


    @Autowired
    private BackstageUserMapper backstageUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //获取查询的用户对象
      BackstageUser backstageUser = backstageUserMapper.findBackstageUserByUserName(Constants.DEL_STATE, username);
        //判断用户对象是否为空
       if(backstageUser ==  null ){
            return null;
        }

        //登录判断
      User user = new User(backstageUser.getUsername(),backstageUser.getPassword(),backstageUser.getState()==0?true:false,
                true,true,true,getGrantedAuthority(backstageUser.getBackstageRoles()));

        return user;

    }

    public List<SimpleGrantedAuthority> getGrantedAuthority(List<BackstageRole> roles){


        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        for (BackstageRole role : roles) {

            authoritys.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));

        }

        return authoritys;
    }

}
