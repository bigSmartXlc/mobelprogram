package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.BackstageTopPermissionMapper;
import com.hbtcsrzzx.ssm.po.BackstageTopPermission;
import com.hbtcsrzzx.ssm.service.BackstageTopPermissionService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackstageTopPermissionServiceImpl implements BackstageTopPermissionService {
    @Autowired
    private BackstageTopPermissionMapper backstageTopPermissionMapper;

    @Override
    public PageInfo<BackstageTopPermission> findAllBackstageTopPermission(Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        List<BackstageTopPermission> backstageTopPermissionList = backstageTopPermissionMapper.findAllBackstageTopPermission(page, limit);
        PageInfo<BackstageTopPermission> pageInfo = new PageInfo<>(backstageTopPermissionList);

        return pageInfo;
    }

    @Override
    public void addBackstageTopPermission(BackstageTopPermission backstageTopPermission) {

        backstageTopPermission.setState(Constants.NORMAL_STATE);
        int i = backstageTopPermissionMapper.insertSelective(backstageTopPermission);
        if (i != 1) {
            throw new RuntimeException("新增顶级权限菜单失败");

        }
    }

    @Override
    public void updateBackstageTopPermission(BackstageTopPermission backstageTopPermission) {

        int i = backstageTopPermissionMapper.updateByPrimaryKeySelective(backstageTopPermission);
        if (i != 1) {
            throw new RuntimeException("修改顶级权限菜单失败");
        }
    }

    @Override
    public BackstageTopPermission findBackstageTopPermissionById(Integer id) {
        BackstageTopPermission backstageTopPermission = backstageTopPermissionMapper.findBackstageTopPermissionById(id);
        if(backstageTopPermission == null){
            throw new RuntimeException("该顶级菜单不存在");
        }
        return backstageTopPermission;
    }
}
