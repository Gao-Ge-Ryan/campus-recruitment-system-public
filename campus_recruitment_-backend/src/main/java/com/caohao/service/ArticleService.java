package com.caohao.service;

import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.pojo.model.ArticleModel;
import com.caohao.pojo.param.ArticleParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Article)表服务接口
 *
 * @author caohao
 * @since 2021-12-04 14:54:15
 */
public interface ArticleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ArticleModel queryById(String id);

    /**
     * 分页查询
     *
     * @param article 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<ArticleModel> queryByPage(ArticleParam article, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    ArticleParam insert(ArticleParam article);

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    ArticleModel update(ArticleParam article);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<ArticleModel> queryByUser(String username, ArticleTypeEnum type);
}
