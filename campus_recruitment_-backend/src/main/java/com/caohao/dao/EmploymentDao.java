package com.caohao.dao;

import com.caohao.pojo.entity.Employment;
import com.caohao.pojo.model.EmploymentModel;
import com.caohao.pojo.param.EmploymentParam;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Employment)表数据库访问层
 *
 * @author caohao
 * @since 2021-11-14 17:17:17
 */
@Repository
public interface EmploymentDao extends BaseMapper<Employment> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmploymentModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param employment 查询条件
     * @return 对象列表
     */
    List<EmploymentModel> queryAllByLimit(EmploymentParam employment);

    /**
     * 新增数据
     *
     * @param employment 实例对象
     * @return 影响行数
     */
    int insert(EmploymentParam employment);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Employment> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Employment> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Employment> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Employment> entities);

    /**
     * 修改数据
     *
     * @param employment 实例对象
     * @return 影响行数
     */
    int update(EmploymentParam employment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<EmploymentModel> selectByCompanyId(String id);

    int deleteByCompanyId(String companyId);
}

