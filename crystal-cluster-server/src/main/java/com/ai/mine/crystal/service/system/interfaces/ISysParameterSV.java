package com.ai.mine.crystal.service.system.interfaces;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dto.req.SysParametersDTO;
import com.ai.mine.crystal.dto.resp.SysParametersRespDTO;

public interface ISysParameterSV {

    SysParametersRespDTO querySysParameterByCode(String paramCode) throws BusinessException;

    SysParametersDTO saveSysParameters(SysParametersDTO dto) throws BusinessException;

}
