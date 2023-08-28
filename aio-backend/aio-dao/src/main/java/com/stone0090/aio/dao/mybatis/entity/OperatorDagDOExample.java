package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorDagDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperatorDagDOExample() {
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

        public Criteria andDagNameIsNull() {
            addCriterion("DAG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDagNameIsNotNull() {
            addCriterion("DAG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDagNameEqualTo(String value) {
            addCriterion("DAG_NAME =", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameNotEqualTo(String value) {
            addCriterion("DAG_NAME <>", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameGreaterThan(String value) {
            addCriterion("DAG_NAME >", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameGreaterThanOrEqualTo(String value) {
            addCriterion("DAG_NAME >=", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameLessThan(String value) {
            addCriterion("DAG_NAME <", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameLessThanOrEqualTo(String value) {
            addCriterion("DAG_NAME <=", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameLike(String value) {
            addCriterion("DAG_NAME like", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameNotLike(String value) {
            addCriterion("DAG_NAME not like", value, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameIn(List<String> values) {
            addCriterion("DAG_NAME in", values, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameNotIn(List<String> values) {
            addCriterion("DAG_NAME not in", values, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameBetween(String value1, String value2) {
            addCriterion("DAG_NAME between", value1, value2, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagNameNotBetween(String value1, String value2) {
            addCriterion("DAG_NAME not between", value1, value2, "dagName");
            return (Criteria) this;
        }

        public Criteria andDagDescIsNull() {
            addCriterion("DAG_DESC is null");
            return (Criteria) this;
        }

        public Criteria andDagDescIsNotNull() {
            addCriterion("DAG_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andDagDescEqualTo(String value) {
            addCriterion("DAG_DESC =", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescNotEqualTo(String value) {
            addCriterion("DAG_DESC <>", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescGreaterThan(String value) {
            addCriterion("DAG_DESC >", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescGreaterThanOrEqualTo(String value) {
            addCriterion("DAG_DESC >=", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescLessThan(String value) {
            addCriterion("DAG_DESC <", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescLessThanOrEqualTo(String value) {
            addCriterion("DAG_DESC <=", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescLike(String value) {
            addCriterion("DAG_DESC like", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescNotLike(String value) {
            addCriterion("DAG_DESC not like", value, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescIn(List<String> values) {
            addCriterion("DAG_DESC in", values, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescNotIn(List<String> values) {
            addCriterion("DAG_DESC not in", values, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescBetween(String value1, String value2) {
            addCriterion("DAG_DESC between", value1, value2, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagDescNotBetween(String value1, String value2) {
            addCriterion("DAG_DESC not between", value1, value2, "dagDesc");
            return (Criteria) this;
        }

        public Criteria andDagStatusIsNull() {
            addCriterion("DAG_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andDagStatusIsNotNull() {
            addCriterion("DAG_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andDagStatusEqualTo(String value) {
            addCriterion("DAG_STATUS =", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusNotEqualTo(String value) {
            addCriterion("DAG_STATUS <>", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusGreaterThan(String value) {
            addCriterion("DAG_STATUS >", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DAG_STATUS >=", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusLessThan(String value) {
            addCriterion("DAG_STATUS <", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusLessThanOrEqualTo(String value) {
            addCriterion("DAG_STATUS <=", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusLike(String value) {
            addCriterion("DAG_STATUS like", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusNotLike(String value) {
            addCriterion("DAG_STATUS not like", value, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusIn(List<String> values) {
            addCriterion("DAG_STATUS in", values, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusNotIn(List<String> values) {
            addCriterion("DAG_STATUS not in", values, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusBetween(String value1, String value2) {
            addCriterion("DAG_STATUS between", value1, value2, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagStatusNotBetween(String value1, String value2) {
            addCriterion("DAG_STATUS not between", value1, value2, "dagStatus");
            return (Criteria) this;
        }

        public Criteria andDagDataIsNull() {
            addCriterion("DAG_DATA is null");
            return (Criteria) this;
        }

        public Criteria andDagDataIsNotNull() {
            addCriterion("DAG_DATA is not null");
            return (Criteria) this;
        }

        public Criteria andDagDataEqualTo(String value) {
            addCriterion("DAG_DATA =", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataNotEqualTo(String value) {
            addCriterion("DAG_DATA <>", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataGreaterThan(String value) {
            addCriterion("DAG_DATA >", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataGreaterThanOrEqualTo(String value) {
            addCriterion("DAG_DATA >=", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataLessThan(String value) {
            addCriterion("DAG_DATA <", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataLessThanOrEqualTo(String value) {
            addCriterion("DAG_DATA <=", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataLike(String value) {
            addCriterion("DAG_DATA like", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataNotLike(String value) {
            addCriterion("DAG_DATA not like", value, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataIn(List<String> values) {
            addCriterion("DAG_DATA in", values, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataNotIn(List<String> values) {
            addCriterion("DAG_DATA not in", values, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataBetween(String value1, String value2) {
            addCriterion("DAG_DATA between", value1, value2, "dagData");
            return (Criteria) this;
        }

        public Criteria andDagDataNotBetween(String value1, String value2) {
            addCriterion("DAG_DATA not between", value1, value2, "dagData");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIsNull() {
            addCriterion("PUBLISH_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIsNotNull() {
            addCriterion("PUBLISH_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTypeEqualTo(String value) {
            addCriterion("PUBLISH_TYPE =", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotEqualTo(String value) {
            addCriterion("PUBLISH_TYPE <>", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThan(String value) {
            addCriterion("PUBLISH_TYPE >", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE >=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThan(String value) {
            addCriterion("PUBLISH_TYPE <", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_TYPE <=", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeLike(String value) {
            addCriterion("PUBLISH_TYPE like", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotLike(String value) {
            addCriterion("PUBLISH_TYPE not like", value, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeIn(List<String> values) {
            addCriterion("PUBLISH_TYPE in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotIn(List<String> values) {
            addCriterion("PUBLISH_TYPE not in", values, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE between", value1, value2, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishTypeNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_TYPE not between", value1, value2, "publishType");
            return (Criteria) this;
        }

        public Criteria andPublishConfigIsNull() {
            addCriterion("PUBLISH_CONFIG is null");
            return (Criteria) this;
        }

        public Criteria andPublishConfigIsNotNull() {
            addCriterion("PUBLISH_CONFIG is not null");
            return (Criteria) this;
        }

        public Criteria andPublishConfigEqualTo(String value) {
            addCriterion("PUBLISH_CONFIG =", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigNotEqualTo(String value) {
            addCriterion("PUBLISH_CONFIG <>", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigGreaterThan(String value) {
            addCriterion("PUBLISH_CONFIG >", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigGreaterThanOrEqualTo(String value) {
            addCriterion("PUBLISH_CONFIG >=", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigLessThan(String value) {
            addCriterion("PUBLISH_CONFIG <", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigLessThanOrEqualTo(String value) {
            addCriterion("PUBLISH_CONFIG <=", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigLike(String value) {
            addCriterion("PUBLISH_CONFIG like", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigNotLike(String value) {
            addCriterion("PUBLISH_CONFIG not like", value, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigIn(List<String> values) {
            addCriterion("PUBLISH_CONFIG in", values, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigNotIn(List<String> values) {
            addCriterion("PUBLISH_CONFIG not in", values, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigBetween(String value1, String value2) {
            addCriterion("PUBLISH_CONFIG between", value1, value2, "publishConfig");
            return (Criteria) this;
        }

        public Criteria andPublishConfigNotBetween(String value1, String value2) {
            addCriterion("PUBLISH_CONFIG not between", value1, value2, "publishConfig");
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