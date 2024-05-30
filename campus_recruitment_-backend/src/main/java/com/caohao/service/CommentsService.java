package com.caohao.service;

import com.caohao.pojo.entity.Comments;
import com.github.pagehelper.PageInfo;

/**
 * (Comments)表服务接口
 *
 * @author caohao
 * @since 2022-04-03 19:00:06
 */
public interface CommentsService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Comments queryById(String id);

    /**
     * 分页查询
     *
     * @param comments 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<Comments> queryByPage(Comments comments, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    Comments insert(Comments comments);

    /**
     * 修改数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    Comments update(Comments comments);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
