package com.ai.mine.crystal.interfaces;

import com.ai.mine.crystal.dto.req.DemoDTO;
import com.ai.mine.crystal.dto.resp.DemoRespDTO;
import com.ai.mine.common.dto.PageResponseDTO;

/**
 * IDemoDubboTestRSV
 * 一个dubbo服务的接口定义示例：
 * 入参：&lt;T extends BaseInfo&gt;，dubbo线程中会用到BaseInfo中定义的用户ID和其他本地线程变量
 * 返回值：你自己定，如果是分页查询，返回值为"PageResponseDTO&lt;T extends BaseResponseDTO&gt;"
 */
public interface IDemoDubboTestRSV {

    boolean saveDemoInfo(DemoDTO demoDTO);

    PageResponseDTO<DemoRespDTO> queryDemoPages(DemoDTO demoDTO);
}
