package com.hbtcsrzzx.ssm.po;

public class Menu {
    private Integer id;

    private String name;

    private String pname;

    private Integer pid;

    private String iurl;

    private String ourl;

    private Integer state;

    private Integer type;

    private String icon;

    private String openStyle;

    private Integer pos;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getIurl() {
        return iurl;
    }

    public void setIurl(String iurl) {
        this.iurl = iurl == null ? null : iurl.trim();
    }

    public String getOurl() {
        return ourl;
    }

    public void setOurl(String ourl) {
        this.ourl = ourl == null ? null : ourl.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getOpenStyle() {
        return openStyle;
    }

    public void setOpenStyle(String openStyle) {
        this.openStyle = openStyle == null ? null : openStyle.trim();
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }
}