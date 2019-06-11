package com.ai.mine.crystal.dto.resp;

import java.io.Serializable;

public class DashboardDTO implements Serializable {
    private String title;
    private Integer userCount;
    private Integer menuCount;
    //活跃率，整数值表示的万分位数值，如百分数10.00%的小数值表示为0.10，则其万分位数值表示为1000，即万分之1000，等于百分之10
    private Integer activeRate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getMenuCount() {
        return menuCount;
    }

    public void setMenuCount(Integer menuCount) {
        this.menuCount = menuCount;
    }

    public Integer getActiveRate() {
        return activeRate;
    }

    public void setActiveRate(Integer activeRate) {
        this.activeRate = activeRate;
    }
}
