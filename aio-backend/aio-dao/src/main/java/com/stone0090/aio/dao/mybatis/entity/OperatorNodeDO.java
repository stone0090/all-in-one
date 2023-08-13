package com.stone0090.aio.dao.mybatis.entity;

import java.util.Date;

public class OperatorNodeDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String nodeName;

    private String nodeConfig;

    private Integer dagId;

    private Integer opId;

    private String opCode;

    private String inputMapping;

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

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    public String getNodeConfig() {
        return nodeConfig;
    }

    public void setNodeConfig(String nodeConfig) {
        this.nodeConfig = nodeConfig == null ? null : nodeConfig.trim();
    }

    public Integer getDagId() {
        return dagId;
    }

    public void setDagId(Integer dagId) {
        this.dagId = dagId;
    }

    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    public String getOpCode() {
        return opCode;
    }

    public void setOpCode(String opCode) {
        this.opCode = opCode == null ? null : opCode.trim();
    }

    public String getInputMapping() {
        return inputMapping;
    }

    public void setInputMapping(String inputMapping) {
        this.inputMapping = inputMapping == null ? null : inputMapping.trim();
    }
}