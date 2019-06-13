package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.service.alisr.interfaces.IDownloadVideoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DownloadVideoSVImpl implements IDownloadVideoSV {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static String API_GET_VIDEO_PATH = "getDownloadVideoPath";

    @Autowired
    private KedaMessageProcessor kedaMessageProcessor;

    @Override
    public boolean downloadVideoById(String recordId) throws BusinessException {
        logger.info("开始下载案件笔录视频文件，案件笔录ID：{}", recordId);
        Long start = System.currentTimeMillis();
        //TODO: 调用接口获取笔录下载地址，并执行下载，规范命名后存储到指定目录。
        //1. 封装接口调用参数
        String requestURI = kedaMessageProcessor.parseRequestURI(API_GET_VIDEO_PATH, null);

        //2. 调用科达接口，获取文件下载地址，并更新到笔录表中。
        threadSleep(10000);
        logger.info("获得视频下载地址完成，接口调用耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");
        //3. 执行下载操作，文件下载完成后存储到指定目录

        threadSleep(120000);
        logger.info("视频文件下载到本地，总耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");

        //4. 存储完成后，记录笔录视频下载结果

        threadSleep(10000);
        logger.info("视频保存到云存储完成，总耗时：" + String.valueOf(System.currentTimeMillis() - start) + " ms");
        logger.info("视频下载完成，案件笔录ID：{}", recordId);
        return false;
    }

    private void threadSleep(int milliscond) {
        try {
            Thread.sleep(milliscond);
        } catch (InterruptedException e) {
            logger.error("threadSleep Exception: \n",e);
        }
    }
}
