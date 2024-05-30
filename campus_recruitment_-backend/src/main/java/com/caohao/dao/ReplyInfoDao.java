package com.caohao.dao;

import com.caohao.pojo.entity.ReplyInfo;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (ReplyInfo)表数据库访问层
 *
 * @author caohao
 * @since 2022-04-03 19:01:33
 */
@Repository
public interface ReplyInfoDao extends BaseMapper<ReplyInfo> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReplyInfo queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param replyInfo 查询条件
     * @return 对象列表
     */
    List<ReplyInfo> queryAllByLimit(ReplyInfo replyInfo);

    /**
     * 新增数据
     *
     * @param replyInfo 实例对象
     * @return 影响行数
     */
    int insert(ReplyInfo replyInfo);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReplyInfo> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<ReplyInfo> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<ReplyInfo> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<ReplyInfo> entities);

    /**
     * 修改数据
     *
     * @param replyInfo 实例对象
     * @return 影响行数
     */
    int update(ReplyInfo replyInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<ReplyInfo> selectByIDS(List<String> list);
}

