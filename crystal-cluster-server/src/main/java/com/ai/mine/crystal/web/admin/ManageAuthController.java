package com.ai.mine.crystal.web.admin;

import com.ai.mine.user.core.dto.req.AuthAuthorityDTO;
import com.ai.mine.user.core.dto.resp.AuthAuthorityRespDTO;
import com.ai.mine.user.core.dto.resp.AuthMenuResourceRespDTO;
import com.ai.mine.user.core.service.interfaces.IManageAuthSV;
import com.ai.mine.user.core.service.interfaces.IManageMenuSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 权限相关控制层
 */
@Controller
@RequestMapping("/admin/manageAuthority")
public class ManageAuthController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IManageAuthSV manageAuthSV;

    @Autowired
    private IManageMenuSV manageMenuSV;

    /**
     * 查看权限对应的菜单信息列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping("/auth")
    public String index(Model model) throws Exception {

        //获取传递过来的所有的权限
        AuthAuthorityDTO authAuthorityDTO = new AuthAuthorityDTO();
        List<AuthAuthorityRespDTO> authAuthorityRespDTOList = manageAuthSV.queryAllAuthAuthority(authAuthorityDTO);
        model.addAttribute("authAuthorityList", authAuthorityRespDTOList);

        Long authorityId = authAuthorityRespDTOList.get(0).getAuthorityId();
        List<AuthMenuResourceRespDTO> authMenus = manageMenuSV.queryMenuByAuthorityId(Long.valueOf(100001l));
        model.addAttribute("authMenus", authMenus);


        return "admin/system/auths";
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
     * 权限对应的菜单(既是资源)
     * @param model
     * @param authorityId
     * @return
     */
    @RequestMapping("/authToMenu")
    public String authToMenu(Model model, @RequestParam String authorityId){
        HashMap<String, Object> result = new HashMap<>();
        try{
            System.out.println("---------查詢权限开始----------------------------------");
            Date star = new Date();
            List<AuthMenuResourceRespDTO> authMenus = manageMenuSV.queryMenuByAuthorityId(Long.valueOf(authorityId));
            Date end = new Date();
            long time =  end.getTime() - star.getTime();
            System.out.println("查詢耗時"+time +"   毫秒");
            model.addAttribute("authMenus", authMenus);
        } catch (Exception e) {
            logger.error("获取菜单失败，失败信息：" + e);
        }
        return "admin/system/authmenu";
    }




}
