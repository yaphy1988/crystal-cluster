package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.service.alisr.interfaces.ICaseInfoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CaseInfoSVImpl implements ICaseInfoSV {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private KedaMessageProcessor kedaMessageProcessor;

    @Override
    public boolean downloadCaseInfo(Date startTime, Date endTime) throws BusinessException {
        logger.info("开始下载案件信息，案件开始时间 -- 结束时间：" + startTime.toString() + " - " + endTime.toString());
        //TODO: 调用接口，下载案件信息，并存储到案件待处理表和笔录表中。
        //1. 封装请求参数
        String requestURI = kedaMessageProcessor.parseRequestURI();

        //2. 请求业务系统，获得案件信息

        //3. 案件信息入库保存（待处理表，分为案件和笔录）

        return true;

    }
}
