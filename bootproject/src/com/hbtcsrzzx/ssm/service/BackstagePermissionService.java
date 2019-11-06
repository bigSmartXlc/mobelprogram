package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.BackstagePermission;
import com.hbtcsrzzx.ssm.po.queryVo.Permission;

import java.util.List;

public interface BackstagePermissionService {
    PageInfo<BackstagePermission> findAllBackstagePermission(Integer page, Integer limit);

    void addBackstagePermission(BackstagePermission backstagePermission);

    void updateBackstagePermission(BackstagePermission backstagePermission);

    BackstagePermission findBackstagePermissionById(Integer id);

    List<Permission> findBackstagePermissionList();
}
