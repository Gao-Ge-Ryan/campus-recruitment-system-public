package com.caohao.common.enums.impl;

import com.caohao.common.enums.EnumCode;
/*
* 文章类型枚举
* */

public enum ArticleTypeEnum implements EnumCode{

    Posts(1, "帖子"),
    Collection(2, "集锦"),

    ;


    private final long code;
    private final String msg;

    ArticleTypeEnum(long code, String msg) {
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
