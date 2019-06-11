package com.ai.mine.crystal.service.common;

import com.ai.mine.crystal.dao.mapper.CustomSelectMapper;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 自定义SQL查询服务
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CustomSelectService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomSelectMapper mapper;

    public List<Select2ItemDTO> queryDynamicKVBySql(String sqlStr) {
        List<Select2ItemDTO> list = new ArrayList<>();
        logger.info("queryDynamicKVBySql execute: sqlStr = {}", sqlStr);

        //根据SQL查询出字典定义的键值对列表
        List<Map<String, Object>> items = mapper.selectItemList(sqlStr);

        //封装成Select2ItemDTO对象
        for (Map<String, Object> item : items) {
            Select2ItemDTO dto = new Select2ItemDTO();
            if (item.containsKey("code")) dto.setId(item.get("code").toString());
            if (item.containsKey("text")) dto.setText(item.get("text").toString());
            if (item.containsKey("selected")) dto.setSelected(item.get("selected").toString().equalsIgnoreCase("true"));
            if (item.containsKey("disabled")) dto.setDisabled(item.get("disabled").toString().equalsIgnoreCase("true"));

            list.add(dto);
        }

        return list;
    }
}
