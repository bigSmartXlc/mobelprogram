package com.hbtcsrzzx.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.dao.mapper.BackstagePermissionMapper;
import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.queryVo.Permission;
import com.hbtcsrzzx.ssm.service.BackstagePermissionService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BackstagePermissionServiceImpl implements BackstagePermissionService {
    @Autowired
    private BackstagePermissionMapper backstagePermissionMapper;

    @Override
    public PageInfo<BackstagePermission> findAllBackstagePermission(Integer page, Integer limit) {

        PageHelper.startPage(page, limit);
        List<BackstagePermission> backstagePermissions = backstagePermissionMapper.findAllBackstagePermission();
        PageInfo<BackstagePermission> pageInfo = new PageInfo<>(backstagePermissions);

        return pageInfo;
    }

    @Override
    public void addBackstagePermission(BackstagePermission backstagePermission) {

        backstagePermission.setCreateTime(new Date());
        backstagePermission.setUpdateTime(new Date());
        backstagePermission.setState(Constants.NORMAL_STATE);
        int i = backstagePermissionMapper.insertSelective(backstagePermission);
        if (i != 1) {
            throw new RuntimeException("新增权限列表失败");
        }
    }

    @Override
    public void updateBackstagePermission(BackstagePermission backstagePermission) {

        int i = backstagePermissionMapper.updateByPrimaryKeySelective(backstagePermission);
        if (i != 1) {
            throw new RuntimeException("修改权限列表失败");
        }
    }

    @Override
    public BackstagePermission findBackstagePermissionById(Integer id) {
        BackstagePermission backstagePermission = backstagePermissionMapper.findBackstagePermissionById(id);
        if (backstagePermission == null || backstagePermission.getState() == -1) {
            throw new RuntimeException("该权限不存在或以删除");
        }
        return backstagePermission;
    }

    @Override
    public List<Permission> findBackstagePermissionList() {



        return backstagePermissionMapper.findBackstagePermissionList();
    }
}
