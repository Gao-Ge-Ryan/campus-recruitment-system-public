package com.caohao.pojo.param;

import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.pojo.entity.Article;
import lombok.Data;

import java.io.Serializable;

/**
 * (Article)实体类
 *
 * @author caohao
 * @since 2021-12-04 14:54:14
 */
@Data
public class ArticleParam extends Article {

    /**
     * 1帖子，2集锦
     */
    private ArticleTypeEnum type;

}

