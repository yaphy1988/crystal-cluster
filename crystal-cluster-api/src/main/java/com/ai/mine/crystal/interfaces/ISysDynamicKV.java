package com.ai.mine.crystal.interfaces;

import com.ai.mine.common.dto.BaseInfo;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;

import java.util.List;

public interface ISysDynamicKV {
    /**
     * 服务提供方需要实现ISysDynamicKV接口中的queryDynamicDictionary方法，返回下拉框数据的List
     * @return List[Select2ItemDTO]的列表，如果是分组下拉框，则是组的列表
     * @throws BusinessException 失败则抛出业务异常
     */
    List<Select2ItemDTO> queryDynamicDictionary(BaseInfo baseInfo) throws BusinessException;
}
