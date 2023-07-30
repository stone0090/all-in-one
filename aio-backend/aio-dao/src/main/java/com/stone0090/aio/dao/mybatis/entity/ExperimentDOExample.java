package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExperimentDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ExperimentDOExample() {
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

        public Criteria andExNameIsNull() {
            addCriterion("EX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andExNameIsNotNull() {
            addCriterion("EX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andExNameEqualTo(String value) {
            addCriterion("EX_NAME =", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotEqualTo(String value) {
            addCriterion("EX_NAME <>", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameGreaterThan(String value) {
            addCriterion("EX_NAME >", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameGreaterThanOrEqualTo(String value) {
            addCriterion("EX_NAME >=", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLessThan(String value) {
            addCriterion("EX_NAME <", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLessThanOrEqualTo(String value) {
            addCriterion("EX_NAME <=", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameLike(String value) {
            addCriterion("EX_NAME like", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotLike(String value) {
            addCriterion("EX_NAME not like", value, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameIn(List<String> values) {
            addCriterion("EX_NAME in", values, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotIn(List<String> values) {
            addCriterion("EX_NAME not in", values, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameBetween(String value1, String value2) {
            addCriterion("EX_NAME between", value1, value2, "exName");
            return (Criteria) this;
        }

        public Criteria andExNameNotBetween(String value1, String value2) {
            addCriterion("EX_NAME not between", value1, value2, "exName");
            return (Criteria) this;
        }

        public Criteria andExDescIsNull() {
            addCriterion("EX_DESC is null");
            return (Criteria) this;
        }

        public Criteria andExDescIsNotNull() {
            addCriterion("EX_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andExDescEqualTo(String value) {
            addCriterion("EX_DESC =", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescNotEqualTo(String value) {
            addCriterion("EX_DESC <>", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescGreaterThan(String value) {
            addCriterion("EX_DESC >", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescGreaterThanOrEqualTo(String value) {
            addCriterion("EX_DESC >=", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescLessThan(String value) {
            addCriterion("EX_DESC <", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescLessThanOrEqualTo(String value) {
            addCriterion("EX_DESC <=", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescLike(String value) {
            addCriterion("EX_DESC like", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescNotLike(String value) {
            addCriterion("EX_DESC not like", value, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescIn(List<String> values) {
            addCriterion("EX_DESC in", values, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescNotIn(List<String> values) {
            addCriterion("EX_DESC not in", values, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescBetween(String value1, String value2) {
            addCriterion("EX_DESC between", value1, value2, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExDescNotBetween(String value1, String value2) {
            addCriterion("EX_DESC not between", value1, value2, "exDesc");
            return (Criteria) this;
        }

        public Criteria andExStatusIsNull() {
            addCriterion("EX_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andExStatusIsNotNull() {
            addCriterion("EX_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andExStatusEqualTo(String value) {
            addCriterion("EX_STATUS =", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotEqualTo(String value) {
            addCriterion("EX_STATUS <>", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusGreaterThan(String value) {
            addCriterion("EX_STATUS >", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusGreaterThanOrEqualTo(String value) {
            addCriterion("EX_STATUS >=", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusLessThan(String value) {
            addCriterion("EX_STATUS <", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusLessThanOrEqualTo(String value) {
            addCriterion("EX_STATUS <=", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusLike(String value) {
            addCriterion("EX_STATUS like", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotLike(String value) {
            addCriterion("EX_STATUS not like", value, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusIn(List<String> values) {
            addCriterion("EX_STATUS in", values, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotIn(List<String> values) {
            addCriterion("EX_STATUS not in", values, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusBetween(String value1, String value2) {
            addCriterion("EX_STATUS between", value1, value2, "exStatus");
            return (Criteria) this;
        }

        public Criteria andExStatusNotBetween(String value1, String value2) {
            addCriterion("EX_STATUS not between", value1, value2, "exStatus");
            return (Criteria) this;
        }

        public Criteria andNodeDagIsNull() {
            addCriterion("NODE_DAG is null");
            return (Criteria) this;
        }

        public Criteria andNodeDagIsNotNull() {
            addCriterion("NODE_DAG is not null");
            return (Criteria) this;
        }

        public Criteria andNodeDagEqualTo(String value) {
            addCriterion("NODE_DAG =", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagNotEqualTo(String value) {
            addCriterion("NODE_DAG <>", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagGreaterThan(String value) {
            addCriterion("NODE_DAG >", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_DAG >=", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagLessThan(String value) {
            addCriterion("NODE_DAG <", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagLessThanOrEqualTo(String value) {
            addCriterion("NODE_DAG <=", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagLike(String value) {
            addCriterion("NODE_DAG like", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagNotLike(String value) {
            addCriterion("NODE_DAG not like", value, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagIn(List<String> values) {
            addCriterion("NODE_DAG in", values, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagNotIn(List<String> values) {
            addCriterion("NODE_DAG not in", values, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagBetween(String value1, String value2) {
            addCriterion("NODE_DAG between", value1, value2, "nodeDag");
            return (Criteria) this;
        }

        public Criteria andNodeDagNotBetween(String value1, String value2) {
            addCriterion("NODE_DAG not between", value1, value2, "nodeDag");
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