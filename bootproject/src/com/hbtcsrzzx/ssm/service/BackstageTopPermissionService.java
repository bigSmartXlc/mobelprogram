package com.hbtcsrzzx.ssm.service;

import com.github.pagehelper.PageInfo;
import com.hbtcsrzzx.ssm.po.BackstageTopPermission;

public interface BackstageTopPermissionService {
    PageInfo<BackstageTopPermission> findAllBackstageTopPermission(Integer page, Integer limit);

    void addBackstageTopPermission(BackstageTopPermission backstageTopPermission);

    void updateBackstageTopPermission(BackstageTopPermission backstageTopPermission);

    BackstageTopPermission findBackstageTopPermissionById(Integer id);
}
