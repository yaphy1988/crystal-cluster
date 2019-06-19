package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.crystal.common.DateUtil;
import com.ai.mine.crystal.common.HttpRequestUtil;
import com.ai.mine.crystal.service.common.KedaAPICasesDTO;
import com.ai.mine.crystal.service.common.KedaAPIRecordsDTO;
import com.ai.mine.crystal.service.common.KedaRequestBody;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class KedaMessageProcessor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public KedaAPICasesDTO getCasesByAPI(String requestURI, KedaRequestBody requestBody) {
        try {
            JSONObject json = HttpRequestUtil.postJson(requestURI, requestBody);

            //封装返回数据
            String ret = json.getString("ret");
            if ("0".equals(ret)) {
                JSONObject successData = json.getJSONObject("successData");
                String caseId = successData.getString("id");
                String caseName = successData.getString("caseName");
                String caseUuid = successData.getString("caseUuid");
                String caseNumber = successData.getString("caseNumber");
                String createTime = successData.getString("createTime");
                String modifyTime = successData.getString("modifyTime");
                String areaId = successData.getString("areaId");
                String areaName = successData.getString("areaName");
                String caseStatus = successData.getString("caseStatus");
                JSONArray records = successData.getJSONArray("records");
                List<KedaAPIRecordsDTO> recordsList = new ArrayList<>();
                for (int i = 0 ; i < records.size(); i++) {
                    JSONObject record = records.getJSONObject(i);
                    String recordId = record.getString("recordId");
                    String uuid = record.getString("uuid");
                    String templateId = record.getString("templateId");
                    String asker = record.getString("asker");
                    String persionId = record.getString("persionId");
                    String startTime = record.getString("startTime");
                    String endTime = record.getString("endTime");
                    String status = record.getString("status");
                    String recordType = record.getString("recordType");
                    String applyId = record.getString("applyId");
                    String remoteRoom = record.getString("remoteRoom");
                    String localRoom = record.getString("localRoom");

                    KedaAPIRecordsDTO recordsDTO = new KedaAPIRecordsDTO();
                    recordsDTO.setRecordId(recordId);
                    recordsDTO.setRecordUuid(uuid);
                    recordsDTO.setTemplateId(templateId);
                    recordsDTO.setAsker(asker);
                    recordsDTO.setPersionId(persionId);
                    recordsDTO.setStartTime(DateUtil.parse(startTime));
                    recordsDTO.setEndTime(DateUtil.parse(endTime));
                    recordsDTO.setStatus(status);
                    recordsDTO.setRecordType(recordType);
                    recordsDTO.setApplyId(applyId);
                    recordsDTO.setRemoteRoom(remoteRoom);
                    recordsDTO.setLocalRoom(localRoom);

                    recordsList.add(recordsDTO);
                }
                KedaAPICasesDTO casesDTO = new KedaAPICasesDTO();
                casesDTO.setCaseId(caseId);
                casesDTO.setCaseName(caseName);
                casesDTO.setCaseUuid(caseUuid);
                casesDTO.setCaseNumber(caseNumber);
                casesDTO.setCreateTime(DateUtil.parse(createTime));
                casesDTO.setModifyTime(DateUtil.parse(modifyTime));
                casesDTO.setAreaId(areaId);
                casesDTO.setAreaName(areaName);
                casesDTO.setCaseStatus(caseStatus);
                casesDTO.setRecords(recordsList);

                return casesDTO;
            } else {
                logger.error("调用科达接口返回结果失败，返回报文信息：" + json.toJSONString());
            }
        } catch (Exception e) {
            logger.error("调用科达接口请求数据出现异常：",e);
        }
        return null;
    }

    /**
     * 通过接口获得视频文件下载地址
     * @param requestURI
     * @param requestBody
     * @return
     */
    public String getVideoUrlByAPI(String requestURI, KedaRequestBody requestBody) {
        try {
            JSONObject json = HttpRequestUtil.postJson(requestURI, requestBody);
            //封装返回数据
            String ret = json.getString("ret");
            if ("0".equals(ret)) {
                String videoUrl = json.getString("successData");

                if (!StringUtils.isEmpty(videoUrl) && videoUrl.startsWith("http")) {
                    return videoUrl;
                } else {
                    logger.error("调用科达接口返回结果失败，没有获得视频下载地址，返回报文信息：" + json.toJSONString());
                }
            }
        } catch (Exception e) {
            logger.error("调用科达接口请求数据出现异常：",e);
        }
        return null;
    }


}
