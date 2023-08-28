package com.stone0090.aio.dao.mybatis.entity;

import java.util.Date;

public class OperatorDagDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String dagName;

    private String dagDesc;

    private String dagStatus;

    private String dagData;

    private String publishType;

    private String publishConfig;

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

    public String getDagName() {
        return dagName;
    }

    public void setDagName(String dagName) {
        this.dagName = dagName == null ? null : dagName.trim();
    }

    public String getDagDesc() {
        return dagDesc;
    }

    public void setDagDesc(String dagDesc) {
        this.dagDesc = dagDesc == null ? null : dagDesc.trim();
    }

    public String getDagStatus() {
        return dagStatus;
    }

    public void setDagStatus(String dagStatus) {
        this.dagStatus = dagStatus == null ? null : dagStatus.trim();
    }

    public String getDagData() {
        return dagData;
    }

    public void setDagData(String dagData) {
        this.dagData = dagData == null ? null : dagData.trim();
    }

    public String getPublishType() {
        return publishType;
    }

    public void setPublishType(String publishType) {
        this.publishType = publishType == null ? null : publishType.trim();
    }

    public String getPublishConfig() {
        return publishConfig;
    }

    public void setPublishConfig(String publishConfig) {
        this.publishConfig = publishConfig == null ? null : publishConfig.trim();
    }
}