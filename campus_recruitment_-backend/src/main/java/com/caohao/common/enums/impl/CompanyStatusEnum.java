package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;

/*
 * 公司审核状态枚举
 * */

public enum CompanyStatusEnum implements EnumCode {

    Check_Pending(0, "待审核"),
    Approve(1, "审核通过"),
    Audit_Failed(2, "审核不通过"),
    ;

    private final long code;
    private final String msg;

    CompanyStatusEnum(long code, String msg) {
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
