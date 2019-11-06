package com.hbtcsrzzx.ssm.po;

public class Content {
    private Long id;

    private Long categoryId;


    private String title;

    private String url;

    private String pic;

    private Integer state;

    private Integer sortOrder;
    private String categoryStr;

    public String getCategoryStr() {
          if(categoryId == 1L){
              this.categoryStr = "轮播图";
          }

        if(categoryId == 2L){
            this.categoryStr = "新闻图";
        }
        return categoryStr;
    }

    public void setCategoryStr(String categoryStr) {
        this.categoryStr = categoryStr;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}