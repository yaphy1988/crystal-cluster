package com.ai.mine.crystal.dao.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CustomSelectMapper {
    List<Map<String, Object>> selectItemList(@Param(value = "sqlStr") String sqlStr);
}
