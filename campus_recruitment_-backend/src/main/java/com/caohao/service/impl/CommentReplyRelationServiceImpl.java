package com.caohao.service.impl;

import com.caohao.pojo.entity.CommentReplyRelation;
import com.caohao.dao.CommentReplyRelationDao;
import com.caohao.service.CommentReplyRelationService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (CommentReplyRelation)表服务实现类
 *
 * @author caohao
 * @since 2022-04-03 19:02:15
 */
@Service("commentReplyRelationService")
public class CommentReplyRelationServiceImpl implements CommentReplyRelationService {
    @Resource
    private CommentReplyRelationDao commentReplyRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CommentReplyRelation queryById(String id) {
        return this.commentReplyRelationDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param commentReplyRelation 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<CommentReplyRelation> queryByPage(CommentReplyRelation commentReplyRelation,  Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentReplyRelation> commentReplyRelations =this.commentReplyRelationDao.queryAllByLimit(commentReplyRelation);
        return new PageInfo<>(commentReplyRelations);
    }

    /**
     * 新增数据
     *
     * @param commentReplyRelation 实例对象
     * @return 实例对象
     */
    @Override
    public CommentReplyRelation insert(CommentReplyRelation commentReplyRelation) {
        this.commentReplyRelationDao.insert(commentReplyRelation);
        return commentReplyRelation;
    }

    /**
     * 修改数据
     *
     * @param commentReplyRelation 实例对象
     * @return 实例对象
     */
    @Override
    public CommentReplyRelation update(CommentReplyRelation commentReplyRelation) {
        this.commentReplyRelationDao.update(commentReplyRelation);
        return this.queryById(commentReplyRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.commentReplyRelationDao.deleteById(id) > 0;
    }
}
