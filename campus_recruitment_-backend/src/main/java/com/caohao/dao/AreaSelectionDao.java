package com.caohao.dao;

import com.caohao.pojo.entity.AreaSelection;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (AreaSelection)表数据库访问层
 *
 * @author caohao
 * @since 2022-04-04 17:01:41
 */
@Repository
public interface AreaSelectionDao extends BaseMapper<AreaSelection> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    AreaSelection queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param areaSelection 查询条件
     * @return 对象列表
     */
    List<AreaSelection> queryAllByLimit(AreaSelection areaSelection);

    /**
     * 新增数据
     *
     * @param areaSelection 实例对象
     * @return 影响行数
     */
    int insert(AreaSelection areaSelection);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<AreaSelection> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<AreaSelection> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<AreaSelection> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<AreaSelection> entities);

    /**
     * 修改数据
     *
     * @param areaSelection 实例对象
     * @return 影响行数
     */
    int update(AreaSelection areaSelection);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<AreaSelection> selectByParentCode(String parentCode);
}

