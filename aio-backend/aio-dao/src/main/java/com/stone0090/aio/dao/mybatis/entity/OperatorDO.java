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

    private String programmingLanguage;

    private String algorithmCode;

    private String algorithmPath;

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

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage == null ? null : programmingLanguage.trim();
    }

    public String getAlgorithmCode() {
        return algorithmCode;
    }

    public void setAlgorithmCode(String algorithmCode) {
        this.algorithmCode = algorithmCode == null ? null : algorithmCode.trim();
    }

    public String getAlgorithmPath() {
        return algorithmPath;
    }

    public void setAlgorithmPath(String algorithmPath) {
        this.algorithmPath = algorithmPath == null ? null : algorithmPath.trim();
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