package com.caohao.dao;

import com.caohao.pojo.entity.CommentReplyRelation;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (CommentReplyRelation)表数据库访问层
 *
 * @author caohao
 * @since 2022-04-03 19:02:15
 */
@Repository
public interface CommentReplyRelationDao extends BaseMapper<CommentReplyRelation> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CommentReplyRelation queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param commentReplyRelation 查询条件
     * @return 对象列表
     */
    List<CommentReplyRelation> queryAllByLimit(CommentReplyRelation commentReplyRelation);

    /**
     * 新增数据
     *
     * @param commentReplyRelation 实例对象
     * @return 影响行数
     */
    @Override
    int insert(CommentReplyRelation commentReplyRelation);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<CommentReplyRelation> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<CommentReplyRelation> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<CommentReplyRelation> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<CommentReplyRelation> entities);

    /**
     * 修改数据
     *
     * @param commentReplyRelation 实例对象
     * @return 影响行数
     */
    int update(CommentReplyRelation commentReplyRelation);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

}

