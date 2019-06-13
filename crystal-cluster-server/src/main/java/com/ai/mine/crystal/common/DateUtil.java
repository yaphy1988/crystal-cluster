package com.ai.mine.crystal.common;

import java.sql.Timestamp;

public class DateUtil extends com.ai.paas.utils.DateUtil {
    @Override
    protected Timestamp getSysDate() {
        return new Timestamp(System.currentTimeMillis());
    }
}
