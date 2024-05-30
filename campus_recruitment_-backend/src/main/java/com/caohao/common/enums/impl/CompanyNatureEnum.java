package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;

/*
 * 公司性质枚举
 * */

public enum CompanyNatureEnum implements EnumCode {
    Private_Enterprises(0, "私企"),
    State_Owned_Enterprise(1, "国企"),
    Listed_Company (2, "上市公司"),
    ;

    private final long code;
    private final String msg;

    CompanyNatureEnum(long code, String msg) {
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
