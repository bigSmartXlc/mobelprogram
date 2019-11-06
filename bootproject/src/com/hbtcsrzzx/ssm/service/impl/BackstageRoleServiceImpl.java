package com.hbtcsrzzx.ssm.service.impl;

import com.hbtcsrzzx.ssm.dao.mapper.BackstageRoleMapper;
import com.hbtcsrzzx.ssm.dao.mapper.BackstageRolePermissionMapper;
import com.hbtcsrzzx.ssm.po.BackstageRole;
import com.hbtcsrzzx.ssm.po.BackstageRolePermission;
import com.hbtcsrzzx.ssm.service.BackstageRoleService;
import com.hbtcsrzzx.utils.ConfigConsts.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class BackstageRoleServiceImpl implements BackstageRoleService {
    @Autowired
    private BackstageRoleMapper backstageRoleMapper;
    @Autowired
    private BackstageRolePermissionMapper backstageRolePermissionMapper;

    @Override
    public List<BackstageRole> findAllBackstageRole(Integer page, Integer limit) {

        if (page == null || page <= 0) {
            page = 1;
        }

        if (limit == null || limit <= 0) {
            page = 10;
        }


        Integer startPage = (page - 1) * limit;

        List<BackstageRole> BackstageRoles = backstageRoleMapper.findAllBackstageRole(startPage, limit);
        if (BackstageRoles == null || BackstageRoles.size() <= 0) {
            throw new RuntimeException("查询结果为空");
        }

        return BackstageRoles;
    }

    @Override
    public int getCount() {

        Integer count = backstageRoleMapper.getCount(Constants.DEL_STATE);
        if (count == null || count <= 0) {
            throw new RuntimeException("结果总数不正确");
        }

        return count;
    }

    @Override
    public BackstageRole findBackstageRoleById(Integer id) {
        BackstageRole BackstageRole = backstageRoleMapper.findBackstageRoleById(id);
        if (BackstageRole == null) {
            throw new RuntimeException("找不到对应的分成纪录");
        }

        return BackstageRole;
    }

    @Override
    public void saveRoleAndPermission(Integer roleId, List<Integer> permissionIds) {

        int tmp = backstageRolePermissionMapper.delRoleAndPermissionByRoleId(roleId);
        if (tmp < 0) {
            throw new RuntimeException("清空角色权限失败");
        }
        if (permissionIds == null || permissionIds.size() <= 0) {
            return;
        }
        for (Integer permissionId : permissionIds) {
            BackstageRolePermission backstageRolePermission = new BackstageRolePermission();
            backstageRolePermission.setCreateTime(new Date());
            backstageRolePermission.setPermissionId(permissionId);
            backstageRolePermission.setRoleId(roleId);
            backstageRolePermission.setState(Constants.NORMAL_STATE);
            backstageRolePermission.setUpdateTime(new Date());
            int i = backstageRolePermissionMapper.insert(backstageRolePermission);
            if (i != 1) {
                throw new RuntimeException("新增角色权限失败");
            }
        }

    }

    @Override
    public int addBackstageRole(BackstageRole backstageRole) {

        backstageRole.setState(Constants.NORMAL_STATE);
        backstageRole.setCreateTime(new Date());
        backstageRole.setUpdateTime(new Date());
        int i = backstageRoleMapper.insert(backstageRole);
        if (i != 1) {
            throw new RuntimeException("新增失败");
        }

        return i;
    }

    @Override
    public int updateBackstageRole(BackstageRole backstageRole) {


        int i = backstageRoleMapper.updateByPrimaryKeySelective(backstageRole);

        if (i != 1) {
            throw new RuntimeException("修改失败");
        }

        return i;
    }
}
