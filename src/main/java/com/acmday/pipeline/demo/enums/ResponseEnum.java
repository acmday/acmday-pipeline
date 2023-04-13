package com.acmday.pipeline.demo.enums;

/**
 * @Author acmday
 * @Date 2023/4/13 2:23 下午
 */
public enum ResponseEnum implements IBaseEnum {

    //成功
    SUCCESS(0, "SUCCESS"),
    PARAM_ERROR(1, "PARAM_ERROR"),
    PIPELINE_ERROR(2, "PIPELINE_ERROR"),
    SYS_ERROR(3, "SYSTEM_ERROR"),
    ;

    private int code;

    private String value;

    ResponseEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}