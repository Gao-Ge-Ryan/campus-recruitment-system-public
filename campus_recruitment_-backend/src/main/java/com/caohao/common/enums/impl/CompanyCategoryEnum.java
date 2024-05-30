package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;

/*
 * 公司类别枚举
 * */

public enum CompanyCategoryEnum implements EnumCode {

    Internet(1, "互联网"),
    Financial(2, "金融"),
    Education(3, "教育"),
    ;

    private final long code;
    private final String msg;

    CompanyCategoryEnum(long code, String msg) {
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
