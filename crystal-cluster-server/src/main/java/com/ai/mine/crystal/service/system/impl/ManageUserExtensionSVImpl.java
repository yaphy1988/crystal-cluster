package com.ai.mine.crystal.service.system.impl;

import com.ai.mine.crystal.dao.mapper.TUserExtensionMapper;
import com.ai.mine.crystal.dao.model.TUserExtension;
import com.ai.mine.crystal.dto.resp.UserExtensionRespDTO;
import com.ai.mine.crystal.service.system.interfaces.IManageUserExtensionSV;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class ManageUserExtensionSVImpl implements IManageUserExtensionSV {
    @Autowired
    TUserExtensionMapper userExtensionMapper;

    @Override
    public UserExtensionRespDTO queryUserExtensionByUserId(Long userId) {
        Assert.notNull(userId, "userId为空，无法执行查询");
        TUserExtension userExtension = userExtensionMapper.selectByPrimaryKey(userId);
        if (userExtension != null) {
            UserExtensionRespDTO respDTO = new UserExtensionRespDTO();
            BeanUtils.copyProperties(userExtension, respDTO);
            return respDTO;
        } else {
            return null;
        }
    }
}
