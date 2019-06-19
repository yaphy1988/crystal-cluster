package com.ai.mine.crystal.service.common;

import java.io.Serializable;
import java.util.Date;

public class KedaAPIRecordsDTO implements Serializable {

    private String recordId;

    private String recordUuid;

    private String templateId;

    private String asker;

    private String persionId;

    private Date startTime;

    private Date endTime;

    private String status;

    private String recordType;

    private String applyId;

    private String remoteRoom;

    private String localRoom;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getRecordUuid() {
        return recordUuid;
    }

    public void setRecordUuid(String recordUuid) {
        this.recordUuid = recordUuid;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public String getPersionId() {
        return persionId;
    }

    public void setPersionId(String persionId) {
        this.persionId = persionId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getRemoteRoom() {
        return remoteRoom;
    }

    public void setRemoteRoom(String remoteRoom) {
        this.remoteRoom = remoteRoom;
    }

    public String getLocalRoom() {
        return localRoom;
    }

    public void setLocalRoom(String localRoom) {
        this.localRoom = localRoom;
    }
}
