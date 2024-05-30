package com.caohao.service.impl;

import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.CommentReplyRelationDao;
import com.caohao.pojo.entity.CommentReplyRelation;
import com.caohao.pojo.entity.ReplyInfo;
import com.caohao.dao.ReplyInfoDao;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.ReplyInfoService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (ReplyInfo)表服务实现类
 *
 * @author caohao
 * @since 2022-04-03 19:01:33
 */
@Service("replyInfoService")
public class ReplyInfoServiceImpl implements ReplyInfoService {
    @Resource
    private ReplyInfoDao replyInfoDao;
    @Resource
    private CommentReplyRelationDao commentReplyRelationDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ReplyInfo queryById(String id) {
        return this.replyInfoDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param replyInfo 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<ReplyInfo> queryByPage(ReplyInfo replyInfo,  Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ReplyInfo> replyInfos =this.replyInfoDao.queryAllByLimit(replyInfo);
        return new PageInfo<>(replyInfos);
    }

    /**
     * 新增数据
     *
     * @param replyInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ReplyInfo insert(ReplyInfo replyInfo) {
        CommentReplyRelation commentReplyRelation = new CommentReplyRelation();
        commentReplyRelation.setCommentsId(replyInfo.getCommentsId());
        commentReplyRelation.setId(IDGenerator.StringID());
        commentReplyRelation.setCreateTime(DateUtil.getCurrentTimeMillis());
        String s = IDGenerator.StringID();
        commentReplyRelation.setReplyInfoId(s);
//        if (replyInfo.getType().equals("one")){
//            commentReplyRelation.setType(1);
//        }else {
//            commentReplyRelation.setType(2);
//            commentReplyRelation.setParentReplyId(replyInfo.getParentReplyId());
//        }
        commentReplyRelation.setParentReplyId(replyInfo.getCommentsId());
        commentReplyRelationDao.insert(commentReplyRelation);

        replyInfo.setId(s);
        replyInfo.setCreateTime(DateUtil.getCurrentTimeMillis());
        replyInfo.setOperatorId(GetTokenInfoUtil.getUsername());
        this.replyInfoDao.insert(replyInfo);
        return replyInfo;
    }

    /**
     * 修改数据
     *
     * @param replyInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ReplyInfo update(ReplyInfo replyInfo) {
        this.replyInfoDao.update(replyInfo);
        return this.queryById(replyInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.replyInfoDao.deleteById(id) > 0;
    }
}
