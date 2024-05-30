package com.caohao.service.impl;

import com.caohao.pojo.entity.UserResume;
import com.caohao.dao.UserResumeDao;
import com.caohao.service.UserResumeService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (UserResume)表服务实现类
 *
 * @author caohao
 * @since 2022-04-03 19:23:29
 */
@Service("userResumeService")
public class UserResumeServiceImpl implements UserResumeService {
    @Resource
    private UserResumeDao userResumeDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserResume queryById(String id) {
        return this.userResumeDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param userResume 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<UserResume> queryByPage(UserResume userResume,  Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserResume> userResumes =this.userResumeDao.queryAllByLimit(userResume);
        return new PageInfo<>(userResumes);
    }

    /**
     * 新增数据
     *
     * @param userResume 实例对象
     * @return 实例对象
     */
    @Override
    public UserResume insert(UserResume userResume) {
        this.userResumeDao.insert(userResume);
        return userResume;
    }

    /**
     * 修改数据
     *
     * @param userResume 实例对象
     * @return 实例对象
     */
    @Override
    public UserResume update(UserResume userResume) {
        this.userResumeDao.update(userResume);
        return this.queryById(userResume.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userResumeDao.deleteById(id) > 0;
    }

    @Override
    public List<UserResume> getResumeByUserName(String username) {

        return userResumeDao.getResumeByUserName(username);
    }
}
