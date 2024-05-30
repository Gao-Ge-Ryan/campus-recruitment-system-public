package com.caohao.common.enums.impl;


import com.caohao.common.enums.EnumCode;

/*
 * 用户角色枚举
 * */

public enum UserRoleEnum implements EnumCode {

    User(1,"普通用户"),
    Admin (2,"管理员"),
    Enterprise_User (3,"企业用户"),
    ;

    private final long code;
    private final String msg;

    UserRoleEnum(long code, String msg) {
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
