package com.caohao.pojo.entity;

import com.caohao.pojo.model.UserModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Comments)实体类
 *
 * @author caohao
 * @since 2022-04-03 19:00:03
 */
@Data
public class Comments implements Serializable {
    private static final long serialVersionUID = 659533907258538475L;

    private String id;

    private String articleId;

    private String content;

    private String recipientId;

    private String operatorId;

    private Long createTime;

    private List<ReplyInfo> replyInfos;

    private UserModel userModel;



}

