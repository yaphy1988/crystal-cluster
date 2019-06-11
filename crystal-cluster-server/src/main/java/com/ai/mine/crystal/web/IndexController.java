package com.ai.mine.crystal.web;

import com.ai.mine.common.exception.BusinessException;
import com.ai.mine.security.defaults.MineUserDetails;
import com.ai.mine.user.core.common.UserTypeEnum;
import com.ai.mine.user.core.dto.resp.UserBaseRespDTO;
import com.ai.mine.user.core.service.interfaces.IManageUserSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    IManageUserSV manageUserSV;

    @RequestMapping("/")
    public String defaultIndex() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MineUserDetails userDetails = null;
        String userType = null;

        if (principal instanceof MineUserDetails) {
            userDetails = (MineUserDetails) principal;
        }
        if (userDetails != null) {
            UserBaseRespDTO userDto = manageUserSV.queryUserBase(userDetails.getUserId());
            if (userDto == null) throw new BusinessException("用户不存在","100001");
            userType = userDto.getUserType();
        }
        if (UserTypeEnum.ADMIN.name().equalsIgnoreCase(userType)) {
            return "redirect:/admin/dashboard/index";
        } else {
            return "redirect:/index";
        }
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }


    @RequestMapping("/admin")
    public String admin() {
        return "redirect:/admin/dashboard/index";
    }

}
