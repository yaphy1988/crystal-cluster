package com.ai.mine.crystal.dubbo.demo;

import com.ai.mine.crystal.dto.req.DemoDTO;
import com.ai.mine.crystal.dto.resp.DemoRespDTO;
import com.ai.mine.crystal.interfaces.IDemoDubboTestRSV;
import com.ai.mine.crystal.service.demo.interfaces.IDemoSV;
import com.ai.mine.common.dto.PageResponseDTO;
import com.alibaba.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Service(
        version = "${demo.service.version}",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoDubboTestRSVImpl implements IDemoDubboTestRSV {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    IDemoSV demoSV;

    @Override
    public boolean saveDemoInfo(DemoDTO demoDTO) {
        logger.info("DemoDubboTestRSVImpl execute ...");
        return true;
    }

    @Override
    public PageResponseDTO<DemoRespDTO> queryDemoPages(DemoDTO demoDTO) {
        return demoSV.queryDemoPages(demoDTO);
    }
}
