package com.caohao.service.impl;

import com.caohao.common.utils.DateUtil;
import com.caohao.common.utils.IDGenerator;
import com.caohao.dao.CompanyDao;
import com.caohao.dao.EmploymentUserDao;
import com.caohao.pojo.entity.Employment;
import com.caohao.dao.EmploymentDao;
import com.caohao.pojo.model.CompanyModel;
import com.caohao.pojo.model.EmploymentModel;
import com.caohao.pojo.model.EmploymentUserModel;
import com.caohao.pojo.param.EmploymentParam;
import com.caohao.pojo.param.EmploymentUserParam;
import com.caohao.security.util.GetTokenInfoUtil;
import com.caohao.service.EmploymentService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import java.util.List;

/**
 * (Employment)表服务实现类
 *
 * @author caohao
 * @since 2021-11-14 17:17:17
 */
@Service("employmentService")
public class EmploymentServiceImpl implements EmploymentService {
    @Resource
    private EmploymentDao employmentDao;
    @Resource
    private CompanyDao companyDao;
    @Resource
    private EmploymentUserDao employmentUserDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public EmploymentModel queryById(String id) {
        EmploymentModel employmentModel = this.employmentDao.queryById(id);
        employmentModel.setCompanyModel(companyDao.queryById(employmentModel.getCompanyId()));
        EmploymentUserParam employmentUserParam = new EmploymentUserParam();
        employmentUserParam.setUserId(GetTokenInfoUtil.getUsername());
        employmentUserParam.setEmploymentId(id);
        List<EmploymentUserModel> employmentUserModels = employmentUserDao.queryAllByLimit(employmentUserParam);
        if (employmentUserModels.size()>0){
            employmentModel.setUserStatus("have");
        }else {
            employmentModel.setUserStatus("no-have");
        }
        return employmentModel;
    }

    /**
     * 分页查询
     *
     * @param employment 筛选条件
     * @param pageNum      当前页数
     * @param pageSize     每页显示数量
     * @return 查询结果
     */
    @Override
    public PageInfo<EmploymentModel> queryByPage(EmploymentParam employment, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<EmploymentModel> employments =this.employmentDao.queryAllByLimit(employment);
        for (EmploymentModel employmentModel : employments) {
            CompanyModel companyModel = companyDao.queryById(employmentModel.getCompanyId());
            employmentModel.setCompanyModel(companyModel);
        }
        return new PageInfo<>(employments);
    }

    /**
     * 新增数据
     *
     * @param employment 实例对象
     * @return 实例对象
     */
    @Override
    public EmploymentParam insert(EmploymentParam employment) {
        employment.setId(IDGenerator.StringID());
        employment.setCreateTime(DateUtil.getCurrentTimeMillis());
        employment.setUpdateTime(DateUtil.getCurrentTimeMillis());
        employment.setUserId(GetTokenInfoUtil.getUsername());
        employment.setStatus(0);
        this.employmentDao.insert(employment);
        return employment;
    }

    /**
     * 修改数据
     *
     * @param employment 实例对象
     * @return 实例对象
     */
    @Override
    public EmploymentModel update(EmploymentParam employment) {
        employment.setUpdateTime(DateUtil.getCurrentTimeMillis());
        this.employmentDao.update(employment);
        return this.queryById(employment.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String id) {
        employmentUserDao.deleteByEmployId(id);
        return this.employmentDao.deleteById(id) > 0;
    }

    @Override
    public PageInfo<EmploymentModel> getByUsername(String username, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EmploymentParam employmentParam = new EmploymentParam();
        employmentParam.setUserId(GetTokenInfoUtil.getUsername());
        List<EmploymentModel> employments =this.employmentDao.queryAllByLimit(employmentParam);
        for (EmploymentModel employmentModel : employments) {
            CompanyModel companyModel = companyDao.queryById(employmentModel.getCompanyId());
            employmentModel.setCompanyModel(companyModel);
        }
        return new PageInfo<>(employments);
    }


}
