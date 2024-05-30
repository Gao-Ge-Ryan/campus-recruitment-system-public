package com.caohao.dao;

import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.pojo.entity.Article;
import com.caohao.pojo.model.ArticleModel;
import com.caohao.pojo.param.ArticleParam;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Article)表数据库访问层
 *
 * @author caohao
 * @since 2021-12-04 14:54:13
 */
@Repository
public interface ArticleDao extends BaseMapper<Article> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticleModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param article 查询条件
     * @return 对象列表
     */
    List<ArticleModel> queryAllByLimit(ArticleParam article);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int insert(ArticleParam article);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Article> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Article> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Article> entities);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 影响行数
     */
    int update(Article article);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<ArticleModel> queryByUser(String username, ArticleTypeEnum type);
}

