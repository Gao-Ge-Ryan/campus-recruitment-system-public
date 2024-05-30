package com.caohao.dao;

import com.caohao.pojo.entity.EmploymentUser;
import com.caohao.pojo.model.EmploymentUserModel;
import com.caohao.pojo.param.EmploymentUserParam;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (EmploymentUser)表数据库访问层
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
@Repository
public interface EmploymentUserDao extends BaseMapper<EmploymentUser> {

    int deleteByEmployId(String employmentId);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmploymentUserModel queryById(String id);

    /**
     * 查询指定行数据
     *
     * @param employmentUser 查询条件
     * @return 对象列表
     */
    List<EmploymentUserModel> queryAllByLimit(EmploymentUserParam employmentUser);

    /**
     * 新增数据
     *
     * @param employmentUser 实例对象
     * @return 影响行数
     */
    int insert(EmploymentUserParam employmentUser);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmploymentUser> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<EmploymentUser> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<EmploymentUser> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<EmploymentUser> entities);

    /**
     * 修改数据
     *
     * @param employmentUser 实例对象
     * @return 影响行数
     */
    int update(EmploymentUserParam employmentUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(String id);

    List<EmploymentUserModel> getByUser(String username);
}

