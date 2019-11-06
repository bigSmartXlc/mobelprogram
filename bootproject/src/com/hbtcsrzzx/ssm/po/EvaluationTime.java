package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class EvaluationTime {
    private Integer id;

    private Date testTime;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}