package com.caohao.dao;

import com.caohao.pojo.entity.Company;
import com.caohao.pojo.model.CompanyModel;
import com.caohao.pojo.param.CompanyParam;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Company)表数据库访问层
 *
 * @author caohao
 * @since 2021-11-14 16:28:21
 */
@Repository
public interface CompanyDao extends BaseMapper<Company> {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CompanyModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param company 查询条件
     * @return 对象列表
     */
    List<CompanyModel> queryAllByLimit(CompanyParam company);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int insert(CompanyParam company);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<Company> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<Company> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<Company> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<Company> entities);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 影响行数
     */
    int update(CompanyParam company);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    CompanyModel queryByUsername(String username);
}

