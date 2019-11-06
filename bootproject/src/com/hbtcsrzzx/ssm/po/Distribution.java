package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class Distribution {
    private Integer id;

    private String distributionLevel;

    private String recommenderLevel;

    private Integer returnProportion;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDistributionLevel() {
        return distributionLevel;
    }

    public void setDistributionLevel(String distributionLevel) {
        this.distributionLevel = distributionLevel == null ? null : distributionLevel.trim();
    }

    public String getRecommenderLevel() {
        return recommenderLevel;
    }

    public void setRecommenderLevel(String recommenderLevel) {
        this.recommenderLevel = recommenderLevel == null ? null : recommenderLevel.trim();
    }

    public Integer getReturnProportion() {
        return returnProportion;
    }

    public void setReturnProportion(Integer returnProportion) {
        this.returnProportion = returnProportion;
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