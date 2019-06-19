package com.ai.mine.crystal.jobs;

import com.ai.mine.common.dto.PageResponseDTO;
import com.ai.mine.crystal.dto.req.KedaCaseinfoDTO;
import com.ai.mine.crystal.dto.req.KedaRecordDTO;
import com.ai.mine.crystal.dto.resp.KedaCaseinfoRespDTO;
import com.ai.mine.crystal.dto.resp.KedaRecordRespDTO;
import com.ai.mine.crystal.enums.JobStatusEnum;
import com.ai.mine.crystal.service.alisr.interfaces.ICaseInfoSV;
import com.ai.mine.crystal.service.alisr.interfaces.IDownloadVideoSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class DownloadVideoJob extends AbstractJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ICaseInfoSV caseInfoSV;

    @Autowired
    IDownloadVideoSV downloadVideoSV;

    @Override
    protected void doTask() {

        logger.info("DownloadVideoJob，开始执行下载视频任务……");

        KedaCaseinfoDTO dto = new KedaCaseinfoDTO();
        dto.setPageNo(1);
        dto.setPageSize(2);
        PageResponseDTO<KedaCaseinfoRespDTO> pageInfo = caseInfoSV.pendingCaseInfoPages(dto);
        if (pageInfo == null || pageInfo.getCount() == 0) {
            logger.info("DownloadVideoJob，没有需要处理的案件！");
            return;
        }
        for (KedaCaseinfoRespDTO caseInfo: pageInfo.getResult()) {
            KedaRecordDTO recordDTO = new KedaRecordDTO();
            recordDTO.setCaseId(caseInfo.getCaseId());
            recordDTO.setPageNo(1);
            recordDTO.setPageSize(2);
            PageResponseDTO<KedaRecordRespDTO> records = caseInfoSV.pendingRecordPages(recordDTO);
            //如果没有待处理的笔录，则直接结束当前处理案件
            if (records == null || pageInfo.getCount() == 0) {
                logger.info("DownloadVideoJob，没有要下载的笔录视频！");
                KedaCaseinfoDTO updateCaseInfo = new KedaCaseinfoDTO();
                BeanUtils.copyProperties(caseInfo, updateCaseInfo);
                updateCaseInfo.setJobStatus(JobStatusEnum.complete.toString());
                updateCaseInfo.setJobTime(new Date());
                updateCaseInfo = caseInfoSV.completeCaseVideoDownload(updateCaseInfo);
                if (updateCaseInfo == null) {
                    logger.error("DownloadVideoJob，更新案件视频下载状态失败，请联系管理员！");
                }
            } else {
                //如果存在需要处理的笔录，执行下载视频的操作
                for (KedaRecordRespDTO recordRespDTO : records.getResult()) {
                    downloadVideoSV.downloadVideoById(recordRespDTO.getRecordId());
                }
            }
        }
    }
}
