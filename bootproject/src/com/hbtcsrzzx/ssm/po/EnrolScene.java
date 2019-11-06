package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class EnrolScene {
    private Integer id;

    private String scene;

    private Integer cityId;

    private Integer dateId;

    private String detailedAddress;

    private Integer state;

    private Date createTime;

    private Date updateTime;
    private City city;
    private EvaluationDate evaluationDate;
    public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public EvaluationDate getEvaluationDate() {
		return evaluationDate;
	}

	public void setEvaluationDate(EvaluationDate evaluationDate) {
		this.evaluationDate = evaluationDate;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getDateId() {
        return dateId;
    }

    public void setDateId(Integer dateId) {
        this.dateId = dateId;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
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