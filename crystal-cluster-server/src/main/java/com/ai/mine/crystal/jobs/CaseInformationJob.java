package com.ai.mine.crystal.jobs;

import com.ai.mine.crystal.common.DateUtil;
import com.ai.mine.crystal.dto.resp.SysParametersRespDTO;
import com.ai.mine.crystal.service.alisr.interfaces.ICaseInfoSV;
import com.ai.mine.crystal.service.system.interfaces.ISysParameterSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        Date currentDealDate = DateUtil.getTheDayDate(DateUtil.getNowAsTimestamp());
        SysParametersRespDTO dto = sysParameterSV.querySysParameterByCode(PARAM_CODE);
        if (dto != null) {
            String dateStr = dto.getParamValue();
            currentDealDate = DateUtil.str2Date(dateStr);
        }
        Date endDate = DateUtil.addDays(currentDealDate, 1);
        boolean success = caseInfoSV.downloadCaseInfo(currentDealDate, endDate);

        if (success) {
            logger.info("CaseInformationJob，案件信息下载成功！");
        }
    }
}
