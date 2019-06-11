package com.ai.mine.crystal.service.common;

import com.ai.mine.security.defaults.MineUserExtension;

public class UserExtensionDTO extends MineUserExtension {

    private String industry;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
