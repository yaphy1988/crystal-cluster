package com.ai.mine.crystal.service.system.interfaces;


import com.ai.mine.crystal.dto.resp.UserExtensionRespDTO;

/**
 * IManageUserExtensionSV 管理用户扩展信息
 */
public interface IManageUserExtensionSV {

    /**
     * 根据用户编码userId查询用户扩展信息表（t_user_extension）中的信息
     * @param userId 用户编码 = t_user_base.user_id
     * @return UserExtensionRespDTO
     */
    UserExtensionRespDTO queryUserExtensionByUserId(Long userId);
}
