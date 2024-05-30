package com.caohao.pojo.entity;

import java.io.Serializable;

/**
 * (CommentReplyRelation)实体类
 *
 * @author caohao
 * @since 2022-04-03 19:02:15
 */
public class CommentReplyRelation implements Serializable {
    private static final long serialVersionUID = -53631543095324050L;

    private String id;

    private String replyInfoId;

    private String commentsId;
    /**
     * 1回复评论，2评论下回复的回复
     */
    private Integer type;

    private String parentReplyId;

    private Long createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReplyInfoId() {
        return replyInfoId;
    }

    public void setReplyInfoId(String replyInfoId) {
        this.replyInfoId = replyInfoId;
    }

    public String getCommentsId() {
        return commentsId;
    }

    public void setCommentsId(String commentsId) {
        this.commentsId = commentsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParentReplyId() {
        return parentReplyId;
    }

    public void setParentReplyId(String parentReplyId) {
        this.parentReplyId = parentReplyId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}

