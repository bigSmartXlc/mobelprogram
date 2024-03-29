package com.hbtcsrzzx.ssm.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SharingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SharingExample() {
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

        public Criteria andInstitutionalNatureIsNull() {
            addCriterion("institutional_nature is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureIsNotNull() {
            addCriterion("institutional_nature is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureEqualTo(String value) {
            addCriterion("institutional_nature =", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureNotEqualTo(String value) {
            addCriterion("institutional_nature <>", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureGreaterThan(String value) {
            addCriterion("institutional_nature >", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureGreaterThanOrEqualTo(String value) {
            addCriterion("institutional_nature >=", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureLessThan(String value) {
            addCriterion("institutional_nature <", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureLessThanOrEqualTo(String value) {
            addCriterion("institutional_nature <=", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureLike(String value) {
            addCriterion("institutional_nature like", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureNotLike(String value) {
            addCriterion("institutional_nature not like", value, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureIn(List<String> values) {
            addCriterion("institutional_nature in", values, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureNotIn(List<String> values) {
            addCriterion("institutional_nature not in", values, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureBetween(String value1, String value2) {
            addCriterion("institutional_nature between", value1, value2, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andInstitutionalNatureNotBetween(String value1, String value2) {
            addCriterion("institutional_nature not between", value1, value2, "institutionalNature");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionIsNull() {
            addCriterion("salesman_commission is null");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionIsNotNull() {
            addCriterion("salesman_commission is not null");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionEqualTo(Integer value) {
            addCriterion("salesman_commission =", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionNotEqualTo(Integer value) {
            addCriterion("salesman_commission <>", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionGreaterThan(Integer value) {
            addCriterion("salesman_commission >", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionGreaterThanOrEqualTo(Integer value) {
            addCriterion("salesman_commission >=", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionLessThan(Integer value) {
            addCriterion("salesman_commission <", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionLessThanOrEqualTo(Integer value) {
            addCriterion("salesman_commission <=", value, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionIn(List<Integer> values) {
            addCriterion("salesman_commission in", values, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionNotIn(List<Integer> values) {
            addCriterion("salesman_commission not in", values, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionBetween(Integer value1, Integer value2) {
            addCriterion("salesman_commission between", value1, value2, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andSalesmanCommissionNotBetween(Integer value1, Integer value2) {
            addCriterion("salesman_commission not between", value1, value2, "salesmanCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionIsNull() {
            addCriterion("institutional_commission is null");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionIsNotNull() {
            addCriterion("institutional_commission is not null");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionEqualTo(Integer value) {
            addCriterion("institutional_commission =", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionNotEqualTo(Integer value) {
            addCriterion("institutional_commission <>", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionGreaterThan(Integer value) {
            addCriterion("institutional_commission >", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionGreaterThanOrEqualTo(Integer value) {
            addCriterion("institutional_commission >=", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionLessThan(Integer value) {
            addCriterion("institutional_commission <", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionLessThanOrEqualTo(Integer value) {
            addCriterion("institutional_commission <=", value, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionIn(List<Integer> values) {
            addCriterion("institutional_commission in", values, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionNotIn(List<Integer> values) {
            addCriterion("institutional_commission not in", values, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionBetween(Integer value1, Integer value2) {
            addCriterion("institutional_commission between", value1, value2, "institutionalCommission");
            return (Criteria) this;
        }

        public Criteria andInstitutionalCommissionNotBetween(Integer value1, Integer value2) {
            addCriterion("institutional_commission not between", value1, value2, "institutionalCommission");
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

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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