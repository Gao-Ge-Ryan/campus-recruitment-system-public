package com.caohao.pojo.model;

import com.caohao.common.enums.EnumEntity;
import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.pojo.entity.Article;
import com.caohao.pojo.entity.Comments;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * (Article)实体类
 *
 * @author caohao
 * @since 2021-12-04 14:54:14
 */
@Data
public class ArticleModel extends Article {

    /**
     * 1帖子，2集锦
     */
    private EnumEntity type;
    private UserModel userModel;
    private Integer replySize;
    private List<Comments> comments;


}

