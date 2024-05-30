package com.caohao.service;

import com.caohao.pojo.entity.Company;
import com.caohao.pojo.model.CompanyModel;
import com.caohao.pojo.param.CompanyParam;
import com.github.pagehelper.PageInfo;

/**
 * (Company)表服务接口
 *
 * @author caohao
 * @since 2021-11-14 16:28:23
 */
public interface CompanyService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CompanyModel queryById(String id);

    /**
     * 分页查询
     *
     * @param company 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<CompanyModel> queryByPage(CompanyParam company, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    CompanyParam insert(CompanyParam company);

    /**
     * 修改数据
     *
     * @param company 实例对象
     * @return 实例对象
     */
    CompanyModel update(CompanyParam company);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    CompanyModel queryByUsername(String username);
}
