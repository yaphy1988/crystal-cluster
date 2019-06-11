package com.ai.mine.crystal.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * 定义Web项目返回Json的基础格式，业务根据实际需要继承此类
 */
public class WebRespVO implements Serializable {

    private String resultCode;

    private String resultMessage;

    public WebRespVO(String resultCode, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
