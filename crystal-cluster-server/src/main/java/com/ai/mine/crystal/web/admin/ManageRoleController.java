package com.ai.mine.crystal.web.admin;

import com.ai.mine.user.core.dto.req.AuthAuthorityDTO;
import com.ai.mine.user.core.dto.req.UserRoleAuthorityDTO;
import com.ai.mine.user.core.dto.req.UserRoleDTO;
import com.ai.mine.user.core.dto.resp.AuthAuthorityRespDTO;
import com.ai.mine.user.core.dto.resp.UserRoleAuthorityRespDTO;
import com.ai.mine.user.core.dto.resp.UserRoleRespDTO;
import com.ai.mine.user.core.service.interfaces.IManageAuthSV;
import com.ai.mine.user.core.service.interfaces.IManageRoleSV;
import com.ai.paas.utils.CollectionUtil;
import com.ai.paas.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 管理系统角色
 * 提供：
 *   1. 角色列表展示
 *   2. 新增角色
 *   3. 角色拥有的权限授权
 *   4. 删除角色
 */
@Controller
@RequestMapping("/admin/manageRole")
public class ManageRoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IManageRoleSV manageRoleSV;

    @Autowired
    private IManageAuthSV manageAuthSV;

    /**
     * 查询角色列表信息
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/roles")
    public String index(Model model) throws Exception {
        UserRoleDTO var1 = new UserRoleDTO();
        var1.setIsValid(Byte.valueOf("1")); //查询有效的角色信息
        List<UserRoleRespDTO> roleList = manageRoleSV.queryUserRoleList(var1);
        model.addAttribute("roleList", roleList);
        return "admin/system/roles";
    }

    /**
     * 跳转到新增角色界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/newRole")
    public String newRole(Model model) throws Exception {
        return "admin/system/newrole";
    }

    /**
     * 设置返回的结果集信息
     * @param flag
     * @param msg
     * @return
     */
    public HashMap<String, Object> getComResult(boolean flag, String msg){
        HashMap<String, Object> result = new HashMap<>();
        result.put("msg", msg);
        result.put("success", flag);
        return result;
    }

    /**
     * 创建角色
     * @param model
     * @param roleName
     * @param roleType
     * @param rankNumber
     * @return
     */
    @RequestMapping("/createRole")
    @ResponseBody
    public Object createRole(Model model, @RequestParam String roleName, @RequestParam String roleType,
                             @RequestParam String rankNumber){
        HashMap<String, Object> result = new HashMap<>();
        try{
            if(StringUtil.isBlank(roleName)){
                return getComResult(false,"角色名称不能为空！");
            }
            if(StringUtil.isBlank(roleType)){
                return getComResult(false,"角色类型不能为空！");
            }

            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleName(roleName);
            userRoleDTO.setRoleType(roleType);
            userRoleDTO.setRankNumber(Integer.valueOf(rankNumber));
            manageRoleSV.createUserRole(userRoleDTO);
            return getComResult(true,"角色新增成功！");
        } catch (Exception e) {
            logger.error("创建校色失败，失败信息：" + e);
            return getComResult(false,"角色创建失败！");
        }
    }

    /**
     * 失效创建的角色名称
     * @param model
     * @param roleId
     * @return
     */
    @RequestMapping("/deleteRole")
    @ResponseBody
    public Object deleteRole(Model model, @RequestParam String roleId){
        HashMap<String, Object> result = new HashMap<>();
        try{
            if(StringUtil.isBlank(roleId)){
                return getComResult(false,"传递的角色编码为空，不允许删除！");
            }
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleId(Integer.valueOf(roleId));
            userRoleDTO.setIsValid(Byte.valueOf("0"));
            manageRoleSV.updateUserRole(userRoleDTO);
            return getComResult(true,"角色删除成功！");
        } catch (Exception e) {
            logger.error("角色删除失败，失败信息：" + e);
            return getComResult(false,"角色删除失败！");
        }
    }

    /**
     * 编辑角色
     * @param model
     * @param roleName
     * @param roleType
     * @param rankNumber
     * @return
     */
    @RequestMapping("/updateRole")
    @ResponseBody
    public Object updateRole(Model model, @RequestParam String roleName, @RequestParam String roleType,
                             @RequestParam String rankNumber,@RequestParam Long userRoleId){
        HashMap<String, Object> result = new HashMap<>();
        try{
            if(StringUtil.isBlank(roleName)){
                return getComResult(false,"角色名称不能为空！");
            }
            if(StringUtil.isBlank(roleType)){
                return getComResult(false,"角色类型不能为空！");
            }
            if(userRoleId == null || userRoleId.longValue() <= 0){
                return getComResult(false,"角色编码不能为空！");
            }
            UserRoleDTO userRoleDTO = new UserRoleDTO();
            userRoleDTO.setRoleId(userRoleId.intValue());
            userRoleDTO.setRoleName(roleName);
            userRoleDTO.setRoleType(roleType);
            userRoleDTO.setRankNumber(Integer.valueOf(rankNumber));
            manageRoleSV.updateUserRole(userRoleDTO);
            return getComResult(true,"角色更新成功！");
        } catch (Exception e) {
            logger.error("角色更新失败，失败信息：" + e);
            return getComResult(false,"角色更新失败！");
        }
    }

    /**
     * 跳转授权界面
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/authorityRole")
    public String authorityRole(Model model,@RequestParam String roleId) throws Exception {

        UserRoleRespDTO userRoleRespDTO = manageRoleSV.queryUserRole(Long.valueOf(roleId));
        model.addAttribute("userRoleRespDTO", userRoleRespDTO);

        //获取传递过来的所有的权限
        AuthAuthorityDTO authAuthorityDTO = new AuthAuthorityDTO();
        List<AuthAuthorityRespDTO> authAuthorityRespDTOList = manageAuthSV.queryAllAuthAuthority(authAuthorityDTO);


        //获取传递角色拥有的权限ID
        UserRoleAuthorityDTO var1 = new UserRoleAuthorityDTO();
        var1.setRoleId(Integer.valueOf(roleId));
        List<UserRoleAuthorityRespDTO> userRoleAuthorityRespDTOS = manageRoleSV.queryUserRoleAuthorityList(var1);

        List<AuthAuthorityRespDTO> authAuthorityResps = new ArrayList<AuthAuthorityRespDTO>();
        List<AuthAuthorityRespDTO> hadAuthAuthorityResps = new ArrayList<AuthAuthorityRespDTO>();
        if(!CollectionUtil.isEmpty(userRoleAuthorityRespDTOS)){
            for(AuthAuthorityRespDTO authAuthorityRespDTO : authAuthorityRespDTOList){
                boolean ifHadFlag = false;
                for(UserRoleAuthorityRespDTO userRoleAuthorityRespDTO : userRoleAuthorityRespDTOS){
                    if(authAuthorityRespDTO.getAuthorityId().longValue()
                            == userRoleAuthorityRespDTO.getAuthorityId().longValue()){
                        hadAuthAuthorityResps.add(authAuthorityRespDTO);
                        ifHadFlag = true;
                        break;
                    }
                }
                if(!ifHadFlag){
                    authAuthorityResps.add(authAuthorityRespDTO);
                }
            }
            model.addAttribute("authAuthorityResps", authAuthorityResps);
        } else {
            model.addAttribute("authAuthorityResps", authAuthorityRespDTOList);
        }
        model.addAttribute("hadAuthAuthorityResps", hadAuthAuthorityResps);

        return "admin/system/authorityRole";
    }

    /**
     * 角色授权保存
     * @param model
     * @param authorityIds
     * @param roleId
     * @return
     */
    @RequestMapping("/saveAuthorityRole")
    @ResponseBody
    public Object saveAuthorityRole(Model model, @RequestParam String roleId, @RequestParam(value = "authorityIds[]") String[] authorityIds){
        HashMap<String, Object> result = new HashMap<>();
        try{
            if(authorityIds == null){
                return getComResult(false,"授权信息不能为空！");
            }
            if(StringUtil.isBlank(roleId)){
                return getComResult(false,"角色编码不能为空！");
            }

            //保存角色授权信息
            Integer roleIdInt = Integer.valueOf(roleId);
            List<UserRoleAuthorityDTO> userRoleAuthorityDTOS = new ArrayList<UserRoleAuthorityDTO>();
            UserRoleAuthorityDTO userRoleAuthorityDTO = null;
            for(String authorityId : authorityIds){
                userRoleAuthorityDTO = new UserRoleAuthorityDTO();
                userRoleAuthorityDTO.setRoleId(roleIdInt);
                userRoleAuthorityDTO.setAuthorityId(Long.valueOf(authorityId));
                userRoleAuthorityDTOS.add(userRoleAuthorityDTO);
            }
            manageRoleSV.saveUserRoleAutorityList(userRoleAuthorityDTOS, roleIdInt);

            return getComResult(true,"授权成功！");
        } catch (Exception e) {
            logger.error("授权失败，失败信息：" + e);
            return getComResult(false,"授权失败！");
        }
    }
}
