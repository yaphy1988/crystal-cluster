package com.ai.mine.crystal.service.alisr.interfaces;

import com.ai.mine.common.exception.BusinessException;

import java.util.Date;

public interface ICaseInfoSV {

    /**
     * 获取案件信息，含案件的所有笔录信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return boolean 下载成功或失败
     * @throws BusinessException 业务异常
     */
    boolean downloadCaseInfo(Date startTime, Date endTime) throws BusinessException;

}
