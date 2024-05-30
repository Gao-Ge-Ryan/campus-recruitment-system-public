package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.UserRoleEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author caohao
 * @since 2021-11-14 18:48:16
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -80774066608981041L;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 身份证号
     */
    private String identityNum;
    /**
     * 居住地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 修改时间
     */
    private Long updateTime;
    /**
     * id
     */
    private String id;
    /**
     * 积分500
     */
    private Integer integral;
    /**
     * 信誉1，2，3，4，5
     */
    private Integer credit;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 注销：逻辑删除
     */
    private Integer deleted;

    private String email;

    private String realName;



}

