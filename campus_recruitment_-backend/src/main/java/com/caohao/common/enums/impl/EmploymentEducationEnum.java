package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;

/*
 * 职位学历要求枚举
 * */

public enum EmploymentEducationEnum implements EnumCode {


    Bachelor_Or_Above_Degree(0, "本科及以上学历"),
    College_Degree_Or_Above(1, "专科及以上学历"),
    Master_Degree_Or_Above(2, "研究生及以上学历"),
    ;

    private final long code;
    private final String msg;

    EmploymentEducationEnum(long code, String msg) {
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
