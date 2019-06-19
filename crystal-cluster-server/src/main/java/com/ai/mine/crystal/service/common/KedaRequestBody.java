package com.ai.mine.crystal.service.common;

public class KedaRequestBody {

    private String method;
    private String methodRequestParam;
    private String startTime;
    private String endTime;

    public KedaRequestBody() {
        this.method = null;
        this.methodRequestParam = null;
        this.startTime = "";
        this.endTime = "";
    }

    public KedaRequestBody(String method, String methodRequestParam) {
        this.method = method;
        this.methodRequestParam = methodRequestParam;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getMethodRequestParam() {
        return methodRequestParam;
    }

    public void setMethodRequestParam(String methodRequestParam) {
        this.methodRequestParam = methodRequestParam;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
