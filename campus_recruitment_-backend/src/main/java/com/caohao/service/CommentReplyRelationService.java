package com.caohao.service;

import com.caohao.pojo.entity.CommentReplyRelation;
import com.github.pagehelper.PageInfo;

/**
 * (CommentReplyRelation)表服务接口
 *
 * @author caohao
 * @since 2022-04-03 19:02:15
 */
public interface CommentReplyRelationService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CommentReplyRelation queryById(String id);

    /**
     * 分页查询
     *
     * @param commentReplyRelation 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<CommentReplyRelation> queryByPage(CommentReplyRelation commentReplyRelation, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param commentReplyRelation 实例对象
     * @return 实例对象
     */
    CommentReplyRelation insert(CommentReplyRelation commentReplyRelation);

    /**
     * 修改数据
     *
     * @param commentReplyRelation 实例对象
     * @return 实例对象
     */
    CommentReplyRelation update(CommentReplyRelation commentReplyRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
