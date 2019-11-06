package com.hbtcsrzzx.ssm.po;

public class Level {
    private Integer id;

    private String level;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}