package com.hbtcsrzzx.ssm.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class GUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    protected int startRow;
    protected int pageSize;

    public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public GUserExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("gender is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("gender is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(String value) {
            addCriterion("gender =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(String value) {
            addCriterion("gender <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(String value) {
            addCriterion("gender >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(String value) {
            addCriterion("gender >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(String value) {
            addCriterion("gender <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(String value) {
            addCriterion("gender <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLike(String value) {
            addCriterion("gender like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotLike(String value) {
            addCriterion("gender not like", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<String> values) {
            addCriterion("gender in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<String> values) {
            addCriterion("gender not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(String value1, String value2) {
            addCriterion("gender between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(String value1, String value2) {
            addCriterion("gender not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("nationality =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("nationality <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("nationality >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("nationality >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("nationality <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("nationality <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("nationality like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("nationality not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("nationality in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("nationality not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("nationality between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("nationality not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationIsNull() {
            addCriterion("nation is null");
            return (Criteria) this;
        }

        public Criteria andNationIsNotNull() {
            addCriterion("nation is not null");
            return (Criteria) this;
        }

        public Criteria andNationEqualTo(String value) {
            addCriterion("nation =", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotEqualTo(String value) {
            addCriterion("nation <>", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThan(String value) {
            addCriterion("nation >", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationGreaterThanOrEqualTo(String value) {
            addCriterion("nation >=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThan(String value) {
            addCriterion("nation <", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLessThanOrEqualTo(String value) {
            addCriterion("nation <=", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationLike(String value) {
            addCriterion("nation like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotLike(String value) {
            addCriterion("nation not like", value, "nation");
            return (Criteria) this;
        }

        public Criteria andNationIn(List<String> values) {
            addCriterion("nation in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotIn(List<String> values) {
            addCriterion("nation not in", values, "nation");
            return (Criteria) this;
        }

        public Criteria andNationBetween(String value1, String value2) {
            addCriterion("nation between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andNationNotBetween(String value1, String value2) {
            addCriterion("nation not between", value1, value2, "nation");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterionForJDBCDate("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterionForJDBCDate("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterionForJDBCDate("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterionForJDBCDate("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterionForJDBCDate("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNull() {
            addCriterion("identity is null");
            return (Criteria) this;
        }

        public Criteria andIdentityIsNotNull() {
            addCriterion("identity is not null");
            return (Criteria) this;
        }

        public Criteria andIdentityEqualTo(String value) {
            addCriterion("identity =", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotEqualTo(String value) {
            addCriterion("identity <>", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThan(String value) {
            addCriterion("identity >", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityGreaterThanOrEqualTo(String value) {
            addCriterion("identity >=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThan(String value) {
            addCriterion("identity <", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLessThanOrEqualTo(String value) {
            addCriterion("identity <=", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityLike(String value) {
            addCriterion("identity like", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotLike(String value) {
            addCriterion("identity not like", value, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityIn(List<String> values) {
            addCriterion("identity in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotIn(List<String> values) {
            addCriterion("identity not in", values, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityBetween(String value1, String value2) {
            addCriterion("identity between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andIdentityNotBetween(String value1, String value2) {
            addCriterion("identity not between", value1, value2, "identity");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNull() {
            addCriterion("parent_name is null");
            return (Criteria) this;
        }

        public Criteria andParentNameIsNotNull() {
            addCriterion("parent_name is not null");
            return (Criteria) this;
        }

        public Criteria andParentNameEqualTo(String value) {
            addCriterion("parent_name =", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotEqualTo(String value) {
            addCriterion("parent_name <>", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThan(String value) {
            addCriterion("parent_name >", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameGreaterThanOrEqualTo(String value) {
            addCriterion("parent_name >=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThan(String value) {
            addCriterion("parent_name <", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLessThanOrEqualTo(String value) {
            addCriterion("parent_name <=", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameLike(String value) {
            addCriterion("parent_name like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotLike(String value) {
            addCriterion("parent_name not like", value, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameIn(List<String> values) {
            addCriterion("parent_name in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotIn(List<String> values) {
            addCriterion("parent_name not in", values, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameBetween(String value1, String value2) {
            addCriterion("parent_name between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andParentNameNotBetween(String value1, String value2) {
            addCriterion("parent_name not between", value1, value2, "parentName");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andEMailIsNull() {
            addCriterion("e_mail is null");
            return (Criteria) this;
        }

        public Criteria andEMailIsNotNull() {
            addCriterion("e_mail is not null");
            return (Criteria) this;
        }

        public Criteria andEMailEqualTo(String value) {
            addCriterion("e_mail =", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotEqualTo(String value) {
            addCriterion("e_mail <>", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailGreaterThan(String value) {
            addCriterion("e_mail >", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailGreaterThanOrEqualTo(String value) {
            addCriterion("e_mail >=", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLessThan(String value) {
            addCriterion("e_mail <", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLessThanOrEqualTo(String value) {
            addCriterion("e_mail <=", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailLike(String value) {
            addCriterion("e_mail like", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotLike(String value) {
            addCriterion("e_mail not like", value, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailIn(List<String> values) {
            addCriterion("e_mail in", values, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotIn(List<String> values) {
            addCriterion("e_mail not in", values, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailBetween(String value1, String value2) {
            addCriterion("e_mail between", value1, value2, "eMail");
            return (Criteria) this;
        }

        public Criteria andEMailNotBetween(String value1, String value2) {
            addCriterion("e_mail not between", value1, value2, "eMail");
            return (Criteria) this;
        }

        public Criteria andParentPhoneIsNull() {
            addCriterion("parent_phone is null");
            return (Criteria) this;
        }

        public Criteria andParentPhoneIsNotNull() {
            addCriterion("parent_phone is not null");
            return (Criteria) this;
        }

        public Criteria andParentPhoneEqualTo(String value) {
            addCriterion("parent_phone =", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneNotEqualTo(String value) {
            addCriterion("parent_phone <>", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneGreaterThan(String value) {
            addCriterion("parent_phone >", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("parent_phone >=", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneLessThan(String value) {
            addCriterion("parent_phone <", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneLessThanOrEqualTo(String value) {
            addCriterion("parent_phone <=", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneLike(String value) {
            addCriterion("parent_phone like", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneNotLike(String value) {
            addCriterion("parent_phone not like", value, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneIn(List<String> values) {
            addCriterion("parent_phone in", values, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneNotIn(List<String> values) {
            addCriterion("parent_phone not in", values, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneBetween(String value1, String value2) {
            addCriterion("parent_phone between", value1, value2, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andParentPhoneNotBetween(String value1, String value2) {
            addCriterion("parent_phone not between", value1, value2, "parentPhone");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNull() {
            addCriterion("regtime is null");
            return (Criteria) this;
        }

        public Criteria andRegtimeIsNotNull() {
            addCriterion("regtime is not null");
            return (Criteria) this;
        }

        public Criteria andRegtimeEqualTo(Date value) {
            addCriterion("regtime =", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotEqualTo(Date value) {
            addCriterion("regtime <>", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThan(Date value) {
            addCriterion("regtime >", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("regtime >=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThan(Date value) {
            addCriterion("regtime <", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeLessThanOrEqualTo(Date value) {
            addCriterion("regtime <=", value, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeIn(List<Date> values) {
            addCriterion("regtime in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotIn(List<Date> values) {
            addCriterion("regtime not in", values, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeBetween(Date value1, Date value2) {
            addCriterion("regtime between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andRegtimeNotBetween(Date value1, Date value2) {
            addCriterion("regtime not between", value1, value2, "regtime");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}