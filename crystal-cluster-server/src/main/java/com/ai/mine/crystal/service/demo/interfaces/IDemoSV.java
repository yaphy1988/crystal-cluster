package com.ai.mine.crystal.service.demo.interfaces;

import com.ai.mine.crystal.dto.req.DemoDTO;
import com.ai.mine.crystal.dto.resp.DemoRespDTO;
import com.ai.mine.common.dto.PageResponseDTO;
import com.ai.mine.common.exception.BusinessException;

public interface IDemoSV {
    PageResponseDTO<DemoRespDTO> queryDemoPages(DemoDTO demoDTO) throws BusinessException;
}
