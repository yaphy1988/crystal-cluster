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
    public static final String PRODUCT_TYPE_KEY = "productType";
    //所有角色的字典
    public static final String ROLES_DICT_KEY = "rolesDict";
    //包含用户已有角色选中状态的角色字典
    public static final String ROLES_DICT_BYUSER_KEY = "rolesDictByUser";
    public static final String PARENT_CHANNEL_KEY = "parentChannel";

    /**
     * 将动态字典的KEY对应的字典定义放到Map中
     */
    public static final Map<String, DynamicKVDTO> DYNAMICKV_MAP = new HashMap<String, DynamicKVDTO>() {
        {
            put(PRODUCT_TYPE_KEY, new DynamicKVDTO(DICTIONARY_TYPE_SQL,
                    "select type_key code, type_name text from T_product_type where status = '1'"));
            put(ROLES_DICT_KEY, new DynamicKVDTO(DICTIONARY_TYPE_INTF,
                    "rolesDictionarySVImpl"));
            put(ROLES_DICT_BYUSER_KEY, new DynamicKVDTO(DICTIONARY_TYPE_INTF,
                    "rolesDictionaryByUserSVImpl"));
            put(PARENT_CHANNEL_KEY,new DynamicKVDTO(DICTIONARY_TYPE_SQL,
                    "select channel_id code,channel_name text from t_channel_info where channel_level = '1'" ));
        }
    };

}
