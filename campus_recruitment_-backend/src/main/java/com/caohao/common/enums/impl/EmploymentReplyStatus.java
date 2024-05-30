package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;

/*
 * 企业投递记录枚举
 * */

public enum EmploymentReplyStatus implements EnumCode {

    Wait_For_Reply(0, "待回复"),
    Refused_Entry(1, "拒绝入职"),
    Agree_With_Induction(2, "同意入职"),
    ;

    private final long code;
    private final String msg;

    EmploymentReplyStatus(long code, String msg) {
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
