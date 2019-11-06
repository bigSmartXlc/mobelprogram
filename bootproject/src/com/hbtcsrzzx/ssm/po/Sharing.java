package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class Sharing {
    private Integer id;

    private String institutionalNature;

    private Integer salesmanCommission;

    private Integer institutionalCommission;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInstitutionalNature() {
        return institutionalNature;
    }

    public void setInstitutionalNature(String institutionalNature) {
        this.institutionalNature = institutionalNature == null ? null : institutionalNature.trim();
    }

    public Integer getSalesmanCommission() {
        return salesmanCommission;
    }

    public void setSalesmanCommission(Integer salesmanCommission) {
        this.salesmanCommission = salesmanCommission;
    }

    public Integer getInstitutionalCommission() {
        return institutionalCommission;
    }

    public void setInstitutionalCommission(Integer institutionalCommission) {
        this.institutionalCommission = institutionalCommission;
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