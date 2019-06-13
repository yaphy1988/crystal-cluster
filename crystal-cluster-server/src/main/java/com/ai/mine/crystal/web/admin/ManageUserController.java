package com.ai.mine.crystal.web.admin;

import com.ai.mine.crystal.common.core.SpringContextHolder;
import com.ai.mine.crystal.constant.DynamicMapConstant;
import com.ai.mine.crystal.dto.req.DynamicKVDTO;
import com.ai.mine.crystal.dto.resp.Select2ItemDTO;
import com.ai.mine.crystal.interfaces.ISysDynamicKV;
import com.ai.mine.security.utils.SecurityUtil;
import com.ai.mine.user.core.common.CommonConstants;
import com.ai.mine.user.core.common.UserTypeEnum;
import com.ai.mine.user.core.dto.req.UserBaseDTO;
import com.ai.mine.user.core.dto.req.UserRoleuserDTO;
import com.ai.mine.user.core.dto.resp.UserBaseRespDTO;
import com.ai.mine.user.core.service.interfaces.IManageRoleSV;
import com.ai.mine.user.core.service.interfaces.IManageUserSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * 管理系统中的用户
 * 提供：
 *   1. 分类型的用户清单分页展示
 *   2. 系统后台管理类用户的角色分配
 *   3. 新增后台管理类用户
 *   4. 修改后台管理类用户的密码
 *   5. 失效用户
 */
@Controller
@RequestMapping("/admin/manageUser")
public class ManageUserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IManageUserSV manageUserSV;

    @Autowired
    private IManageRoleSV manageRoleSV;

    @RequestMapping("/users")
    public String index(Model model) throws Exception {
        UserBaseDTO userBaseDTO = new UserBaseDTO();
        //userBaseDTO.setUserType(UserTypeEnum.ADMIN.name());
        List<UserBaseRespDTO> userList = manageUserSV.queryUserBaseList(userBaseDTO);
        model.addAttribute("userList", userList);
        return "admin/system/users";
    }
    @RequestMapping("/queryusers")
    public String queryusers(Model model, UserBaseDTO userBaseDTO) throws Exception {

        List<UserBaseRespDTO> userList = manageUserSV.queryUserBaseList(userBaseDTO);

        model.addAttribute("userList", userList);
        return "admin/system/users :: #userTables";

    }

    @RequestMapping("/newUser")
    public String newUser(Model model) throws Exception {
        return "admin/system/newuser";
    }

    @RequestMapping("/showedit")
    public String showEdit(Model model, @RequestParam Long userId) throws Exception {
        UserBaseRespDTO user = manageUserSV.queryUserBase(userId);
        model.addAttribute("user", user);
        model.addAttribute("select2", queryRoleDict(userId));
        return "admin/system/editusermodule :: #edit-user-form";
    }

    @RequestMapping(value = "/checkname")
    @ResponseBody
    public Object checkname(@RequestParam String username) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        UserBaseRespDTO user = manageUserSV.loadUserId(username);
        if (user != null && user.getUserId() > 0) {
            //有用户返回false
            result.put("success", false);
        } else {
            //没有用户返回true
            result.put("success", true);
        }
        return result;
    }

    @RequestMapping("/createUser")
    @ResponseBody
    public Object createUser(Model model, @RequestParam String username, @RequestParam String nickname,
                           @RequestParam String fullname, @RequestParam String mobile, @RequestParam String email,
                           @RequestParam String password, @RequestParam(value = "roles[]") String[] roles) throws Exception {

      HashMap<String, Object> result = new HashMap<>();
      try{
        //新建用户
        UserBaseDTO userBase = new UserBaseDTO();
        userBase.setUserType(UserTypeEnum.ADMIN.name());
        userBase.seteMail(email);
        userBase.setMobilePhone(mobile);
        userBase.setFullName(fullname);
        userBase.setNickName(nickname);
        userBase.setUserName(username);
        userBase.setUserEnabled(CommonConstants.ENABLED);
        //设置密码
        if (StringUtils.isEmpty(password)) password = "123456";
        String passwordEnc = SecurityUtil.encode(password);
        userBase = manageUserSV.createUser(userBase, UserTypeEnum.ADMIN, passwordEnc);

        if (userBase.getUserId() > 0) {
            //设置角色
            for (String role : roles) {
                UserRoleuserDTO roleUser = new UserRoleuserDTO();
                roleUser.setRoleId(Integer.valueOf(role));
                roleUser.setUserId(userBase.getUserId());

                manageRoleSV.createUserRoleUser(roleUser);
            }
            result.put("success", true);
        } else {
            result.put("success", false);
            result.put("msg","创建失败了");
        }
    }catch(Exception e){
          result.put("success", false);
          result.put("msg",e.getMessage());
    }
        return result;
    }

    @RequestMapping("/edituser")
    @ResponseBody
    public Object editUser(Model model, @RequestParam Long userid, @RequestParam String username, @RequestParam String nickname,
                             @RequestParam String fullname, @RequestParam String mobile, @RequestParam String email,
                             @RequestParam(value = "roles[]") String[] roles) throws Exception {

        HashMap<String, Object> result = new HashMap<>();
        try{
            //修改用户
            UserBaseDTO userBase = new UserBaseDTO();
            UserBaseRespDTO userBaseRespDTO = manageUserSV.queryUserBase(userid);
            BeanUtils.copyProperties(userBaseRespDTO, userBase);
            userBase.setUserType(UserTypeEnum.ADMIN.name());
            userBase.seteMail(email);
            userBase.setMobilePhone(mobile);
            userBase.setFullName(fullname);
            userBase.setNickName(nickname);
            userBase.setUserEnabled(CommonConstants.ENABLED);

            userBase = manageUserSV.updateUserBase(userBase);

            if (userBase.getUserId() > 0) {
                //更新角色
                //TODO: 识别用户角色变更信息，保存更新后的角色
                for (String role : roles) {
                    UserRoleuserDTO roleUser = new UserRoleuserDTO();
                    roleUser.setRoleId(Integer.valueOf(role));
                    roleUser.setUserId(userBase.getUserId());

                }
                result.put("success", true);
            } else {
                result.put("success", false);
                result.put("msg","创建失败了");
            }
        }catch(Exception e){
            result.put("success", false);
            result.put("msg",e.getMessage());
        }
        return result;
    }

    private List<Select2ItemDTO> queryRoleDict(Long userId) {
        logger.info("查询用户 {} 拥有的角色的值！", userId);
        String dictionaryName = DynamicMapConstant.ROLES_DICT_BYUSER_KEY;

        //查询出字典对应的定义
        DynamicKVDTO dynamicKVDTO = DynamicMapConstant.DYNAMICKV_MAP.get(dictionaryName);
        if (dynamicKVDTO == null || StringUtils.isEmpty(dynamicKVDTO.getDictionaryInstance())) {
            logger.error("输入的字典名错误，找不到字典 {} 定义！", dictionaryName);
            return null;
        }

        ISysDynamicKV sysDynamicKV = SpringContextHolder.getBean(dynamicKVDTO.getDictionaryInstance());
        UserBaseDTO info = new UserBaseDTO();
        info.setUserId(userId);

        return sysDynamicKV.queryDynamicDictionary(info);
    }

}
