package com.ai.mine.crystal.service.demo.impl;

import com.ai.mine.crystal.dao.mapper.DemoMapper;
import com.ai.mine.crystal.dao.model.Demo;
import com.ai.mine.crystal.dao.model.DemoExample;
import com.ai.mine.crystal.dto.req.DemoDTO;
import com.ai.mine.crystal.dto.resp.DemoRespDTO;
import com.ai.mine.crystal.service.demo.interfaces.IDemoSV;
import com.ai.mine.common.dto.PageResponseDTO;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.common.utils.PageResponseFactory;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoSVImpl implements IDemoSV {

    @Autowired
    DemoMapper demoMapper;

    @Override
    public PageResponseDTO<DemoRespDTO> queryDemoPages(DemoDTO baseInfo) throws BusinessException {
        int pageNo = baseInfo.getPageNo();
        int pageSize = baseInfo.getPageSize();
        String sortName = baseInfo.getGridQuerySortName();
        String sortOrder = baseInfo.getGridQuerySortOrder();

        DemoExample example = new DemoExample();
        if (StringUtil.isNotEmpty(sortName)) {
            example.setOrderByClause(sortName + " " + sortOrder);
        } else {
            example.setOrderByClause("create_time");
        }
        PageHelper.startPage(pageNo, pageSize);
        List<Demo> list = demoMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            PageInfo<Demo> pageInfo = new PageInfo<>(list);
            return PageResponseFactory.genPageResponse(pageInfo, DemoRespDTO.class);
        }
        return null;
    }
}
