package com.ai.mine.crystal.service.alisr.impl;

import com.ai.mine.common.dto.PageResponseDTO;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.common.utils.PageResponseFactory;
import com.ai.mine.crystal.common.DateUtil;
import com.ai.mine.crystal.dao.mapper.TKedaCaseinfoMapper;
import com.ai.mine.crystal.dao.mapper.TKedaRecordMapper;
import com.ai.mine.crystal.dao.model.TKedaCaseinfo;
import com.ai.mine.crystal.dao.model.TKedaCaseinfoExample;
import com.ai.mine.crystal.dao.model.TKedaRecord;
import com.ai.mine.crystal.dao.model.TKedaRecordExample;
import com.ai.mine.crystal.dto.req.KedaCaseinfoDTO;
import com.ai.mine.crystal.dto.req.KedaRecordDTO;
import com.ai.mine.crystal.dto.resp.KedaCaseinfoRespDTO;
import com.ai.mine.crystal.dto.resp.KedaRecordRespDTO;
import com.ai.mine.crystal.enums.JobStatusEnum;
import com.ai.mine.crystal.service.alisr.interfaces.ICaseInfoSV;
import com.ai.mine.crystal.service.common.KedaAPICasesDTO;
import com.ai.mine.crystal.service.common.KedaAPIRecordsDTO;
import com.ai.mine.crystal.service.common.KedaRequestBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CaseInfoSVImpl implements ICaseInfoSV {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String API_GET_CASES = "getCases";
    private final static String CHARSET = "UTF-8";

    @Autowired
    private KedaMessageProcessor kedaMessageProcessor;

    @Autowired
    private TKedaCaseinfoMapper kedaCaseinfoMapper;

    @Autowired
    private TKedaRecordMapper kedaRecordMapper;

    @Override
    public boolean downloadCaseInfo(Date startTime, Date endTime) throws BusinessException {
        logger.info("开始下载案件信息，案件开始时间 -- 结束时间：" + startTime.toString() + " - " + endTime.toString());
        //Done: 调用接口，下载案件信息，并存储到案件待处理表和笔录表中。
        //1. 封装请求参数
        Map<String, String> getParam = new HashMap<>();
        try {
            getParam.put("startTime",URLEncoder.encode(DateUtil.formatTime(startTime),CHARSET));
            getParam.put("endTime",URLEncoder.encode(DateUtil.formatTime(endTime),CHARSET));
        } catch (UnsupportedEncodingException e) {
            logger.error("URLEncoder失败，编码格式不对！"+CHARSET);
        }
        String requestURI = kedaMessageProcessor.parseRequestURI(API_GET_CASES, getParam);
        logger.info("requestURI = " + requestURI);
        KedaRequestBody requestBody = new KedaRequestBody(API_GET_CASES, "startTime,endTime");
        requestBody.setStartTime(DateUtil.formatTime(startTime));
        requestBody.setEndTime(DateUtil.formatTime(endTime));

        //2. 请求业务系统，获得案件信息
        List<KedaAPICasesDTO> apiCasesList = kedaMessageProcessor.getCasesByAPI(requestURI,requestBody);
        for (KedaAPICasesDTO apiCasesDTO : apiCasesList) {
            if (apiCasesDTO == null || StringUtils.isBlank(apiCasesDTO.getCaseId())) {
                logger.warn("科达远程提讯系统无案件信息，查询时间："+requestBody.getStartTime() + " -- " + requestBody.getEndTime());
                return true;
            }

            //3. 案件信息入库保存（待处理表，分为案件和笔录）
            KedaCaseinfoDTO caseInfoDTO = parseCaseInfo(apiCasesDTO);
            caseInfoDTO = this.saveCaseinfoByPrimary(caseInfoDTO);
            if (caseInfoDTO == null) {
                throw new BusinessException("保存案件信息出现异常","300101");
            }
            for (KedaAPIRecordsDTO apiRecordsDTO : apiCasesDTO.getRecords()) {
                KedaRecordDTO recordDTO = parseRecordInfo(caseInfoDTO.getCaseId(), apiRecordsDTO);
                recordDTO = this.saveRecordByPrimary(recordDTO);
                if (recordDTO == null) {
                    throw new BusinessException("保存笔录信息出现异常","300102");
                }
            }
        }

        return true;

    }

    @Override
    public PageResponseDTO<KedaCaseinfoRespDTO> pendingCaseInfoPages(KedaCaseinfoDTO caseinfoDTO) throws BusinessException {
        int pageNo = caseinfoDTO.getPageNo();
        int pageSize = caseinfoDTO.getPageSize();

        TKedaCaseinfoExample example = new TKedaCaseinfoExample();
        List<String> jobStatus = new ArrayList<>();
        jobStatus.add(JobStatusEnum.pending.toString());
        jobStatus.add(JobStatusEnum.processing.toString());
        TKedaCaseinfoExample.Criteria criteria = example.createCriteria();
        criteria.andJobStatusIn(jobStatus);
        //TODO: 增加案件状态查询条件
        example.setOrderByClause(" create_time asc");

        PageHelper.startPage(pageNo, pageSize);
        List<TKedaCaseinfo> list = kedaCaseinfoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            PageInfo<TKedaCaseinfo> pageInfo = new PageInfo<>(list);
            return PageResponseFactory.genPageResponse(pageInfo, KedaCaseinfoRespDTO.class);
        }
        return null;
    }

    @Override
    public PageResponseDTO<KedaRecordRespDTO> pendingRecordPages(KedaRecordDTO recordDTO) throws BusinessException {
        int pageNo = recordDTO.getPageNo();
        int pageSize = recordDTO.getPageSize();

        TKedaRecordExample example = new TKedaRecordExample();
        TKedaRecordExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(recordDTO.getCaseId())) {
            criteria.andCaseIdEqualTo(recordDTO.getCaseId());
        }
        //TODO: 增加笔录状态查询条件
        List<String> jobStatus = new ArrayList<>();
        jobStatus.add(JobStatusEnum.pending.toString());
        jobStatus.add(JobStatusEnum.processing.toString());
        criteria.andJobStatusIn(jobStatus);
        example.setOrderByClause(" job_time asc");

        PageHelper.startPage(pageNo, pageSize);
        List<TKedaRecord> list = kedaRecordMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            PageInfo<TKedaRecord> pageInfo = new PageInfo<>(list);
            return PageResponseFactory.genPageResponse(pageInfo, KedaRecordRespDTO.class);
        }
        return null;
    }

    @Override
    public KedaCaseinfoDTO completeCaseVideoDownload(KedaCaseinfoDTO caseinfoDTO) throws BusinessException {
        if (caseinfoDTO == null || StringUtils.isBlank(caseinfoDTO.getCaseId())) {
            throw new BusinessException("案件ID不能为空","300103");
        }
        TKedaCaseinfo caseinfo = kedaCaseinfoMapper.selectByPrimaryKey(caseinfoDTO.getCaseId());
        if (caseinfo == null) {
            throw new BusinessException("无法找到案件信息","300104");
        }
        caseinfo.setJobStatus(caseinfoDTO.getJobStatus());
        caseinfo.setJobTime(caseinfo.getJobTime());

        if (kedaCaseinfoMapper.updateByPrimaryKeySelective(caseinfo) > 0) {
            return caseinfoDTO;
        }
        return null;
    }

    @Override
    public KedaCaseinfoDTO saveCaseinfoByPrimary(KedaCaseinfoDTO caseinfoDTO) throws BusinessException {
        if (caseinfoDTO == null || StringUtils.isBlank(caseinfoDTO.getCaseId())) {
            throw new BusinessException("案件ID不能为空","300103");
        }
        int res;
        TKedaCaseinfo caseinfo = kedaCaseinfoMapper.selectByPrimaryKey(caseinfoDTO.getCaseId());
        if (caseinfo == null) {
            caseinfo = new TKedaCaseinfo();
            BeanUtils.copyProperties(caseinfoDTO, caseinfo);
            caseinfo.setJobStatus(JobStatusEnum.pending.toString());
            res = kedaCaseinfoMapper.insertSelective(caseinfo);
        } else {
            BeanUtils.copyProperties(caseinfoDTO, caseinfo);
            caseinfo.setJobStatus(JobStatusEnum.pending.toString());
            res = kedaCaseinfoMapper.updateByPrimaryKeySelective(caseinfo);
        }
        caseinfoDTO.setJobStatus(JobStatusEnum.pending.toString());
        return (res == 1) ? caseinfoDTO : null;
    }

    @Override
    public KedaRecordDTO saveRecordByPrimary(KedaRecordDTO recordDTO) throws BusinessException {
        if (recordDTO == null || StringUtils.isBlank(recordDTO.getRecordId())
                || StringUtils.isBlank(recordDTO.getCaseId())) {
            throw new BusinessException("案件ID和笔录ID不能为空","300105");
        }
        int res;
        TKedaRecord record = kedaRecordMapper.selectByPrimaryKey(recordDTO.getRecordId());
        if (record == null) {
            record = new TKedaRecord();
            BeanUtils.copyProperties(recordDTO, record);
            record.setJobStatus(JobStatusEnum.pending.toString());
            res = kedaRecordMapper.insertSelective(record);
        } else {
            BeanUtils.copyProperties(recordDTO, record);
            record.setJobStatus(JobStatusEnum.pending.toString());
            res = kedaRecordMapper.updateByPrimaryKeySelective(record);
        }
        recordDTO.setJobStatus(JobStatusEnum.pending.toString());
        return (res == 1) ? recordDTO : null;
    }

    private KedaCaseinfoDTO parseCaseInfo(KedaAPICasesDTO apiCasesDTO) {
        KedaCaseinfoDTO dto = new KedaCaseinfoDTO();
        BeanUtils.copyProperties(apiCasesDTO, dto);
        dto.setJobStatus(JobStatusEnum.pending.toString());
        return dto;
    }

    private KedaRecordDTO parseRecordInfo(String caseId, KedaAPIRecordsDTO apiRecordsDTO) {
        KedaRecordDTO dto = new KedaRecordDTO();
        BeanUtils.copyProperties(apiRecordsDTO, dto);
        dto.setCaseId(caseId);
        dto.setJobStatus(JobStatusEnum.pending.toString());
        dto.setRecordStatus(apiRecordsDTO.getStatus());
        return dto;
    }

}
