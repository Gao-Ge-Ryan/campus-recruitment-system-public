package com.caohao.service;

import com.caohao.pojo.entity.EmploymentUser;
import com.caohao.pojo.model.EmploymentUserModel;
import com.caohao.pojo.param.EmploymentUserParam;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (EmploymentUser)表服务接口
 *
 * @author caohao
 * @since 2021-11-14 17:48:02
 */
public interface EmploymentUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    EmploymentUserModel queryById(String id);

    /**
     * 分页查询
     *
     * @param employmentUser 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<EmploymentUserModel> queryByPage(EmploymentUserParam employmentUser, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param employmentUser 实例对象
     * @return 实例对象
     */
    EmploymentUserParam insert(EmploymentUserParam employmentUser);

    /**
     * 修改数据
     *
     * @param employmentUser 实例对象
     * @return 实例对象
     */
    EmploymentUserModel update(EmploymentUserParam employmentUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<EmploymentUserModel> getByUser(String username);

    PageInfo<EmploymentUserModel> queryByPageCompany(EmploymentUserParam employmentUser, Integer pageNum, Integer pageSize);
}
