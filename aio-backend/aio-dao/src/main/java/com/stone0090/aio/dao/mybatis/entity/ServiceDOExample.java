package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ServiceDOExample() {
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

        public Criteria andSvcUuidIsNull() {
            addCriterion("SVC_UUID is null");
            return (Criteria) this;
        }

        public Criteria andSvcUuidIsNotNull() {
            addCriterion("SVC_UUID is not null");
            return (Criteria) this;
        }

        public Criteria andSvcUuidEqualTo(String value) {
            addCriterion("SVC_UUID =", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidNotEqualTo(String value) {
            addCriterion("SVC_UUID <>", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidGreaterThan(String value) {
            addCriterion("SVC_UUID >", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidGreaterThanOrEqualTo(String value) {
            addCriterion("SVC_UUID >=", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidLessThan(String value) {
            addCriterion("SVC_UUID <", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidLessThanOrEqualTo(String value) {
            addCriterion("SVC_UUID <=", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidLike(String value) {
            addCriterion("SVC_UUID like", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidNotLike(String value) {
            addCriterion("SVC_UUID not like", value, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidIn(List<String> values) {
            addCriterion("SVC_UUID in", values, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidNotIn(List<String> values) {
            addCriterion("SVC_UUID not in", values, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidBetween(String value1, String value2) {
            addCriterion("SVC_UUID between", value1, value2, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcUuidNotBetween(String value1, String value2) {
            addCriterion("SVC_UUID not between", value1, value2, "svcUuid");
            return (Criteria) this;
        }

        public Criteria andSvcNameIsNull() {
            addCriterion("SVC_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSvcNameIsNotNull() {
            addCriterion("SVC_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSvcNameEqualTo(String value) {
            addCriterion("SVC_NAME =", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameNotEqualTo(String value) {
            addCriterion("SVC_NAME <>", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameGreaterThan(String value) {
            addCriterion("SVC_NAME >", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameGreaterThanOrEqualTo(String value) {
            addCriterion("SVC_NAME >=", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameLessThan(String value) {
            addCriterion("SVC_NAME <", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameLessThanOrEqualTo(String value) {
            addCriterion("SVC_NAME <=", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameLike(String value) {
            addCriterion("SVC_NAME like", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameNotLike(String value) {
            addCriterion("SVC_NAME not like", value, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameIn(List<String> values) {
            addCriterion("SVC_NAME in", values, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameNotIn(List<String> values) {
            addCriterion("SVC_NAME not in", values, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameBetween(String value1, String value2) {
            addCriterion("SVC_NAME between", value1, value2, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcNameNotBetween(String value1, String value2) {
            addCriterion("SVC_NAME not between", value1, value2, "svcName");
            return (Criteria) this;
        }

        public Criteria andSvcStatusIsNull() {
            addCriterion("SVC_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andSvcStatusIsNotNull() {
            addCriterion("SVC_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andSvcStatusEqualTo(String value) {
            addCriterion("SVC_STATUS =", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusNotEqualTo(String value) {
            addCriterion("SVC_STATUS <>", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusGreaterThan(String value) {
            addCriterion("SVC_STATUS >", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusGreaterThanOrEqualTo(String value) {
            addCriterion("SVC_STATUS >=", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusLessThan(String value) {
            addCriterion("SVC_STATUS <", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusLessThanOrEqualTo(String value) {
            addCriterion("SVC_STATUS <=", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusLike(String value) {
            addCriterion("SVC_STATUS like", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusNotLike(String value) {
            addCriterion("SVC_STATUS not like", value, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusIn(List<String> values) {
            addCriterion("SVC_STATUS in", values, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusNotIn(List<String> values) {
            addCriterion("SVC_STATUS not in", values, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusBetween(String value1, String value2) {
            addCriterion("SVC_STATUS between", value1, value2, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcStatusNotBetween(String value1, String value2) {
            addCriterion("SVC_STATUS not between", value1, value2, "svcStatus");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIsNull() {
            addCriterion("SVC_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIsNotNull() {
            addCriterion("SVC_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andSvcTypeEqualTo(String value) {
            addCriterion("SVC_TYPE =", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotEqualTo(String value) {
            addCriterion("SVC_TYPE <>", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeGreaterThan(String value) {
            addCriterion("SVC_TYPE >", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeGreaterThanOrEqualTo(String value) {
            addCriterion("SVC_TYPE >=", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeLessThan(String value) {
            addCriterion("SVC_TYPE <", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeLessThanOrEqualTo(String value) {
            addCriterion("SVC_TYPE <=", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeLike(String value) {
            addCriterion("SVC_TYPE like", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotLike(String value) {
            addCriterion("SVC_TYPE not like", value, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeIn(List<String> values) {
            addCriterion("SVC_TYPE in", values, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotIn(List<String> values) {
            addCriterion("SVC_TYPE not in", values, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeBetween(String value1, String value2) {
            addCriterion("SVC_TYPE between", value1, value2, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcTypeNotBetween(String value1, String value2) {
            addCriterion("SVC_TYPE not between", value1, value2, "svcType");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdIsNull() {
            addCriterion("SVC_BIZ_ID is null");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdIsNotNull() {
            addCriterion("SVC_BIZ_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdEqualTo(Integer value) {
            addCriterion("SVC_BIZ_ID =", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdNotEqualTo(Integer value) {
            addCriterion("SVC_BIZ_ID <>", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdGreaterThan(Integer value) {
            addCriterion("SVC_BIZ_ID >", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("SVC_BIZ_ID >=", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdLessThan(Integer value) {
            addCriterion("SVC_BIZ_ID <", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdLessThanOrEqualTo(Integer value) {
            addCriterion("SVC_BIZ_ID <=", value, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdIn(List<Integer> values) {
            addCriterion("SVC_BIZ_ID in", values, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdNotIn(List<Integer> values) {
            addCriterion("SVC_BIZ_ID not in", values, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdBetween(Integer value1, Integer value2) {
            addCriterion("SVC_BIZ_ID between", value1, value2, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcBizIdNotBetween(Integer value1, Integer value2) {
            addCriterion("SVC_BIZ_ID not between", value1, value2, "svcBizId");
            return (Criteria) this;
        }

        public Criteria andSvcUrlIsNull() {
            addCriterion("SVC_URL is null");
            return (Criteria) this;
        }

        public Criteria andSvcUrlIsNotNull() {
            addCriterion("SVC_URL is not null");
            return (Criteria) this;
        }

        public Criteria andSvcUrlEqualTo(String value) {
            addCriterion("SVC_URL =", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlNotEqualTo(String value) {
            addCriterion("SVC_URL <>", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlGreaterThan(String value) {
            addCriterion("SVC_URL >", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlGreaterThanOrEqualTo(String value) {
            addCriterion("SVC_URL >=", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlLessThan(String value) {
            addCriterion("SVC_URL <", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlLessThanOrEqualTo(String value) {
            addCriterion("SVC_URL <=", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlLike(String value) {
            addCriterion("SVC_URL like", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlNotLike(String value) {
            addCriterion("SVC_URL not like", value, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlIn(List<String> values) {
            addCriterion("SVC_URL in", values, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlNotIn(List<String> values) {
            addCriterion("SVC_URL not in", values, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlBetween(String value1, String value2) {
            addCriterion("SVC_URL between", value1, value2, "svcUrl");
            return (Criteria) this;
        }

        public Criteria andSvcUrlNotBetween(String value1, String value2) {
            addCriterion("SVC_URL not between", value1, value2, "svcUrl");
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