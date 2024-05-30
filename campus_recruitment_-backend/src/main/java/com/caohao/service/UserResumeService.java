package com.caohao.service;

import com.caohao.pojo.entity.UserResume;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * (UserResume)表服务接口
 *
 * @author caohao
 * @since 2022-04-03 19:23:29
 */
public interface UserResumeService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserResume queryById(String id);

    /**
     * 分页查询
     *
     * @param userResume 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<UserResume> queryByPage(UserResume userResume, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param userResume 实例对象
     * @return 实例对象
     */
    UserResume insert(UserResume userResume);

    /**
     * 修改数据
     *
     * @param userResume 实例对象
     * @return 实例对象
     */
    UserResume update(UserResume userResume);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    List<UserResume> getResumeByUserName(String username);
}
