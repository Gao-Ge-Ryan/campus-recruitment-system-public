package com.caohao.service;

import com.caohao.common.Result;
import com.caohao.pojo.entity.User;
import com.caohao.pojo.model.UserModel;
import com.caohao.pojo.param.UserParam;
import com.github.pagehelper.PageInfo;

/**
 * (User)表服务接口
 *
 * @author caohao
 * @since 2021-11-14 18:48:17
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    UserModel queryById(String id);

    /**
     * 分页查询
     *
     * @param user 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<UserModel> queryByPage(UserParam user, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    UserParam insert(UserParam user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    UserModel update(UserParam user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

    UserModel selectByUserName(String username);

    Result registeredUser(UserParam user);

    Result updatePassword(UserParam user);


    UserModel queryByUsername(String username);

    void patchInfo(UserParam user);
}
