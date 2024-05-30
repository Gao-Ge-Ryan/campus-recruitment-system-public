package com.caohao.service;

import com.caohao.pojo.entity.Employment;
import com.caohao.pojo.model.EmploymentModel;
import com.caohao.pojo.param.EmploymentParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (Employment)表服务接口
 *
 * @author caohao
 * @since 2021-11-14 17:17:17
 */
public interface EmploymentService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmploymentModel queryById(String id);

    /**
     * 分页查询
     *
     * @param employment 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<EmploymentModel> queryByPage(EmploymentParam employment, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param employment 实例对象
     * @return 实例对象
     */
    EmploymentParam insert(EmploymentParam employment);

    /**
     * 修改数据
     *
     * @param employment 实例对象
     * @return 实例对象
     */
    EmploymentModel update(EmploymentParam employment);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);


    PageInfo<EmploymentModel> getByUsername(String username, Integer pageNum, Integer pageSize);
}
