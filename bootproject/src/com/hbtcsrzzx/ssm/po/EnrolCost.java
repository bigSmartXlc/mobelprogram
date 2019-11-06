package com.hbtcsrzzx.ssm.po;

public class EnrolCost {
	private Integer id;

	private Integer lid;

	private Integer cost;

	private Integer state;
	private Level level;

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLid() {
		return lid;
	}

	public void setLid(Integer lid) {
		this.lid = lid;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}