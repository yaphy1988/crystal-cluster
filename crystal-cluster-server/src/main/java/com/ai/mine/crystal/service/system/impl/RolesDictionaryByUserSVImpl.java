package com.ai.mine.crystal.service.system.impl;

import com.ai.mine.common.dto.BaseInfo;
import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;
import com.ai.mine.crystal.interfaces.ISysDynamicKV;
import com.ai.mine.user.core.common.CommonConstants;
import com.ai.mine.user.core.dao.mapper.TUserRoleMapper;
import com.ai.mine.user.core.dao.mapper.TUserRoleuserMapper;
import com.ai.mine.user.core.dao.model.TUserRole;
import com.ai.mine.user.core.dao.model.TUserRoleExample;
import com.ai.mine.user.core.dao.model.TUserRoleuser;
import com.ai.mine.user.core.dao.model.TUserRoleuserExample;
import com.ai.mine.user.core.dto.req.UserBaseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class RolesDictionaryByUserSVImpl implements ISysDynamicKV {

    @Autowired
    private TUserRoleMapper userRoleMapper;

    @Autowired
    private TUserRoleuserMapper userRoleuserMapper;

    @Override
    public List<Select2ItemDTO> queryDynamicDictionary(BaseInfo baseInfo) throws BusinessException {
        List<Select2ItemDTO> items = new ArrayList<>();
        List<TUserRoleuser> roleusers = new ArrayList<>();

        //查询指定用户已经拥有的角色
        if (baseInfo instanceof UserBaseDTO) {
            Long userId = ((UserBaseDTO) baseInfo).getUserId();
            TUserRoleuserExample example = new TUserRoleuserExample();
            example.createCriteria().andUserIdEqualTo(userId);
            roleusers = userRoleuserMapper.selectByExample(example);
        }

        //查询所有角色列表
        TUserRoleExample example = new TUserRoleExample();
        example.createCriteria().andIsValidEqualTo(CommonConstants.STATUS_VALID);
        List<TUserRole> list = userRoleMapper.selectByExample(example);

        //封装Select2ItemDTO列表用于显示下拉框
        for (TUserRole role : list) {
            Select2ItemDTO item = new Select2ItemDTO();
            item.setId(role.getRoleId().toString());
            item.setText(role.getRoleName());
            for (TUserRoleuser roleuser: roleusers) {
                //判断用户具有某角色，则选中状态为true
                if (roleuser.getRoleId().equals(role.getRoleId())) {
                    item.setSelected(true);
                }
            }
            items.add(item);
        }

        return items;
    }
}
