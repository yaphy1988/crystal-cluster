package com.ai.mine.crystal.jobs;

import com.ai.mine.crystal.service.alisr.interfaces.IDownloadVideoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DownloadVideoJob extends AbstractJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IDownloadVideoSV downloadVideoSV;

    @Override
    protected void doTask() {

        logger.warn("DownloadVideoJob，开始执行下载视频任务……");

        downloadVideoSV.downloadVideoById("123");
    }
}
