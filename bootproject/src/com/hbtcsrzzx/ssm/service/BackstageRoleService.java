package com.hbtcsrzzx.ssm.service;

import com.hbtcsrzzx.ssm.po.BackstageRole;

import java.util.List;

public interface BackstageRoleService {
    List<BackstageRole> findAllBackstageRole(Integer page, Integer limit);

    int getCount();

    int addBackstageRole(BackstageRole backstageRole);

    int updateBackstageRole(BackstageRole backstageRole);

    BackstageRole findBackstageRoleById(Integer id);

    /**
     * 根据前台传入的角色id,与权限列表,插入关联关系
     * @param roleId
     * @param
     */
    void saveRoleAndPermission(Integer roleId, List<Integer> permissionIds);
}
