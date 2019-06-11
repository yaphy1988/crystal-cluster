package com.ai.mine.crystal.service.system.impl;

import com.ai.mine.common.dto.BaseInfo;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;
import com.ai.mine.crystal.interfaces.ISysDynamicKV;
import com.ai.mine.user.core.common.CommonConstants;
import com.ai.mine.user.core.dao.mapper.TUserRoleMapper;
import com.ai.mine.user.core.dao.model.TUserRole;
import com.ai.mine.user.core.dao.model.TUserRoleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class RolesDictionarySVImpl implements ISysDynamicKV {

    @Autowired
    private TUserRoleMapper userRoleMapper;

    @Override
    public List<Select2ItemDTO> queryDynamicDictionary(BaseInfo baseInfo) throws BusinessException {
        List<Select2ItemDTO> items = new ArrayList<>();

        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria().andIsValidEqualTo(CommonConstants.STATUS_VALID);
        List<TUserRole> list = userRoleMapper.selectByExample(example);

        for (TUserRole role : list) {
            Select2ItemDTO item = new Select2ItemDTO();
            item.setId(role.getRoleId().toString());
            item.setText(role.getRoleName());
            items.add(item);
        }
        return items;
    }
}
