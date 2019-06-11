package com.ai.mine.crystal.web.admin;

import com.ai.mine.common.dto.BaseInfo;
import com.ai.mine.crystal.common.core.SpringContextHolder;
import com.ai.mine.crystal.constant.DynamicMapConstant;
import com.ai.mine.crystal.constant.EnumsMapConstant;
import com.ai.mine.crystal.dto.WebRespVO;
import com.ai.mine.crystal.dto.req.DynamicKVDTO;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;
import com.ai.mine.crystal.dto.resp.Select2PaginateDTO;
import com.ai.mine.crystal.dto.resp.Select2RespDTO;
import com.ai.mine.crystal.enums.IEnumMessage;
import com.ai.mine.crystal.interfaces.ISysDynamicKV;
import com.ai.mine.crystal.service.common.CustomSelectService;
import com.ai.paas.util.CacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/admin/dictionary")
public class SysDictionaryController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String CACHED_DICTIONARY_KEY_PREFIX = "DICTIONARY_KEY_";
    /**
     * 存储Enum枚举类型的字典，用于缓存
     */
    private ConcurrentHashMap<String, Select2RespDTO> dictionaryMap = new ConcurrentHashMap<>();

    @Autowired
    private CustomSelectService customSelectService;

    // 使用缓存机制，将字典缓存下来，不用每次都用反射生成一次数据，增加缓存清理机制
    // 1. 缓存机制：对于Enum枚举类字典使用内存缓存机制，用HashMap缓存字典，对于动态字典，使用Redis缓存
    // 2. 缓存清理机制，仅对动态缓存有效，支持按照单个字典key的缓存清理，支持所有字典的缓存清理

    @RequestMapping("/enumskv/{dictionaryName}")
    @ResponseBody
    public Object queryEnumsKV(@PathVariable String dictionaryName) {
        logger.info("查询字典 {} 的值！", dictionaryName);
        HashMap<String, Object> errorResp = new HashMap<>();

        //如果本地内存中有缓存，则直接使用，否则就生成后放入缓存
        if (dictionaryMap.containsKey(dictionaryName)) {
            return dictionaryMap.get(dictionaryName);
        }

        //查询出字典对应的Enum定义
        String enumClass = EnumsMapConstant.ENUMS_MAP.get(dictionaryName);
        if (StringUtils.isEmpty(enumClass)) {
            logger.error("输入的字典名错误，找不到字典 {} 定义！", dictionaryName);
            errorResp.put("error","输入的字典名错误!");
            return errorResp;
        }

        //反射机制获得Enum对象
        try {
            Class<?> cls = Class.forName(enumClass);
            Method method = cls.getMethod("values");
            IEnumMessage inter[] = (IEnumMessage[]) method.invoke(null, null);
            List<Select2ItemDTO> list = new ArrayList<>();
            for (IEnumMessage enumMessage : inter) {
                Select2ItemDTO item = new Select2ItemDTO();
                item.setId(String.valueOf(enumMessage.getCode()));
                item.setText(enumMessage.getText());
                list.add(item);
            }

            //封装成Select2的数据对象，返回
            Select2RespDTO resp = new Select2RespDTO();
            resp.setResults(list);
            resp.setPaginate(new Select2PaginateDTO());

            //存入缓存并返回
            dictionaryMap.put(dictionaryName, resp);
            return resp;
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.error("实例化枚举对象 {} 失败，请联系开发人员处理！异常信息：", enumClass, e);
            errorResp.put("error","实例化枚举对象失败，请联系系统维护人员!");
            return errorResp;
        }
    }

    @RequestMapping("/dynamickv/{dictionaryName}")
    @ResponseBody
    public Object queryDynamicKV(@PathVariable String dictionaryName) {
        logger.info("查询字典 {} 的值！", dictionaryName);
        HashMap<String, Object> errorResp = new HashMap<>();

        //从Redis缓存服务中获取缓存的字典，如果不存在缓存则重现查询，放入Redis缓存
        Select2RespDTO cachedDict = getCachedDictionary(dictionaryName);
        if (cachedDict != null) return cachedDict;

        //查询出字典对应的定义
        DynamicKVDTO dynamicKVDTO = DynamicMapConstant.DYNAMICKV_MAP.get(dictionaryName);
        if (dynamicKVDTO == null || StringUtils.isEmpty(dynamicKVDTO.getDictionaryInstance())) {
            logger.error("输入的字典名错误，找不到字典 {} 定义！", dictionaryName);
            errorResp.put("error","输入的字典名错误!");
            return errorResp;
        }

        //根据动态字典的类型，执行查询服务，获得字典列表
        List<Select2ItemDTO> list;
        if (dynamicKVDTO.getDictionaryType() == DynamicMapConstant.DICTIONARY_TYPE_SQL) {
            list = customSelectService.queryDynamicKVBySql(dynamicKVDTO.getDictionaryInstance());
        } else if (dynamicKVDTO.getDictionaryType() == DynamicMapConstant.DICTIONARY_TYPE_INTF) {
            ISysDynamicKV sysDynamicKV = SpringContextHolder.getBean(dynamicKVDTO.getDictionaryInstance());
            list = sysDynamicKV.queryDynamicDictionary(new BaseInfo());
        } else {
            logger.error("字典类型 dynamicKVDTO.getDictionaryType = {} 错误，请联系开发人员！", dynamicKVDTO.getDictionaryType());
            errorResp.put("error","字典类型错误，请联系系统维护人员!");
            return errorResp;
        }

        //封装成Select2的数据对象，返回
        Select2ItemDTO select2ItemDTO = new Select2ItemDTO();
        select2ItemDTO.setId("");
        select2ItemDTO.setText("全部");
        list.add(select2ItemDTO);
        Select2RespDTO resp = new Select2RespDTO();
        resp.setResults(list);
        resp.setPaginate(new Select2PaginateDTO());

        return setCachedDictionary(dictionaryName, resp);
    }

    //动态查询，但是不进行缓存
    @RequestMapping("/dynamickvnocache/{dictionaryName}")
    @ResponseBody
    public Object queryDynamicKVNoCache(@PathVariable String dictionaryName) {
        logger.info("查询字典 {} 的值！", dictionaryName);
        HashMap<String, Object> errorResp = new HashMap<>();

        //查询出字典对应的定义
        DynamicKVDTO dynamicKVDTO = DynamicMapConstant.DYNAMICKV_MAP.get(dictionaryName);
        if (dynamicKVDTO == null || StringUtils.isEmpty(dynamicKVDTO.getDictionaryInstance())) {
            logger.error("输入的字典名错误，找不到字典 {} 定义！", dictionaryName);
            errorResp.put("error","输入的字典名错误!");
            return errorResp;
        }

        //根据动态字典的类型，执行查询服务，获得字典列表
        List<Select2ItemDTO> list;
        if (dynamicKVDTO.getDictionaryType() == DynamicMapConstant.DICTIONARY_TYPE_SQL) {
            list = customSelectService.queryDynamicKVBySql(dynamicKVDTO.getDictionaryInstance());
        } else if (dynamicKVDTO.getDictionaryType() == DynamicMapConstant.DICTIONARY_TYPE_INTF) {
            ISysDynamicKV sysDynamicKV = SpringContextHolder.getBean(dynamicKVDTO.getDictionaryInstance());
            list = sysDynamicKV.queryDynamicDictionary(new BaseInfo());
        } else {
            logger.error("字典类型 dynamicKVDTO.getDictionaryType = {} 错误，请联系开发人员！", dynamicKVDTO.getDictionaryType());
            errorResp.put("error","字典类型错误，请联系系统维护人员!");
            return errorResp;
        }

        //封装成Select2的数据对象，返回
        Select2RespDTO resp = new Select2RespDTO();
        resp.setResults(list);
        resp.setPaginate(new Select2PaginateDTO());

        return resp;
    }

    @RequestMapping("/cleankv/{dictionaryName}")
    @ResponseBody
    public Object cleanDynamicKVCache(@PathVariable String dictionaryName) {
        logger.info("清空字典 {} 的缓存！", dictionaryName);
        if (cleanCachedDictionary(dictionaryName)) {
            return new WebRespVO("success","删除成功！");
        } else {
            return new WebRespVO("failed","删除失败！");
        }
    }

    @RequestMapping("/cleanallkv")
    @ResponseBody
    public Object cleanAllDynamicKVCache() {
        for (Map.Entry<String,DynamicKVDTO> entrySet : DynamicMapConstant.DYNAMICKV_MAP.entrySet()) {
            String dictionayName = entrySet.getKey();
            if (!cleanCachedDictionary(dictionayName)) {
                //删除失败，返回错误信息，退出循环
                return new WebRespVO("failed","删除失败！");
            }
        }
        return new WebRespVO("success","删除成功！");
    }

    private Select2RespDTO getCachedDictionary(String dictionaryName) {
        return (Select2RespDTO) CacheUtil.getItem(CACHED_DICTIONARY_KEY_PREFIX + dictionaryName);
    }

    private Select2RespDTO setCachedDictionary(String dictionaryName, Select2RespDTO dictionary) {
        CacheUtil.addItem(CACHED_DICTIONARY_KEY_PREFIX + dictionaryName, dictionary, 3600);
        return dictionary;
    }

    private boolean cleanCachedDictionary(String dictionaryName) {
        if (CacheUtil.exists(CACHED_DICTIONARY_KEY_PREFIX + dictionaryName)) {
            long del = CacheUtil.delItem(CACHED_DICTIONARY_KEY_PREFIX + dictionaryName);
            return del > 0;
        } else {
            return true;
        }
    }

}
