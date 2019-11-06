package com.hbtcsrzzx.ssm.po;

import java.util.Date;
import java.util.List;

public class BackstageRole {
    private Integer id;

    private String roleName;

    private String roleDesc;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private List<BackstagePermission> backstagePermission;

    public List<BackstagePermission> getBackstagePermission() {
        return backstagePermission;
    }

    public void setBackstagePermission(List<BackstagePermission> backstagePermission) {
        this.backstagePermission = backstagePermission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}