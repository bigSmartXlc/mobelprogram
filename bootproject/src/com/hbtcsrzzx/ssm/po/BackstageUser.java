package com.hbtcsrzzx.ssm.po;

import java.util.Date;
import java.util.List;

public class BackstageUser {
    private Integer id;

    private String username;

    private String password;

    private String name;

    private String contact;

    private Integer state;

    private String userLogo;

    private Date createtime;

    private Date updatetime;

    private List<BackstageRole> backstageRoles;

    public List<BackstageRole> getBackstageRoles() {
        return backstageRoles;
    }

    public void setBackstageRoles(List<BackstageRole> backstageRoles) {
        this.backstageRoles = backstageRoles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }
}