package com.github.wwying.enums;

public enum SysParamConstants {
    ENABLED_YES("T","上架"),
    ENABKED_NO("F","下架");

    private String key;
    private String value;

    SysParamConstants(String key, String value){
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
