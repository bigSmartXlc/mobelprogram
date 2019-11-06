package com.hbtcsrzzx.ssm.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EvaluationDate {
	private Integer id;

	private Integer lid;

	private Integer sid;

	private Integer cid;

	private Date testDate;

	private String timeFrame;

	private Integer state;

	private Category category;
	private Subject subject;
	private Level level;
	private String testDateStr;

	public String getTestDateStr() {
		return testDateStr;
	}

	public void setTestDateStr(String testDateStr) {
		if (testDateStr != null && testDateStr != "") {

			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
			try {
				this.testDate = format.parse(testDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		this.testDateStr = testDateStr;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

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

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getTimeFrame() {
		return timeFrame;
	}

	public void setTimeFrame(String timeFrame) {
		this.timeFrame = timeFrame == null ? null : timeFrame.trim();
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}