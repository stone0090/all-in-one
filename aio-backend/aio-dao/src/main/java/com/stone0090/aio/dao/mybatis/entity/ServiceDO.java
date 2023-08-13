package com.stone0090.aio.dao.mybatis.entity;

import java.util.Date;

public class ServiceDO {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer isDeleted;

    private String svcUuid;

    private String svcName;

    private String svcStatus;

    private String svcType;

    private Integer svcBizId;

    private String svcUrl;

    private String inputParam;

    private String outputParam;

    private String invokeType;

    private String callbackUrl;

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

    public String getSvcUuid() {
        return svcUuid;
    }

    public void setSvcUuid(String svcUuid) {
        this.svcUuid = svcUuid == null ? null : svcUuid.trim();
    }

    public String getSvcName() {
        return svcName;
    }

    public void setSvcName(String svcName) {
        this.svcName = svcName == null ? null : svcName.trim();
    }

    public String getSvcStatus() {
        return svcStatus;
    }

    public void setSvcStatus(String svcStatus) {
        this.svcStatus = svcStatus == null ? null : svcStatus.trim();
    }

    public String getSvcType() {
        return svcType;
    }

    public void setSvcType(String svcType) {
        this.svcType = svcType == null ? null : svcType.trim();
    }

    public Integer getSvcBizId() {
        return svcBizId;
    }

    public void setSvcBizId(Integer svcBizId) {
        this.svcBizId = svcBizId;
    }

    public String getSvcUrl() {
        return svcUrl;
    }

    public void setSvcUrl(String svcUrl) {
        this.svcUrl = svcUrl == null ? null : svcUrl.trim();
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

    public String getInvokeType() {
        return invokeType;
    }

    public void setInvokeType(String invokeType) {
        this.invokeType = invokeType == null ? null : invokeType.trim();
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl == null ? null : callbackUrl.trim();
    }
}