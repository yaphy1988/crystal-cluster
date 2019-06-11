package com.ai.mine.crystal.dto.resp;

import com.ai.mine.common.dto.BaseResponseDTO;

public class UserExtensionRespDTO extends BaseResponseDTO {

    private Long userId;

    private String industry;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
