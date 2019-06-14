package com.ai.mine.crystal.jobs;

import com.ai.mine.crystal.common.DateUtil;
import com.ai.mine.crystal.dto.req.SysParametersDTO;
import com.ai.mine.crystal.dto.resp.SysParametersRespDTO;
import com.ai.mine.crystal.service.alisr.interfaces.ICaseInfoSV;
import com.ai.mine.crystal.service.system.interfaces.ISysParameterSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class CaseInformationJob extends AbstractJob {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String PARAM_CODE = "KEDA_JOB_DEAL_DATE";

    @Autowired
    private ISysParameterSV sysParameterSV;

    @Autowired
    private ICaseInfoSV caseInfoSV;

    @Override
    protected void doTask() {
        logger.info("CaseInformationJob，自动任务开始执行……");

        Date today = DateUtil.getTheDayDate(DateUtil.getNowAsTimestamp());
        Date currentDealDate;
        SysParametersRespDTO dto = sysParameterSV.querySysParameterByCode(PARAM_CODE);
        if (dto != null) {
            String dateStr = dto.getParamValue();
            currentDealDate = DateUtil.str2Date(dateStr);
        } else {
            currentDealDate = today;
            dto = new SysParametersRespDTO();
            dto.setParamCode(PARAM_CODE);
            dto.setParamName("科达提讯案件下载任务处理日期");
            dto.setCreateTime(new Date());
            dto.setDescription("自动任务当前处理到哪一天，格式：yyyy-MM-dd");
        }
        Date endDate = DateUtil.addDays(currentDealDate, 1);
        boolean success = caseInfoSV.downloadCaseInfo(currentDealDate, endDate);
        if (success) {
            logger.info("CaseInformationJob，案件信息下载成功！");
            if (currentDealDate.before(today)) {
                SysParametersDTO parametersDTO = new SysParametersDTO();
                BeanUtils.copyProperties(dto, parametersDTO);
                parametersDTO.setParamValue(DateUtil.formatDate(endDate));
                parametersDTO.setUpdateTime(new Date());
                sysParameterSV.saveSysParameters(parametersDTO);
            }
        } else {
            logger.error("CaseInformationJob，案件信息下载失败！");
        }

    }
}
