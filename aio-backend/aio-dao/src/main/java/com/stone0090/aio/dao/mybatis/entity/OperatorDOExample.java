package com.stone0090.aio.dao.mybatis.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperatorDOExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperatorDOExample() {
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

        public Criteria andOpNameIsNull() {
            addCriterion("OP_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOpNameIsNotNull() {
            addCriterion("OP_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOpNameEqualTo(String value) {
            addCriterion("OP_NAME =", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotEqualTo(String value) {
            addCriterion("OP_NAME <>", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThan(String value) {
            addCriterion("OP_NAME >", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameGreaterThanOrEqualTo(String value) {
            addCriterion("OP_NAME >=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThan(String value) {
            addCriterion("OP_NAME <", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLessThanOrEqualTo(String value) {
            addCriterion("OP_NAME <=", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameLike(String value) {
            addCriterion("OP_NAME like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotLike(String value) {
            addCriterion("OP_NAME not like", value, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameIn(List<String> values) {
            addCriterion("OP_NAME in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotIn(List<String> values) {
            addCriterion("OP_NAME not in", values, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameBetween(String value1, String value2) {
            addCriterion("OP_NAME between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpNameNotBetween(String value1, String value2) {
            addCriterion("OP_NAME not between", value1, value2, "opName");
            return (Criteria) this;
        }

        public Criteria andOpStatusIsNull() {
            addCriterion("OP_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andOpStatusIsNotNull() {
            addCriterion("OP_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andOpStatusEqualTo(String value) {
            addCriterion("OP_STATUS =", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotEqualTo(String value) {
            addCriterion("OP_STATUS <>", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusGreaterThan(String value) {
            addCriterion("OP_STATUS >", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusGreaterThanOrEqualTo(String value) {
            addCriterion("OP_STATUS >=", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusLessThan(String value) {
            addCriterion("OP_STATUS <", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusLessThanOrEqualTo(String value) {
            addCriterion("OP_STATUS <=", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusLike(String value) {
            addCriterion("OP_STATUS like", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotLike(String value) {
            addCriterion("OP_STATUS not like", value, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusIn(List<String> values) {
            addCriterion("OP_STATUS in", values, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotIn(List<String> values) {
            addCriterion("OP_STATUS not in", values, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusBetween(String value1, String value2) {
            addCriterion("OP_STATUS between", value1, value2, "opStatus");
            return (Criteria) this;
        }

        public Criteria andOpStatusNotBetween(String value1, String value2) {
            addCriterion("OP_STATUS not between", value1, value2, "opStatus");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageIsNull() {
            addCriterion("PROGRAMMING_LANGUAGE is null");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageIsNotNull() {
            addCriterion("PROGRAMMING_LANGUAGE is not null");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageEqualTo(String value) {
            addCriterion("PROGRAMMING_LANGUAGE =", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageNotEqualTo(String value) {
            addCriterion("PROGRAMMING_LANGUAGE <>", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageGreaterThan(String value) {
            addCriterion("PROGRAMMING_LANGUAGE >", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("PROGRAMMING_LANGUAGE >=", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageLessThan(String value) {
            addCriterion("PROGRAMMING_LANGUAGE <", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageLessThanOrEqualTo(String value) {
            addCriterion("PROGRAMMING_LANGUAGE <=", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageLike(String value) {
            addCriterion("PROGRAMMING_LANGUAGE like", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageNotLike(String value) {
            addCriterion("PROGRAMMING_LANGUAGE not like", value, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageIn(List<String> values) {
            addCriterion("PROGRAMMING_LANGUAGE in", values, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageNotIn(List<String> values) {
            addCriterion("PROGRAMMING_LANGUAGE not in", values, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageBetween(String value1, String value2) {
            addCriterion("PROGRAMMING_LANGUAGE between", value1, value2, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andProgrammingLanguageNotBetween(String value1, String value2) {
            addCriterion("PROGRAMMING_LANGUAGE not between", value1, value2, "programmingLanguage");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeIsNull() {
            addCriterion("ALGORITHM_CODE is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeIsNotNull() {
            addCriterion("ALGORITHM_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeEqualTo(String value) {
            addCriterion("ALGORITHM_CODE =", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeNotEqualTo(String value) {
            addCriterion("ALGORITHM_CODE <>", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeGreaterThan(String value) {
            addCriterion("ALGORITHM_CODE >", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeGreaterThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_CODE >=", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeLessThan(String value) {
            addCriterion("ALGORITHM_CODE <", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeLessThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_CODE <=", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeLike(String value) {
            addCriterion("ALGORITHM_CODE like", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeNotLike(String value) {
            addCriterion("ALGORITHM_CODE not like", value, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeIn(List<String> values) {
            addCriterion("ALGORITHM_CODE in", values, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeNotIn(List<String> values) {
            addCriterion("ALGORITHM_CODE not in", values, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeBetween(String value1, String value2) {
            addCriterion("ALGORITHM_CODE between", value1, value2, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmCodeNotBetween(String value1, String value2) {
            addCriterion("ALGORITHM_CODE not between", value1, value2, "algorithmCode");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathIsNull() {
            addCriterion("ALGORITHM_PATH is null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathIsNotNull() {
            addCriterion("ALGORITHM_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathEqualTo(String value) {
            addCriterion("ALGORITHM_PATH =", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathNotEqualTo(String value) {
            addCriterion("ALGORITHM_PATH <>", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathGreaterThan(String value) {
            addCriterion("ALGORITHM_PATH >", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathGreaterThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_PATH >=", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathLessThan(String value) {
            addCriterion("ALGORITHM_PATH <", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathLessThanOrEqualTo(String value) {
            addCriterion("ALGORITHM_PATH <=", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathLike(String value) {
            addCriterion("ALGORITHM_PATH like", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathNotLike(String value) {
            addCriterion("ALGORITHM_PATH not like", value, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathIn(List<String> values) {
            addCriterion("ALGORITHM_PATH in", values, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathNotIn(List<String> values) {
            addCriterion("ALGORITHM_PATH not in", values, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathBetween(String value1, String value2) {
            addCriterion("ALGORITHM_PATH between", value1, value2, "algorithmPath");
            return (Criteria) this;
        }

        public Criteria andAlgorithmPathNotBetween(String value1, String value2) {
            addCriterion("ALGORITHM_PATH not between", value1, value2, "algorithmPath");
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