package com.caohao.service.impl;

import com.caohao.common.enums.impl.ArticleTypeEnum;
import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.CommentsDao;
import com.caohao.dao.UserDao;
import com.caohao.dao.ArticleDao;
import com.caohao.pojo.entity.Comments;
import com.caohao.pojo.model.ArticleModel;
import com.caohao.pojo.param.ArticleParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.ArticleService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Article)表服务实现类
 *
 * @author caohao
 * @since 2021-12-04 14:54:15
 */
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;
    @Resource
    private UserDao userDao;
    @Resource
    private CommentsDao commentsDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ArticleModel queryById(String id) {
        ArticleModel articleModel = this.articleDao.queryById(id);
        ArticleParam articleParam = new ArticleParam();
        articleParam.setId(id);
        if(articleModel.getCollectNumber()==null){
            articleParam.setCollectNumber(1);
        }else {
            articleParam.setCollectNumber(articleModel.getCollectNumber()+1);
        }

        this.articleDao.update(articleParam);
        articleModel.setUserModel(userDao.selectByUserName(articleModel.getUserId()));
        List<Comments> commentsList=commentsDao.selectByArticleId(articleModel.getId());
        articleModel.setComments(commentsList);
        return articleModel;
    }

    /**
     * 分页查询
     *
     * @param article 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<ArticleModel> queryByPage(ArticleParam article, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleModel> articles =this.articleDao.queryAllByLimit(article);
        for (ArticleModel articleModel : articles) {
            articleModel.setUserModel(userDao.selectByUserName(articleModel.getUserId()));
            List<Comments> commentsList=commentsDao.selectByArticleId(articleModel.getId());
            articleModel.setReplySize(commentsList.size());
        }
        return new PageInfo<>(articles);
    }

    /**
     * 新增数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleParam insert(ArticleParam article) {
        article.setId(IDGenerator.StringID());
        article.setUserId(GetTokenInfoUtil.getUsername());
        article.setCreateTime(DateUtil.getCurrentTimeMillis());
        this.articleDao.insert(article);
        return article;
    }

    /**
     * 修改数据
     *
     * @param article 实例对象
     * @return 实例对象
     */
    @Override
    public ArticleModel update(ArticleParam article) {
        this.articleDao.update(article);
        return this.queryById(article.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.articleDao.deleteById(id) > 0;
    }

    @Override
    public List<ArticleModel> queryByUser(String username, ArticleTypeEnum type) {
        List<ArticleModel> articleModels = articleDao.queryByUser(username, type);
        for (ArticleModel articleModel : articleModels) {
            articleModel.setUserModel(userDao.selectByUserName(username));
        }
        return articleModels;
    }
}
