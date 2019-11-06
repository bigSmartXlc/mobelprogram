package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class BackstagePermission {
    private Integer id;

    private Integer upperId;

    private String permissionName;

    private String url;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    private BackstageTopPermission backstageTopPermission;

    public BackstageTopPermission getBackstageTopPermission() {
        return backstageTopPermission;
    }

    public void setBackstageTopPermission(BackstageTopPermission backstageTopPermission) {
        this.backstageTopPermission = backstageTopPermission;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUpperId() {
        return upperId;
    }

    public void setUpperId(Integer upperId) {
        this.upperId = upperId;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName == null ? null : permissionName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
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