package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;
/*
 * 学生投递记录枚举
 * */


public enum EmploymentUserStatus implements EnumCode {

    Wait_For_Reply(0, "待回复"),
    Accept_the_offer(1, "接收offer"),
    Refuse_to_accept_the_offer(2, "拒绝offer"),
    ;

    private final long code;
    private final String msg;

    EmploymentUserStatus(long code, String msg) {
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
