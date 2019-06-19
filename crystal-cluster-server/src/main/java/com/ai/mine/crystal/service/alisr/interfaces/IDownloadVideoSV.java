package com.ai.mine.crystal.service.alisr.interfaces;

import com.ai.mine.common.exception.BusinessException;

public interface IDownloadVideoSV {

    /**
     * 根据笔录ID获取视频文件并下载下来
     * @param recordId 笔录ID
     * @return boolean 是否成功
     * @throws BusinessException 业务异常
     */
    boolean downloadVideoById(String recordId) throws BusinessException;

    /**
     * 保存笔录视频文件下载地址
     * @param recordId 笔录ID
     * @param videoUrl 文件URL
     * @return String 重命名文件名
     * @throws BusinessException 业务异常
     */
    String saveRecordVideoUrl(String recordId, String videoUrl) throws BusinessException;

    /**
     * 完成笔录视频下载
     * @param recordId 笔录ID
     * @return boolean 成功或失败
     * @throws BusinessException 业务异常
     */
    boolean completeRecordVideoDownload(String recordId) throws BusinessException;
}
