package com.ai.mine.crystal.dto.resp;

import com.ai.mine.common.dto.BaseResponseDTO;

import java.util.Date;

public class KedaRecordRespDTO extends BaseResponseDTO {

    private String recordId;

    private String recordUuid;

    private String caseId;

    private String templateId;

    private String asker;

    private String persionId;

    private Date startTime;

    private Date endTime;

    private String recordStatus;

    private String recordType;

    private String applyId;

    private String remoteRoom;

    private String localRoom;

    private String videoUrl;

    private String jobStatus;

    private Date jobTime;

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

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
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

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Date getJobTime() {
        return jobTime;
    }

    public void setJobTime(Date jobTime) {
        this.jobTime = jobTime;
    }
}
