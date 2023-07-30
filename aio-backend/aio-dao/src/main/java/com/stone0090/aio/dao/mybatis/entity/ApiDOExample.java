package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiDOExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("GMT_CREATE is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("GMT_CREATE is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("GMT_CREATE =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("GMT_CREATE <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("GMT_CREATE >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("GMT_CREATE <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("GMT_CREATE <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("GMT_CREATE in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("GMT_CREATE not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("GMT_CREATE not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("GMT_MODIFIED is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("GMT_MODIFIED is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("GMT_MODIFIED =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("GMT_MODIFIED >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("GMT_MODIFIED <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("GMT_MODIFIED <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("GMT_MODIFIED in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("GMT_MODIFIED not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
            addCriterion("GMT_MODIFIED not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNull() {
            addCriterion("IS_DELETED is null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIsNotNull() {
            addCriterion("IS_DELETED is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeletedEqualTo(Integer value) {
            addCriterion("IS_DELETED =", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotEqualTo(Integer value) {
            addCriterion("IS_DELETED <>", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThan(Integer value) {
            addCriterion("IS_DELETED >", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETED >=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThan(Integer value) {
            addCriterion("IS_DELETED <", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("IS_DELETED <=", value, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedIn(List<Integer> values) {
            addCriterion("IS_DELETED in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotIn(List<Integer> values) {
            addCriterion("IS_DELETED not in", values, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETED between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andIsDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("IS_DELETED not between", value1, value2, "isDeleted");
            return (Criteria) this;
        }

        public Criteria andApiUuidIsNull() {
            addCriterion("API_UUID is null");
            return (Criteria) this;
        }

        public Criteria andApiUuidIsNotNull() {
            addCriterion("API_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andApiUuidEqualTo(String value) {
            addCriterion("API_UUID =", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidNotEqualTo(String value) {
            addCriterion("API_UUID <>", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidGreaterThan(String value) {
            addCriterion("API_UUID >", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidGreaterThanOrEqualTo(String value) {
            addCriterion("API_UUID >=", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidLessThan(String value) {
            addCriterion("API_UUID <", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidLessThanOrEqualTo(String value) {
            addCriterion("API_UUID <=", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidLike(String value) {
            addCriterion("API_UUID like", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidNotLike(String value) {
            addCriterion("API_UUID not like", value, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidIn(List<String> values) {
            addCriterion("API_UUID in", values, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidNotIn(List<String> values) {
            addCriterion("API_UUID not in", values, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidBetween(String value1, String value2) {
            addCriterion("API_UUID between", value1, value2, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiUuidNotBetween(String value1, String value2) {
            addCriterion("API_UUID not between", value1, value2, "apiUuid");
            return (Criteria) this;
        }

        public Criteria andApiNameIsNull() {
            addCriterion("API_NAME is null");
            return (Criteria) this;
        }

        public Criteria andApiNameIsNotNull() {
            addCriterion("API_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andApiNameEqualTo(String value) {
            addCriterion("API_NAME =", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotEqualTo(String value) {
            addCriterion("API_NAME <>", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameGreaterThan(String value) {
            addCriterion("API_NAME >", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameGreaterThanOrEqualTo(String value) {
            addCriterion("API_NAME >=", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLessThan(String value) {
            addCriterion("API_NAME <", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLessThanOrEqualTo(String value) {
            addCriterion("API_NAME <=", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameLike(String value) {
            addCriterion("API_NAME like", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotLike(String value) {
            addCriterion("API_NAME not like", value, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameIn(List<String> values) {
            addCriterion("API_NAME in", values, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotIn(List<String> values) {
            addCriterion("API_NAME not in", values, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameBetween(String value1, String value2) {
            addCriterion("API_NAME between", value1, value2, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiNameNotBetween(String value1, String value2) {
            addCriterion("API_NAME not between", value1, value2, "apiName");
            return (Criteria) this;
        }

        public Criteria andApiTypeIsNull() {
            addCriterion("API_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andApiTypeIsNotNull() {
            addCriterion("API_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andApiTypeEqualTo(String value) {
            addCriterion("API_TYPE =", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeNotEqualTo(String value) {
            addCriterion("API_TYPE <>", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeGreaterThan(String value) {
            addCriterion("API_TYPE >", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeGreaterThanOrEqualTo(String value) {
            addCriterion("API_TYPE >=", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeLessThan(String value) {
            addCriterion("API_TYPE <", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeLessThanOrEqualTo(String value) {
            addCriterion("API_TYPE <=", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeLike(String value) {
            addCriterion("API_TYPE like", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeNotLike(String value) {
            addCriterion("API_TYPE not like", value, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeIn(List<String> values) {
            addCriterion("API_TYPE in", values, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeNotIn(List<String> values) {
            addCriterion("API_TYPE not in", values, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeBetween(String value1, String value2) {
            addCriterion("API_TYPE between", value1, value2, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiTypeNotBetween(String value1, String value2) {
            addCriterion("API_TYPE not between", value1, value2, "apiType");
            return (Criteria) this;
        }

        public Criteria andApiStatusIsNull() {
            addCriterion("API_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andApiStatusIsNotNull() {
            addCriterion("API_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andApiStatusEqualTo(String value) {
            addCriterion("API_STATUS =", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusNotEqualTo(String value) {
            addCriterion("API_STATUS <>", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusGreaterThan(String value) {
            addCriterion("API_STATUS >", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusGreaterThanOrEqualTo(String value) {
            addCriterion("API_STATUS >=", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusLessThan(String value) {
            addCriterion("API_STATUS <", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusLessThanOrEqualTo(String value) {
            addCriterion("API_STATUS <=", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusLike(String value) {
            addCriterion("API_STATUS like", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusNotLike(String value) {
            addCriterion("API_STATUS not like", value, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusIn(List<String> values) {
            addCriterion("API_STATUS in", values, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusNotIn(List<String> values) {
            addCriterion("API_STATUS not in", values, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusBetween(String value1, String value2) {
            addCriterion("API_STATUS between", value1, value2, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andApiStatusNotBetween(String value1, String value2) {
            addCriterion("API_STATUS not between", value1, value2, "apiStatus");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNull() {
            addCriterion("TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTypeIdIsNotNull() {
            addCriterion("TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTypeIdEqualTo(Integer value) {
            addCriterion("TYPE_ID =", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotEqualTo(Integer value) {
            addCriterion("TYPE_ID <>", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThan(Integer value) {
            addCriterion("TYPE_ID >", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("TYPE_ID >=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThan(Integer value) {
            addCriterion("TYPE_ID <", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdLessThanOrEqualTo(Integer value) {
            addCriterion("TYPE_ID <=", value, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdIn(List<Integer> values) {
            addCriterion("TYPE_ID in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotIn(List<Integer> values) {
            addCriterion("TYPE_ID not in", values, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdBetween(Integer value1, Integer value2) {
            addCriterion("TYPE_ID between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andTypeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("TYPE_ID not between", value1, value2, "typeId");
            return (Criteria) this;
        }

        public Criteria andApiUrlIsNull() {
            addCriterion("API_URL is null");
            return (Criteria) this;
        }

        public Criteria andApiUrlIsNotNull() {
            addCriterion("API_URL is not null");
            return (Criteria) this;
        }

        public Criteria andApiUrlEqualTo(String value) {
            addCriterion("API_URL =", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlNotEqualTo(String value) {
            addCriterion("API_URL <>", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlGreaterThan(String value) {
            addCriterion("API_URL >", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlGreaterThanOrEqualTo(String value) {
            addCriterion("API_URL >=", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlLessThan(String value) {
            addCriterion("API_URL <", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlLessThanOrEqualTo(String value) {
            addCriterion("API_URL <=", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlLike(String value) {
            addCriterion("API_URL like", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlNotLike(String value) {
            addCriterion("API_URL not like", value, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlIn(List<String> values) {
            addCriterion("API_URL in", values, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlNotIn(List<String> values) {
            addCriterion("API_URL not in", values, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlBetween(String value1, String value2) {
            addCriterion("API_URL between", value1, value2, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andApiUrlNotBetween(String value1, String value2) {
            addCriterion("API_URL not between", value1, value2, "apiUrl");
            return (Criteria) this;
        }

        public Criteria andInputParamIsNull() {
            addCriterion("INPUT_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andInputParamIsNotNull() {
            addCriterion("INPUT_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andInputParamEqualTo(String value) {
            addCriterion("INPUT_PARAM =", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamNotEqualTo(String value) {
            addCriterion("INPUT_PARAM <>", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamGreaterThan(String value) {
            addCriterion("INPUT_PARAM >", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_PARAM >=", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamLessThan(String value) {
            addCriterion("INPUT_PARAM <", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamLessThanOrEqualTo(String value) {
            addCriterion("INPUT_PARAM <=", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamLike(String value) {
            addCriterion("INPUT_PARAM like", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamNotLike(String value) {
            addCriterion("INPUT_PARAM not like", value, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamIn(List<String> values) {
            addCriterion("INPUT_PARAM in", values, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamNotIn(List<String> values) {
            addCriterion("INPUT_PARAM not in", values, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamBetween(String value1, String value2) {
            addCriterion("INPUT_PARAM between", value1, value2, "inputParam");
            return (Criteria) this;
        }

        public Criteria andInputParamNotBetween(String value1, String value2) {
            addCriterion("INPUT_PARAM not between", value1, value2, "inputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamIsNull() {
            addCriterion("OUTPUT_PARAM is null");
            return (Criteria) this;
        }

        public Criteria andOutputParamIsNotNull() {
            addCriterion("OUTPUT_PARAM is not null");
            return (Criteria) this;
        }

        public Criteria andOutputParamEqualTo(String value) {
            addCriterion("OUTPUT_PARAM =", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamNotEqualTo(String value) {
            addCriterion("OUTPUT_PARAM <>", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamGreaterThan(String value) {
            addCriterion("OUTPUT_PARAM >", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamGreaterThanOrEqualTo(String value) {
            addCriterion("OUTPUT_PARAM >=", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamLessThan(String value) {
            addCriterion("OUTPUT_PARAM <", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamLessThanOrEqualTo(String value) {
            addCriterion("OUTPUT_PARAM <=", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamLike(String value) {
            addCriterion("OUTPUT_PARAM like", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamNotLike(String value) {
            addCriterion("OUTPUT_PARAM not like", value, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamIn(List<String> values) {
            addCriterion("OUTPUT_PARAM in", values, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamNotIn(List<String> values) {
            addCriterion("OUTPUT_PARAM not in", values, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamBetween(String value1, String value2) {
            addCriterion("OUTPUT_PARAM between", value1, value2, "outputParam");
            return (Criteria) this;
        }

        public Criteria andOutputParamNotBetween(String value1, String value2) {
            addCriterion("OUTPUT_PARAM not between", value1, value2, "outputParam");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeIsNull() {
            addCriterion("INVOKE_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeIsNotNull() {
            addCriterion("INVOKE_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeEqualTo(String value) {
            addCriterion("INVOKE_TYPE =", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeNotEqualTo(String value) {
            addCriterion("INVOKE_TYPE <>", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeGreaterThan(String value) {
            addCriterion("INVOKE_TYPE >", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOKE_TYPE >=", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeLessThan(String value) {
            addCriterion("INVOKE_TYPE <", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeLessThanOrEqualTo(String value) {
            addCriterion("INVOKE_TYPE <=", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeLike(String value) {
            addCriterion("INVOKE_TYPE like", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeNotLike(String value) {
            addCriterion("INVOKE_TYPE not like", value, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeIn(List<String> values) {
            addCriterion("INVOKE_TYPE in", values, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeNotIn(List<String> values) {
            addCriterion("INVOKE_TYPE not in", values, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeBetween(String value1, String value2) {
            addCriterion("INVOKE_TYPE between", value1, value2, "invokeType");
            return (Criteria) this;
        }

        public Criteria andInvokeTypeNotBetween(String value1, String value2) {
            addCriterion("INVOKE_TYPE not between", value1, value2, "invokeType");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlIsNull() {
            addCriterion("CALLBACK_URL is null");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlIsNotNull() {
            addCriterion("CALLBACK_URL is not null");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlEqualTo(String value) {
            addCriterion("CALLBACK_URL =", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlNotEqualTo(String value) {
            addCriterion("CALLBACK_URL <>", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlGreaterThan(String value) {
            addCriterion("CALLBACK_URL >", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlGreaterThanOrEqualTo(String value) {
            addCriterion("CALLBACK_URL >=", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlLessThan(String value) {
            addCriterion("CALLBACK_URL <", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlLessThanOrEqualTo(String value) {
            addCriterion("CALLBACK_URL <=", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlLike(String value) {
            addCriterion("CALLBACK_URL like", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlNotLike(String value) {
            addCriterion("CALLBACK_URL not like", value, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlIn(List<String> values) {
            addCriterion("CALLBACK_URL in", values, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlNotIn(List<String> values) {
            addCriterion("CALLBACK_URL not in", values, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlBetween(String value1, String value2) {
            addCriterion("CALLBACK_URL between", value1, value2, "callbackUrl");
            return (Criteria) this;
        }

        public Criteria andCallbackUrlNotBetween(String value1, String value2) {
            addCriterion("CALLBACK_URL not between", value1, value2, "callbackUrl");
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