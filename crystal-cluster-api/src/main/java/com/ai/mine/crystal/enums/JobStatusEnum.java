package com.ai.mine.crystal.enums;

public enum JobStatusEnum implements IEnumMessage {
    pending(1, "待处理"),
    processing(2, "处理中"),
    failed(3, "失败"),
    complete(9, "已完成");

    /**
     * 以下开始定义code/text组成的枚举对象
     */
    private final int code;

    private final String text;

    JobStatusEnum(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public int getCode() {
        return this.code;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.valueOf(this.code);
    }

    public static JobStatusEnum valueOf(int code) {
        for (JobStatusEnum status : values()) {
            if (status.code == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
