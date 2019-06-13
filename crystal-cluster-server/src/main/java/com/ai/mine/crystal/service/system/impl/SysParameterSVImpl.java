package com.ai.mine.crystal.service.system.impl;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dao.mapper.TSysParametersMapper;
import com.ai.mine.crystal.dao.model.TSysParameters;
import com.ai.mine.crystal.dto.req.SysParametersDTO;
import com.ai.mine.crystal.dto.resp.SysParametersRespDTO;
import com.ai.mine.crystal.service.system.interfaces.ISysParameterSV;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class SysParameterSVImpl implements ISysParameterSV {

    @Autowired
    TSysParametersMapper sysParametersMapper;

    @Override
    public SysParametersRespDTO querySysParameterByCode(String paramCode) throws BusinessException {
        TSysParameters param = sysParametersMapper.selectByPrimaryKey(paramCode);
        if (param != null) {
            SysParametersRespDTO dto = new SysParametersRespDTO();
            BeanUtils.copyProperties(param, dto);
            return dto;
        }
        return null;
    }

    @Override
    public SysParametersDTO saveSysParameters(SysParametersDTO dto) throws BusinessException {
        TSysParameters parameters = new TSysParameters();
        BeanUtils.copyProperties(dto, parameters);
        if (StringUtils.isNotBlank(dto.getParamCode())) {
            sysParametersMapper.updateByPrimaryKeySelective(parameters);
            return dto;
        }
        return null;
    }
}
