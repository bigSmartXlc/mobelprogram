package com.hbtcsrzzx.ssm.po;

public class BackstageTopPermission {
    private Integer id;

    private String menuName;

    private Integer state;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}