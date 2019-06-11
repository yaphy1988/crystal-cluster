package com.ai.mine.crystal.enums;

public enum SexEnum implements IEnumMessage {
    MAN(1, "男"),
    WOMAN(2, "女");

    /**
     * 以下开始定义code/text组成的枚举对象
     */
    private final int code;

    private final String text;

    SexEnum(int code, String text) {
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

    public static SexEnum valueOf(int code) {
        for (SexEnum sex : values()) {
            if (sex.code == code) {
                return sex;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
