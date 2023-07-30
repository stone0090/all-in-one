package com.stone0090.aio.dao.mybatis.entity;

import java.util.Date;

public class OperatorDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String opCode;

    private String opName;

    private String opStatus;

    private String algoLanguage;

    private String algoCode;

    private String algoPath;

    private String inputParam;

    private String outputParam;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode == null ? null : opCode.trim();
    }

    public String getOpName() {
        return opName;
    }

    public void setOpName(String opName) {
        this.opName = opName == null ? null : opName.trim();
    }

    public String getOpStatus() {
        return opStatus;
    }

    public void setOpStatus(String opStatus) {
        this.opStatus = opStatus == null ? null : opStatus.trim();
    }

    public String getAlgoLanguage() {
        return algoLanguage;
    }

    public void setAlgoLanguage(String algoLanguage) {
        this.algoLanguage = algoLanguage == null ? null : algoLanguage.trim();
    }

    public String getAlgoCode() {
        return algoCode;
    }

    public void setAlgoCode(String algoCode) {
        this.algoCode = algoCode == null ? null : algoCode.trim();
    }

    public String getAlgoPath() {
        return algoPath;
    }

    public void setAlgoPath(String algoPath) {
        this.algoPath = algoPath == null ? null : algoPath.trim();
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam == null ? null : inputParam.trim();
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam == null ? null : outputParam.trim();
    }
}