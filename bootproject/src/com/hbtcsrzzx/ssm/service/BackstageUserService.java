package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageUser;

import java.util.List;

public interface BackstageUserService {

    List<BackstageUser> findAllBackstageUser(Integer page, Integer limit);

    int getCount();

    int addBackstageUser(BackstageUser backstageUser);

    int updateBackstageUser(BackstageUser backstageUser);

    BackstageUser findBackstageUserById(Integer id);

    int saveUserAndRole(Integer userId, Integer[] multi);

    BackstageUser findBackstageUserByUserName( Integer state,String username);


    PageInfo<BackstageUser> findBackstageUsersByRoleName(String roleName, Integer page, Integer limit);


    /**
     *
     * 查询角色以及机构的所有用户
     * @return 用户列表
     */

    List<BackstageUser> findBackstageUserAllByInstitution();
}
