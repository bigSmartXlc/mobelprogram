package com.hbtcsrzzx.ssm.po.queryVo;

import com.hbtcsrzzx.ssm.po.BackstageTopPermission;

import java.util.List;

/**
 * 权限菜单集合包装类
 */
public class Permission {
    //一级权限名称
    private String title;
    //一级权限id
    private Integer id;
    //子级权限列表
    private List<ChildPermission> children;
  /*  //是否选中
    private Object checked;

    public Object getChecked() {
        return checked;
    }

    public void setChecked(Object checked) {
        this.checked = checked;
    }
*/
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public List<ChildPermission> getChildren() {
        return children;
    }

    public void setChildren(List<ChildPermission> children) {
        this.children = children;
    }
}
