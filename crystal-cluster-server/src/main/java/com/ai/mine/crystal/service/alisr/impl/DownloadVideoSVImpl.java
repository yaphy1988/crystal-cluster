package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.service.alisr.interfaces.IDownloadVideoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class DownloadVideoSVImpl implements IDownloadVideoSV {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean downloadVideoById(String recordId) throws BusinessException {
        logger.info("开始下载案件笔录视频文件，案件笔录ID：{}", recordId);
        //TODO: 调用接口获取笔录下载地址，并执行下载，规范命名后存储到指定目录。
        //1. 封装接口调用参数

        //2. 调用科达接口，获取文件下载地址，并更新到笔录表中。

        //3. 执行下载操作，文件下载完成后存储到指定目录

        //4. 存储完成后，记录笔录视频下载结果

        return false;
    }
}
