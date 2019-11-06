package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class QuestionAnswers {
    private Integer id;

    private String question;

    private Integer state;

    private Date createtime;

    private Date updatetime;

    private String answers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
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

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers == null ? null : answers.trim();
    }
}