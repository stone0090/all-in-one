package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NodeInstanceDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public NodeInstanceDOExample() {
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

        public Criteria andExIdIsNull() {
            addCriterion("EX_ID is null");
            return (Criteria) this;
        }

        public Criteria andExIdIsNotNull() {
            addCriterion("EX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andExIdEqualTo(Integer value) {
            addCriterion("EX_ID =", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdNotEqualTo(Integer value) {
            addCriterion("EX_ID <>", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdGreaterThan(Integer value) {
            addCriterion("EX_ID >", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("EX_ID >=", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdLessThan(Integer value) {
            addCriterion("EX_ID <", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdLessThanOrEqualTo(Integer value) {
            addCriterion("EX_ID <=", value, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdIn(List<Integer> values) {
            addCriterion("EX_ID in", values, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdNotIn(List<Integer> values) {
            addCriterion("EX_ID not in", values, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdBetween(Integer value1, Integer value2) {
            addCriterion("EX_ID between", value1, value2, "exId");
            return (Criteria) this;
        }

        public Criteria andExIdNotBetween(Integer value1, Integer value2) {
            addCriterion("EX_ID not between", value1, value2, "exId");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNull() {
            addCriterion("OP_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpIdIsNotNull() {
            addCriterion("OP_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpIdEqualTo(Integer value) {
            addCriterion("OP_ID =", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotEqualTo(Integer value) {
            addCriterion("OP_ID <>", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThan(Integer value) {
            addCriterion("OP_ID >", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("OP_ID >=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThan(Integer value) {
            addCriterion("OP_ID <", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdLessThanOrEqualTo(Integer value) {
            addCriterion("OP_ID <=", value, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdIn(List<Integer> values) {
            addCriterion("OP_ID in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotIn(List<Integer> values) {
            addCriterion("OP_ID not in", values, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdBetween(Integer value1, Integer value2) {
            addCriterion("OP_ID between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpIdNotBetween(Integer value1, Integer value2) {
            addCriterion("OP_ID not between", value1, value2, "opId");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNull() {
            addCriterion("OP_CODE is null");
            return (Criteria) this;
        }

        public Criteria andOpCodeIsNotNull() {
            addCriterion("OP_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andOpCodeEqualTo(String value) {
            addCriterion("OP_CODE =", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotEqualTo(String value) {
            addCriterion("OP_CODE <>", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThan(String value) {
            addCriterion("OP_CODE >", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeGreaterThanOrEqualTo(String value) {
            addCriterion("OP_CODE >=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThan(String value) {
            addCriterion("OP_CODE <", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLessThanOrEqualTo(String value) {
            addCriterion("OP_CODE <=", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeLike(String value) {
            addCriterion("OP_CODE like", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotLike(String value) {
            addCriterion("OP_CODE not like", value, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeIn(List<String> values) {
            addCriterion("OP_CODE in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotIn(List<String> values) {
            addCriterion("OP_CODE not in", values, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeBetween(String value1, String value2) {
            addCriterion("OP_CODE between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andOpCodeNotBetween(String value1, String value2) {
            addCriterion("OP_CODE not between", value1, value2, "opCode");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNull() {
            addCriterion("NODE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNodeNameIsNotNull() {
            addCriterion("NODE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNodeNameEqualTo(String value) {
            addCriterion("NODE_NAME =", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotEqualTo(String value) {
            addCriterion("NODE_NAME <>", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThan(String value) {
            addCriterion("NODE_NAME >", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_NAME >=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThan(String value) {
            addCriterion("NODE_NAME <", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLessThanOrEqualTo(String value) {
            addCriterion("NODE_NAME <=", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameLike(String value) {
            addCriterion("NODE_NAME like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotLike(String value) {
            addCriterion("NODE_NAME not like", value, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameIn(List<String> values) {
            addCriterion("NODE_NAME in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotIn(List<String> values) {
            addCriterion("NODE_NAME not in", values, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameBetween(String value1, String value2) {
            addCriterion("NODE_NAME between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andNodeNameNotBetween(String value1, String value2) {
            addCriterion("NODE_NAME not between", value1, value2, "nodeName");
            return (Criteria) this;
        }

        public Criteria andInputMappingIsNull() {
            addCriterion("INPUT_MAPPING is null");
            return (Criteria) this;
        }

        public Criteria andInputMappingIsNotNull() {
            addCriterion("INPUT_MAPPING is not null");
            return (Criteria) this;
        }

        public Criteria andInputMappingEqualTo(String value) {
            addCriterion("INPUT_MAPPING =", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingNotEqualTo(String value) {
            addCriterion("INPUT_MAPPING <>", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingGreaterThan(String value) {
            addCriterion("INPUT_MAPPING >", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_MAPPING >=", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingLessThan(String value) {
            addCriterion("INPUT_MAPPING <", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingLessThanOrEqualTo(String value) {
            addCriterion("INPUT_MAPPING <=", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingLike(String value) {
            addCriterion("INPUT_MAPPING like", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingNotLike(String value) {
            addCriterion("INPUT_MAPPING not like", value, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingIn(List<String> values) {
            addCriterion("INPUT_MAPPING in", values, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingNotIn(List<String> values) {
            addCriterion("INPUT_MAPPING not in", values, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingBetween(String value1, String value2) {
            addCriterion("INPUT_MAPPING between", value1, value2, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andInputMappingNotBetween(String value1, String value2) {
            addCriterion("INPUT_MAPPING not between", value1, value2, "inputMapping");
            return (Criteria) this;
        }

        public Criteria andNodeConfigIsNull() {
            addCriterion("NODE_CONFIG is null");
            return (Criteria) this;
        }

        public Criteria andNodeConfigIsNotNull() {
            addCriterion("NODE_CONFIG is not null");
            return (Criteria) this;
        }

        public Criteria andNodeConfigEqualTo(String value) {
            addCriterion("NODE_CONFIG =", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigNotEqualTo(String value) {
            addCriterion("NODE_CONFIG <>", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigGreaterThan(String value) {
            addCriterion("NODE_CONFIG >", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigGreaterThanOrEqualTo(String value) {
            addCriterion("NODE_CONFIG >=", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigLessThan(String value) {
            addCriterion("NODE_CONFIG <", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigLessThanOrEqualTo(String value) {
            addCriterion("NODE_CONFIG <=", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigLike(String value) {
            addCriterion("NODE_CONFIG like", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigNotLike(String value) {
            addCriterion("NODE_CONFIG not like", value, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigIn(List<String> values) {
            addCriterion("NODE_CONFIG in", values, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigNotIn(List<String> values) {
            addCriterion("NODE_CONFIG not in", values, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigBetween(String value1, String value2) {
            addCriterion("NODE_CONFIG between", value1, value2, "nodeConfig");
            return (Criteria) this;
        }

        public Criteria andNodeConfigNotBetween(String value1, String value2) {
            addCriterion("NODE_CONFIG not between", value1, value2, "nodeConfig");
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