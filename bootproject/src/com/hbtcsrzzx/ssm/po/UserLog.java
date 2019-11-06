package com.hbtcsrzzx.ssm.po;

import java.text.ParseException;
import java.util.Date;

import com.hbtcsrzzx.utils.DateUtils;

public class UserLog {
    private Integer id;

    private String phone;

    private String password;

    private String name;

    private Integer gender;

    private String nationality;

    private String nation;

    private Integer cityId;

    private Date birthday;

    private String idCard;

    private String unit;

    private String parentName;

    private String familyAddress;

    private String mailbox;

    private String familyPhone;

    private Date creationTime;

    private Date updateTime;

    private Integer state;

    private String distributionLevel;

    private String recommender;

    private Date recommendedTime;
    
    private String birthdayStr;

    private String genderStr;
    
    private City city;

    private String code;
    
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getGenderStr() {
		if (this.gender != null) {
			if (gender==0) {
				genderStr = "女";
				return genderStr;
			}

			if (gender==1) {
				genderStr = "男";
				return genderStr;
			}
		}
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		if(birthdayStr!=null){
			try {
				this.birthday = DateUtils.getStringConvertDate(birthdayStr, "yyyy-MM-dd");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		this.birthdayStr = birthdayStr;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName == null ? null : parentName.trim();
    }

    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress == null ? null : familyAddress.trim();
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox == null ? null : mailbox.trim();
    }

    public String getFamilyPhone() {
        return familyPhone;
    }

    public void setFamilyPhone(String familyPhone) {
        this.familyPhone = familyPhone == null ? null : familyPhone.trim();
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDistributionLevel() {
        return distributionLevel;
    }

    public void setDistributionLevel(String distributionLevel) {
        this.distributionLevel = distributionLevel;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public Date getRecommendedTime() {
        return recommendedTime;
    }

    public void setRecommendedTime(Date recommendedTime) {
        this.recommendedTime = recommendedTime;
    }
}