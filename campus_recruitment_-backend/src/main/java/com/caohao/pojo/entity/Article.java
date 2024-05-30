package com.caohao.pojo.entity;

import com.caohao.common.enums.impl.ArticleTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author caohao
 * @since 2021-12-04 14:54:14
 */
@Data
public class Article implements Serializable {
    private static final long serialVersionUID = -74021729361892646L;

    private String id;

    private String title;

    private String content;

    private String picPath;
    /**
     * 发布者名字
     */
    private String userId;

    private Long createTime;

    private Long updateTime;

    /**
     * 收藏数
     */
    private Integer collectNumber;
    /**
     * 点赞数
     */
    private Integer thumbUp;

}

