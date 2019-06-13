package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.crystal.service.common.KedaCasesDTO;
import com.ai.mine.crystal.service.common.KedaRequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

@Service
public class KedaMessageProcessor {

    @Value("${application.keda.api}")
    private String kedaApi;

    public String parseRequestURI(String apiMethod, Map<String, String> param) {
        StringBuilder stringBuilder = new StringBuilder();
        Assert.hasText(apiMethod,"API请求方法不能为空！");
        stringBuilder.append(kedaApi).append("?method=").append(apiMethod);

        //如果有Get参数则封装到请求URL中
        if (param != null && !param.isEmpty()) {
            for (Map.Entry<String, String> entry : param.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                stringBuilder.append("&").append(key).append("=").append(value);
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 通过接口获得案件信息
     * @param requestURI
     * @param requestBody
     * @return
     */
    public KedaCasesDTO getCasesByAPI(String requestURI, KedaRequestBody requestBody) {

        return null;
    }

    /**
     * 通过接口获得视频文件下载地址
     * @param requestURI
     * @param requestBody
     * @return
     */
    public String getVideoUrlByAPI(String requestURI, KedaRequestBody requestBody) {

        return "";
    }


}
