package com.caohao.service;

import com.caohao.pojo.entity.Feedback;
import com.caohao.pojo.model.FeedbackModel;
import com.caohao.pojo.param.FeedbackParam;
import com.github.pagehelper.PageInfo;

/**
 * 问题反馈(Feedback)表服务接口
 *
 * @author caohao
 * @since 2021-11-14 18:36:17
 */
public interface FeedbackService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    FeedbackModel queryById(String id);

    /**
     * 分页查询
     *
     * @param feedback 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<FeedbackModel> queryByPage(FeedbackParam feedback, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    FeedbackParam insert(FeedbackParam feedback);

    /**
     * 修改数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    FeedbackModel update(FeedbackParam feedback);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
