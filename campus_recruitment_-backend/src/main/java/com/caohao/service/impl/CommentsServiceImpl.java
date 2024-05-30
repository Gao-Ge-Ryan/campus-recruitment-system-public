package com.caohao.service.impl;

import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.CommentReplyRelationDao;
import com.caohao.dao.ReplyInfoDao;
import com.caohao.dao.UserDao;
import com.caohao.pojo.entity.CommentReplyRelation;
import com.caohao.pojo.entity.Comments;
import com.caohao.dao.CommentsDao;
import com.caohao.pojo.entity.ReplyInfo;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.CommentsService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * (Comments)表服务实现类
 *
 * @author caohao
 * @since 2022-04-03 19:00:06
 */
@Service("commentsService")
public class CommentsServiceImpl implements CommentsService {
    @Resource
    private CommentsDao commentsDao;
    @Resource
    private ReplyInfoDao replyInfoDao;
    @Resource
    private CommentReplyRelationDao commentReplyRelationDao;
    @Resource
    private UserDao userDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Comments queryById(String id) {
        return this.commentsDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param comments 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<Comments> queryByPage(Comments comments,  Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Comments> commentss =this.commentsDao.queryAllByLimit(comments);
        for (Comments comment : commentss) {
            comment.setUserModel(userDao.selectByUserName(comment.getOperatorId()));
            CommentReplyRelation commentReplyRelation = new CommentReplyRelation();
            commentReplyRelation.setCommentsId(comment.getId());
//            commentReplyRelation.setType(1);
            List<CommentReplyRelation>  commentReplyRelations =  commentReplyRelationDao.queryAllByLimit(commentReplyRelation);
            Set<String> stringSet = commentReplyRelations.stream().map(CommentReplyRelation::getReplyInfoId).collect(Collectors.toSet());


            if (stringSet.size()>0){
                List<ReplyInfo> replyInfos = replyInfoDao.selectByIDS(new ArrayList<>(stringSet));
                for (ReplyInfo replyInfo : replyInfos) {
                    replyInfo.setSelfUserModel(userDao.selectByUserName(replyInfo.getOperatorId()));
                    replyInfo.setOtherUserModel(userDao.selectByUserName(replyInfo.getRecipientId()));
                }
                comment.setReplyInfos(replyInfos);
//                for (ReplyInfo replyInfo : replyInfos) {
//                    commentReplyRelation.setCommentsId(comment.getId());
//                    commentReplyRelation.setType(2);
//                    commentReplyRelation.setParentReplyId(replyInfo.getId());
//                    List<CommentReplyRelation>  commentReplyRelationss =  commentReplyRelationDao.queryAllByLimit(commentReplyRelation);
//                    Set<String> stringSets = commentReplyRelationss.stream().map(CommentReplyRelation::getReplyInfoId).collect(Collectors.toSet());
//                    if (stringSets.size()>0){
//                        List<ReplyInfo> replyInfoss = replyInfoDao.selectByIDS(new ArrayList<>(stringSets));
//                        replyInfo.setReplyInfos(replyInfoss);
//                    }
//
//                }
            }

        }
        return new PageInfo<>(commentss);
    }

    /**
     * 新增数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    @Override
    public Comments insert(Comments comments) {
        comments.setId(IDGenerator.StringID());
        comments.setOperatorId(GetTokenInfoUtil.getUsername());
        comments.setCreateTime(DateUtil.getCurrentTimeMillis());
        this.commentsDao.insert(comments);
        return comments;
    }

    /**
     * 修改数据
     *
     * @param comments 实例对象
     * @return 实例对象
     */
    @Override
    public Comments update(Comments comments) {
        this.commentsDao.update(comments);
        return this.queryById(comments.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.commentsDao.deleteById(id) > 0;
    }
}
