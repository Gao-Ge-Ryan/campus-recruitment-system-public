package com.caohao.service.impl;

import com.caohao.common.Result;
import com.caohao.common.enums.impl.CompanyStatusEnum;
import com.caohao.common.enums.impl.UserRoleEnum;
import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.CompanyDao;
import com.caohao.dao.UserDao;
import com.caohao.pojo.model.UserModel;
import com.caohao.pojo.param.CompanyParam;
import com.caohao.pojo.param.UserParam;
import com.caohao.security.util.BCryptUtil;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author caohao
 * @since 2021-11-14 18:48:17
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CompanyDao companyDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserModel queryById(String id) {
        return this.userDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param user     筛选条件
     * @param pageNum  当前页数
     * @param pageSize 每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<UserModel> queryByPage(UserParam user, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserModel> users = this.userDao.queryAllByLimit(user);
        return new PageInfo<>(users);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserParam insert(UserParam user) {
        String stringID = IDGenerator.StringID();
        user.setId(stringID);
        user.setCreateTime(DateUtil.getCurrentTimeMillis());
        user.setUpdateTime(DateUtil.getCurrentTimeMillis());
        user.setPassword(BCryptUtil.encode(user.getPassword()));
        user.setIntegral(0);
        user.setCredit(500);
        if (user.getRole().equals(UserRoleEnum.Enterprise_User)) {
            CompanyParam company = new CompanyParam();
            company.setId(IDGenerator.StringID());
            company.setCreateTime(DateUtil.getCurrentTimeMillis());
            company.setUpdateTime(DateUtil.getCurrentTimeMillis());
            company.setUserId(user.getUserName());
            company.setStatus(CompanyStatusEnum.Check_Pending);
            companyDao.insert(company);
        }
        this.userDao.insert(user);
        return user;

    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public UserModel update(UserParam user) {
        user.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.userDao.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        return this.userDao.deleteById(id) > 0;
    }

    @Override
    public UserModel selectByUserName(String username) {
        UserParam userParam = new UserParam();
        userParam.setUserName(username);
        List<UserModel> userModels = userDao.queryAllByLimit(userParam);
        if (userModels != null && userModels.size() > 0) {
            UserModel userModel = userModels.get(0);
            return userModel;
        }
        return null;
    }

    @Override
    public Result registeredUser(UserParam user) {
        String authCode = stringRedisTemplate.opsForValue().get("AuthCode" + user.getUserName());
        if (authCode == null || !authCode.equals(user.getInputCode())) {
            return Result.failed("验证码错误");
        }
        UserModel userModel = userDao.selectByUserName(user.getUserName());
        if (userModel != null) {
            return Result.failed("该邮箱已被注册");
        }
        user.setId(IDGenerator.StringID());
        user.setCreateTime(DateUtil.getCurrentTimeMillis());
        user.setUpdateTime(DateUtil.getCurrentTimeMillis());
        user.setPassword(BCryptUtil.encode(user.getPassword()));
        user.setRole(UserRoleEnum.User);
        user.setIntegral(0);
        user.setCredit(500);
        this.userDao.insert(user);
        return Result.ok(user);
    }

    @Override
    public Result updatePassword(UserParam user) {
        String username = GetTokenInfoUtil.getUsername();
        String authCode = stringRedisTemplate.opsForValue().get("AuthCode" + username);
        if (authCode == null || !authCode.equals(user.getInputCode())) {
            return Result.failed("验证码错误");
        }

        user.setPassword(BCryptUtil.encode(user.getPassword()));
        user.setUserName(username);
        userDao.patchInfo(user);
        return Result.ok(user);
    }

    @Override
    public UserModel queryByUsername(String username) {
        return userDao.selectByUserName(username);
    }

    @Override
    public void patchInfo(UserParam user) {
        user.setUserName(GetTokenInfoUtil.getUsername());
        userDao.patchInfo(user);
    }

}
