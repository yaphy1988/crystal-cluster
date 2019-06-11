package com.ai.mine.crystal.service.common;

import com.ai.mine.crystal.dto.resp.UserExtensionRespDTO;
import com.ai.mine.crystal.service.system.interfaces.IManageUserExtensionSV;
import com.ai.mine.security.interfaces.IUserExtendedService;
import com.ai.mine.user.core.dto.resp.UserAuthRespDTO;
import com.ai.mine.user.core.dto.resp.UserBaseRespDTO;
import com.ai.mine.user.core.service.interfaces.IManageUserSV;
import com.ai.paas.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MineUserExtendedService implements IUserExtendedService {

    @Autowired
    IManageUserSV manageUserSV;

    @Autowired
    IManageUserExtensionSV manageUserExtensionSV;

    @Override
    public Object loadUserExtendedInfoByUserId(Long userId) {
        Assert.notNull(userId,"用户编码userId不能为空！");
        UserExtensionDTO extensionDTO = new UserExtensionDTO();
        UserBaseRespDTO userBase = manageUserSV.queryUserBase(userId);
        UserAuthRespDTO userAuth = manageUserSV.queryUserAuth(userId);
        UserExtensionRespDTO userExtension = manageUserExtensionSV.queryUserExtensionByUserId(userId);

        if (userBase != null) {
            BeanUtils.copyProperties(userBase, extensionDTO);
            extensionDTO.setCreateTime(DateUtil.formatTime(userBase.getCreateTime()));
        }
        if (userAuth != null) BeanUtils.copyProperties(userAuth, extensionDTO);
        if (userExtension != null) BeanUtils.copyProperties(userExtension, extensionDTO);
        return extensionDTO;
    }
}
