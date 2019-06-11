package com.ai.mine.crystal.dto.resp;

import java.io.Serializable;

public class Select2PaginateDTO implements Serializable {

    private Boolean more = false;

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }
}
