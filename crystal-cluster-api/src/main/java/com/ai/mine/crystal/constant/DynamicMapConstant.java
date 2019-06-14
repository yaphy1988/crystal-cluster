package com.ai.mine.crystal.constant;


import com.ai.mine.crystal.dto.req.DynamicKVDTO;

import java.util.HashMap;
import java.util.Map;

public class DynamicMapConstant {

    public static final int DICTIONARY_TYPE_SQL = 0;
    public static final int DICTIONARY_TYPE_INTF = 1;

    /**
     * 定义一个动态字典的KEY
     */
    //所有角色的字典
    public static final String ROLES_DICT_KEY = "rolesDict";
    //包含用户已有角色选中状态的角色字典
    public static final String ROLES_DICT_BYUSER_KEY = "rolesDictByUser";

    /**
     * 将动态字典的KEY对应的字典定义放到Map中
     */
    public static final Map<String, DynamicKVDTO> DYNAMICKV_MAP = new HashMap<String, DynamicKVDTO>() {
        {
            put(ROLES_DICT_KEY, new DynamicKVDTO(DICTIONARY_TYPE_INTF,
                    "rolesDictionarySVImpl"));
            put(ROLES_DICT_BYUSER_KEY, new DynamicKVDTO(DICTIONARY_TYPE_INTF,
                    "rolesDictionaryByUserSVImpl"));

        }
    };

}
