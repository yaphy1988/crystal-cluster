package com.ai.mine.crystal.service.common;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class KedaCasesDTO implements Serializable {

    private String caseId;

    private String caseName;

    private String caseUuid;

    private String caseNumber;

    private Date createTime;

    private Date modifyTime;

    private String areaId;

    private String areaName;

    private String caseStatus;

    private List<KedaRecordsDTO> records;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseUuid() {
        return caseUuid;
    }

    public void setCaseUuid(String caseUuid) {
        this.caseUuid = caseUuid;
    }

    public String getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(String caseNumber) {
        this.caseNumber = caseNumber;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public List<KedaRecordsDTO> getRecords() {
        return records;
    }

    public void setRecords(List<KedaRecordsDTO> records) {
        this.records = records;
    }
}
