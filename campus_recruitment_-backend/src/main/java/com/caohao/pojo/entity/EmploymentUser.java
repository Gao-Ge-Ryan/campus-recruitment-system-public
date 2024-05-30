package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.EmploymentReplyStatus;
import com.caohao.common.enums.impl.EmploymentUserStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * (EmploymentUser)实体类
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
@Data
public class EmploymentUser implements Serializable {
    private static final long serialVersionUID = -65741003971629401L;

    private String id;
    /**
     * 职位id
     */
    private String employmentId;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 投递者姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 介绍
     */
    private String introduce;
    /**
     * 简历
     */
    private String resume;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 逻辑删除
     */
    private Integer deleted;
    /**
     * 回复时间
     */
    private Long replyTime;
    /**
     * 回复内容
     */
    private String replyContent;
    /**
     * 回复人
     */
    private String replyUser;




}

