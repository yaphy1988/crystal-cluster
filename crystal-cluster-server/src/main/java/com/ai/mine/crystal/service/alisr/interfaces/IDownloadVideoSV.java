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
}
