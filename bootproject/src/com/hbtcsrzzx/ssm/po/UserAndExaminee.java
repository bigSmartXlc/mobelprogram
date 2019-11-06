package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class UserAndExaminee {
    private Integer userId;

    private Integer examineeId;

    private String recommenderLevel;

    private Integer state;

    private Date createTime;

    private Date updateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExamineeId() {
        return examineeId;
    }

    public void setExamineeId(Integer examineeId) {
        this.examineeId = examineeId;
    }

    public String getRecommenderLevel() {
        return recommenderLevel;
    }

    public void setRecommenderLevel(String recommenderLevel) {
        this.recommenderLevel = recommenderLevel == null ? null : recommenderLevel.trim();
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