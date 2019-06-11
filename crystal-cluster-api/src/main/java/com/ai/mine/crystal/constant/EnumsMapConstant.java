package com.ai.mine.crystal.constant;

import com.ai.mine.crystal.enums.SexEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举字典的常量定义
 */
public class EnumsMapConstant {
    /**
     * 定义一个字典的KEY
     */
    public static final String SEX_KEY = "sex";





    /**
     * 将字典的KEY对应的Enum实现放到Map中
     */
    public static final Map<String, String> ENUMS_MAP = new HashMap<String, String>() {
        {
            put(SEX_KEY, SexEnum.class.getName());

        }
    };

}
