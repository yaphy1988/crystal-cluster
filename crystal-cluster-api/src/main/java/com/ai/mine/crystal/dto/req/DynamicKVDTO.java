package com.ai.mine.crystal.dto.req;

/**
 * 定义动态字典的描述对象
 */
public class DynamicKVDTO {

    /**
     * 字典类型：0=SQL定义，1=接口定义，常量定义参见DynamicMapConstant.DICTIONARY_TYPE_SQL 和DynamicMapConstant.DICTIONARY_TYPE_INTF
     * 说明：SQL定义返回至少包含code-text两个字段的列表，接口定义是实现ISysDynamicKV接口的Bean名称
     */
    private int dictionaryType;

    /**
     * 字典实体定义，根据类型不同分别取值为SQL字符串、ISysDynamicKV接口的实现类名
     * SQL例子：select type_key code, type_name text from t_product_type where status = '1'
     * 接口例子：com.ai.seed.seedcenter.service.system.impl.RoleDictionarySVImpl
     */
    private String dictionaryInstance;

    public DynamicKVDTO(int dictionaryType, String dictionaryInstance) {
        this.dictionaryType = dictionaryType;
        this.dictionaryInstance = dictionaryInstance;
    }

    public int getDictionaryType() {
        return dictionaryType;
    }

    public void setDictionaryType(int dictionaryType) {
        this.dictionaryType = dictionaryType;
    }

    public String getDictionaryInstance() {
        return dictionaryInstance;
    }

    public void setDictionaryInstance(String dictionaryInstance) {
        this.dictionaryInstance = dictionaryInstance;
    }
}
