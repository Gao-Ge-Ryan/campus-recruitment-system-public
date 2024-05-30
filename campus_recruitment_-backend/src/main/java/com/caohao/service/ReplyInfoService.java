package com.caohao.service;

import com.caohao.pojo.entity.ReplyInfo;
import com.github.pagehelper.PageInfo;

/**
 * (ReplyInfo)表服务接口
 *
 * @author caohao
 * @since 2022-04-03 19:01:33
 */
public interface ReplyInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ReplyInfo queryById(String id);

    /**
     * 分页查询
     *
     * @param replyInfo 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    PageInfo<ReplyInfo> queryByPage(ReplyInfo replyInfo, Integer pageNum, Integer pageSize);

    /**
     * 新增数据
     *
     * @param replyInfo 实例对象
     * @return 实例对象
     */
    ReplyInfo insert(ReplyInfo replyInfo);

    /**
     * 修改数据
     *
     * @param replyInfo 实例对象
     * @return 实例对象
     */
    ReplyInfo update(ReplyInfo replyInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(String id);

}
