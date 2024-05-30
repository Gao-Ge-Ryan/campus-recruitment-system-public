package com.caohao.pojo.entity;

import com.caohao.pojo.model.UserModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (ReplyInfo)实体类
 *
 * @author caohao
 * @since 2022-04-03 19:01:33
 */
@Data
public class ReplyInfo implements Serializable {
    private static final long serialVersionUID = 793359999221163869L;

    private String id;

    private String content;

    /*被评论人 */
    private String recipientId;

    private String operatorId;

    private Long createTime;

    private String atid;

    private String commentsId;

    private String parentReplyId;

    private String type;

    private List<ReplyInfo> replyInfos;

    private UserModel selfUserModel;
    private UserModel otherUserModel;




}

