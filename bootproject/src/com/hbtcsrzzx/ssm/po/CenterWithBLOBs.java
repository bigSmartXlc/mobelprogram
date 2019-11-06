package com.hbtcsrzzx.ssm.po;

public class CenterWithBLOBs extends Center {
    private String context;

    private String introduction;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}