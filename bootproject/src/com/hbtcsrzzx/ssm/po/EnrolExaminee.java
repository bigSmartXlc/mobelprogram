package com.hbtcsrzzx.ssm.po;

import java.util.Date;

public class EnrolExaminee {
    private Integer id;

    private String name;

    private Integer gender;

    private String nationality;

    private String nation;

    private String examinationCard;

    private String idCard;

    private String unit;

    private String evaluationAddress;

    private String category;

    private String subject;

    private String level;

    private Integer enrolSceneId;

    private String recommendUnit;

    private Integer payStatus;

    private Integer auditStatus;

    private String userLogPhone;

    private Integer state;

    private Integer cost;

    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    private Integer examinationResults;

    private String institutionName;
    private String institutionNature;
    private Date createTime;

    public String getInstitutionNature() {
        return institutionNature;
    }

    public void setInstitutionNature(String institutionNature) {
        this.institutionNature = institutionNature;
    }

    private Date updateTime;


    private EnrolScene enrolScene;
    private String genderStr;
    private String payStatusStr;
    private String auditStatusStr;
    private String examinationResultsStr;



    public String getExaminationResultsStr() {
        if (this.examinationResults != null) {
            if(examinationResults==0){
                examinationResultsStr = "考试结果未审核";
            }
            if(examinationResults==1){
                examinationResultsStr = "通过";
            }
            if(examinationResults==2){
                examinationResultsStr = "未通过";
            }
        }
        return examinationResultsStr;
    }

    public void setExaminationResultsStr(String examinationResultsStr) {
        this.examinationResultsStr = examinationResultsStr;
    }

    public EnrolScene getEnrolScene() {
        return enrolScene;
    }

    public void setEnrolScene(EnrolScene enrolScene) {
        this.enrolScene = enrolScene;
    }

    public String getGenderStr() {
        if (this.gender != null) {
            if (gender == 0) {
                genderStr = "女";
                return genderStr;
            }

            if (gender == 1) {
                genderStr = "男";
                return genderStr;
            }
        }
        return genderStr;
    }

    public void setGenderStr(String genderStr) {
        this.genderStr = genderStr;
    }

    public String getPayStatusStr() {

        if (this.payStatus != null) {
            if (payStatus == 0) {
                payStatusStr = "未缴费";
                return payStatusStr;
            }

            if (payStatus == 1) {
                payStatusStr = "已缴费";
                return payStatusStr;
            }

        }
        return payStatusStr;
    }

    public void setPayStatusStr(String payStatusStr) {
        this.payStatusStr = payStatusStr;
    }

    public String getAuditStatusStr() {
        if (auditStatus == 0) {
            auditStatusStr = "未审核";
            return auditStatusStr;
        }
        if (auditStatus == 1) {
            auditStatusStr = "审核通过";
            return auditStatusStr;
        }
        if (auditStatus == 2) {
            auditStatusStr = "审核未通过";
            return auditStatusStr;
        }
        return auditStatusStr;
    }




    public void setAuditStatusStr(String auditStatusStr) {
        this.auditStatusStr = auditStatusStr;
    }

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

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality == null ? null : nationality.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getExaminationCard() {
        return examinationCard;
    }

    public void setExaminationCard(String examinationCard) {
        this.examinationCard = examinationCard == null ? null : examinationCard.trim();
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit == null ? null : unit.trim();
    }

    public String getEvaluationAddress() {
        return evaluationAddress;
    }

    public void setEvaluationAddress(String evaluationAddress) {
        this.evaluationAddress = evaluationAddress == null ? null : evaluationAddress.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public Integer getEnrolSceneId() {
        return enrolSceneId;
    }

    public void setEnrolSceneId(Integer enrolSceneId) {
        this.enrolSceneId = enrolSceneId;
    }

    public String getRecommendUnit() {
        return recommendUnit;
    }

    public void setRecommendUnit(String recommendUnit) {
        this.recommendUnit = recommendUnit == null ? null : recommendUnit.trim();
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUserLogPhone() {
        return userLogPhone;
    }

    public void setUserLogPhone(String userLogPhone) {
        this.userLogPhone = userLogPhone == null ? null : userLogPhone.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getExaminationResults() {
        return examinationResults;
    }

    public void setExaminationResults(Integer examinationResults) {
        this.examinationResults = examinationResults;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName == null ? null : institutionName.trim();
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