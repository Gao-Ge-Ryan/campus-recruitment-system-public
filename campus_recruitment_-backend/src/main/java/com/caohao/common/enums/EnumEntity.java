package com.caohao.common.enums;

import lombok.Data;

/**
 * 枚举转换成的实体类
 *
 * @author caohao
 * @since 2021-11-07 13:42:39
 */
@Data
public class EnumEntity implements EnumCode{
    private EnumCode enumCode;
    private long code;
    private String msg;

}
