package com.caohao.service.impl;

import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.pojo.entity.Feedback;
import com.caohao.dao.FeedbackDao;
import com.caohao.pojo.model.FeedbackModel;
import com.caohao.pojo.param.FeedbackParam;
import com.caohao.service.FeedbackService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * 问题反馈(Feedback)表服务实现类
 *
 * @author caohao
 * @since 2021-11-14 18:36:17
 */
@Service("feedbackService")
public class FeedbackServiceImpl implements FeedbackService {
    @Resource
    private FeedbackDao feedbackDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public FeedbackModel queryById(String id) {
        return this.feedbackDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param feedback 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<FeedbackModel> queryByPage(FeedbackParam feedback, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FeedbackModel> feedbacks =this.feedbackDao.queryAllByLimit(feedback);
        return new PageInfo<>(feedbacks);
    }

    /**
     * 新增数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    @Override
    public FeedbackParam insert(FeedbackParam feedback) {
        feedback.setId(IDGenerator.StringID());
        feedback.setCreateTime(DateUtil.getCurrentTimeMillis());
        this.feedbackDao.insert(feedback);
        return feedback;
    }

    /**
     * 修改数据
     *
     * @param feedback 实例对象
     * @return 实例对象
     */
    @Override
    public FeedbackModel update(FeedbackParam feedback) {
        feedback.setReplyTime(DateUtil.getCurrentTimeMillis());
        this.feedbackDao.update(feedback);
        return this.queryById(feedback.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.feedbackDao.deleteById(id) > 0;
    }
}
