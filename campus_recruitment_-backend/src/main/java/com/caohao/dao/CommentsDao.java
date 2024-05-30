package com.caohao.dao;

import com.caohao.pojo.entity.Comments;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Comments)表数据库访问层
 *
 * @author caohao
 * @since 2022-04-03 19:00:03
 */
@Repository
public interface CommentsDao extends BaseMapper<Comments> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comments queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param comments 查询条件
     * @return 对象列表
     */
    List<Comments> queryAllByLimit(Comments comments);

    /**
     * 新增数据
     *
     * @param comments 实例对象
     * @return 影响行数
     */
    int insert(Comments comments);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comments> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Comments> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Comments> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Comments> entities);

    /**
     * 修改数据
     *
     * @param comments 实例对象
     * @return 影响行数
     */
    int update(Comments comments);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<Comments> selectByArticleId(String id);
}

