package com.ai.mine.crystal.web.admin;

import com.ai.mine.crystal.dto.resp.DashboardDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 所有后台用户登录后的首页，仪表盘展示关键信息
 * 提供：
 *   1. 系统中机构数量的统计
 *   2. 系统中会员用户数量的统计
 *   3. 门户的访客数量和访问次数统计
 *   4. 系统通知消息的展示
 *   5. 业务资讯信息的展示
 *   6. 个人任务的展示
 */
@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/index")
    public String index(Model model) {
        logger.info("访问admin/dashboard/index页面成功！");

        DashboardDTO dashboard = new DashboardDTO();
        dashboard.setTitle("仪表盘");
        dashboard.setUserCount(123);
        dashboard.setMenuCount(45);
        dashboard.setActiveRate(2030);
        model.addAttribute("dashboard",dashboard);
        return "admin/dashboard/index";
    }
}
