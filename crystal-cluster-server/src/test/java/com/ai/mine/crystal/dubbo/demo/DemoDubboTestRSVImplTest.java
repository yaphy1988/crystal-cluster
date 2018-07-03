package com.ai.mine.crystal.dubbo.demo;

import com.ai.mine.crystal.AbstractBaseTest;
import com.ai.mine.crystal.dto.req.DemoDTO;
import com.ai.mine.crystal.dto.resp.DemoRespDTO;
import com.ai.mine.crystal.interfaces.IDemoDubboTestRSV;
import com.ai.mine.common.dto.PageResponseDTO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class DemoDubboTestRSVImplTest extends AbstractBaseTest {

    @Autowired
    IDemoDubboTestRSV demoDubboTestRSV;

    @Test
    public void saveDemoInfo() {
        DemoDTO dto = new DemoDTO();
        boolean result = demoDubboTestRSV.saveDemoInfo(dto);
        Assert.assertTrue(result);
    }

    @Test
    public void queryDemoPages() {
        DemoDTO dto = new DemoDTO();
        dto.setPageNo(1);
        dto.setPageSize(1);
        dto.setGridQuerySortName("name");
        dto.setGridQuerySortOrder("desc");

        PageResponseDTO<DemoRespDTO> resp = demoDubboTestRSV.queryDemoPages(dto);
        System.out.println(resp.toString());
    }
}