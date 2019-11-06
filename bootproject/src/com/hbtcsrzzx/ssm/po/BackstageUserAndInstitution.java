package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class BackstageUserAndInstitution {
    private Integer institutionId;

    private Integer backstageUser;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Integer getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(Integer institutionId) {
        this.institutionId = institutionId;
    }

    public Integer getBackstageUser() {
        return backstageUser;
    }

    public void setBackstageUser(Integer backstageUser) {
        this.backstageUser = backstageUser;
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