package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.FeedBackStatusEnum;
import com.caohao.common.enums.impl.FeedBackTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 问题反馈(Feedback)实体类
 *
 * @author caohao
 * @since 2021-11-14 18:36:17
 */
@Data
public class Feedback implements Serializable {
    private static final long serialVersionUID = -73116585888000840L;

    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 用户id
     */
    private String userId;

    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 回复内容
     */
    private String replyContent;
    /**
     * 回复人id
     */
    private String replyUserId;
    /**
     * 回复时间
     */
    private Long replyTime;




}

