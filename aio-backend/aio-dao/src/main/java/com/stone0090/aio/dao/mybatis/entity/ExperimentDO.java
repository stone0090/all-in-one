package com.stone0090.aio.dao.mybatis.entity;

import java.util.Date;

public class ExperimentDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String exName;

    private String exDesc;

    private String exStatus;

    private String nodeDag;

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

    public String getExName() {
        return exName;
    }

    public void setExName(String exName) {
        this.exName = exName == null ? null : exName.trim();
    }

    public String getExDesc() {
        return exDesc;
    }

    public void setExDesc(String exDesc) {
        this.exDesc = exDesc == null ? null : exDesc.trim();
    }

    public String getExStatus() {
        return exStatus;
    }

    public void setExStatus(String exStatus) {
        this.exStatus = exStatus == null ? null : exStatus.trim();
    }

    public String getNodeDag() {
        return nodeDag;
    }

    public void setNodeDag(String nodeDag) {
        this.nodeDag = nodeDag == null ? null : nodeDag.trim();
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