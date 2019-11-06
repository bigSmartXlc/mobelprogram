package com.hbtcsrzzx.ssm.po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GUser {
	private Integer id;

	private String name;

	private String gender;

	private String phone;

	private String nationality;

	private String nation;

	private String city;

	private Date birthday;

	private String identity;

	private String password;

	private String unit;

	private String parentName;

	private String address;

	private String eMail;

	private String parentPhone;

	private Date regtime;

	private Integer state;
	private String birthdayStr;

	private String genderStr;

	public String getGenderStr() {
		if (this.gender != null) {
			if (gender.equals("0")) {
				genderStr = "男";
				return genderStr;
			}

			if (gender.equals("1")) {
				genderStr = "女";
				return genderStr;
			}
		}
		return genderStr;
	}

	public void setGenderStr(String genderStr) {
		this.genderStr = genderStr;
	}

	public String getBirthdayStr() {
		if (birthday != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			birthdayStr = format.format(birthday);
		}
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {

		if (birthdayStr != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				this.birthday = format.parse(birthdayStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city == null ? null : city.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity == null ? null : identity.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail == null ? null : eMail.trim();
	}

	public String getParentPhone() {
		return parentPhone;
	}

	public void setParentPhone(String parentPhone) {
		this.parentPhone = parentPhone == null ? null : parentPhone.trim();
	}

	public Date getRegtime() {
		return regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}