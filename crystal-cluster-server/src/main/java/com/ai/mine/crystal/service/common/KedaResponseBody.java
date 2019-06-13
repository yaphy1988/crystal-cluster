package com.ai.mine.crystal.service.common;

import java.util.List;

public class KedaResponseBody {

    private List<String> successList;
    private String successData;
    private List<String> faileList;
    private Integer successCount;
    private Integer faileCount;
    private String error;
    private String ret;
    private List<String> errdetail;

    public List<String> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<String> successList) {
        this.successList = successList;
    }

    public String getSuccessData() {
        return successData;
    }

    public void setSuccessData(String successData) {
        this.successData = successData;
    }

    public List<String> getFaileList() {
        return faileList;
    }

    public void setFaileList(List<String> faileList) {
        this.faileList = faileList;
    }

    public Integer getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Integer successCount) {
        this.successCount = successCount;
    }

    public Integer getFaileCount() {
        return faileCount;
    }

    public void setFaileCount(Integer faileCount) {
        this.faileCount = faileCount;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public List<String> getErrdetail() {
        return errdetail;
    }

    public void setErrdetail(List<String> errdetail) {
        this.errdetail = errdetail;
    }
}
