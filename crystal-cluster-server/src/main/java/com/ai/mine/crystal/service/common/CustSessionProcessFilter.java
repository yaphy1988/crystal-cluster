package com.ai.mine.crystal.service.common;

import com.ai.mine.crystal.common.WebSite;
import com.ai.mine.crystal.common.WebSiteConstant;
import com.ai.mine.security.defaults.MineCustProcessRequestFilter;
import com.ai.mine.user.core.common.AuthConstants;
import com.ai.mine.user.core.dto.req.AuthMenuResourceDTO;
import com.ai.mine.user.core.dto.resp.AuthMenuResourceBottomUpDTO;
import com.ai.mine.user.core.service.interfaces.IManageMenuSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Component
public class CustSessionProcessFilter extends MineCustProcessRequestFilter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private HashMap<String, AuthMenuResourceBottomUpDTO> menuMaps = new HashMap<>();

    private AuthMenuResourceBottomUpDTO defaultMenu = null;

    @Autowired
    private IManageMenuSV manageMenuSV;

    @Override
    protected void initFilterBean() throws ServletException {
        AuthMenuResourceDTO dto = new AuthMenuResourceDTO();
        dto.setMenuEnabled(AuthConstants.MENU_ENABLED_VALID);
        try {
            List<AuthMenuResourceBottomUpDTO> menuList = manageMenuSV.queryAllMenuResource();
            if (menuList != null && menuList.size() > 0) {
                for (AuthMenuResourceBottomUpDTO menu : menuList) {
                    menuMaps.put(menu.getMenuUrl(), menu);
                }
                defaultMenu = menuList.get(0);
            }
        } catch (Exception e) {
            logger.error("初始化CustSessionProcessFilter出现错误:", e);
        }
    }

    @Override
    protected void doCustFilter(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前请求的URL
        String currentUrl = request.getRequestURI();
        if (logger.isDebugEnabled()) logger.debug(currentUrl);
        AuthMenuResourceBottomUpDTO currentMenu = null;
        WebSite site = WebSite.other;
        if (currentUrl.startsWith("/admin/")) {
            site = WebSite.admin;
        } else if (currentUrl.startsWith("/portal/")) {
            site = WebSite.portal;
        }
        switch (site) {
            case admin :
                //如果访问的是后台菜单，查询当前请求URL对应的菜单信息
                if (menuMaps.containsKey(currentUrl)) {
                    currentMenu = menuMaps.get(currentUrl);
                    //在session中记录当前访问页面的信息
                    request.getSession().setAttribute(WebSiteConstant.SESSION_CURRENT_MENU_KEY,currentMenu);
                }
                break;
            case portal:
                //如果访问的是前台页面，查询当前请求所属的频道
                break;
        }
        //session中的WebSiteConstant.SESSION_CURRENT_MENU_KEY取值不能为空
        if (request.getSession().getAttribute(WebSiteConstant.SESSION_CURRENT_MENU_KEY) == null) {
            request.getSession().setAttribute(WebSiteConstant.SESSION_CURRENT_MENU_KEY, defaultMenu);
        }

    }
}
