package com.caohao.common.enums.impl;


import com.caohao.common.enums.EnumCode;

public enum  FeedBackTypeEnum implements EnumCode {

    Bug(1,"bug反馈"),
    Suggest (2,"建议"),
    Complaint  (3,"投诉"),
    ;

    private final long code;
    private final String msg;

    FeedBackTypeEnum(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
