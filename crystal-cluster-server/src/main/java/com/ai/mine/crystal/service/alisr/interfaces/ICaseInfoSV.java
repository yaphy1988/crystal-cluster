package com.ai.mine.crystal.service.alisr.interfaces;

import com.ai.mine.common.dto.PageResponseDTO;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dto.req.KedaCaseinfoDTO;
import com.ai.mine.crystal.dto.req.KedaRecordDTO;
import com.ai.mine.crystal.dto.resp.KedaCaseinfoRespDTO;
import com.ai.mine.crystal.dto.resp.KedaRecordRespDTO;

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

    /**
     * 查询待处理的案件信息
     * @param caseinfoDTO 分页查询信息
     * @return 分页KedaCaseinfoRespDTO
     * @throws BusinessException 业务异常
     */
    PageResponseDTO<KedaCaseinfoRespDTO> pendingCaseInfoPages(KedaCaseinfoDTO caseinfoDTO) throws BusinessException;

    /**
     * 查询待处理的笔录信息
     * @param recordDTO 分页查询信息
     * @return 分页KedaRecordRespDTO
     * @throws BusinessException 业务异常
     */
    PageResponseDTO<KedaRecordRespDTO> pendingRecordPages(KedaRecordDTO recordDTO) throws BusinessException;

    /**
     * 完成视频下载后更新案件信息
     * @param caseinfoDTO 已经完成下载的案件信息
     * @return KedaCaseinfoDTO
     * @throws BusinessException 业务异常
     */
    KedaCaseinfoDTO completeCaseVideoDownload(KedaCaseinfoDTO caseinfoDTO) throws BusinessException;

    /**
     * 保存案件信息Caseinfo，含新建和更新
     * @param caseinfoDTO 案件信息
     * @return caseinfoDTO 保存成功后原样返回
     * @throws BusinessException 业务异常
     */
    KedaCaseinfoDTO saveCaseinfoByPrimary(KedaCaseinfoDTO caseinfoDTO) throws BusinessException;

    /**
     * 保存案件笔录信息Record，含新建和更新
     * @param recordDTO 笔录信息
     * @return recordDTO 保存成功后原样返回
     * @throws BusinessException 业务异常
     */
    KedaRecordDTO saveRecordByPrimary(KedaRecordDTO recordDTO) throws BusinessException;

}
